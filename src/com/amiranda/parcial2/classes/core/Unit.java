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
public class Unit {
    private int hitpoints;
    private int attackPoints;
    private int buildTime;
    private int successRate;
    private int rawMaterialsCost;
    private int energyCost;
    private int moneyCost;

    public Unit(int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost) {
        this.hitpoints = hitpoints;
        this.attackPoints = attackPoints;
        this.buildTime = buildTime;
        this.successRate = successRate;
        this.rawMaterialsCost = rawMaterialsCost;
        this.energyCost = energyCost;
        this.moneyCost = moneyCost;
    }

    public int getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(int successRate) {
        this.successRate = successRate;
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

    public void setBuildTime(int buildTime) {
        this.buildTime = buildTime;
    }

    public int getRawMaterialsCost() {
        return rawMaterialsCost;
    }

    public void setRawMaterialsCost(int rawMaterialsCost) {
        this.rawMaterialsCost = rawMaterialsCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }

    public int getMoneyCost() {
        return moneyCost;
    }

    public void setMoneyCost(int moneyCost) {
        this.moneyCost = moneyCost;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
    
    
}
