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

/**
 *
 * @author allan
 */
public interface AttackInteractions {
    public final int CANCEL_ATTACK = -1;
    public final int COMMAND_CENTER = 1;
    public final int FACTORY = 2;
    public final int MARKET = 3;
    public final int POWER_MINE = 4;
    public final int MILITARY_BUILDING = 5;
    
   public Player attackOperations(Player attackingPlayer, Player targetPlayer); 
   
   /*La fase uno de cada metodo remueve las unidades atacantes de las unidades disponibles
   mientras que la fase 2 agrega a la orden de ataque*/
   public ArrayList<Squad> deploySquad1(ArrayList<Squad> units, int qty);
   public ArrayList<Squad> deploySquad2(ArrayList<Squad> units, int qty);
   public ArrayList<Specialist> deploySpecialist1(ArrayList<Specialist> units, int qty);
   public ArrayList<Specialist> deploySpecialist2(ArrayList<Specialist> units, int qty);
   public ArrayList<LightVehicle> deployLAV1(ArrayList<LightVehicle> units, int qty);
   public ArrayList<LightVehicle> deployLAV2(ArrayList<LightVehicle> units, int qty);
   public ArrayList<HeavyVehicle> deployHeavy1(ArrayList<HeavyVehicle> units, int qty);
   public ArrayList<HeavyVehicle> deployHeavy2(ArrayList<HeavyVehicle> units, int qty);
   
   public ArrayList<AttackCommand> setAttackRemainingTurns(ArrayList<AttackCommand> commands);
   public Player runInvasionPhase(ArrayList<AttackCommand> commands, Player target);
   
   /*Resolviendo ataque exitoso*/
   public boolean attackSuccessful(int successRate);
   
   //Ejecutando la defensa
   public ArrayList<AttackCommand> resistAttack(Player defender, ArrayList<AttackCommand> attackers);
}
