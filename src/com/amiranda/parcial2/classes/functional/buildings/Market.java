/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.parcial2.classes.functional.buildings;

import com.amiranda.parcial2.classes.core.Building;

/**
 *
 * @author allan
 */
public class Market extends Building{
    private String name;
    private int buildProgress;

    public Market(String name, int hitpoints, int buildTime, int capacity, int productionPerTurn, int contents, int moneyPrice, int energyPrice) {
        super(hitpoints, buildTime, capacity, productionPerTurn, contents, moneyPrice, energyPrice);
        this.name = name;
        this.buildProgress = buildTime;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuildProgress() {
        return buildProgress;
    }

    public void setBuildProgress(int buildProgress) {
        this.buildProgress = buildProgress;
    }

    
    
}
