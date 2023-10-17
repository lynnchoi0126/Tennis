package com.example.tennis.racquet.controller;

import java.time.LocalDate;

public class RentForm {
    private LocalDate date;
    private String racquetNo;
    private String name;
    private boolean rentOrReturn;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRacquetNo() {
        return racquetNo;
    }

    public void setRacquetNo(String racquetNo) {
        this.racquetNo = racquetNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getRentOrReturn() {
        return rentOrReturn;
    }

    public void setRentOrReturn(boolean rentOrReturn) {
        this.rentOrReturn = rentOrReturn;
    }
}
