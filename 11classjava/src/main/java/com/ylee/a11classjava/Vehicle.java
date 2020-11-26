package com.ylee.a11classjava;

import androidx.annotation.NonNull;

public  class Vehicle  {
    private String name;
    private int speed;
    private final int maxspeed;
    private final int maxFuelTank;
    private int numberOFWheels;
    private boolean hasABS;

    /****
     *
     * @param name : 자동차이름
     * @param speed ; 현재속도
     * @param maxspeed : 최대속도
     * @param maxFuelTank :..
     * @param numberOFWheels :..
     * @param hasABS : ABS 장착여부
     * 개발자: 이용희
     *               2020 11 26
     */

    public Vehicle(String name, int speed,
                   int maxspeed, int maxFuelTank,
                   int numberOFWheels, boolean hasABS) {
        this.name = name;
        this.speed = speed;
        this.maxspeed = maxspeed;
        this.maxFuelTank = maxFuelTank;
        this.numberOFWheels = numberOFWheels;
        this.hasABS = hasABS;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s %s \n %s %s \n %s %s \n",
                "이름 :", getName(), "속도 :", getSpeed(),
                "ABS장착여부", isHasABS());


    }

//    abstract String sound();
//    {
//        // return "soundFromVehicle";
//    }

     String sound()
    {
        return "soundFromVehicle";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if(speed < 0) {
            return;
        }
        this.speed = speed;
    }

    public int getMaxspeed() {
        return maxspeed;
    }

    public int getMaxFuelTank() {
        return maxFuelTank;
    }

    public int getNumberOFWheels() {
        return numberOFWheels;
    }

    public void setNumberOFWheels(int numberOFWheels) {
        this.numberOFWheels = numberOFWheels;
    }

    public boolean isHasABS() {
        return hasABS;
    }

    public void setHasABS(boolean hasABS) {
        this.hasABS = hasABS;
    }
}
