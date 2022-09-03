package com.com;

import java.util.List;

public class RechargeBean {

    /**
     * list : [{"id":1,"CarId":1,"Money":56,"Balance":98,"RechargeTime":"2019-08-24 08:45:56"},{"id":2,"CarId":1,"Money":90,"Balance":158,"RechargeTime":"2019-08-25 09:45:36"},{"id":3,"CarId":1,"Money":50,"Balance":125,"RechargeTime":"2019-08-27 10:45:36"}]
     * result : true
     * msg : 成功
     */

    private boolean result;
    private String msg;
    private List<ListBean> list;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * CarId : 1
         * Money : 56
         * Balance : 98
         * RechargeTime : 2019-08-24 08:45:56
         */

        private int id;
        private int CarId;
        private int Money;
        private int Balance;
        private String RechargeTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCarId() {
            return CarId;
        }

        public void setCarId(int CarId) {
            this.CarId = CarId;
        }

        public int getMoney() {
            return Money;
        }

        public void setMoney(int Money) {
            this.Money = Money;
        }

        public int getBalance() {
            return Balance;
        }

        public void setBalance(int Balance) {
            this.Balance = Balance;
        }

        public String getRechargeTime() {
            return RechargeTime;
        }

        public void setRechargeTime(String RechargeTime) {
            this.RechargeTime = RechargeTime;
        }
    }
}
