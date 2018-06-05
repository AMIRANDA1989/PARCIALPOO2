/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.core.Player;
import com.amiranda.parcial2.classes.functional.buildings.Factory;
import com.amiranda.parcial2.classes.functional.buildings.Market;
import java.util.ArrayList;

/**
 *
 * @author allan
 */
public interface BuildingInteractions {
    //valida que hayan suficientes fondos para crear un edificio
    public String buildApproval(int money, int energy, int moneyPrice, int energyPrice);
    
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
    
}
