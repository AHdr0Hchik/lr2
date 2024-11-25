package com.ahdrohchik.pmvu_lr2_v7;

import java.io.Serializable;

public class Laptop implements Serializable {
    private String manufacturer;
    private double screenSize;
    private int batteryLife;
    private String comment;
    private boolean isSelected;

    // Конструктор с параметрами
    public Laptop(String manufacturer, double screenSize, int batteryLife) {
        this.manufacturer = manufacturer;
        this.screenSize = screenSize;
        this.batteryLife = batteryLife;
        this.comment = "";
        this.isSelected = false;
    }

    // Конструктор для десериализации
    public Laptop(String serializedData) {
        String[] parts = serializedData.split(";");
        this.manufacturer = parts[0];
        this.screenSize = Double.parseDouble(parts[1]);
        this.batteryLife = Integer.parseInt(parts[2]);
        this.comment = parts[3];
        this.isSelected = Boolean.parseBoolean(parts[4]);
    }

    // Геттеры и сеттеры
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(int batteryLife) {
        this.batteryLife = batteryLife;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    // Метод для сериализации
    @Override
    public String toString() {
        return manufacturer + ";" + screenSize + ";" + batteryLife + ";" + comment + ";" + isSelected;
    }
}
