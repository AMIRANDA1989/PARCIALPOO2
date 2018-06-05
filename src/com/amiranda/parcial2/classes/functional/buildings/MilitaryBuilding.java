/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.parcial2.classes.functional.buildings;

/**
 *
 * @author allan
 */
public class MilitaryBuilding {
    private String name;
    private int hitpoints;
    private int capacity;
    private int buildTime;
    private int moneyPrice;
    private int energyPrice;
    private int buildProgress;

    public MilitaryBuilding(String name, int hitpoints, int capacity, int buildTime, int moneyPrice, int energyPrice) {
        this.name = name;
        this.hitpoints = hitpoints;
        this.capacity = capacity;
        this.buildTime = buildTime;
        this.moneyPrice = moneyPrice;
        this.energyPrice = energyPrice;
        this.buildProgress = buildTime;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(int buildTime) {
        this.buildTime = buildTime;
    }

    public int getMoneyPrice() {
        return moneyPrice;
    }

    public void setMoneyPrice(int moneyPrice) {
        this.moneyPrice = moneyPrice;
    }

    public int getEnergyPrice() {
        return energyPrice;
    }

    public void setEnergyPrice(int energyPrice) {
        this.energyPrice = energyPrice;
    }

    public int getBuildProgress() {
        return buildProgress;
    }

    public void setBuildProgress(int buildProgress) {
        this.buildProgress = buildProgress;
    }
    
    
}
