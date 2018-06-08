/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.core.Player;
import com.amiranda.parcial2.classes.functional.buildings.ComandCenter;
import com.amiranda.parcial2.classes.functional.units.HeavyVehicle;
import com.amiranda.parcial2.classes.functional.units.LightVehicle;
import com.amiranda.parcial2.classes.functional.units.Specialist;
import com.amiranda.parcial2.classes.functional.units.Squad;
import java.util.ArrayList;

/**
 *
 * @author allan
 */
public class UnitInteractionsImpl implements UnitInteractions {

    UserInteractionsImpl userInteractions = new UserInteractionsImpl();

    @Override
    public String buildApproval(int money, int materials, int moneyPrice, int materialsPrice) {
        String result = "YES";
        String temp = "";
        if (money < moneyPrice) {
            temp = temp + " Dinero insuficientes, necesitas: " + moneyPrice + ", tienes : " + money;
        }

        if (materials < materialsPrice) {
            temp = temp + " Materia prima insuficiente, necesitas: " + materialsPrice + ", tienes : " + materials;
        }

        if (!(temp.isEmpty())) {
            //Si la cadena temporal no esta vacia, quiere decir se encontro algun problema al comprar el edificio
            result = temp;
        }

        return result;
    }

    @Override
    public Squad setupPlayerSquad(String name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost) {
        Squad escuadron = new Squad(name, hitpoints, attackPoints, buildTime, successRate, rawMaterialsCost, energyCost, moneyCost);
        return escuadron;
    }

    @Override
    public Specialist setupPlayerSpecialist(String Name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost) {
        Specialist especialista = new Specialist(Name, hitpoints, attackPoints, buildTime, successRate, rawMaterialsCost, energyCost, moneyCost);
        return especialista;
    }

    @Override
    public Squad createNewSquad(Squad baseUnit) {
        Squad escuadron = new Squad(baseUnit.getName(), baseUnit.getHitpoints(), baseUnit.getAttackPoints(), baseUnit.getBuildTime(), baseUnit.getSuccessRate(), baseUnit.getRawMaterialsCost(), baseUnit.getEnergyCost(), baseUnit.getMoneyCost());
        return escuadron;
    }

    @Override
    public Specialist createNewSpecialist(Specialist baseUnit) {
        Specialist especialista = new Specialist(baseUnit.getName(), baseUnit.getHitpoints(), baseUnit.getAttackPoints(), baseUnit.getBuildTime(), baseUnit.getSuccessRate(), baseUnit.getRawMaterialsCost(), baseUnit.getEnergyCost(), baseUnit.getMoneyCost());
        return especialista;
    }

