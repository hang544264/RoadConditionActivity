package com.com;

import java.util.List;

public class EtcBean {

    /**
     * list : [{"CarId":5,"EtcInTime":"2019-08-24 08:45:42","EtcOutTime":"2019-08-24 08:45:56","Money":5},{"CarId":5,"EtcInTime":"2019-08-25 08:45:42","EtcOutTime":"2019-08-25 08:45:56","Money":5}]
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
         * CarId : 5
         * EtcInTime : 2019-08-24 08:45:42
         * EtcOutTime : 2019-08-24 08:45:56
         * Money : 5
         */

        private int CarId;
        private String EtcInTime;
        private String EtcOutTime;
        private int Money;

        public int getCarId() {
            return CarId;
        }

        public void setCarId(int CarId) {
            this.CarId = CarId;
        }

        public String getEtcInTime() {
            return EtcInTime;
        }

        public void setEtcInTime(String EtcInTime) {
            this.EtcInTime = EtcInTime;
        }

        public String getEtcOutTime() {
            return EtcOutTime;
        }

        public void setEtcOutTime(String EtcOutTime) {
            this.EtcOutTime = EtcOutTime;
        }

        public int getMoney() {
            return Money;
        }

        public void setMoney(int Money) {
            this.Money = Money;
        }
    }
}
