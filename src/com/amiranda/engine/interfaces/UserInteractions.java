/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.core.Player;
import com.amiranda.parcial2.classes.functional.buildings.Factory;
import com.amiranda.parcial2.classes.functional.buildings.Market;
import com.amiranda.parcial2.classes.functional.buildings.MilitaryBuilding;
import com.amiranda.parcial2.classes.functional.buildings.PowerMine;
import java.util.ArrayList;

/**
 *
 * @author allan
 * La interfaz UserInteractions es la que maneja todas las funciones
 * en donde el usuario tenga que interactuar con el sistema.
 */
public interface UserInteractions {

    public final int INFO_MESSAGE = 1;
    public final int WARNING_MESSAGE = 2;
    public final int ERROR_MESSAGE = 3;
    public final int ALERT_MESSAGE = 4;
    
    public boolean confirmAction();
        
    public int getPlayerRaza(int numPlayer);
    
    public String setPlayerName();
    
    public void showMessage(int messageType, String message);
    
    public int mainMenu(Player activePlayer);
    
    public int factoryMenu();
    
    public int marketMenu();
    
    public int powerMineMenu();
    
    public int militaryBaseMenu();
    
    public int unitConstructionMenu();
}
