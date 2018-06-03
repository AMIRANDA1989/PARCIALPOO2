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
public class Jupiter extends Raza{

    public Jupiter(int code, String name) {
        super(code, name);
    }
    
    @Override
    public void getDescription() {
        System.out.println("------------------------------------");
        System.out.println("ORGANIZACION JUPITER");
        System.out.println("A pesar que el Nuevo Orden Mundial tiene una influencia inigualable en el mundo, siempre existe alguien que se opone a los poderosos.");
        System.out.println("Jupiter, nombrados así por el dios romano que combatio contra los titanes y salio victorioso al final, es una organizacion clandestina");
        System.out.println("que considera que los metodos y los fines del nuevo Orden Mundial corrompen a la Humanidad y la estan convertiendo en el monstruo que");
        System.out.println("dicen que estan combatiendo. A pesar de que ellos mismos consideren su causa como noble y unica, siempre estan dispuestos a jugar sucio");
        System.out.println("para cumplir sus metas. Son considerados como una organizacion terrorista por muchos");
        System.out.println("-------------------------------------");
        System.out.println("CUALIDADES ESPECIALES");
        System.out.println("BUNKERS SECRETOS: Jupiter sabe bien como esconder sus bases, incluso en medio de la ciudad, haciendo fortalezas impenetrables");
        System.out.println("bajo las narices del enemigo");
        System.out.println("- Los edificios de Jupiter son altamente resistentes teniendo 100 puntos de vida adicionales.");
        System.out.println("- Crear edificios capaces de resistir duros castigos hace que sean mas lentos en construir, agregando 1 turno adicional a su construccion");
    }

    @Override
    public int buildingHitpointModifier(int hitpoints) {
        return hitpoints + 100;
    }

    @Override
    public int buildingTimeModifier(int time) {
        return time +1;
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
        return hitpoints;
    }

    @Override
    public int soldierTimeModifier(int time) {
        return time;
    }

    @Override
    public int soldierDamageModifier(int damage) {
        return damage;
    }

    @Override
    public int resourcePriceModifier(int resource) {
        return resource;
    }

    @Override
    public int successRateModifier(int success) {
        return success;
    }
    
}
