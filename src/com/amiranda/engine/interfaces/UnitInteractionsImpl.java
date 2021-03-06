/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.core.AttackCommand;
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
    public LightVehicle setupPlayerLAV(String Name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost) {
        LightVehicle vehicle = new LightVehicle(Name, hitpoints, attackPoints, buildTime, successRate, rawMaterialsCost, energyCost, moneyCost);
        return vehicle;
    }

    @Override
    public HeavyVehicle setupPlayerHeavy(String Name, int hitpoints, int attackPoints, int buildTime, int successRate, int rawMaterialsCost, int energyCost, int moneyCost) {
        HeavyVehicle vehicle = new HeavyVehicle(Name, hitpoints, attackPoints, buildTime, successRate, rawMaterialsCost, energyCost, moneyCost);
        return vehicle;
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
    public LightVehicle createNewLAV(LightVehicle baseUnit) {
        LightVehicle vehiculo = new LightVehicle(baseUnit.getName(), baseUnit.getHitpoints(), baseUnit.getAttackPoints(), baseUnit.getBuildTime(), baseUnit.getSuccessRate(), baseUnit.getRawMaterialsCost(), baseUnit.getEnergyCost(), baseUnit.getMoneyCost());
        return vehiculo;
    }

    @Override
    public HeavyVehicle createNewHeavy(HeavyVehicle baseUnit) {
        HeavyVehicle vehiculo = new HeavyVehicle(baseUnit.getName(), baseUnit.getHitpoints(), baseUnit.getAttackPoints(), baseUnit.getBuildTime(), baseUnit.getSuccessRate(), baseUnit.getRawMaterialsCost(), baseUnit.getEnergyCost(), baseUnit.getMoneyCost());
        return vehiculo;
    }

    @Override
    public Player unitOperations(Player activePlayer) {
        boolean menu = true;
        Player processedPlayer = activePlayer;
        ArrayList<Squad> activeSquads = processedPlayer.getSquads();
        ArrayList<Specialist> activeSpecialist = processedPlayer.getSpecialist();

        ArrayList<Squad> pendingSquads = processedPlayer.getSquadConstruction();
        ArrayList<Specialist> pendingSpecialist = processedPlayer.getSpecialistConstruction();

        ArrayList<LightVehicle> pendingLAVs = processedPlayer.getLAVConstruction();
        ArrayList<HeavyVehicle> pendingHeavies = processedPlayer.getHeavyConstruction();

        ArrayList<Squad> tmpSquad = new ArrayList();
        ArrayList<Specialist> tmpSpecialist = new ArrayList();

        ComandCenter newCC = activePlayer.getCc();

        while (menu) {
            switch (userInteractions.unitConstructionMenu()) {
                case 0: //Revisando fabricas activas
                    this.checkActiveUnitsStats(activePlayer);
                    this.checkDeployedUnitsSquadStats(activePlayer);

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
                    if (this.checkAvailableSpace(activePlayer)) {
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
                    } else {
                        userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No hay espacio suficiente para esta unidad, construye mas bases militares");
                    }
                    break;

                case 3: //entrenando un nuevo especialista
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Un " + processedPlayer.getPlayerBaseSpecialist().getName() + "Cuesta " + processedPlayer.getPlayerBaseSpecialist().getMoneyCost() + "de Dinero y " + processedPlayer.getPlayerBaseSpecialist().getRawMaterialsCost() + " de materia prima.");

                    //Primero hay que validar que no hayan especialistas creados, desplegados o en proceso de creacion
                    if (this.checkAvailableSpace(activePlayer)) {
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
                            } else {
                                userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "Ya existe un especialista activo, desplegado o en proceso de entrenamiento");
                            }

                        }
                    } else {
                        userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No hay espacio suficiente para esta unidad, construye mas bases militares");
                    }

                    break;

                case 4: //entrenando un nuevo vehiculo liviano
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Un " + processedPlayer.getPlayerBaseLAV().getName() + "Cuesta " + processedPlayer.getPlayerBaseLAV().getMoneyCost() + "de Dinero y " + processedPlayer.getPlayerBaseLAV().getRawMaterialsCost() + " de materia prima.");

                    //Primero hay que validar que no hayan especialistas creados, desplegados o en proceso de creacion
                    if (userInteractions.confirmAction()) {
                        if (this.checkAvailableSpace(activePlayer)) {
                            if (buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getRawMaterialQty(), processedPlayer.getPlayerBaseLAV().getMoneyCost(), processedPlayer.getPlayerBaseLAV().getRawMaterialsCost()).equals("YES")) {
                                LightVehicle newUnit = createNewLAV(processedPlayer.getPlayerBaseLAV());
                                pendingLAVs.add(newUnit);
                                processedPlayer.setLAVConstruction(pendingLAVs);
                                newCC.setMoneyQty(newCC.getMoneyQty() - processedPlayer.getPlayerBaseLAV().getMoneyCost());
                                newCC.setRawMaterialQty(newCC.getRawMaterialQty() - processedPlayer.getPlayerBaseLAV().getRawMaterialsCost());
                            } else {
                                userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getRawMaterialQty(), processedPlayer.getPlayerBaseLAV().getMoneyCost(), processedPlayer.getPlayerBaseLAV().getRawMaterialsCost()));
                            }
                        } else {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No hay espacio suficiente para esta unidad, construye mas bases militares");
                        }
                    }

                    break;

                case 5: //entrenando un nuevo vehiculo pesado
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Un " + processedPlayer.getPlayerBaseHeavy().getName() + "Cuesta " + processedPlayer.getPlayerBaseHeavy().getMoneyCost() + "de Dinero y " + processedPlayer.getPlayerBaseHeavy().getRawMaterialsCost() + " de materia prima.");

                    //Primero hay que validar que no hayan especialistas creados, desplegados o en proceso de creacion
                    if (userInteractions.confirmAction()) {
                        if (this.checkAvailableSpace(activePlayer)) {
                            if (buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getRawMaterialQty(), processedPlayer.getPlayerBaseHeavy().getMoneyCost(), processedPlayer.getPlayerBaseHeavy().getRawMaterialsCost()).equals("YES")) {
                                HeavyVehicle newUnit = createNewHeavy(processedPlayer.getPlayerBaseHeavy());
                                pendingHeavies.add(newUnit);
                                processedPlayer.setHeavyConstruction(pendingHeavies);
                                newCC.setMoneyQty(newCC.getMoneyQty() - processedPlayer.getPlayerBaseHeavy().getMoneyCost());
                                newCC.setRawMaterialQty(newCC.getRawMaterialQty() - processedPlayer.getPlayerBaseHeavy().getRawMaterialsCost());
                            } else {
                                userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getRawMaterialQty(), processedPlayer.getPlayerBaseLAV().getMoneyCost(), processedPlayer.getPlayerBaseLAV().getRawMaterialsCost()));
                            }
                        } else {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No hay espacio suficiente para esta unidad, construye mas bases militares");
                        }
                    }

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
        ArrayList<LightVehicle> result = new ArrayList();
        int progress;
        for (LightVehicle f : colaProd) {
            progress = f.getBuildProgress();
            progress--;
            f.setBuildProgress(progress);
            result.add(f);
        }
        return result;
    }

    @Override
    public ArrayList<LightVehicle> lavQueueProduction(ArrayList<LightVehicle> colaProd, ArrayList<LightVehicle> playerUnits, LightVehicle playerBaseUnit) {
        ArrayList<LightVehicle> result = playerUnits;
        LightVehicle newUnit;
        newUnit = this.createNewLAV(playerBaseUnit);
        for (LightVehicle f : colaProd) {
            if (f.getBuildProgress() <= 0) {
                result.add(newUnit);
                userInteractions.showMessage(userInteractions.INFO_MESSAGE, "Se ha finalizado la construccion de " + playerBaseUnit.getName());
            }
        }

        return result;
    }

    @Override
    public ArrayList<LightVehicle> lavCleanQueue(ArrayList<LightVehicle> colaProd) {
        ArrayList<LightVehicle> result = new ArrayList();
        for (LightVehicle f : colaProd) {
            if (!(f.getBuildProgress() <= 0)) {
                result.add(f);
            }
        }

        return result;
    }

    @Override
    public ArrayList<LightVehicle> lavMaintenance(ArrayList<LightVehicle> playerUnit) {
        ArrayList<LightVehicle> result = new ArrayList();
        for (LightVehicle f : playerUnit) {
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
    public ArrayList<HeavyVehicle> heavyQueueMaintenance(ArrayList<HeavyVehicle> colaProd) {
        ArrayList<HeavyVehicle> result = new ArrayList();
        int progress;
        for (HeavyVehicle f : colaProd) {
            progress = f.getBuildProgress();
            progress--;
            f.setBuildProgress(progress);
            result.add(f);
        }
        return result;
    }

    @Override
    public ArrayList<HeavyVehicle> heavyQueueProduction(ArrayList<HeavyVehicle> colaProd, ArrayList<HeavyVehicle> playerUnits, HeavyVehicle playerBaseUnit) {
        ArrayList<HeavyVehicle> result = playerUnits;
        HeavyVehicle newUnit;
        newUnit = this.createNewHeavy(playerBaseUnit);
        for (HeavyVehicle f : colaProd) {
            if (f.getBuildProgress() <= 0) {
                result.add(newUnit);
                userInteractions.showMessage(userInteractions.INFO_MESSAGE, "Se ha finalizado la construccion de " + playerBaseUnit.getName());
            }
        }

        return result;
    }

    @Override
    public ArrayList<HeavyVehicle> heavyCleanQueue(ArrayList<HeavyVehicle> colaProd) {
        ArrayList<HeavyVehicle> result = new ArrayList();
        for (HeavyVehicle f : colaProd) {
            if (!(f.getBuildProgress() <= 0)) {
                result.add(f);
            }
        }

        return result;
    }

    @Override
    public ArrayList<HeavyVehicle> heavyMaintenance(ArrayList<HeavyVehicle> playerUnit) {
        ArrayList<HeavyVehicle> result = new ArrayList();
        for (HeavyVehicle f : playerUnit) {
            //Reduciendo en 1 el tiempo de turno de construccion
            if (f.getHitpoints() <= 0) {
                userInteractions.showMessage(UserInteractionsImpl.ALERT_MESSAGE, "Tu " + f.getName() + " ha sido destruida");
            } else {
                result.add(f);
            }
        }

        return result;
    }

    @Override
    public void checkActiveUnitsStats(Player player) {
        System.out.print("--ESCUADRONES DISPONIBLES--");
        if (!(player.getSquads().isEmpty())) {
            for (Squad a : player.getSquads()) {
                userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "" + a.getName() + " Vida: " + a.getHitpoints() + " Ataque: " + a.getAttackPoints() + "Efectividad de combate: " + a.getSuccessRate() + "%");
            }
        } else {
            userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "No hay unidades entrenadas");
        }

        System.out.print("--ESPECIALISTAS DISPONIBLES--");
        if (!(player.getSpecialist().isEmpty())) {
            for (Specialist a : player.getSpecialist()) {
                userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "" + a.getName() + " Vida: " + a.getHitpoints() + " Ataque: " + a.getAttackPoints() + "Efectividad de combate: " + a.getSuccessRate() + "%");
            }
        } else {
            userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "No hay unidades entrenadas");
        }

        System.out.print("--VEHICULOS LIVIANOS DISPONIBLES--");
        if (!(player.getLAVs().isEmpty())) {
            for (LightVehicle a : player.getLAVs()) {
                userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "" + a.getName() + " Vida: " + a.getHitpoints() + " Ataque: " + a.getAttackPoints() + "Efectividad de combate: " + a.getSuccessRate() + "%");
            }
        } else {
            userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "No hay unidades entrenadas");
        }

        System.out.print("--VEHICULOS PESADOS DISPONIBLES--");
        if (!(player.getHeavies().isEmpty())) {
            for (HeavyVehicle a : player.getHeavies()) {
                userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "" + a.getName() + " Vida: " + a.getHitpoints() + " Ataque: " + a.getAttackPoints() + "Efectividad de combate: " + a.getSuccessRate() + "%");
            }
        } else {
            userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "No hay unidades entrenadas");
        }

    }

    @Override
    public void checkDeployedUnitsSquadStats(Player player) {
        for (AttackCommand a : player.getAttackCommands()) {
            System.out.print("--ESCUADRONES DESPLEGADOS--");
            if (!(a.getDeployedSquads().isEmpty())) {
                for (Squad b : a.getDeployedSquads()) {
                    userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "" + b.getName() + " Vida: " + b.getHitpoints() + " Ataque: " + b.getAttackPoints() + "Efectividad de combate: " + b.getSuccessRate() + "%");
                }
            } else {
                userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "No hay unidades entrenadas");
            }

            System.out.print("--ESPECIALISTAS DESPLEGADOS--");
            if (!(a.getDeployedSpecialist().isEmpty())) {
                for (Specialist b : a.getDeployedSpecialist()) {
                    userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "" + b.getName() + " Vida: " + b.getHitpoints() + " Ataque: " + b.getAttackPoints() + "Efectividad de combate: " + b.getSuccessRate() + "%");
                }
            } else {
                userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "No hay unidades entrenadas");
            }

            System.out.print("--VEHICULOS LIVIANOS DESPLEGADOS--");
            if (!(a.getDeployedLAV().isEmpty())) {
                for (LightVehicle b : a.getDeployedLAV()) {
                    userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "" + b.getName() + " Vida: " + b.getHitpoints() + " Ataque: " + b.getAttackPoints() + "Efectividad de combate: " + b.getSuccessRate() + "%");
                }
            } else {
                userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "No hay unidades entrenadas");
            }

            System.out.print("--VEHICULOS PESADOS DESPLEGADOS--");
            if (!(a.getDeployedHeavy().isEmpty())) {
                for (HeavyVehicle b : a.getDeployedHeavy()) {
                    userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "" + b.getName() + " Vida: " + b.getHitpoints() + " Ataque: " + b.getAttackPoints() + "Efectividad de combate: " + b.getSuccessRate() + "%");
                }
            } else {
                userInteractions.showMessage(UserInteractionsImpl.INFO_MESSAGE, "No hay unidades entrenadas");
            }
        }
    }

    @Override
    public boolean checkAvailableSpace(Player p) {
        int usedSpace;
        usedSpace = p.getSquadConstruction().size() + p.getSquads().size() + p.getSpecialist().size() + p.getLAVConstruction().size() + p.getLAVs().size() + p.getHeavies().size() + p.getHeavyConstruction().size();

        return p.getCc().getUnitCapacity() > usedSpace;
    }

}
