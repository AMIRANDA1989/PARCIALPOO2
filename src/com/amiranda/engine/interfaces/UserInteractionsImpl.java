/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import java.util.Scanner;

/**
 *
 * @author allan
 */
public class UserInteractionsImpl implements UserInteractions{
    Scanner in = new Scanner(System.in);
    
    @Override
    public int getPlayerRaza(int numPlayer) {
        boolean validator = true;
        String option;
        int result = 0;
        
        while (validator){
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("JUGADOR " + numPlayer);
            System.out.println("Elige a tu raza (ingresa el numero)");
            System.out.println("1 - Nuevo Orden Mundial");
            System.out.println("2 - Grupo Jupiter");
            System.out.println("3 - Los Invasores");
                      
            System.out.print("Raza a elegir: "); 
            option = in.nextLine(); 
            
            switch(option){
                case "1" : 
                    result = 1;
                    validator = false;
                    break;
                
                case "2":
                    result = 2;
                    validator = false;
                    break;
                    
                case "3":
                    result = 3;
                    validator = false;
                    break;
                    
                default:
                    System.out.println("Por favor, ingrese un número valido");
                    break;
            }
        }
        
        return result;
    }

    @Override
    public boolean confirmAction() {
        String option;
        System.out.println("");
        System.out.println("Desea confirmar esta accion?");
        System.out.println("Presione Y para confirmar, cualquier otra tecla para cancelar");
        System.out.print("Confirmar: "); 

        option = in.nextLine().toUpperCase();

        return option.equals("Y");
    }

    @Override
    public String setPlayerName() {
        String name = "Defaulto";
        boolean confirm = false;
                
        while(!(confirm)){
            System.out.print("Igrese su nombre: ");
            name = in.nextLine();
            confirm = confirmAction();
        }
        return name;
    }
    
}
