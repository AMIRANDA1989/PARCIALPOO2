/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.parcial2.classes.functional.units;

import com.amiranda.parcial2.classes.core.Unit;

/**
 *
 * @author allan
 */
public class Squad extends Unit{
    private String name;

    public Squad(String name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost) {
        super(hitpoints, attackPoints, buildTime, successRate, rawMaterialsCost, energyCost, moneyCost);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