    @Override
    public Player unitOperations(Player activePlayer) {
        boolean menu = true;
        Player processedPlayer = activePlayer;
        ArrayList<Squad> activeSquads = processedPlayer.getSquads();
        ArrayList<Specialist> activeSpecialist = processedPlayer.getSpecialist();

        ArrayList<Squad> pendingSquads = processedPlayer.getSquadConstruction();
        ArrayList<Specialist> pendingSpecialist = processedPlayer.getSpecialistConstruction();

        ArrayList<Squad> tmpSquad = new ArrayList();
        ArrayList<Specialist> tmpSpecialist = new ArrayList();

        ComandCenter newCC = activePlayer.getCc();

        while (menu) {
            switch (userInteractions.unitConstructionMenu()) {
                case 0: //Revisando fabricas activas
                    System.out.println("---ESCUADRONES DISPONIBLES---");
                    if (activeSquads.isEmpty()) {
                        this.userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "No hay unidades disponibles.");
                    } else {
                        for (Squad f : activeSquads) {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + "-> Vida: " + f.getHitpoints());
                        }
                    }

                    System.out.println("---ESPECIALISTA DISPONIBLES---");
                    if (activeSquads.isEmpty()) {
                        this.userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "No hay unidades disponibles.");
                    } else {
                        for (Specialist f : activeSpecialist) {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + "-> Vida: " + f.getHitpoints());
                        }
                    }

                    System.out.println("---ESCUADRONES DESPLEGADOS---");
                    if (activePlayer.getDeployedSquads().isEmpty()) {
                        this.userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "No hay unidades disponibles.");
                    } else {
                        for (Squad f : activePlayer.getDeployedSquads()) {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + "-> Vida: " + f.getHitpoints());
                        }
                    }

                    System.out.println("---ESPECIALISTA DESPLEGADO---");
                    if (activePlayer.getDeployedSpecialist().isEmpty()) {
                        this.userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "No hay unidades disponibles.");
                    } else {
                        for (Specialist f : activePlayer.getDeployedSpecialist()) {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + "-> Vida: " + f.getHitpoints());
                        }
                    }

                    break;

                case 1: //Revisando cola de construccion de unidades
                    System.out.println("---ESCUADRONES EN ENTRENAMIENTO---");
                    if (pendingSquads.isEmpty()) {
                        this.userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "No hay unidades en entrenamiento.");
                    } else {
                        for (Squad f : pendingSquads) {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " -> Turnos restantes: " + f.getBuildProgress());
                        }
                    }

                    System.out.println("---ESPECIALISTA EN ENTRENAMIENTO---");
                    if (pendingSpecialist.isEmpty()) {
                        this.userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "No hay unidades en entrenamiento.");
                    } else {
                        for (Specialist f : pendingSpecialist) {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " -> Turnos restantes: " + f.getBuildProgress());
                        }
                    }

                case 2: //Creando escuadron nuevo
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Un " + processedPlayer.getPlayerBaseSquad().getName() + "Cuesta " + processedPlayer.getPlayerBaseSquad().getMoneyCost() + "de Dinero y " + processedPlayer.getPlayerBaseSquad().getRawMaterialsCost() + " de materia prima.");
                    if (userInteractions.confirmAction()) {
                        if (buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getRawMaterialQty(), processedPlayer.getPlayerBaseSquad().getMoneyCost(), processedPlayer.getPlayerBaseSquad().getRawMaterialsCost()).equals("YES")) {
                            Squad newUnit = createNewSquad(processedPlayer.getPlayerBaseSquad());
                            pendingSquads.add(newUnit);
                            processedPlayer.setSquadConstruction(pendingSquads);
                            newCC.setMoneyQty(newCC.getMoneyQty() - processedPlayer.getPlayerBaseSquad().getMoneyCost());
                            newCC.setRawMaterialQty(newCC.getRawMaterialQty() - processedPlayer.getPlayerBaseSquad().getRawMaterialsCost());
                        } else {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getRawMaterialQty(), processedPlayer.getPlayerBaseSquad().getMoneyCost(), processedPlayer.getPlayerBaseSquad().getRawMaterialsCost()));
                        }
                    }
                    break;

                case 3: //entrenando un nuevo especialista
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Un " + processedPlayer.getPlayerBaseSquad().getName() + "Cuesta " + processedPlayer.getPlayerBaseSquad().getMoneyCost() + "de Dinero y " + processedPlayer.getPlayerBaseSquad().getRawMaterialsCost() + " de materia prima.");

                    //Primero hay que validar que no hayan especialistas creados, desplegados o en proceso de creacion
                    if (processedPlayer.getDeployedSpecialist().isEmpty() && processedPlayer.getSpecialist().isEmpty() && processedPlayer.getSpecialistConstruction().isEmpty()) {
                        if (userInteractions.confirmAction()) {
                            if (buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getRawMaterialQty(), processedPlayer.getPlayerBaseSpecialist().getMoneyCost(), processedPlayer.getPlayerBaseSpecialist().getRawMaterialsCost()).equals("YES")) {
                                Specialist newUnit = createNewSpecialist(processedPlayer.getPlayerBaseSpecialist());
                                pendingSpecialist.add(newUnit);
                                processedPlayer.setSpecialistConstruction(pendingSpecialist);
                                newCC.setMoneyQty(newCC.getMoneyQty() - processedPlayer.getPlayerBaseSpecialist().getMoneyCost());
                                newCC.setRawMaterialQty(newCC.getRawMaterialQty() - processedPlayer.getPlayerBaseSpecialist().getRawMaterialsCost());
                            } else {
                                userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getRawMaterialQty(), processedPlayer.getPlayerBaseSpecialist().getMoneyCost(), processedPlayer.getPlayerBaseSpecialist().getRawMaterialsCost()));
                            }
                        }else{
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "Ya existe un especialista activo, desplegado o en proceso de entrenamiento");
                        }
                            
                    }

                    break;

                case 4: //entrenando un nuevo vehiculo liviano

                    break;

                case 5: //entrenando un nuevo vehiculo pesado

                    break;

                case 6: //Saliendo del menu de fabricas
                    menu = false;
                    break;
            }
        }

        return processedPlayer;
    }

    @Override
    public ArrayList<Squad> squadQueueMaintenance(ArrayList<Squad> colaProd) {
        ArrayList<Squad> result = new ArrayList();
        int progress;
        for (Squad f : colaProd) {
            progress = f.getBuildProgress();
            progress--;
            f.setBuildProgress(progress);
            result.add(f);
        }
        return result;
    }

    @Override
    public ArrayList<Squad> squadQueueProduction(ArrayList<Squad> colaProd, ArrayList<Squad> playerUnits, Squad playerBaseUnit) {
        ArrayList<Squad> result = playerUnits;
        Squad newUnit;
        newUnit = this.createNewSquad(playerBaseUnit);
        for (Squad f : colaProd) {
            if (f.getBuildProgress() <= 0) {
                result.add(newUnit);
                userInteractions.showMessage(userInteractions.INFO_MESSAGE, "Se ha finalizado la construccion de " + playerBaseUnit.getName());
            }
        }

        return result;
    }

    @Override
    public ArrayList<Squad> squadCleanQueue(ArrayList<Squad> colaProd) {
        ArrayList<Squad> result = new ArrayList();
        for (Squad f : colaProd) {
            if (!(f.getBuildProgress() <= 0)) {
                result.add(f);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<Squad> squadMaintenance(ArrayList<Squad> playerUnit) {
        ArrayList<Squad> result = new ArrayList();
        for (Squad f : playerUnit) {
            //Reduciendo en 1 el tiempo de turno de construccion
            if (f.getHitpoints() <= 0) {
                userInteractions.showMessage(userInteractions.ALERT_MESSAGE, "Tu " + f.getName() + " ha sido destruida");
            } else {
                result.add(f);
            }
        }

        return result;
    }

    @Override
    public ArrayList<Specialist> specialistQueueMaintenance(ArrayList<Specialist> colaProd) {
        ArrayList<Specialist> result = new ArrayList();
        int progress;
        for (Specialist f : colaProd) {
            progress = f.getBuildProgress();
            progress--;
            f.setBuildProgress(progress);
            result.add(f);
        }
        return result;
    }

    @Override
    public ArrayList<Specialist> specialistQueueProduction(ArrayList<Specialist> colaProd, ArrayList<Specialist> playerUnits, Specialist playerBaseUnit) {
        ArrayList<Specialist> result = playerUnits;
        Specialist newUnit;
        newUnit = this.createNewSpecialist(playerBaseUnit);
        for (Specialist f : colaProd) {
            if (f.getBuildProgress() <= 0) {
                result.add(newUnit);
                userInteractions.showMessage(userInteractions.INFO_MESSAGE, "Se ha finalizado la construccion de " + playerBaseUnit.getName());
            }
        }

        return result;
    }

    @Override
    public ArrayList<Specialist> specialistCleanQueue(ArrayList<Specialist> colaProd) {
        ArrayList<Specialist> result = new ArrayList();
        for (Specialist f : colaProd) {
            if (!(f.getBuildProgress() <= 0)) {
                result.add(f);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<Specialist> specialistMaintenance(ArrayList<Specialist> playerUnit) {
        ArrayList<Specialist> result = new ArrayList();
        for (Specialist f : playerUnit) {
            //Reduciendo en 1 el tiempo de turno de construccion
            if (f.getHitpoints() <= 0) {
                userInteractions.showMessage(userInteractions.ALERT_MESSAGE, "Tu " + f.getName() + " ha sido destruida");
            } else {
                result.add(f);
            }
        }

        return result;
    }

    @Override
    public ArrayList<LightVehicle> lavQueueMaintenance(ArrayList<LightVehicle> colaProd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<LightVehicle> lavQueueProduction(ArrayList<LightVehicle> colaProd, ArrayList<LightVehicle> playerUnits, LightVehicle playerBaseUnit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<LightVehicle> lavCleanQueue(ArrayList<LightVehicle> colaProd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<LightVehicle> lavMaintenance(ArrayList<LightVehicle> playerUnit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HeavyVehicle> heavyQueueMaintenance(ArrayList<HeavyVehicle> colaProd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HeavyVehicle> heavyQueueProduction(ArrayList<HeavyVehicle> colaProd, ArrayList<HeavyVehicle> playerUnits, HeavyVehicle playerBaseUnit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HeavyVehicle> heavyCleanQueue(ArrayList<HeavyVehicle> colaProd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HeavyVehicle> heavyMaintenance(ArrayList<HeavyVehicle> playerUnit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
