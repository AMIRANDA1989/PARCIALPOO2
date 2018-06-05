/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.parcial2.classes.core;

/**
 *
 * @author allan
 */
public class Building {
    private int hitpoints;
    final int buildTime;
    private int capacity;
    private int productionPerTurn;
    private int contents;
    private int moneyPrice;
    private int energyPrice;

    public Building(int hitpoints, int buildTime, int capacity, int productionPerTurn, int contents, int moneyPrice, int energyPrice) {
        this.hitpoints = hitpoints;
        this.buildTime = buildTime;
        this.capacity = capacity;
        this.productionPerTurn = productionPerTurn;
        this.contents = contents;
        this.moneyPrice = moneyPrice;
        this.energyPrice = energyPrice;
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

    

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getBuildTime() {
        return buildTime;
    }
       
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getProductionPerTurn() {
        return productionPerTurn;
    }

    public void setProductionPerTurn(int productionPerTurn) {
        this.productionPerTurn = productionPerTurn;
    }

    public int getContents() {
        return contents;
    }

    public void setContents(int contents) {
        this.contents = contents;
    }
    
    
}
