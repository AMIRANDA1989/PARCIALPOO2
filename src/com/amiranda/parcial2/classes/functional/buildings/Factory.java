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
public class Factory extends Building{
    private String name;

    public Factory(String name, int hitpoints, int buildTime, int capacity, int productionPerTurn) {
        super(hitpoints, buildTime, capacity, productionPerTurn);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   
    
    
}
