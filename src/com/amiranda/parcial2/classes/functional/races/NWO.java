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
public class NWO extends Raza{

    public NWO(int code, String name) {
        super(code, name);
    }
    
    @Override
    public void getDescription() {
        System.out.println("------------------------------------");
        System.out.println("NUEVO ORDEN MUNDIAL");
        System.out.println("Un comglomerado de misteriosos, pero poderosas, personas que finalmente han decidido salir de la oscuridad");
        System.out.println("para asumir el control de todos los paises del mundo que sucumben ante su poderosa influencia economica.");
        System.out.println("Las jefes de esta organizacion confian en que el fin justifica los medios y utilizan su fuerza economica y politica");
        System.out.println("para mejorar a la humanidad a travez de experimentos que olvidan cualquier tipo de etica, todo para construir un mundo mejor");
        System.out.println("...");
        System.out.println("Para los que puedan costearlo");
        System.out.println("-------------------------------------");
        System.out.println("CUALIDADES ESPECIALES");
        System.out.println("TANQUES DE CLONACION: El nuevo orden mundial no se preocupa por hacer que sus socios y consumidores tengan que luchar");
        System.out.println("es por eso que utilizan soldados diseñados para luchar hasta morir");
        System.out.println("- Los escuadrones y especialistas del Nuevo Orden Mundial tardan un turno menos en fabricar");
        System.out.println("- La produccion en masa los vuelve mas debiles y tienen 20 puntos de vida menos");
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
        return hitpoints - 20;
    }

    @Override
    public int soldierTimeModifier(int time) {
        return time - 1;
    }

    @Override
    public int resourcePriceModifier(int resource) {
        return resource;
    }

    @Override
    public int soldierDamageModifier(int damage) {
        return damage;
    }

    @Override
    public int successRateModifier(int success) {
        return success;
    }
}
