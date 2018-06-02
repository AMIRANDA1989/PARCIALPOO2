/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.parcial2.classes.core;

/**
 *
 * @author allan
 * La clase abstracta Raza es la clase basica de las razas subsecuentes del juego
 */
public abstract class Raza {
    private final int code; //codigo identificador de la raza
    private final String name; //nombre identificador de la raza

    public Raza(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    
    
    //Obtiene una descripcion de la raza
    public abstract void getDescription();
    
    //modificadores de hitpoints de edificaciones
    public abstract int buildingHitpointModifier(int hitpoints);
    
    //modificadores de tiempo de edificacion
    public abstract int buildingTimeModifier(int time);
    
    //modificadores de hitpoints de vehiculos
    public abstract int vehicleHitpointModifier(int hitpoints);
    
    //modificadores de tiempo de construccion de vehiculos
    public abstract int vehicleTimeModifier(int time);
    
    //modificadores de hitpoints de unidades
    public abstract int soldierHitpointModifier(int hitpoints);
    
    //modificador tiempo de entrenamiento de unidades
    public abstract int soldierTimeModifier(int time);
    
    //modificador de puntos de ataque de las unidades
    public abstract int soldierDamageModifier(int damage);
   
    //modificador de precio de recursos
    public abstract int resourcePriceModifier(int resource);
}
