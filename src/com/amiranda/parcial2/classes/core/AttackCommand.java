/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.parcial2.classes.core;

import com.amiranda.parcial2.classes.functional.units.HeavyVehicle;
import com.amiranda.parcial2.classes.functional.units.LightVehicle;
import com.amiranda.parcial2.classes.functional.units.Specialist;
import com.amiranda.parcial2.classes.functional.units.Squad;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author allan
 * AttackCommand es una orden de ataque que ejecuta el jugador
 * consta de mappings que indican el tipo de edificio que se va a atacar y las unidades que lo atacaran
 */
public class AttackCommand {
    private int target;
    private int attackTime;
    private ArrayList<Squad> deployedSquads = new ArrayList();
    private ArrayList<Specialist> deployedSpecialist = new ArrayList();
    private ArrayList<LightVehicle> deployedLAV = new ArrayList();
    private ArrayList<HeavyVehicle> deployedHeavy = new ArrayList();

    public AttackCommand() {
        this.attackTime = 2;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public ArrayList<Squad> getDeployedSquads() {
        return deployedSquads;
    }

    public void setDeployedSquads(ArrayList<Squad> deployedSquads) {
        this.deployedSquads = deployedSquads;
    }

    public ArrayList<Specialist> getDeployedSpecialist() {
        return deployedSpecialist;
    }

    public void setDeployedSpecialist(ArrayList<Specialist> deployedSpecialist) {
        this.deployedSpecialist = deployedSpecialist;
    }

    public ArrayList<LightVehicle> getDeployedLAV() {
        return deployedLAV;
    }

    public void setDeployedLAV(ArrayList<LightVehicle> deployedLAV) {
        this.deployedLAV = deployedLAV;
    }

    public ArrayList<HeavyVehicle> getDeployedHeavy() {
        return deployedHeavy;
    }

    public void setDeployedHeavy(ArrayList<HeavyVehicle> deployedHeavy) {
        this.deployedHeavy = deployedHeavy;
    }

    public int getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(int attackTime) {
        this.attackTime = attackTime;
    }

    
    
    
}
