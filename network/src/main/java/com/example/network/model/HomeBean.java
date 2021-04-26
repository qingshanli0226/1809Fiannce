package com.example.network.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class HomeBean implements Serializable {

    /**
     * code : 200
     * msg : 请求成功
     * result : {"imageArr":[{"ID":"1","IMAPAURL":"http://fund.eastmoney.com/f10/jjjl_002939.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index01.png"},{"ID":"2","IMAPAURL":"http://fund.eastmoney.com/519983.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index02.png"},{"ID":"3","IMAPAURL":"http://www.gffunds.com.cn/funds/?fundcode=004997","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index03.png"},{"ID":"5","IMAPAURL":"http://fund.eastmoney.com/002939.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index04.png"}],"proInfo":{"id":"1","memberNum":"100","minTouMoney":"100","money":"10","name":"硅谷彩虹新手计划","progress":"90","suodingDays":"30","yearRate":"8.00"}}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    @Override
    public String toString() {
        return "HomeBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

    public static class ResultBean implements Serializable {
        /**
         * imageArr : [{"ID":"1","IMAPAURL":"http://fund.eastmoney.com/f10/jjjl_002939.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index01.png"},{"ID":"2","IMAPAURL":"http://fund.eastmoney.com/519983.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index02.png"},{"ID":"3","IMAPAURL":"http://www.gffunds.com.cn/funds/?fundcode=004997","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index03.png"},{"ID":"5","IMAPAURL":"http://fund.eastmoney.com/002939.html","IMAURL":"http://49.233.0.68:8080/atguigu/img/P2PInvest/index04.png"}]
         * proInfo : {"id":"1","memberNum":"100","minTouMoney":"100","money":"10","name":"硅谷彩虹新手计划","progress":"90","suodingDays":"30","yearRate":"8.00"}
         */

        private ProInfoBean proInfo;
        private List<ImageArrBean> imageArr;

        public ProInfoBean getProInfo() {
            return proInfo;
        }

        public void setProInfo(ProInfoBean proInfo) {
            this.proInfo = proInfo;
        }

        public List<ImageArrBean> getImageArr() {
            return imageArr;
        }

        public void setImageArr(List<ImageArrBean> imageArr) {
            this.imageArr = imageArr;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "proInfo=" + proInfo +
                    ", imageArr=" + imageArr +
                    '}';
        }

        public static class ProInfoBean implements Serializable {
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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMemberNum() {
                return memberNum;
            }

            public void setMemberNum(String memberNum) {
                this.memberNum = memberNum;
            }

            public String getMinTouMoney() {
                return minTouMoney;
            }

            public void setMinTouMoney(String minTouMoney) {
                this.minTouMoney = minTouMoney;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProgress() {
                return progress;
            }

            public void setProgress(String progress) {
                this.progress = progress;
            }

            public String getSuodingDays() {
                return suodingDays;
            }

            public void setSuodingDays(String suodingDays) {
                this.suodingDays = suodingDays;
            }

            public String getYearRate() {
                return yearRate;
            }

            public void setYearRate(String yearRate) {
                this.yearRate = yearRate;
            }

            @Override
            public String toString() {
                return "ProInfoBean{" +
                        "id='" + id + '\'' +
                        ", memberNum='" + memberNum + '\'' +
                        ", minTouMoney='" + minTouMoney + '\'' +
                        ", money='" + money + '\'' +
                        ", name='" + name + '\'' +
                        ", progress='" + progress + '\'' +
                        ", suodingDays='" + suodingDays + '\'' +
                        ", yearRate='" + yearRate + '\'' +
                        '}';
            }
        }


        public static class ImageArrBean implements Serializable {
            /**
             * ID : 1
             * IMAPAURL : http://fund.eastmoney.com/f10/jjjl_002939.html
             * IMAURL : http://49.233.0.68:8080/atguigu/img/P2PInvest/index01.png
             */

            @SerializedName("ID")
            private String id;
            @SerializedName("IMAPAURL")
            private String imapaurl;
            @SerializedName("IMAURL")
            private String imaurl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImapaurl() {
                return imapaurl;
            }

            public void setImapaurl(String imapaurl) {
                this.imapaurl = imapaurl;
            }

            public String getImaurl() {
                return imaurl;
            }

            public void setImaurl(String imaurl) {
                this.imaurl = imaurl;
            }
        }
    }
}
