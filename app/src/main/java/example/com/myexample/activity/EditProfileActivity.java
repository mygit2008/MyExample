package example.com.myexample.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import example.com.myexample.MyApp;
import example.com.myexample.R;
import example.com.myexample.bean.UpdateNickNameBean;
import example.com.myexample.bean.UploadBean;
import example.com.myexample.presenter.UpdateNickNamePresenter;
import example.com.myexample.presenter.UploadPresenter;
import example.com.myexample.view.IUpdateNickNameView;
import example.com.myexample.view.IUploadView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener, IUploadView, IUpdateNickNameView {

    private ImageView back;
    private EditText nickname;
    private SimpleDraweeView userIcn;
    /**
     * 手机
     */
    private EditText userTel;
    /**
     * 保存
     */
    private Button save;
    //本地相册图片选择
    private String[] mCustomItem = {"本地相册", "相机拍照"};
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private File Defaltefile;
    private Uri tempUri;
    private File file;
    private SharedPreferences sp;
    private UploadPresenter presenter;
    private UpdateNickNamePresenter nickNamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        initView();
        presenter = new UploadPresenter(this);
        nickNamePresenter = new UpdateNickNamePresenter(this);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        nickname = (EditText) findViewById(R.id.nickname);
        userIcn = (SimpleDraweeView) findViewById(R.id.user_icn);
        userIcn.setOnClickListener(this);
        userTel = (EditText) findViewById(R.id.user_tel);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.user_icn:
                showSingleChoiceDialog();
                break;
            case R.id.save:
                nickNamePresenter.updateNickName(sp.getString("uid", "0"), nickname.getText().toString());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取相机返回值
        if (resultCode == -1) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    userIcn.setImageURI(tempUri);
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    userIcn.setImageURI(tempUri);
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        Bitmap parcelable = extras.getParcelable("data");
                        saveFile(parcelable, "head.jpg");
                        Log.e("图片路径", file.getName().trim());
                        RequestBody filebody = RequestBody.create(MediaType.parse("application/octet-stream"), file != null ? file : Defaltefile);
                        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), filebody);
                        presenter.uploadFile(sp.getString("uid", "0"), part);
                    }
                    break;
            }
        }
    }

    public void showSingleChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle("选择：");
        builder.setItems(mCustomItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Defaltefile = new File(Environment.getExternalStorageDirectory() + "/revoeye/", "image.jpg");
                        tempUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    // 开始对图片进行裁剪处理
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //判断sd卡是否存在
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.toString();
    }

    //将图片保存到本地，参数一个是bitmap,一个是文件名字
    public void saveFile(Bitmap bm, String fileName) {
        try {
            String path = getSDPath() + "/revoeye/";
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            file = new File(path + fileName);
            BufferedOutputStream bos;
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    public void successIcon(UploadBean uploadBean) {
        Toast.makeText(MyApp.context, "更换头像成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successName(UpdateNickNameBean updateNickNameBean) {
        Toast.makeText(MyApp.context, "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
