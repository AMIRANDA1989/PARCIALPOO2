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

    public MilitaryBuilding(String name, int hitpoints, int capacity, int buildTime) {
        this.name = name;
        this.hitpoints = hitpoints;
        this.capacity = capacity;
        this.buildTime = buildTime;
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
    
    
}
