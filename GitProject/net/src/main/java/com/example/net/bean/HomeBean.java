package com.example.net.bean;

import java.util.List;


public class HomeBean {
    /**
     * code : 200
     * msg : 请求成功
     * result : {"imageArr":[{"ID":"1","IMAPAURL":"http://fund.eastmoney.com/f10/jjjl_002939.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index01.png"},{"ID":"2","IMAPAURL":"http://fund.eastmoney.com/519983.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index02.png"},{"ID":"3","IMAPAURL":"http://www.gffunds.com.cn/funds/?fundcode=004997","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index03.png"},{"ID":"5","IMAPAURL":"http://fund.eastmoney.com/002939.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index04.png"}],"proInfo":{"id":"1","memberNum":"100","minTouMoney":"100","money":"10","name":"硅谷彩虹新手计划","progress":"90","suodingDays":"30","yearRate":"8.00"}}
     */

    private Integer code;
    private String msg;
    private ResultBean result;

    @Override
    public String toString() {
        return "HomeBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * imageArr : [{"ID":"1","IMAPAURL":"http://fund.eastmoney.com/f10/jjjl_002939.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index01.png"},{"ID":"2","IMAPAURL":"http://fund.eastmoney.com/519983.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index02.png"},{"ID":"3","IMAPAURL":"http://www.gffunds.com.cn/funds/?fundcode=004997","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index03.png"},{"ID":"5","IMAPAURL":"http://fund.eastmoney.com/002939.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index04.png"}]
         * proInfo : {"id":"1","memberNum":"100","minTouMoney":"100","money":"10","name":"硅谷彩虹新手计划","progress":"90","suodingDays":"30","yearRate":"8.00"}
         */

        private List<ImageArrBean> imageArr;
        private ProInfoBean proInfo;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "imageArr=" + imageArr +
                    ", proInfo=" + proInfo +
                    '}';
        }

        public List<ImageArrBean> getImageArr() {
            return imageArr;
        }

        public void setImageArr(List<ImageArrBean> imageArr) {
            this.imageArr = imageArr;
        }

        public ProInfoBean getProInfo() {
            return proInfo;
        }

        public void setProInfo(ProInfoBean proInfo) {
            this.proInfo = proInfo;
        }

        public static class ProInfoBean {
            /**
             * id : 1
             * memberNum : 100
             * minTouMoney : 100
             * money : 10
             * name : 硅谷彩虹新手计划
             * progress : 90
             * suodingDays : 30
             * yearRate : 8.00
             */

            private String id;
            private String memberNum;
            private String minTouMoney;
            private String money;
            private String name;
            private String progress;
            private String suodingDays;
            private String yearRate;
        }


        public static class ImageArrBean {
            /**
             * ID : 1
             * IMAPAURL : http://fund.eastmoney.com/f10/jjjl_002939.html
             * IMAURL : http://49.233.0.68:8080/atguigu/img/P2PInvest/index01.png
             */

            private String ID;
            private String IMAPAURL;
            private String IMAURL;

            @Override
            public String toString() {
                return "ImageArrBean{" +
                        "ID='" + ID + '\'' +
                        ", IMAPAURL='" + IMAPAURL + '\'' +
                        ", IMAURL='" + IMAURL + '\'' +
                        '}';
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getIMAPAURL() {
                return IMAPAURL;
            }

            public void setIMAPAURL(String IMAPAURL) {
                this.IMAPAURL = IMAPAURL;
            }

            public String getIMAURL() {
                return IMAURL;
            }

            public void setIMAURL(String IMAURL) {
                this.IMAURL = IMAURL;
            }
        }
    }
}
