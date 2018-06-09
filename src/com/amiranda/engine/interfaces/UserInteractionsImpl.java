/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.core.Player;
import java.util.Scanner;

/**
 *
 * @author allan
 */
public class UserInteractionsImpl implements UserInteractions {

    Scanner in = new Scanner(System.in);

    @Override
    public int getPlayerRaza(int numPlayer) {
        boolean validator = true;
        String option;
        int result = 0;

        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("JUGADOR " + numPlayer);
            System.out.println("Elige a tu raza (ingresa el numero)");
            System.out.println("1 - Nuevo Orden Mundial");
            System.out.println("2 - Grupo Jupiter");
            System.out.println("3 - Los Invasores");

            System.out.print("Raza a elegir: ");
            option = in.nextLine();

            switch (option) {
                case "1":
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

        while (!(confirm)) {
            System.out.print("Igrese su nombre: ");
            name = in.nextLine();
            confirm = confirmAction();
        }
        return name;
    }

    @Override
    public void showMessage(int messageType, String message) {
        switch (messageType) {
            case (1)://INFO_MESSAGE
                System.out.println("");
                System.out.println("[INFO] - " + message);
                break;

            case (2): //WARNING_MESSAGE
                System.out.println("");
                System.out.println("*ADVERTENCIA* - " + message);
                break;

            case (3): //ERROR_MESSAGE
                System.out.println("***");
                System.out.println("<ERROR> - " + message);
                System.out.println("***");
                break;

            case (4)://ALERT_MESSAGE
                System.out.println("");
                System.out.println("!!ALERTA!! - " + message);
        }
    }

    @Override
    public int mainMenu(Player activePlayer) {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("JUGADOR " + activePlayer.getName().toUpperCase());
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar mis recursos.");
            System.out.println("1 - Administrar Fabricas.");
            System.out.println("2 - Administrar Mercados.");
            System.out.println("3 - Administrar Minas de Energia.");
            System.out.println("4 - Administrar Bases Militares.");
            System.out.println("5 - Entrenar unidades.");
            System.out.println("6 - Atacar a mi oponente.");
            System.out.println("7 - Defender mi base.");
            System.out.println("8 - Finalizar mi turno.");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;

                case "3":
                    return 3;

                case "4":
                    return 4;

                case "5":
                    return 5;

                case "6":
                    return 6;

                case "7":
                    return 7;

                case "8":
                    return 7;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 7;
    }

    @Override
    public int factoryMenu() {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("ADMINISTRACION DE FABRICAS");
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar estado de mis fabricas activas.");
            System.out.println("1 - consultar estado de fabricas a construir.");
            System.out.println("2 - Recolectar recursos.");
            System.out.println("3 - Construir una nueva fabrica");
            System.out.println("4 - regresar.");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;

                case "3":
                    return 3;

                case "4":
                    return 4;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 4;
    }

    @Override
    public int marketMenu() {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("ADMINISTRACION DE MERCADOS");
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar estado de mis mercados activos.");
            System.out.println("1 - consultar estado de mercados a construir.");
            System.out.println("2 - Recolectar recursos.");
            System.out.println("3 - Construir un nuevo mercado.");
            System.out.println("4 - regresar.");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;

                case "3":
                    return 3;

                case "4":
                    return 4;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 4;
    }

    @Override
    public int powerMineMenu() {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("ADMINISTRACION DE MINAS DE ENERGIA");
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar estado de mis minas activas.");
            System.out.println("1 - consultar estado de minas a construir.");
            System.out.println("2 - Recolectar recursos.");
            System.out.println("3 - Construir una nueva mina.");
            System.out.println("4 - regresar.");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;

                case "3":
                    return 3;

                case "4":
                    return 4;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 4;
    }

    @Override
    public int militaryBaseMenu() {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("ADMINISTRACION DE BASES MILITARES");
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar estado de mis bases activas.");
            System.out.println("1 - consultar estado de bases a construir.");
            System.out.println("2 - Construir una nueva base.");
            System.out.println("3 - regresar.");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;

                case "3":
                    return 3;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 4;
    }

    @Override
    public int unitConstructionMenu() {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("ADMINISTRACION DE UNIDADES MILITARES");
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar estado de mis Unidades activas.");
            System.out.println("1 - consultar estado de Unidades en entrenamiento.");
            System.out.println("2 - Entrenar escuadrones.");
            System.out.println("3 - Entrenar Especialista.");
            System.out.println("4 - Entrenar vehiculo liviano.");
            System.out.println("5 - Entrenar vehiculo pesado.");
            System.out.println("6 - regresar.");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;

                case "3":
                    return 3;

                case "4":
                    return 4;

                case "5":
                    return 5;

                case "6":
                    return 6;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 6;
    }

    @Override
    public int attackMenu() {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("PLANIFICACION DE ATAQUE");
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar estado de mis Unidades activas.");
            System.out.println("1 - Preparar unidades para el ataque");
            System.out.println("2 - regresar.");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 2;
    }

    @Override
    public int defendMenu() {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("PLANIFICACION DE DEFENSA");
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar estado de mis Unidades activas.");
            System.out.println("1 - Desplegar unidades para la defensa");
            System.out.println("2 - regresar.");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 2;

    }

    @Override
    public int defineAttackMenu() {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("PLANIFICACION DE ATAQUE");
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar estado de mis Unidades activas.");
            System.out.println("1 - Desplegar unidades para la defensa");
            System.out.println("2 - regresar.");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 2;
    }

    @Override
    public int attPhase1Menu() {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("PLANIFICACION DE ATAQUE");
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar estado de mis Unidades activas.");
            System.out.println("1 - Atacar Centro de Mando enemigo.");
            System.out.println("2 - Atacar Fabricas del enemigo.");
            System.out.println("3 - Atacar Mercados del enemigo.");
            System.out.println("4 - Atacar Bases Militares del enemigo.");
            System.out.println("5 - Cancelar y volver a menu principal.");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;
                    
                case "3":
                    return 3;
                    
                case "4":
                    return 4;
                    
                case "5":
                    return 5;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 5;
    }

    @Override
    public int attPhase2Menu() {
        boolean validator = true;
        String option;
        while (validator) {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("PLANIFICACION DE ATAQUE FASE 2 (Seleccion de unidades)");
            System.out.println("Elige la accion que deseas realizar (ingresa el numero)");
            System.out.println("0 - Consultar estado de mis Unidades activas.");
            System.out.println("1 - Desplegar Escuadrones");
            System.out.println("2 - Desplegar Especialista.");
            System.out.println("3 - Desplegar vehiculos livianos.");
            System.out.println("4 - Desplegar vehiculos pesados.");
            System.out.println("5 - Confirmar Ataque");
            System.out.println("6 - Cancelar Ataque");

            System.out.print("Accion a realizar: ");
            option = in.nextLine();

            switch (option) {
                case "0":
                    return 0;

                case "1":
                    return 1;

                case "2":
                    return 2;
                    
                case "3":
                    return 3;
                    
                case "4":
                    return 4;
                    
                case "5":
                    return 5;
                    
                case "6":
                    return 6;

                default:
                    this.showMessage(UserInteractions.ERROR_MESSAGE, "Ingrese una opcion valida por favor");
                    break;
            }
        }
        return 5;
    }

    @Override
    public int pickAttackingUnits(int availableUnits) {
        String option;
        
        this.showMessage(UserInteractions.INFO_MESSAGE, "Tienes " + availableUnits + " disponibles para el ataque");
        System.out.print("Con cuantas unidades deseas atacar?");
        option = in.nextLine();
        
        if(Integer.parseInt(option) > availableUnits){
            this.showMessage(UserInteractions.ERROR_MESSAGE, "La cantidad ingresada no concuerda con las unidades disponibles");
            return -1;
        }else{
            return Integer.parseInt(option);
        }
    }

}
