/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

/**
 *
 * @author allan
 * La interfaz UserInteractions es la que maneja todas las funciones
 * en donde el usuario tenga que interactuar con el sistema.
 */
public interface UserInteractions {
    public boolean confirmAction();
        
    public int getPlayerRaza(int numPlayer);
    
    public String setPlayerName();
}
