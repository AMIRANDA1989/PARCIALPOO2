/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.core.AttackCommand;
import com.amiranda.parcial2.classes.core.Player;
import com.amiranda.parcial2.classes.functional.buildings.ComandCenter;
import com.amiranda.parcial2.classes.functional.buildings.Factory;
import com.amiranda.parcial2.classes.functional.buildings.Market;
import com.amiranda.parcial2.classes.functional.buildings.PowerMine;
import com.amiranda.parcial2.classes.functional.units.HeavyVehicle;
import com.amiranda.parcial2.classes.functional.units.LightVehicle;
import com.amiranda.parcial2.classes.functional.units.Specialist;
import com.amiranda.parcial2.classes.functional.units.Squad;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author allan
 */
public class AttackInteractionsImpl implements AttackInteractions {

    UserInteractions userInteractions = new UserInteractionsImpl();

    boolean commandSetup = true;

    @Override
    public Player attackOperations(Player attackingPlayer, Player targetPlayer) {
        int targetBuilding = -1;
        Player attPlayer = attackingPlayer;
        Player trgPlayer = targetPlayer;

        ArrayList<Squad> remainingSquads = new ArrayList();
        ArrayList<Specialist> remainingSpecialist = new ArrayList();
        ArrayList<LightVehicle> remainingLAVs = new ArrayList();
        ArrayList<HeavyVehicle> remainingHeavies = new ArrayList();

        ArrayList<Squad> attSquads = new ArrayList();
        ArrayList<Specialist> attSpecialist = new ArrayList();
        ArrayList<LightVehicle> attLAVs = new ArrayList();
        ArrayList<HeavyVehicle> attHeavies = new ArrayList();

        ArrayList<AttackCommand> attCommands = new ArrayList();

        AttackCommand command = new AttackCommand();

        switch (userInteractions.attPhase1Menu()) {
            case 1: //atacar centro de mando enemigo
                if (targetPlayer.getFactories().isEmpty() && targetPlayer.getMarkets().isEmpty() && targetPlayer.getMines().isEmpty() && targetPlayer.getMbs().isEmpty()) {
                    //validando que los edificios del oponenente (EN ESE MOMENTO) esten destruidos
                    targetBuilding = AttackInteractions.COMMAND_CENTER;
                } else {
                    userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "Todavia hay edificios en pie en la base de tu enemigo, destruyelos!");
                }
                break;

            case 2: //atacar fabricas del enemigo
                if (targetPlayer.getFactories().isEmpty()) {
                    userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "Tu oponente no tiene fabricas en pie!");
                } else {
                    targetBuilding = AttackInteractions.FACTORY;
                }
                break;

