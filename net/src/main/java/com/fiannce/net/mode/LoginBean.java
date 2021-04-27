package com.fiannce.net.mode;

public class LoginBean {
    /**
     * code : 200
     * message : 登录成功
     * result : {"id":"111","name":"111","password":"111","email":null,"phone":"123456","point":null,"address":"??","money":null,"avatar":"/img/1438946011155.jpg","token":"d8e63851-b79c-47e6-90fd-207cc8454679AND1619568366564","gPassword":null}
     */

    private String code;
    private String message;
    private ResultBean result;

    @Override
    public String toString() {
        return "LoginBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", email=" + email +
                    ", phone='" + phone + '\'' +
                    ", point=" + point +
                    ", address='" + address + '\'' +
                    ", money=" + money +
                    ", avatar='" + avatar + '\'' +
                    ", token='" + token + '\'' +
                    ", gPassword=" + gPassword +
                    '}';
        }

        /**
         * id : 111
         * name : 111
         * password : 111
         * email : null
         * phone : 123456
         * point : null
         * address : ??
         * money : null
         * avatar : /img/1438946011155.jpg
         * token : d8e63851-b79c-47e6-90fd-207cc8454679AND1619568366564
         * gPassword : null
         */

        private String id;
        private String name;
        private String password;
        private Object email;
        private String phone;
        private Object point;
        private String address;
        private Object money;
        private String avatar;
        private String token;
        private Object gPassword;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getPoint() {
            return point;
        }

        public void setPoint(Object point) {
            this.point = point;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getMoney() {
            return money;
        }

        public void setMoney(Object money) {
            this.money = money;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getGPassword() {
            return gPassword;
        }

        public void setGPassword(Object gPassword) {
            this.gPassword = gPassword;
        }
    }
}
