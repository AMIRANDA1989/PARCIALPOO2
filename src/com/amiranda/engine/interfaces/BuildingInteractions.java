/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.core.Player;
import com.amiranda.parcial2.classes.functional.buildings.ComandCenter;
import com.amiranda.parcial2.classes.functional.buildings.Factory;
import com.amiranda.parcial2.classes.functional.buildings.Market;
import com.amiranda.parcial2.classes.functional.buildings.MilitaryBuilding;
import com.amiranda.parcial2.classes.functional.buildings.PowerMine;
import java.util.ArrayList;

/**
 *
 * @author allan
 */
public interface BuildingInteractions {
    //valida que hayan suficientes fondos para crear un edificio
    public String buildApproval(int money, int energy, int moneyPrice, int energyPrice);
    //Genera los recursos de los edificios correspondientes
    public int generateResources(int capacity, int generationPerTurn, int currentCollected);
       
    
    /*Metodos para las fabricas*/
    public void factoryActiveStatus(ArrayList<Factory> playerBuilding);
    public void factoryPendingStatus(ArrayList<Factory> colaProd);
    public Player factoryOperations(Player activePlayer);
    public ArrayList<Factory> factoryQueueMaintenance(ArrayList<Factory> colaProd);
    public ArrayList<Factory> factoryQueueProduction(ArrayList<Factory> colaProd, ArrayList<Factory> playerBuildings, Factory playerBaseBuilding);
    public ArrayList<Factory> factoryCleanQueue(ArrayList<Factory> colaProd);
    public ArrayList<Factory> factoryMaintenance(ArrayList<Factory> playerBuilding);
    
    /*Metodos para los mercados*/
    public void marketActiveStatus(ArrayList<Market> playerBuilding);
    public void marketPendingStatus(ArrayList<Market> colaProd);
    public Player marketOperations(Player activePlayer);
    public ArrayList<Market> marketQueueMaintenance(ArrayList<Market> colaProd);
    public ArrayList<Market> marketQueueProduction(ArrayList<Market> colaProd, ArrayList<Market> playerBuildings, Market playerBaseBuilding);
    public ArrayList<Market> marketCleanQueue(ArrayList<Market> colaProd);
    public ArrayList<Market> marketMaintenance(ArrayList<Market> playerBuilding);
    
    /*Metodos para las minas*/
    public void powerMineActiveStatus(ArrayList<PowerMine> playerBuilding);
    public void powerMinePendingStatus(ArrayList<PowerMine> colaProd);
    public Player powerMineOperations(Player activePlayer);
    public ArrayList<PowerMine> powerMineQueueMaintenance(ArrayList<PowerMine> colaProd);
    public ArrayList<PowerMine> powerMineQueueProduction(ArrayList<PowerMine> colaProd, ArrayList<PowerMine> playerBuildings, PowerMine playerBaseBuilding);
    public ArrayList<PowerMine> powerMineCleanQueue(ArrayList<PowerMine> colaProd);
    public ArrayList<PowerMine> powerMineMaintenance(ArrayList<PowerMine> playerBuilding);
    
    /*Metodos para las bases militares*/
    
    public void militaryBaseActiveStatus(ArrayList<MilitaryBuilding> playerBuilding);
    public void militaryBaseStatus(ArrayList<MilitaryBuilding> colaProd);
    public Player militaryBaseOperations(Player activePlayer);
    public ArrayList<MilitaryBuilding> militaryBaseQueueMaintenance(ArrayList<MilitaryBuilding> colaProd);
    public ArrayList<MilitaryBuilding> militaryBaseQueueProduction(ArrayList<MilitaryBuilding> colaProd, ArrayList<MilitaryBuilding> playerBuildings, MilitaryBuilding playerBaseBuilding);
    public ArrayList<MilitaryBuilding> militaryBaseCleanQueue(ArrayList<MilitaryBuilding> colaProd);
    public ArrayList<MilitaryBuilding> militaryBaseMaintenance(ArrayList<MilitaryBuilding> playerBuilding);
    public int militaryBaseCurrentCapacity(Player player);
    
    //metodos para llenar de recursos los edificios
    public ArrayList<Factory> factoryFillResources(ArrayList<Factory> buildings);
    public ArrayList<Market> marketFillResources(ArrayList<Market> buildings);
    public ArrayList<PowerMine> powerMineFillResources(ArrayList<PowerMine> buildings);
    
    //metodos para el centro de control
    public Player ccOperations(Player activePlayer);
    public ComandCenter upgradeCC(ComandCenter cc);
}
