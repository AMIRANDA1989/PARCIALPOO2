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
public class PowerMine extends Building{
    String nombre;

    public PowerMine(String nombre, int hitpoints, int buildTime, int capacity, int productionPerTurn) {
        super(hitpoints, buildTime, capacity, productionPerTurn);
        this.nombre = nombre;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
