/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.parcial2.classes.functional.races;

import com.amiranda.parcial2.classes.core.Raza;

/**
 *
 * @author allan
 */
public class Invaders extends Raza{

    public Invaders(int code, String name) {
        super(code, name);
    }
        
    @Override
    public void getDescription() {
        System.out.println("------------------------------------");
        System.out.println("LOS INVASORES");
        System.out.println("Extraña raza de criaturas que surgio del fondo de los oceanos y de la tierra, despertada por la sed infinita");
        System.out.println("de recursos de la humanidad. Estos seres son agresivos y despiadados, aunque analizando bien la manera en la que actuan,");
        System.out.println("se puede notar un comportamiento bastante coordinado, indicando que hay un nivel avanzado de inteligencia entre ellos");
        System.out.println("y algo, o alguien, que esta detras del telon dandoles ordenes");
        System.out.println("-------------------------------------");
        System.out.println("CUALIDADES ESPECIALES");
        System.out.println("DEVORADORES: Los invasores son criaturas masivas, sedientas de sangre y dispuestas a conquistar todo terreno donde coloquen sus pies");
        System.out.println("La humanidad ha encontrado a su depredador natural");
        System.out.println("- Los escuadrones y especialistas de los invasores tienen 30 puntos mas de ataque y 50 mas de vida");
        System.out.println("- Los escuadrones y especialistas invasores tardan 1 turno mas en fabricar y cuestan 100 de energia y materia prima mas");
    }

    @Override
    public int buildingHitpointModifier(int hitpoints) {
        return hitpoints;
    }

    @Override
    public int buildingTimeModifier(int time) {
        return time;
    }

    @Override
    public int vehicleHitpointModifier(int hitpoints) {
        return hitpoints;
    }

    @Override
    public int vehicleTimeModifier(int time) {
        return time;
    }

    @Override
    public int soldierHitpointModifier(int hitpoints) {
        return hitpoints + 50;
    }

    @Override
    public int soldierTimeModifier(int time) {
        return time +1;
    }

    @Override
    public int resourcePriceModifier(int resource) {
        return resource + 100;
    }

    @Override
    public int soldierDamageModifier(int damage) {
        return damage + 30;
    }

    @Override
    public int successRateModifier(int success) {
        return success;
    }
    
}
