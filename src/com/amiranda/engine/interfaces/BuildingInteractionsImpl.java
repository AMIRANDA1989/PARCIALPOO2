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
public class BuildingInteractionsImpl implements BuildingInteractions{
    UserInteractionsImpl userInteractions = new UserInteractionsImpl();
    
    @Override
    public ArrayList<Factory> factoryQueueMaintenance(ArrayList<Factory> colaProd) {
        ArrayList<Factory> result = new ArrayList();
        for(Factory f : colaProd){
            f.setBuildTime(f.getBuildTime() - 1);
            result.add(f);
        }
        return result;
    }
    
    @Override
    public ArrayList<Factory> factoryQueueProduction(ArrayList<Factory> colaProd, ArrayList<Factory> playerFactories, Factory playerBaseFactory) {
        ArrayList<Factory> result = playerFactories;
        for(Factory f : colaProd){
            if(f.getBuildTime() <= 0){
                result.add(playerBaseFactory);
                userInteractions.showMessage(userInteractions.INFO_MESSAGE, "Se ha finalizado la construccion de " + playerBaseFactory.getName());
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<Factory> factoryCleanQueue(ArrayList<Factory> colaProd) {
        int i = 0;
        ArrayList<Factory> result = colaProd;
        for(Factory f : colaProd){
            if(f.getBuildTime() <= 0){
                result.remove(i);
                i++;
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<Factory> factoryMaintenance(ArrayList<Factory> playerFactories) {
        ArrayList<Factory> result = new ArrayList();
        for(Factory f : playerFactories){
            //Reduciendo en 1 el tiempo de turno de construccion
            if(f.getHitpoints()<= 0){
                userInteractions.showMessage(userInteractions.ALERT_MESSAGE, "Tu " + f.getName() + " ha sido destruida");
            }
        }
        
        return result;
    }

    
}
