/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.functional.units.Specialist;
import com.amiranda.parcial2.classes.functional.units.Squad;

/**
 *
 * @author allan
 */
public class UnitInteractionsImpl implements UnitInteractions{

    @Override
    public Squad setupPlayerSquad(String name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost) {
        Squad escuadron = new Squad(name, hitpoints, attackPoints, buildTime, successRate, rawMaterialsCost, energyCost, moneyCost);
        return escuadron;
    }

    @Override
    public Specialist setupPlayerSpecialist(String Name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost) {
        Specialist especialista = new Specialist(Name, hitpoints, attackPoints, buildTime, successRate, rawMaterialsCost, energyCost, moneyCost);
        return especialista;
    }
    
    
}
