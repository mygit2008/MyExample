package example.com.myexample.bean;

import java.util.List;

/**
 * @author zhangjunyou
 * @date 2018/7/11
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class FoodsBean {

    private String message;
    private int status;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private int __v;
        private String created_at;
        private int id;
        private String name;
        private int restaurant_id;
        private List<SpusBean> spus;
        private boolean checked;//表示是否选中

        public boolean getChecked() {
            return checked;
        }
        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRestaurant_id() {
            return restaurant_id;
        }

        public void setRestaurant_id(int restaurant_id) {
            this.restaurant_id = restaurant_id;
        }

        public List<SpusBean> getSpus() {
            return spus;
        }

        public void setSpus(List<SpusBean> spus) {
            this.spus = spus;
        }

        public static class SpusBean {
            /**
             * __v : 0
             * _id : 5ac5b0913714cc2d644f461e
             * attrs : []
             * category_id : 143
             * created_at : 2018-04-05T05:08:44.549Z
             * id : 1089
             * month_saled : 1
             * month_saled_content : 1
             * name : 套餐8：海苔鸡肉卷+辣翅+可乐
             * pic_url : http://p1.meituan.net/wmproduct/735b78e97285214f1e8c62346546e92052099.jpg
             * praise_content : 好吃
             * praise_num : 49
             * restaurant_id : 32
             * skus : [{"_id":"5ac5b0913714cc2d644f461f","description":"","id":1090,"price":"20.8"}]
             * status_remind_list : []
             */

            private int __v;
            private String _id;
            private int category_id;
            private String created_at;
            private int id;
            private int month_saled;
            private String month_saled_content;
            private String name;
            private String pic_url;
            private String praise_content;
            private int praise_num;
            private int restaurant_id;
            private List<?> attrs;
            private List<SkusBean> skus;
            private List<?> status_remind_list;

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMonth_saled() {
                return month_saled;
            }

            public void setMonth_saled(int month_saled) {
                this.month_saled = month_saled;
            }

            public String getMonth_saled_content() {
                return month_saled_content;
            }

            public void setMonth_saled_content(String month_saled_content) {
                this.month_saled_content = month_saled_content;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic_url() {
                return pic_url;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }

            public String getPraise_content() {
                return praise_content;
            }

            public void setPraise_content(String praise_content) {
                this.praise_content = praise_content;
            }

            public int getPraise_num() {
                return praise_num;
            }

            public void setPraise_num(int praise_num) {
                this.praise_num = praise_num;
            }

            public int getRestaurant_id() {
                return restaurant_id;
            }

            public void setRestaurant_id(int restaurant_id) {
                this.restaurant_id = restaurant_id;
            }

            public List<?> getAttrs() {
                return attrs;
            }

            public void setAttrs(List<?> attrs) {
                this.attrs = attrs;
            }

            public List<SkusBean> getSkus() {
                return skus;
            }

            public void setSkus(List<SkusBean> skus) {
                this.skus = skus;
            }

            public List<?> getStatus_remind_list() {
                return status_remind_list;
            }

            public void setStatus_remind_list(List<?> status_remind_list) {
                this.status_remind_list = status_remind_list;
            }

            public static class SkusBean {
                /**
                 * _id : 5ac5b0913714cc2d644f461f
                 * description :
                 * id : 1090
                 * price : 20.8
                 */

                private String _id;
                private String description;
                private int id;
                private String price;

                public String get_id() {
                    return _id;
                }

                public void set_id(String _id) {
                    this._id = _id;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }
            }
        }
    }
}
