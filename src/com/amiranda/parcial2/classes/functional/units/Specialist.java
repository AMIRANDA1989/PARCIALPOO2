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
public class Specialist extends Unit{
    private String Name;

    public Specialist(String Name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost) {
        super(hitpoints, attackPoints, buildTime, successRate, rawMaterialsCost, energyCost, moneyCost);
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    
}
