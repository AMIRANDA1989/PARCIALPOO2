/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.functional.buildings.Factory;
import java.util.ArrayList;

/**
 *
 * @author allan
 */
public interface BuildingInteractions {
    //valida que hayan suficientes fondos para crear un edificio
    public String buildApproval(int money, int energy, int moneyPrice, int energyPrice);
    
    /*Metodos para las fabricas*/
    public void factoryActiveStatus(ArrayList<Factory> playerFactories);
    public void factoryPendingStatus(ArrayList<Factory> colaProd);
    public ArrayList<Factory> factoryQueueMaintenance(ArrayList<Factory> colaProd);
    public ArrayList<Factory> factoryQueueProduction(ArrayList<Factory> colaProd, ArrayList<Factory> playerFactories, Factory playerBaseFactory);
    public ArrayList<Factory> factoryCleanQueue(ArrayList<Factory> colaProd);
    public ArrayList<Factory> factoryMaintenance(ArrayList<Factory> playerFactories);
    
}