            case 3: //atacar mercados del enemigo
                if (targetPlayer.getMarkets().isEmpty()) {
                    userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "Tu oponente no tiene minas en pie!");
                } else {
                    targetBuilding = AttackInteractions.MARKET;
                }
                break;

            case 4: //atacar minas del enemigo
                if (targetPlayer.getMines().isEmpty()) {
                    userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "Tu oponente no tiene minas en pie!");
                } else {
                    targetBuilding = AttackInteractions.POWER_MINE;
                }
                break;

            case 5: //atacar bases militares del enemigo
                if (targetPlayer.getMbs().isEmpty()) {
                    userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "Tu oponente no tiene bases militares en pie!");
                } else {
                    targetBuilding = AttackInteractions.MILITARY_BUILDING;
                }
                break;

            case 6: //atacar bases militares del enemigo
                targetBuilding = AttackInteractions.CANCEL_ATTACK;
                break;

            default:
                targetBuilding = AttackInteractions.CANCEL_ATTACK;
                break;
        }

        //si el ataque no es cancelado, seguir con la seleccion de unidades
        int attackingUnits;

        if (targetBuilding != AttackInteractions.CANCEL_ATTACK) {
            while (commandSetup) {
                switch (userInteractions.attPhase2Menu()) {
                    case 1: //usar escuadrones para atacar
                        if (attPlayer.getSquads().isEmpty()) {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No tienes escuadrones disponibles!");
                        } else {
                            attackingUnits = userInteractions.pickAttackingUnits(attPlayer.getSquads().size());

                            if (attackingUnits != -1) {
                                remainingSquads = this.deploySquad1(attPlayer.getSquads(), attackingUnits);
                                attSquads = this.deploySquad2(attPlayer.getSquads(), attackingUnits);
                            }
                        }
                        break;

                    case 2: //usar especialista para atacar
                        if (attPlayer.getSpecialist().isEmpty()) {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No tienes especialistas disponibles!");
                        } else {
                            attackingUnits = userInteractions.pickAttackingUnits(attPlayer.getSpecialist().size());

                            if (attackingUnits != -1) {
                                remainingSpecialist = new ArrayList();
                                attSpecialist = this.deploySpecialist2(attPlayer.getSpecialist(), attackingUnits);
                            }
                        }
                        break;

                    case 3: //usar LAV para atacar
                        if (attPlayer.getLAVs().isEmpty()) {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No tienes vehiculos livianos disponibles!");
                        } else {
                            attackingUnits = userInteractions.pickAttackingUnits(attPlayer.getLAVs().size());

                            if (attackingUnits != -1) {
                                remainingLAVs = this.deployLAV1(attPlayer.getLAVs(), attackingUnits);
                                attLAVs = this.deployLAV2(attPlayer.getLAVs(), attackingUnits);
                            }
                        }
                        break;

                    case 4: //usar Heavy para atacar
                        if (attPlayer.getHeavies().isEmpty()) {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No tienes vehiculos pesados disponibles!");
                        } else {
                            attackingUnits = userInteractions.pickAttackingUnits(attPlayer.getHeavies().size());

                            if (attackingUnits != -1) {
                                remainingHeavies = this.deployHeavy1(attPlayer.getHeavies(), attackingUnits);
                                attHeavies = this.deployHeavy2(attPlayer.getHeavies(), attackingUnits);
                            }
                        }
                        break;

                    case 5: //confirmar ataque
                        System.out.print("Confirmar y proceder con el ataque?");
                        if (userInteractions.confirmAction()) {
                            command.setTarget(targetBuilding);
                            command.setDeployedSquads(attSquads);
                            command.setDeployedLAV(attLAVs);
                            command.setDeployedHeavy(attHeavies);
                            command.setDeployedSpecialist(attSpecialist);

                            attPlayer.setSquads(remainingSquads);
                            attPlayer.setSpecialist(remainingSpecialist);
                            attPlayer.setLAVs(remainingLAVs);
                            attPlayer.setHeavies(remainingHeavies);

                            //attCommands.add(command);
                            attCommands = attPlayer.getAttackCommands();
                            attCommands.add(command);
                            attPlayer.setAttackCommands(attCommands);

                            userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "El ataque tendrá efecto en 2 turnos");
                            return attPlayer;
                        } else {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Orden Cancelada");
                            return attackingPlayer;
                        }

                    case 6:
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Orden Cancelada");
                        return attackingPlayer;

                }
            }
        } else {
            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Orden Cancelada");
            return attackingPlayer;
        }
        return attackingPlayer;
    }

    @Override
    public ArrayList<Squad> deploySquad1(ArrayList<Squad> units, int qty) {
        int num = 0;
        ArrayList<Squad> result = new ArrayList();
        for (Squad i : units) {
            if (!(num <= qty - 1)) {
                //se toman las primeras unidades en la lista (son las mas veteranas) para atacar
                //por eso se ignoran
                result.add(i);
            }
            num++;
        }

        return result;
    }

    @Override
    public ArrayList<Squad> deploySquad2(ArrayList<Squad> units, int qty) {
        int num = 0;
        ArrayList<Squad> result = new ArrayList();
        for (Squad i : units) {
            if (num <= qty - 1) {
                //se toman las primeras unidades en la lista (son las mas veteranas) para atacar
                result.add(i);
            }
            num++;
        }

        return result;
    }

    @Override
    public ArrayList<Specialist> deploySpecialist1(ArrayList<Specialist> units, int qty) {
        int num = 0;
        ArrayList<Specialist> result = units;
        result.clear();
        return result;
    }

    @Override
    public ArrayList<Specialist> deploySpecialist2(ArrayList<Specialist> units, int qty) {
        int num = 0;
        ArrayList<Specialist> result = new ArrayList();
        result.add(units.get(0));
        return result;
    }

    @Override
    public ArrayList<LightVehicle> deployLAV1(ArrayList<LightVehicle> units, int qty) {
        int num = 0;
        ArrayList<LightVehicle> result = new ArrayList();
        for (LightVehicle i : units) {
            if (!(num <= qty - 1)) {
                //se toman las primeras unidades en la lista (son las mas veteranas) para atacar
                //por eso se ignoran
                result.add(i);
            }
            num++;
        }

        return result;
    }

    @Override
    public ArrayList<LightVehicle> deployLAV2(ArrayList<LightVehicle> units, int qty) {
        int num = 0;
        ArrayList<LightVehicle> result = new ArrayList();
        for (LightVehicle i : units) {
            if (num <= qty - 1) {
                //se toman las primeras unidades en la lista (son las mas veteranas) para atacar
                result.add(i);
            }
            num++;
        }

        return result;
    }

    @Override
    public ArrayList<HeavyVehicle> deployHeavy1(ArrayList<HeavyVehicle> units, int qty) {
        int num = 0;
        ArrayList<HeavyVehicle> result = new ArrayList();
        for (HeavyVehicle i : units) {
            if (!(num <= qty - 1)) {
                //se toman las primeras unidades en la lista (son las mas veteranas) para atacar
                //por eso se ignoran
                result.add(i);
            }
            num++;
        }

        return result;
    }

    @Override
    public ArrayList<HeavyVehicle> deployHeavy2(ArrayList<HeavyVehicle> units, int qty) {
        int num = 0;
        ArrayList<HeavyVehicle> result = new ArrayList();
        for (HeavyVehicle i : units) {
            if (num <= qty - 1) {
                //se toman las primeras unidades en la lista (son las mas veteranas) para atacar
                result.add(i);
            }
            num++;
        }

        return result;
    }

    @Override
    public ArrayList<AttackCommand> setAttackRemainingTurns(ArrayList<AttackCommand> commands) {
        ArrayList<AttackCommand> result = new ArrayList();

        for (AttackCommand a : commands) {
            if (a.getAttackTime() != 0) {
                a.setAttackTime(a.getAttackTime() - 1);
                result.add(a);
            } else {
                result.add(a);
            }
        }
        return result;

    }

    /**
     *
     * @param commands
     * @param target
     * @return el jugador objetivo luego de recibir daño
     */
    @Override
    public Player runInvasionPhase(ArrayList<AttackCommand> commands, Player target) {
        int squadDamage = 0;
        int specialistDamage = 0;
        int LAVDamage = 0;
        int heavyDamage = 0;
        int totalDamage = 0;

        Player result = target;
        //ComandCenter newCC = target.getCc();

        String building = "";

        for (AttackCommand a : commands) {
            if (a.getAttackTime() == 0) {
                switch (a.getTarget()) {
                    case AttackInteractions.COMMAND_CENTER:
                        building = "Centro de control";
                        break;
                    case AttackInteractions.FACTORY:
                        building = target.getPlayerBaseFactory().getName();
                        break;
                    case AttackInteractions.MARKET:
                        building = target.getPlayerBaseMarket().getName();
                        break;
                    case AttackInteractions.POWER_MINE:
                        building = target.getPlayerBasePowerMine().getName();
                        break;
                    case AttackInteractions.MILITARY_BUILDING:
                        building = target.getPlayerBaseMilitaryBuilding().getName();
                        break;
                }

                //procesando los escuadrones
                for (Squad s : a.getDeployedSquads()) {
                    //Solo las unidades con vida pueden atacar
                    if (s.getHitpoints() > 0) {
                        if (attackSuccessful(s.getSuccessRate())) {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "ha hecho " + s.getAttackPoints() + " de daño a un " + building);
                            squadDamage = squadDamage + s.getAttackPoints();
                        } else {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "fallo al momento de atacar al " + building);
                        }

                    }
                }

                //procesando los especialistas
                for (Specialist s : a.getDeployedSpecialist()) {
                    if (attackSuccessful(s.getSuccessRate())) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un" + s.getName() + "ha hecho " + s.getAttackPoints() + " de daño a un " + building);
                        specialistDamage = specialistDamage + s.getAttackPoints();
                    } else {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "fallo al momento de atacar al " + building);
                    }
                }

                //procesando los vehiculos livianos
                for (LightVehicle s : a.getDeployedLAV()) {
                    if (attackSuccessful(s.getSuccessRate())) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un" + s.getName() + "ha hecho " + s.getAttackPoints() + " de daño a un " + building);
                        LAVDamage = LAVDamage + s.getAttackPoints();
                    } else {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "fallo al momento de atacar al " + building);
                    }
                }

                //procesando los vehiculos pesados
                for (HeavyVehicle s : a.getDeployedHeavy()) {
                    if (attackSuccessful(s.getSuccessRate())) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un" + s.getName() + "ha hecho " + s.getAttackPoints() + " de daño a un " + building);
                        heavyDamage = heavyDamage + s.getAttackPoints();
                    } else {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "fallo al momento de atacar al " + building);
                    }
                }

                //una vez procesados los ataques se obtiene el daño total
                totalDamage = squadDamage + specialistDamage + LAVDamage + heavyDamage;

                int tmpDamage = 0; //sirve para saber si no ha sobrado daño despues de un ataque

                //Procesando el daño por hacer
                switch (a.getTarget()) {
                    case AttackInteractions.COMMAND_CENTER:
                        result.getCc().setHitpoints(result.getCc().getHitpoints() - totalDamage);
                        break;
                    case AttackInteractions.FACTORY:
                        for (Factory b : result.getFactories()) {
                            if (totalDamage >= 0) {
                                tmpDamage = b.getHitpoints() - totalDamage;
                                b.setHitpoints(b.getHitpoints() - totalDamage);

                                if (tmpDamage < 0) {
                                    totalDamage = tmpDamage * (-1);
                                }
                            }
                        }
                        break;
                    case AttackInteractions.MARKET:
                        for (Market b : result.getMarkets()) {
                            if (totalDamage >= 0) {
                                tmpDamage = b.getHitpoints() - totalDamage;
                                b.setHitpoints(b.getHitpoints() - totalDamage);

                                if (tmpDamage < 0) {
                                    totalDamage = tmpDamage * (-1);
                                }
                            }
                        }
                        break;
                    case AttackInteractions.POWER_MINE:
                        for (PowerMine b : result.getMines()) {
                            if (totalDamage >= 0) {
                                tmpDamage = b.getHitpoints() - totalDamage;
                                b.setHitpoints(b.getHitpoints() - totalDamage);

                                if (tmpDamage < 0) {
                                    totalDamage = tmpDamage * (-1);
                                }
                            }
                        }
                        break;
                    case AttackInteractions.MILITARY_BUILDING:
                        building = target.getPlayerBaseMilitaryBuilding().getName();
                        break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean attackSuccessful(int successRate) {
        int chance = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        if (chance > successRate) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ArrayList<AttackCommand> resistAttack(Player defender, ArrayList<AttackCommand> attackers) {
        int totalDamage = 0;
        int specialistDamage = 0;
        int LAVDamage = 0;
        int heavyDamage = 0;
        int squadDamage = 0;
        AttackCommand nuevo = new AttackCommand();

        ArrayList<Specialist> newSpec = new ArrayList();
        ArrayList<Squad> newSquad = new ArrayList();
        ArrayList<LightVehicle> newLAV = new ArrayList();
        ArrayList<HeavyVehicle> newHeavy = new ArrayList();

        System.out.println("**LLAMANDO A TODAS LAS UNIDADES A DEFENDER LA BASE**");
        ArrayList<AttackCommand> result = new ArrayList();

        if (!(attackers.isEmpty())) {
            for (AttackCommand a : attackers) {
                //procesando los escuadrones
                for (Squad s : defender.getSquads()) {
                    //Solo las unidades con vida pueden atacar
                    if (s.getHitpoints() > 0) {
                        if (attackSuccessful(s.getSuccessRate())) {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "ha hecho " + s.getAttackPoints() + " de daño a un enemigo");
                            squadDamage = squadDamage + s.getAttackPoints();
                        } else {
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "fallo al momento de defender");
                        }

                    }
                }

                //procesando los especialistas
                for (Specialist s : a.getDeployedSpecialist()) {
                    if (attackSuccessful(s.getSuccessRate())) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "ha hecho " + s.getAttackPoints() + " de daño a un enemigo");
                        specialistDamage = squadDamage + s.getAttackPoints();
                    } else {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "fallo al momento de defender");
                    }
                }

                //procesando los vehiculos livianos
                for (LightVehicle s : a.getDeployedLAV()) {
                    if (attackSuccessful(s.getSuccessRate())) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "ha hecho " + s.getAttackPoints() + " de daño a un enemigo");
                        LAVDamage = squadDamage + s.getAttackPoints();
                    } else {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "fallo al momento de defender");
                    }
                }

                //procesando los vehiculos pesados
                for (HeavyVehicle s : a.getDeployedHeavy()) {
                    if (attackSuccessful(s.getSuccessRate())) {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "ha hecho " + s.getAttackPoints() + " de daño a un enemigo");
                        heavyDamage = squadDamage + s.getAttackPoints();
                    } else {
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Un " + s.getName() + "fallo al momento de defender");
                    }
                }

            }

            totalDamage = squadDamage + specialistDamage + LAVDamage + heavyDamage;
            int tmpDamage = 0; //sirve para saber si no ha sobrado daño despues de un ataque

            for (AttackCommand a : attackers) {

                nuevo = new AttackCommand();
                nuevo.setAttackTime(0);
                nuevo.setTarget(a.getTarget());

                //los especialistas son prioridad
                for (Specialist s : a.getDeployedSpecialist()) {
                    if (s.getHitpoints() > 0) {
                        tmpDamage = s.getHitpoints() - totalDamage;
                        s.setHitpoints(s.getHitpoints() - totalDamage);

                        newSpec.add(s);

                        if (tmpDamage < 0) {
                            totalDamage = tmpDamage * (-1);
                        }
                    }
                }

                //segunda prioridad vehiculos pesados
                for (HeavyVehicle s : a.getDeployedHeavy()) {
                    if (s.getHitpoints() > 0) {
                        tmpDamage = s.getHitpoints() - totalDamage;
                        s.setHitpoints(s.getHitpoints() - totalDamage);

                        newHeavy.add(s);

                        if (tmpDamage < 0) {
                            totalDamage = tmpDamage * (-1);
                        }
                    }
                }

                //tercera prioridad vehiculos livianos
                for (LightVehicle s : a.getDeployedLAV()) {
                    if (s.getHitpoints() > 0) {
                        tmpDamage = s.getHitpoints() - totalDamage;
                        s.setHitpoints(s.getHitpoints() - totalDamage);

                        newLAV.add(s);

                        if (tmpDamage < 0) {
                            totalDamage = tmpDamage * (-1);
                        }
                    }
                }

                //cuarta prioridad escuadrones
                for (Squad s : a.getDeployedSquads()) {
                    if (s.getHitpoints() > 0) {
                        tmpDamage = s.getHitpoints() - totalDamage;
                        s.setHitpoints(s.getHitpoints() - totalDamage);

                        newSquad.add(s);

                        if (tmpDamage < 0) {
                            totalDamage = tmpDamage * (-1);
                        }
                    }
                }

                nuevo.setDeployedSpecialist(newSpec);
                nuevo.setDeployedHeavy(newHeavy);
                nuevo.setDeployedLAV(newLAV);
                nuevo.setDeployedSquads(newSquad);
                result.add(nuevo);
            }
        } else {
            System.out.println("No hay unidades atacando");
        }
        return result;
    }

}
