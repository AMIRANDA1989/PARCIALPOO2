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
public class ComandCenter {
    private int hitpoints;
    private int rawMaterialsCapacity;
    private int moneyCapacity;
    private int energyCapacity;
    private int unitCapacity;
    private int rawMaterialQty;
    private int moneyQty;
    private int energyQty;

    public ComandCenter(int hitpoints, int rawMaterialsCapacity, int moneyCapacity, int energyCapacity, int rawMaterialQty, int moneyQty, int energyQty) {
        this.hitpoints = hitpoints;
        this.rawMaterialsCapacity = rawMaterialsCapacity;
        this.moneyCapacity = moneyCapacity;
        this.energyCapacity = energyCapacity;
        this.rawMaterialQty = rawMaterialQty;
        this.moneyQty = moneyQty;
        this.energyQty = energyQty;
        this.unitCapacity = 0;
    }
    

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getRawMaterialsCapacity() {
        return rawMaterialsCapacity;
    }

    public void setRawMaterialsCapacity(int rawMaterialsCapacity) {
        this.rawMaterialsCapacity = rawMaterialsCapacity;
    }

    public int getMoneyCapacity() {
        return moneyCapacity;
    }

    public void setMoneyCapacity(int moneyCapacity) {
        this.moneyCapacity = moneyCapacity;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    public void setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
    }

    public int getRawMaterialQty() {
        return rawMaterialQty;
    }

    public void setRawMaterialQty(int rawMaterialQty) {
        this.rawMaterialQty = rawMaterialQty;
    }

    public int getMoneyQty() {
        return moneyQty;
    }

    public void setMoneyQty(int moneyQty) {
        this.moneyQty = moneyQty;
    }

    public int getEnergyQty() {
        return energyQty;
    }

    public void setEnergyQty(int energyQty) {
        this.energyQty = energyQty;
    }

    public int getUnitCapacity() {
        return unitCapacity;
    }

    public void setUnitCapacity(int unitCapacity) {
        this.unitCapacity = unitCapacity;
    }
    
    
}
