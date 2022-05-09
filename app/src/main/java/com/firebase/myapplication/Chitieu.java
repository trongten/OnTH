package com.firebase.myapplication;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Chitieu {
    private String tenChiTieu;
    private double sotien;

    public Chitieu() {
    }

    public Chitieu(String tenChiTieu, double sotien) {
        this.tenChiTieu = tenChiTieu;
        this.sotien = sotien;
    }

    public String getTenChiTieu() {
        return tenChiTieu;
    }

    public void setTenChiTieu(String tenChiTieu) {
        this.tenChiTieu = tenChiTieu;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        this.sotien = sotien;
    }
}
