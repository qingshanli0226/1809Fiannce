package com.example.net.bean;

public class LoginBean {
    /**
     * code : 200
     * message : 登录成功
     * result : {"id":"123","name":"123","password":"123","email":null,"phone":null,"point":null,"address":null,"money":null,"avatar":"/img/1438946011155.jpg","token":"14fd8b9a-433a-4b3d-82f0-b1d2d3eeffd1AND1619716044918","gPassword":null}
     */

    private String code;
    private String message;
    /**
     * id : 123
     * name : 123
     * password : 123
     * email : null
     * phone : null
     * point : null
     * address : null
     * money : null
     * avatar : /img/1438946011155.jpg
     * token : 14fd8b9a-433a-4b3d-82f0-b1d2d3eeffd1AND1619716044918
     * gPassword : null
     */

    private ResultBean result;

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
        private String id;
        private String name;
        private String password;
        private Object email;
        private Object phone;
        private Object point;
        private Object address;
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

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getPoint() {
            return point;
        }

        public void setPoint(Object point) {
            this.point = point;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
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
