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
    private int buildTime;

    public Building(int hitpoints, int buildTime) {
        this.hitpoints = hitpoints;
        this.buildTime = buildTime;
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
    
    
}
