/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.parcial2.classes.functional;

import com.amiranda.parcial2.classes.core.Unit;

/**
 *
 * @author allan
 */
public class squad extends Unit{
    private String name;

    public squad(String name, int hitpoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost) {
        super(hitpoints, buildTime, successRate, rawMaterialsCost, energyCost, moneyCost);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
