/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.core.Player;
import com.amiranda.parcial2.classes.functional.units.HeavyVehicle;
import com.amiranda.parcial2.classes.functional.units.LightVehicle;
import com.amiranda.parcial2.classes.functional.units.Specialist;
import com.amiranda.parcial2.classes.functional.units.Squad;
import java.util.ArrayList;

/**
 *
 * @author allan
 */
public interface UnitInteractions {
    //valida que hayan suficientes fondos para crear una unidad
    public String buildApproval(int money, int energy, int moneyPrice, int energyPrice);
    
    public Squad setupPlayerSquad(String Name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost);
    public Specialist setupPlayerSpecialist(String Name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost);
    public LightVehicle setupPlayerLAV(String Name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost);
    public HeavyVehicle setupPlayerHeavy(String Name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost);
    public Squad createNewSquad(Squad baseUnit);
    public Specialist createNewSpecialist(Specialist baseUnit);
    public LightVehicle createNewLAV(LightVehicle baseUnit);
    public HeavyVehicle createNewHeavy(HeavyVehicle baseUnit);
    
    public Player unitOperations(Player activePlayer);
    
    /*Metodos para los escuadrones*/
    public ArrayList<Squad> squadQueueMaintenance(ArrayList<Squad> colaProd);
    public ArrayList<Squad> squadQueueProduction(ArrayList<Squad> colaProd, ArrayList<Squad> playerUnits, Squad playerBaseUnit);
    public ArrayList<Squad> squadCleanQueue(ArrayList<Squad> colaProd);
    public ArrayList<Squad> squadMaintenance(ArrayList<Squad> playerUnit);
    
    /*Metodos para especialistas*/
    public ArrayList<Specialist> specialistQueueMaintenance(ArrayList<Specialist> colaProd);
    public ArrayList<Specialist> specialistQueueProduction(ArrayList<Specialist> colaProd, ArrayList<Specialist> playerUnits, Specialist playerBaseUnit);
    public ArrayList<Specialist> specialistCleanQueue(ArrayList<Specialist> colaProd);
    public ArrayList<Specialist> specialistMaintenance(ArrayList<Specialist> playerUnit);
    
    /*Metodos para vehiculos livianos*/
    public ArrayList<LightVehicle> lavQueueMaintenance(ArrayList<LightVehicle> colaProd);
    public ArrayList<LightVehicle> lavQueueProduction(ArrayList<LightVehicle> colaProd, ArrayList<LightVehicle> playerUnits, LightVehicle playerBaseUnit);
    public ArrayList<LightVehicle> lavCleanQueue(ArrayList<LightVehicle> colaProd);
    public ArrayList<LightVehicle> lavMaintenance(ArrayList<LightVehicle> playerUnit);
    
    /*Metodos para las vehiculos pesados*/
    public ArrayList<HeavyVehicle> heavyQueueMaintenance(ArrayList<HeavyVehicle> colaProd);
    public ArrayList<HeavyVehicle> heavyQueueProduction(ArrayList<HeavyVehicle> colaProd, ArrayList<HeavyVehicle> playerUnits, HeavyVehicle playerBaseUnit);
    public ArrayList<HeavyVehicle> heavyCleanQueue(ArrayList<HeavyVehicle> colaProd);
    public ArrayList<HeavyVehicle> heavyMaintenance(ArrayList<HeavyVehicle> playerUnit);
    
}
