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
    /*Metodos para las fabricas*/
    public ArrayList<Factory> factoryQueueMaintenance(ArrayList<Factory> colaProd);
    public ArrayList<Factory> factoryQueueProduction(ArrayList<Factory> colaProd, ArrayList<Factory> playerFactories, Factory playerBaseFactory);
    public ArrayList<Factory> factoryCleanQueue(ArrayList<Factory> colaProd);
    public ArrayList<Factory> factoryMaintenance(ArrayList<Factory> playerFactories);
    
}
