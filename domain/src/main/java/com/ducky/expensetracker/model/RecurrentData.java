package com.ducky.expensetracker.model;

public class RecurrentData {

        private Integer dayOfMonth;

        public RecurrentData() {
        }

        public RecurrentData(Integer dayOfMonth) {
            this.dayOfMonth = dayOfMonth;
        }

        public Integer getDayOfMonth() {
            return dayOfMonth;
        }

        public void setDayOfMonth(Integer dayOfMonth) {
            this.dayOfMonth = dayOfMonth;
        }
}
