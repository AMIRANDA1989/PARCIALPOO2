/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.core.Player;
import com.amiranda.parcial2.classes.functional.buildings.ComandCenter;
import com.amiranda.parcial2.classes.functional.buildings.Factory;
import com.amiranda.parcial2.classes.functional.buildings.Market;
import com.amiranda.parcial2.classes.functional.buildings.MilitaryBuilding;
import com.amiranda.parcial2.classes.functional.buildings.PowerMine;
import java.util.ArrayList;

/**
 *
 * @author allan
 */
public class BuildingInteractionsImpl implements BuildingInteractions {

    UserInteractionsImpl userInteractions = new UserInteractionsImpl();

    /**
     * generateResources
     *
     * @param capacity capacidad de almacenamiento del edificio
     * @param generationPerTurn recursos que genera por turno el edificio
     * @param currentCollected
     * @return los recursos que generara el edificio que este llamando a este
     * metodo luego de ser validados
     */
    @Override
    public int generateResources(int capacity, int generationPerTurn, int currentCollected) {
        if ((generationPerTurn + currentCollected) <= capacity) {
            return generationPerTurn;
        } else {
            return 0;
        }
    }

    /**
     * factoryActiveStatus
     *
     * @param playerBuilding Muestra el estado de las fabricas que se tienen
     * activas durante el juego
     */
    @Override
    public void factoryActiveStatus(ArrayList<Factory> playerBuilding) {
        for (Factory f : playerBuilding) {
            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " - VIDA: " + f.getHitpoints() + " CAPACIDAD: " + f.getCapacity() + " RECOLECTADO: " + f.getContents());
        }
    }

    /**
     * factoryPendingStatus
     *
     * @param colaProd Muestra cuanto falta para que las fabricas que estan
     * construyendose sean finalizadas
     */
    @Override
    public void factoryPendingStatus(ArrayList<Factory> colaProd) {
        for (Factory f : colaProd) {
            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " - Turnos restantes: " + f.getBuildProgress());

        }
    }

    /**
     * factoryOperations
     *
     * @param activePlayer
     * @return un objeto de jugador procesado
     */
    public Player factoryOperations(Player activePlayer) {
        boolean menu = true;
        Player processedPlayer = activePlayer;
        ArrayList<Factory> active = processedPlayer.getFactories();
        ArrayList<Factory> pending = processedPlayer.getFactConstruction();
        ArrayList<Factory> tmp = new ArrayList();
        ComandCenter newCC = activePlayer.getCc();

        while (menu) {
            switch (userInteractions.factoryMenu()) {
                case 0: //Revisando fabricas activas
                    for (Factory f : active) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + "-> Vida: " + f.getHitpoints() + " Recolectado: " + f.getContents() + " Capacidad: " + f.getCapacity());
                    }
                    break;

                case 1: //Revisando cola de construccion de fabricas
                    for (Factory f : pending) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " -> Turnos restantes: " + f.getBuildProgress());
                    }
                    break;

                case 2: //recolectar recursos
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Se recolectará los recursos de este edificio, deseas proceder?");
                    if (userInteractions.confirmAction()) {
                        for (Factory f : active) {
                            if (processedPlayer.getCc().getRawMaterialsCapacity() >= (f.getContents() + processedPlayer.getCc().getRawMaterialQty())) {
                                //si la suma de los contenidos del edificio son menores a la capacidad, se recoletca el elemento
                                newCC.setRawMaterialQty(f.getContents() + processedPlayer.getCc().getRawMaterialQty());
                                userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Materia Prima colectada de " + f.getName() + ": " + f.getContents());
                                f.setContents(0);
                                tmp.add(f);
                            } else {
                                userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No se pudo recolectar el recurso, excede la capacidad de almacenamiento del centro de mando");
                                tmp.add(f);
                            }
                        }
                        processedPlayer.setCc(newCC);
                        processedPlayer.setFactories(tmp);
                    }
                    break;

                case 3: //Creando una nueva fabrica
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Un " + processedPlayer.getPlayerBaseFactory().getName() + "Cuesta " + processedPlayer.getPlayerBaseFactory().getMoneyPrice() + "de Dinero y " + processedPlayer.getPlayerBaseFactory().getEnergyPrice() + " de energia.");
                    if (userInteractions.confirmAction()) {
                        if (buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getEnergyQty(), processedPlayer.getPlayerBaseFactory().getMoneyPrice(), processedPlayer.getPlayerBaseFactory().getEnergyPrice()).equals("YES")) {
                            Factory newBuilding;
                            newBuilding = new Factory(processedPlayer.getPlayerBaseFactory().getName(), processedPlayer.getPlayerBaseFactory().getHitpoints(), processedPlayer.getPlayerBaseFactory().getBuildTime(), processedPlayer.getPlayerBaseFactory().getCapacity(), processedPlayer.getPlayerBaseFactory().getContents(), processedPlayer.getPlayerBaseFactory().getProductionPerTurn(), processedPlayer.getPlayerBaseFactory().getMoneyPrice(), processedPlayer.getPlayerBaseFactory().getEnergyPrice());
                            pending.add(newBuilding);
                            processedPlayer.setFactConstruction(pending);
                            newCC.setMoneyQty(newCC.getMoneyQty() - processedPlayer.getPlayerBaseFactory().getMoneyPrice());
                            newCC.setEnergyQty(newCC.getEnergyQty() - processedPlayer.getPlayerBaseFactory().getEnergyPrice());
                        } else {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getEnergyQty(), processedPlayer.getPlayerBaseFactory().getMoneyPrice(), processedPlayer.getPlayerBaseFactory().getEnergyPrice()));
                        }
                    }
                    break;

                case 4: //Saliendo del menu de fabricas
                    menu = false;
                    break;
            }
        }

        return processedPlayer;
    }

    /**
     * Factory Queue Maintenance
     *
     * @param colaProd fabricas pendientes a construirse
     * @return Una lista actualizada de la cola de produccion
     */
    @Override
    public ArrayList<Factory> factoryQueueMaintenance(ArrayList<Factory> colaProd) {
        ArrayList<Factory> result = new ArrayList();
        int progress;
        for (Factory f : colaProd) {
            progress = f.getBuildProgress();
            progress--;
            f.setBuildProgress(progress);
            result.add(f);
        }
        return result;
    }

    /**
     * factoryQueueProduction
     *
     * @param colaProd
     * @param playerBuildings
     * @param playerBaseBuildings
     * @return una lista actualizada de las fabricas que se han construido
     */
    @Override
    public ArrayList<Factory> factoryQueueProduction(ArrayList<Factory> colaProd, ArrayList<Factory> playerBuildings, Factory playerBaseBuildings) {
        ArrayList<Factory> result = playerBuildings;
        Factory newBuilding;
        newBuilding = new Factory(playerBaseBuildings.getName(), playerBaseBuildings.getHitpoints(), playerBaseBuildings.getBuildTime(), playerBaseBuildings.getCapacity(), playerBaseBuildings.getProductionPerTurn(), playerBaseBuildings.getContents(), playerBaseBuildings.getMoneyPrice(), playerBaseBuildings.getEnergyPrice());
        for (Factory f : colaProd) {
            if (f.getBuildProgress() <= 0) {
                result.add(newBuilding);
                userInteractions.showMessage(userInteractions.INFO_MESSAGE, "Se ha finalizado la construccion de " + playerBaseBuildings.getName());
            }
        }

        return result;
    }

    /**
     * factoryCleanQueue
     *
     * @param colaProd
     * @return una lista actualizada de las fabricas pendientes
     */
    @Override
    public ArrayList<Factory> factoryCleanQueue(ArrayList<Factory> colaProd) {
        ArrayList<Factory> result = new ArrayList();
        for (Factory f : colaProd) {
            if (!(f.getBuildProgress() <= 0)) {
                result.add(f);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<Factory> factoryMaintenance(ArrayList<Factory> playerBuilding) {
        ArrayList<Factory> result = new ArrayList();
        for (Factory f : playerBuilding) {
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
    public String buildApproval(int money, int energy, int moneyPrice, int energyPrice) {
        String result = "YES";
        String temp = "";
        if (money < moneyPrice) {
            temp = temp + " Dinero insuficientes, necesitas: " + moneyPrice + ", tienes : " + money;
        }

        if (energy < energyPrice) {
            temp = temp + " Energia insuficiente, necesitas: " + energyPrice + ", tienes : " + energy;
        }

        if (!(temp.isEmpty())) {
            //Si la cadena temporal no esta vacia, quiere decir se encontro algun problema al comprar el edificio
            result = temp;
        }

        return result;
    }

    @Override
    public Player marketOperations(Player activePlayer) {
        boolean menu = true;
        Player processedPlayer = activePlayer;
        ArrayList<Market> active = processedPlayer.getMarkets();
        ArrayList<Market> pending = processedPlayer.getMarketConstruction();
        ArrayList<Market> tmp = new ArrayList();
        ComandCenter newCC = activePlayer.getCc();

        while (menu) {
            switch (userInteractions.marketMenu()) {
                case 0: //Revisando fabricas activas
                    for (Market f : active) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + "-> Vida: " + f.getHitpoints() + " Recolectado: " + f.getContents() + " Capacidad: " + f.getCapacity());
                    }
                    break;

                case 1: //Revisando cola de construccion de fabricas
                    for (Market f : pending) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " -> Turnos restantes: " + f.getBuildProgress());
                    }
                    break;

                case 2: //recolectar recursos
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Se recolectará los recursos de este edificio, deseas proceder?");
                    if (userInteractions.confirmAction()) {
                        for (Market f : active) {
                            if (processedPlayer.getCc().getMoneyCapacity() >= (f.getContents() + processedPlayer.getCc().getMoneyQty())) {
                                //si la suma de los contenidos del edificio son menores a la capacidad, se recoletca el elemento
                                newCC.setMoneyQty(f.getContents() + processedPlayer.getCc().getMoneyQty());
                                userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Dinero colectado de " + f.getName() + ": " + f.getContents());
                                f.setContents(0);
                                tmp.add(f);
                            } else {
                                userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No se pudo recolectar el recurso, excede la capacidad de almacenamiento del centro de mando");
                                tmp.add(f);
                            }
                        }
                        processedPlayer.setCc(newCC);
                        processedPlayer.setMarkets(tmp);
                    }
                    break;

                case 3: //Creando una nueva fabrica
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Un " + processedPlayer.getPlayerBaseMarket().getName() + "Cuesta " + processedPlayer.getPlayerBaseMarket().getMoneyPrice() + "de Dinero y " + processedPlayer.getPlayerBaseMarket().getEnergyPrice() + " de energia.");
                    if (userInteractions.confirmAction()) {
                        if (buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getEnergyQty(), processedPlayer.getPlayerBaseMarket().getMoneyPrice(), processedPlayer.getPlayerBaseMarket().getEnergyPrice()).equals("YES")) {
                            Market newBuilding;
                            newBuilding = new Market(processedPlayer.getPlayerBaseMarket().getName(), processedPlayer.getPlayerBaseMarket().getHitpoints(), processedPlayer.getPlayerBaseMarket().getBuildTime(), processedPlayer.getPlayerBaseMarket().getCapacity(), processedPlayer.getPlayerBaseMarket().getContents(), processedPlayer.getPlayerBaseMarket().getProductionPerTurn(), processedPlayer.getPlayerBaseMarket().getMoneyPrice(), processedPlayer.getPlayerBaseMarket().getEnergyPrice());
                            pending.add(newBuilding);
                            processedPlayer.setMarketConstruction(pending);
                            newCC.setMoneyQty(newCC.getMoneyQty() - processedPlayer.getPlayerBaseMarket().getMoneyPrice());
                            newCC.setEnergyQty(newCC.getEnergyQty() - processedPlayer.getPlayerBaseMarket().getEnergyPrice());
                        } else {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getEnergyQty(), processedPlayer.getPlayerBaseMarket().getMoneyPrice(), processedPlayer.getPlayerBaseMarket().getEnergyPrice()));
                        }

                    }

                    break;

                case 4: //Saliendo del menu de fabricas
                    menu = false;
                    break;
            }
        }

        return processedPlayer;
    }

    @Override
    public void marketActiveStatus(ArrayList<Market> playerBuilding) {
        for (Market f : playerBuilding) {
            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " - VIDA: " + f.getHitpoints() + " CAPACIDAD: " + f.getCapacity() + " RECOLECTADO: " + f.getContents());
        }
    }

    @Override
    public void marketPendingStatus(ArrayList<Market> colaProd) {
        for (Market f : colaProd) {
            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " - Turnos restantes: " + f.getBuildProgress());

        }
    }

    @Override
    public ArrayList<Market> marketQueueMaintenance(ArrayList<Market> colaProd) {
        ArrayList<Market> result = new ArrayList();
        int progress;
        for (Market f : colaProd) {
            progress = f.getBuildProgress();
            progress--;
            f.setBuildProgress(progress);
            result.add(f);
        }
        return result;
    }

    @Override
    public ArrayList<Market> marketQueueProduction(ArrayList<Market> colaProd, ArrayList<Market> playerBuildings, Market playerBaseBuilding) {
        ArrayList<Market> result = playerBuildings;
        Market newBuilding;

        for (Market f : colaProd) {
            if (f.getBuildProgress() <= 0) {
                newBuilding = new Market(playerBaseBuilding.getName(), playerBaseBuilding.getHitpoints(), playerBaseBuilding.getBuildTime(), playerBaseBuilding.getCapacity(), playerBaseBuilding.getProductionPerTurn(), playerBaseBuilding.getContents(), playerBaseBuilding.getMoneyPrice(), playerBaseBuilding.getEnergyPrice());
                result.add(newBuilding);
                userInteractions.showMessage(userInteractions.INFO_MESSAGE, "Se ha finalizado la construccion de " + playerBaseBuilding.getName());
            }
        }

        return result;
    }

    @Override
    public ArrayList<Market> marketCleanQueue(ArrayList<Market> colaProd) {
        ArrayList<Market> result = new ArrayList();
        for (Market f : colaProd) {
            if (!(f.getBuildProgress() <= 0)) {
                result.add(f);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<Market> marketMaintenance(ArrayList<Market> playerBuilding) {
        ArrayList<Market> result = new ArrayList();
        for (Market f : playerBuilding) {
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
    public void powerMineActiveStatus(ArrayList<PowerMine> playerBuilding) {
        for (PowerMine f : playerBuilding) {
            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " - VIDA: " + f.getHitpoints() + " CAPACIDAD: " + f.getCapacity() + " RECOLECTADO: " + f.getContents());
        }
    }

    @Override
    public void powerMinePendingStatus(ArrayList<PowerMine> colaProd) {
        for (PowerMine f : colaProd) {
            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " - Turnos restantes: " + f.getBuildProgress());

        }
    }

    @Override
    public Player powerMineOperations(Player activePlayer) {
        boolean menu = true;
        Player processedPlayer = activePlayer;
        ArrayList<PowerMine> active = processedPlayer.getMines();
        ArrayList<PowerMine> pending = processedPlayer.getMineConstruction();
        ArrayList<PowerMine> tmp = new ArrayList();
        ComandCenter newCC = activePlayer.getCc();

        while (menu) {
            switch (userInteractions.powerMineMenu()) {
                case 0: //Revisando fabricas activas
                    for (PowerMine f : active) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + "-> Vida: " + f.getHitpoints() + " Recolectado: " + f.getContents() + " Capacidad: " + f.getCapacity());
                    }
                    break;

                case 1: //Revisando cola de construccion de fabricas
                    for (PowerMine f : pending) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " -> Turnos restantes: " + f.getBuildProgress());
                    }
                    break;

                case 2: //recolectar recursos
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Se recolectará los recursos de este edificio, deseas proceder?");
                    if (userInteractions.confirmAction()) {
                        for (PowerMine f : active) {
                            if (processedPlayer.getCc().getEnergyCapacity() >= (f.getContents() + processedPlayer.getCc().getEnergyQty())) {
                                //si la suma de los contenidos del edificio son menores a la capacidad, se recoletca el elemento
                                newCC.setEnergyQty(f.getContents() + processedPlayer.getCc().getEnergyQty());
                                userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Energia colectada de " + f.getName() + ": " + f.getContents());
                                f.setContents(0);
                                tmp.add(f);
                            } else {
                                userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No se pudo recolectar el recurso, excede la capacidad de almacenamiento del centro de mando");
                                tmp.add(f);
                            }
                        }
                        processedPlayer.setCc(newCC);
                        processedPlayer.setMines(tmp);
                    }
                    break;

                case 3: //Creando una nueva fabrica
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Un " + processedPlayer.getPlayerBaseMarket().getName() + "Cuesta " + processedPlayer.getPlayerBaseMarket().getMoneyPrice() + "de Dinero y " + processedPlayer.getPlayerBaseMarket().getEnergyPrice() + " de energia.");
                    if (userInteractions.confirmAction()) {
                        if (buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getEnergyQty(), processedPlayer.getPlayerBasePowerMine().getMoneyPrice(), processedPlayer.getPlayerBasePowerMine().getEnergyPrice()).equals("YES")) {
                            PowerMine newBuilding;
                            newBuilding = new PowerMine(processedPlayer.getPlayerBasePowerMine().getName(), processedPlayer.getPlayerBasePowerMine().getHitpoints(), processedPlayer.getPlayerBasePowerMine().getBuildTime(), processedPlayer.getPlayerBasePowerMine().getCapacity(), processedPlayer.getPlayerBasePowerMine().getContents(), processedPlayer.getPlayerBasePowerMine().getProductionPerTurn(), processedPlayer.getPlayerBasePowerMine().getMoneyPrice(), processedPlayer.getPlayerBasePowerMine().getEnergyPrice());
                            pending.add(newBuilding);
                            processedPlayer.setMineConstruction(pending);
                            newCC.setMoneyQty(newCC.getMoneyQty() - processedPlayer.getPlayerBasePowerMine().getMoneyPrice());
                            newCC.setEnergyQty(newCC.getEnergyQty() - processedPlayer.getPlayerBasePowerMine().getEnergyPrice());
                        } else {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getEnergyQty(), processedPlayer.getPlayerBasePowerMine().getMoneyPrice(), processedPlayer.getPlayerBasePowerMine().getEnergyPrice()));
                        }
                    }
                    break;

                case 4: //Saliendo del menu de fabricas
                    menu = false;
                    break;
            }
        }

        return processedPlayer;
    }

    @Override
    public ArrayList<PowerMine> powerMineQueueMaintenance(ArrayList<PowerMine> colaProd) {
        ArrayList<PowerMine> result = new ArrayList();
        int progress;
        for (PowerMine f : colaProd) {
            progress = f.getBuildProgress();
            progress--;
            f.setBuildProgress(progress);
            result.add(f);
        }
        return result;
    }

    @Override
    public ArrayList<PowerMine> powerMineQueueProduction(ArrayList<PowerMine> colaProd, ArrayList<PowerMine> playerBuildings, PowerMine playerBaseBuilding) {
        ArrayList<PowerMine> result = playerBuildings;
        PowerMine newBuilding;
        newBuilding = new PowerMine(playerBaseBuilding.getName(), playerBaseBuilding.getHitpoints(), playerBaseBuilding.getBuildTime(), playerBaseBuilding.getCapacity(), playerBaseBuilding.getProductionPerTurn(), playerBaseBuilding.getContents(), playerBaseBuilding.getMoneyPrice(), playerBaseBuilding.getEnergyPrice());
        for (PowerMine f : colaProd) {
            if (f.getBuildProgress() <= 0) {
                result.add(newBuilding);
                userInteractions.showMessage(userInteractions.INFO_MESSAGE, "Se ha finalizado la construccion de " + playerBaseBuilding.getName());
            }
        }

        return result;
    }

    @Override
    public ArrayList<PowerMine> powerMineCleanQueue(ArrayList<PowerMine> colaProd) {
        ArrayList<PowerMine> result = new ArrayList();
        for (PowerMine f : colaProd) {
            if (!(f.getBuildProgress() <= 0)) {
                result.add(f);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<PowerMine> powerMineMaintenance(ArrayList<PowerMine> playerBuilding) {
        ArrayList<PowerMine> result = new ArrayList();
        for (PowerMine f : playerBuilding) {
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
    public void militaryBaseActiveStatus(ArrayList<MilitaryBuilding> playerBuilding) {
        for (MilitaryBuilding f : playerBuilding) {
            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " - VIDA: " + f.getHitpoints() + " CAPACIDAD: " + f.getCapacity());
        }
    }

    @Override
    public void militaryBaseStatus(ArrayList<MilitaryBuilding> colaProd) {
        for (MilitaryBuilding f : colaProd) {
            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " - Turnos restantes: " + f.getBuildProgress());

        }
    }

    @Override
    public Player militaryBaseOperations(Player activePlayer) {
        boolean menu = true;
        Player processedPlayer = activePlayer;
        ArrayList<MilitaryBuilding> active = processedPlayer.getMbs();
        ArrayList<MilitaryBuilding> pending = processedPlayer.getMbsConstruction();
        ArrayList<MilitaryBuilding> tmp = new ArrayList();
        ComandCenter newCC = activePlayer.getCc();

        while (menu) {
            switch (userInteractions.militaryBaseMenu()) {
                case 0: //Revisando fabricas activas
                    for (MilitaryBuilding f : active) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + "-> Vida: " + f.getHitpoints() + " Recolectado: ");
                    }
                    break;

                case 1: //Revisando cola de construccion de fabricas
                    for (MilitaryBuilding f : pending) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, f.getName() + " -> Turnos restantes: " + f.getBuildProgress());
                    }
                    break;

                case 2: //Creando una nueva base militar
                    userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "Un " + processedPlayer.getPlayerBaseMilitaryBuilding().getName() + "Cuesta " + processedPlayer.getPlayerBaseMilitaryBuilding().getMoneyPrice() + "de Dinero y " + processedPlayer.getPlayerBaseMilitaryBuilding().getEnergyPrice() + " de energia.");
                    if (userInteractions.confirmAction()) {
                        if (buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getEnergyQty(), processedPlayer.getPlayerBaseMilitaryBuilding().getMoneyPrice(), processedPlayer.getPlayerBaseMilitaryBuilding().getEnergyPrice()).equals("YES")) {
                            MilitaryBuilding newBuilding;
                            newBuilding = new MilitaryBuilding(processedPlayer.getPlayerBaseMilitaryBuilding().getName(), processedPlayer.getPlayerBaseMilitaryBuilding().getHitpoints(), processedPlayer.getPlayerBaseMilitaryBuilding().getCapacity(), processedPlayer.getPlayerBaseMilitaryBuilding().getBuildTime(), processedPlayer.getPlayerBaseMilitaryBuilding().getMoneyPrice(), processedPlayer.getPlayerBaseMilitaryBuilding().getEnergyPrice());
                            pending.add(newBuilding);
                            processedPlayer.setMbsConstruction(pending);
                            newCC.setMoneyQty(newCC.getMoneyQty() - processedPlayer.getPlayerBaseMilitaryBuilding().getMoneyPrice());
                            newCC.setEnergyQty(newCC.getEnergyQty() - processedPlayer.getPlayerBaseMilitaryBuilding().getEnergyPrice());
                        } else {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, buildApproval(processedPlayer.getCc().getMoneyQty(), processedPlayer.getCc().getEnergyQty(), processedPlayer.getPlayerBaseMilitaryBuilding().getMoneyPrice(), processedPlayer.getPlayerBaseMilitaryBuilding().getEnergyPrice()));
                        }
                    }
                    break;

                case 3: //Saliendo del menu de fabricas
                    menu = false;
                    break;
            }
        }

        return processedPlayer;
    }

    @Override
    public ArrayList<MilitaryBuilding> militaryBaseQueueMaintenance(ArrayList<MilitaryBuilding> colaProd) {
        ArrayList<MilitaryBuilding> result = new ArrayList();
        int progress;
        for (MilitaryBuilding f : colaProd) {
            progress = f.getBuildProgress();
            progress--;
            f.setBuildProgress(progress);
            result.add(f);
        }
        return result;
    }

    @Override
    public ArrayList<MilitaryBuilding> militaryBaseQueueProduction(ArrayList<MilitaryBuilding> colaProd, ArrayList<MilitaryBuilding> playerBuildings, MilitaryBuilding playerBaseBuilding) {
        ArrayList<MilitaryBuilding> result = playerBuildings;
        MilitaryBuilding newBuilding;
        newBuilding = new MilitaryBuilding(playerBaseBuilding.getName(), playerBaseBuilding.getHitpoints(), playerBaseBuilding.getBuildTime(), playerBaseBuilding.getCapacity(), playerBaseBuilding.getMoneyPrice(), playerBaseBuilding.getEnergyPrice());
        for (MilitaryBuilding f : colaProd) {
            if (f.getBuildProgress() <= 0) {
                result.add(newBuilding);
                userInteractions.showMessage(userInteractions.INFO_MESSAGE, "Se ha finalizado la construccion de " + playerBaseBuilding.getName());
            }
        }

        return result;
    }

    @Override
    public ArrayList<MilitaryBuilding> militaryBaseCleanQueue(ArrayList<MilitaryBuilding> colaProd) {
        ArrayList<MilitaryBuilding> result = new ArrayList();
        for (MilitaryBuilding f : colaProd) {
            if (!(f.getBuildProgress() <= 0)) {
                result.add(f);
            }
        }
        
        return result;
    }

    @Override
    public ArrayList<MilitaryBuilding> militaryBaseMaintenance(ArrayList<MilitaryBuilding> playerBuilding) {
        ArrayList<MilitaryBuilding> result = new ArrayList();
        for (MilitaryBuilding f : playerBuilding) {
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
    public int militaryBaseCurrentCapacity(ArrayList<MilitaryBuilding> playerBuildings, int currentUnits, int deployedUnits) {
        int capTotal = 0;

        //obteniendo la capacidad total
        for (MilitaryBuilding f : playerBuildings) {
            capTotal = capTotal + f.getCapacity();
        }

        //quitando de la capacidad total las unidades creadas y deployadas
        capTotal = capTotal - (currentUnits + deployedUnits);

        return capTotal;
    }

    @Override
    public ArrayList<Factory> factoryFillResources(ArrayList<Factory> buildings) {
        ArrayList<Factory> result = new ArrayList();

        for (Factory b : buildings) {
            b.setContents(this.generateResources(b.getCapacity(), b.getProductionPerTurn(), b.getContents()) + b.getContents());
            result.add(b);
        }

        return result;
    }

    @Override
    public ArrayList<Market> marketFillResources(ArrayList<Market> buildings) {
        ArrayList<Market> result = new ArrayList();

        for (Market b : buildings) {
            b.setContents(this.generateResources(b.getCapacity(), b.getProductionPerTurn(), b.getContents()) + b.getContents());
            result.add(b);
        }

        return result;
    }

    @Override
    public ArrayList<PowerMine> powerMineFillResources(ArrayList<PowerMine> buildings) {
        ArrayList<PowerMine> result = new ArrayList();

        for (PowerMine b : buildings) {
            b.setContents(this.generateResources(b.getCapacity(), b.getProductionPerTurn(), b.getContents()) + b.getContents());
            result.add(b);
        }

        return result;
    }

}
