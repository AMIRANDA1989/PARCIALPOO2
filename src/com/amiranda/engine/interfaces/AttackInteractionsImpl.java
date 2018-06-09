/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine.interfaces;

import com.amiranda.parcial2.classes.core.AttackCommand;
import com.amiranda.parcial2.classes.core.Player;
import com.amiranda.parcial2.classes.functional.units.HeavyVehicle;
import com.amiranda.parcial2.classes.functional.units.LightVehicle;
import com.amiranda.parcial2.classes.functional.units.Specialist;
import com.amiranda.parcial2.classes.functional.units.Squad;
import java.util.ArrayList;
import java.util.Map;

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

            case 3: //atacar minas del enemigo
                if (targetPlayer.getMines().isEmpty()) {
                    userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "Tu oponente no tiene minas en pie!");
                } else {
                    targetBuilding = AttackInteractions.POWER_MINE;
                }
                break;

            case 4: //atacar bases militares del enemigo
                if (targetPlayer.getMbs().isEmpty()) {
                    userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "Tu oponente no tiene bases militares en pie!");
                } else {
                    targetBuilding = AttackInteractions.MILITARY_BUILDING;
                }
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
                        if (attPlayer.getSquads().isEmpty()) {
                            userInteractions.showMessage(UserInteractions.ERROR_MESSAGE, "No tienes especialistas disponibles!");
                        } else {
                            attackingUnits = userInteractions.pickAttackingUnits(attPlayer.getSpecialist().size());

                            if (attackingUnits != -1) {
                                remainingSpecialist = this.deploySpecialist1(attPlayer.getSpecialist(), attackingUnits);
                                attSpecialist = this.deploySpecialist2(attPlayer.getSpecialist(), attackingUnits);
                            }
                        }
                        break;
                        
                    case 3: //usar LAV para atacar
                        if (attPlayer.getSquads().isEmpty()) {
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
                        if (attPlayer.getSquads().isEmpty()) {
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
                        if(userInteractions.confirmAction()){
                            command.setTarget(targetBuilding);
                            command.setDeployedSquads(attSquads);
                            command.setDeployedLAV(attLAVs);
                            command.setDeployedHeavy(attHeavies);
                            
                            attPlayer.setSquads(remainingSquads);
                            attPlayer.setSpecialist(remainingSpecialist);
                            attPlayer.setLAVs(remainingLAVs);
                            attPlayer.setHeavies(remainingHeavies);
                            attPlayer.getAttackCommands().add(command);
                            
                            userInteractions.showMessage(UserInteractions.WARNING_MESSAGE, "El ataque tendrá efecto en 2 turnos");
                            return attPlayer;
                        }else{
                            userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Orden Cancelada");
                            return attackingPlayer;
                        }
                        
                    case 6:
                        userInteractions.showMessage(UserInteractions.INFO_MESSAGE, "Orden Cancelada");
                        return attackingPlayer;
                        
                }
            }
        }else{
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
        ArrayList<Specialist> result = new ArrayList();
        for (Specialist i : units) {
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
    public ArrayList<Specialist> deploySpecialist2(ArrayList<Specialist> units, int qty) {
        int num = 0;
        ArrayList<Specialist> result = new ArrayList();
        for (Specialist i : units) {
            if (num <= qty - 1) {
                //se toman las primeras unidades en la lista (son las mas veteranas) para atacar
                result.add(i);
            }
            num++;
        }

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

}
