/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine;

import com.amiranda.engine.interfaces.AttackInteractions;
import com.amiranda.engine.interfaces.AttackInteractionsImpl;
import com.amiranda.engine.interfaces.BuildingInteractions;
import com.amiranda.engine.interfaces.BuildingInteractionsImpl;
import com.amiranda.engine.interfaces.UnitInteractions;
import com.amiranda.engine.interfaces.UnitInteractionsImpl;
import com.amiranda.engine.interfaces.UserInteractions;
import com.amiranda.engine.interfaces.UserInteractionsImpl;
import com.amiranda.parcial2.classes.core.Player;
import com.amiranda.parcial2.classes.functional.buildings.ComandCenter;
import com.amiranda.parcial2.classes.functional.buildings.Factory;
import com.amiranda.parcial2.classes.functional.buildings.Market;
import com.amiranda.parcial2.classes.functional.buildings.MilitaryBuilding;
import com.amiranda.parcial2.classes.functional.buildings.PowerMine;
import com.amiranda.parcial2.classes.functional.races.Invaders;
import com.amiranda.parcial2.classes.functional.races.Jupiter;
import com.amiranda.parcial2.classes.functional.races.NWO;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author allan
 */
public class Engine {

    //Variables del juego
    private int RONDA = 1; //Indica el turno que se está jugando
    private int TURNO = 0; //Indica quien esta jugando en este momento
    private ArrayList<Player> players = new ArrayList(); //orden de los jugadores
    private boolean RUNNING = true;

    //Valores basicos para centro de mando
    final int BASE_CC_RM_CAPACITY = 10000; //capacidad base de materias primas para el centro de mando
    final int BASE_CC_ENERGY_CAPACITY = 5000; //capacidad base de energia para el centro de mando
    final int BASE_CC_MONEY_CAPACITY = 3000; //capacidad base de dinero para el centro de mando
    final int BASE_CC_HITPOINTS = 100;//10000; //vida basica del centro de mando
    final int START_CC_RM = 1500; //valor inicial de materias primas para el centro de mando
    final int START_CC_ENERGY = 1200; //valor inicial de energia del centro de mando
    final int START_CC_MONEY = 1000; //valor inicial de dinero del centro de mando

    //Valores basicos para unidades
    final String BASE_SQUAD_NAME = "(Escuadron)";
    final int BASE_SQUAD_HITPOINTS = 100; //Vida base de los escuadrones
    final int BASE_SQUAD_ATTACKPOINTS = 30; //puntos de ataque base de los escuadrones
    final int BASE_SQUAD_SUCCESS_RATE = 60; //probabilidad de exito de ataque de los escuadrones
    final int BASE_SQUAD_RM_PRICE = 20; //costo de  materias primas de los escuadrones
    final int BASE_SQUAD_MONEY_PRICE = 20; //costo de dinero de los escuadrones
    final int BASE_SQUAD_ENERGY_PRICE = 20; //costo de dinero de los escuadrones
    final int BASE_SQUAD_BUILD_TIME = 2; //tiempo base de construccion de los escuadrones

    //valores basicos para Especialistas
    final String BASE_SPEC_NAME = "(Especialista)";
    final int BASE_SPEC_HITPOINTS = 700; //Vida base de los especialistas
    final int BASE_SPEC_ATTACKPOINTS = 200; //puntos de ataque base de los especialistas
    final int BASE_SPEC_SUCCESS_RATE = 70; //probabilidad de exito de ataque de los especialistas
    final int BASE_SPEC_RM_PRICE = 20; //costo de  materias primas de los especialistas
    final int BASE_SPEC_MONEY_PRICE = 20; //costo de dinero de los especialistas
    final int BASE_SPEC_ENERGY_PRICE = 20; //costo de dinero de los escuadrones
    final int BASE_SPEC_BUILD_TIME = 2; // Costo de dinero de los especialistas

    //valores basicos para LAVs
    final String BASE_LAV_NAME = "(Vehiculo liviano)";
    final int BASE_LAV_HITPOINTS = 500; //Vida base de los especialistas
    final int BASE_LAV_ATTACKPOINTS = 100; //puntos de ataque base de los especialistas
    final int BASE_LAV_SUCCESS_RATE = 60; //probabilidad de exito de ataque de los especialistas
    final int BASE_LAV_RM_PRICE = 20; //costo de  materias primas de los especialistas
    final int BASE_LAV_MONEY_PRICE = 20; //costo de dinero de los especialistas
    final int BASE_LAV_ENERGY_PRICE = 20; //costo de dinero de los escuadrones
    final int BASE_LAV_BUILD_TIME = 2; // Costo de dinero de los especialistas

    //valores basicos para vehiculos pesados
    final String BASE_HEAVY_NAME = "(Vehiculo Pesado)";
    final int BASE_HEAVY_HITPOINTS = 600; //Vida base de los especialistas
    final int BASE_HEAVY_ATTACKPOINTS = 150; //puntos de ataque base de los especialistas
    final int BASE_HEAVY_SUCCESS_RATE = 80; //probabilidad de exito de ataque de los especialistas
    final int BASE_HEAVY_RM_PRICE = 20; //costo de  materias primas de los especialistas
    final int BASE_HEAVY_MONEY_PRICE = 20; //costo de dinero de los especialistas
    final int BASE_HEAVY_ENERGY_PRICE = 20; //costo de dinero de los escuadrones
    final int BASE_HEAVY_BUILD_TIME = 2; // Costo de dinero de los especialistas

    //valores basicos para fabricas
    String BASE_FACTORY_NAME = "(Fabrica)";
    final int BASE_FACTORY_HITPOINTS = 700; //Vida base de las fabricas
    final int BASE_FACTORY_BUILD_TIME = 1; //cantidad de turnos de construccion de una fabrica
    final int BASE_FACTORY_CAPACITY = 500; //Capacidad base de las fabricas
    final int BASE_FACTORY_PRODUCTION = 100; //Capacidad de produccion de las fabricas por turno
    final int BASE_FACTORY_MONEY_PRICE = 500; //costo de dinero de los especialistas
    final int BASE_FACTORY_ENERGY_PRICE = 300; //costo de dinero de los escuadrones

    //valores basicos para Mercados
    final String BASE_MARKET_NAME = "(Mercado)";
    final int BASE_MARKET_HITPOINTS = 300; //Vida base de los mercados
    final int BASE_MARKET_BUILD_TIME = 1; //cantidad de turnos de construccion de un mercado
    final int BASE_MARKET_CAPACITY = 600; //Capacidad base de los Mercados
    final int BASE_MARKET_PRODUCTION = 150; //Capacidad de produccion de las mercados por turno
    final int BASE_MARKET_MONEY_PRICE = 600; //costo de dinero de los especialistas
    final int BASE_MARKET_ENERGY_PRICE = 100; //costo de dinero de los escuadrones

    //valores basicos para Minas de Energia
    final String BASE_PM_NAME = "(Mina de Energia)";
    final int BASE_PM_HITPOINTS = 500; //Vida base de las minas
    final int BASE_PM_BUILD_TIME = 1; //cantidad de turnos de construccion de las minas
    final int BASE_PM_CAPACITY = 1000; //Capacidad base de las minas
    final int BASE_PM_PRODUCTION = 100;//Capacidad de produccion de las minas
    final int BASE_PM_MONEY_PRICE = 800; //costo de dinero de los especialistas
    final int BASE_PM_ENERGY_PRICE = 300; //costo de dinero de los escuadrones

    //valores basicos para Edificios Militares
    final String BASE_MB_NAME = "(Edificio Militar)";
    final int BASE_MB_HITPOINTS = 500; //Vida base de las minas
    final int BASE_MB_BUILD_TIME = 1;//4; //cantidad de turnos de construccion de las minas
    final int BASE_MB_CAPACITY = 10; //Capacidad base de las minas
    //final int BASE_MB_PRODUCTION = 100;//Capacidad de produccion de las minas 
    final int BASE_MB_MONEY_PRICE = 10;//1200; //costo de dinero de los especialistas
    final int BASE_MB_ENERGY_PRICE = 10;//600; //costo de dinero de los escuadrones

    //Declarando Razas
    final NWO NEW_WORLD_ORDER;
    final Jupiter JUPITER_GROUP;
    final Invaders THE_INVADERS;

    //Declarando Jugadores
    Player PLAYER_ONE;
    Player PLAYER_TWO;

    //Declarando interfaces de interaccion
    UserInteractions userInteraction;
    UnitInteractions unitInteraction;
    BuildingInteractions buildingInteraction;
    AttackInteractions attackInteraction;

    /*
    Constructor ENGINE
    Este se ejecuta siempre al declarar una engine en el main
     */
    public Engine() {
        this.NEW_WORLD_ORDER = new NWO(1, "Nuevo Orden Mundial");
        this.JUPITER_GROUP = new Jupiter(2, "Grupo Jupiter");
        this.THE_INVADERS = new Invaders(3, "Los Invasores");

        try {
            this.userInteraction = new UserInteractionsImpl();
            this.unitInteraction = new UnitInteractionsImpl();
            this.buildingInteraction = new BuildingInteractionsImpl();
            this.attackInteraction = new AttackInteractionsImpl();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setupPlayers() {
        int p1Choice = 1, p2Choice = 1;
        String p1Name, p2Name;
        boolean confirmPlayer = false;

        while (!(confirmPlayer)) {
            p1Choice = userInteraction.getPlayerRaza(1);
            switch (p1Choice) {
                case 1:
                    this.NEW_WORLD_ORDER.getDescription();
                    break;
                case 2:
                    this.JUPITER_GROUP.getDescription();
                    break;
                case 3:
                    this.THE_INVADERS.getDescription();
                    break;
            }
            confirmPlayer = userInteraction.confirmAction();
        }

        p1Name = userInteraction.setPlayerName();

        confirmPlayer = false;

        while (!(confirmPlayer)) {
            p2Choice = userInteraction.getPlayerRaza(2);
            switch (p2Choice) {
                case 1:
                    this.NEW_WORLD_ORDER.getDescription();
                    break;
                case 2:
                    this.JUPITER_GROUP.getDescription();
                    break;
                case 3:
                    this.THE_INVADERS.getDescription();
                    break;
            }
            confirmPlayer = userInteraction.confirmAction();
        }

        p2Name = userInteraction.setPlayerName();

        //Inicializando a los jugadores
        PLAYER_ONE = new Player(p1Name, p1Choice, new ComandCenter(this.BASE_CC_HITPOINTS, this.BASE_CC_RM_CAPACITY, this.BASE_CC_MONEY_CAPACITY, this.BASE_CC_ENERGY_CAPACITY, this.START_CC_RM, this.START_CC_MONEY, this.START_CC_ENERGY));

        //configurando las unidades y edificios del jugador 1
        switch (p1Choice) {
            case 1:
                PLAYER_ONE.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Division de Asalto" + " " + this.BASE_SQUAD_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Mech Tipo 002" + " " + this.BASE_SPEC_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseFactory(new Factory("Planta de produccion " + this.BASE_FACTORY_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME), this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION, 0, this.BASE_FACTORY_MONEY_PRICE, this.BASE_FACTORY_ENERGY_PRICE));
                PLAYER_ONE.setPlayerBaseMarket(new Market("Centro de negocios" + this.BASE_MARKET_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME), this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION, 0, this.BASE_MARKET_MONEY_PRICE, this.BASE_MARKET_ENERGY_PRICE));
                PLAYER_ONE.setPlayerBasePowerMine(new PowerMine("Mina Industrial" + this.BASE_PM_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_PM_BUILD_TIME), this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION, 0, this.BASE_PM_MONEY_PRICE, this.BASE_PM_ENERGY_PRICE));
                PLAYER_ONE.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Laboratorio Militar" + this.BASE_MB_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_MB_BUILD_TIME), this.BASE_MB_MONEY_PRICE, this.BASE_MB_ENERGY_PRICE));

                PLAYER_ONE.setPlayerBaseLAV(unitInteraction.setupPlayerLAV("Infanteria acorazada" + " " + this.BASE_LAV_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_LAV_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_LAV_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_LAV_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_LAV_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_LAV_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_LAV_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseHeavy(unitInteraction.setupPlayerHeavy("Mech Tipo 001" + " " + this.BASE_HEAVY_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_HEAVY_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_HEAVY_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_HEAVY_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_HEAVY_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_HEAVY_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_HEAVY_MONEY_PRICE)));
                break;
            case 2:
                PLAYER_ONE.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Division Gladio" + " " + this.BASE_SQUAD_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Mech Tipo Atlas" + " " + this.BASE_SPEC_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseFactory(new Factory("Fabrica clandestina " + this.BASE_FACTORY_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME), this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION, 0, this.BASE_FACTORY_MONEY_PRICE, this.BASE_FACTORY_ENERGY_PRICE));
                PLAYER_ONE.setPlayerBaseMarket(new Market("Mercado negro " + this.BASE_MARKET_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME), this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION, 0, this.BASE_MARKET_MONEY_PRICE, this.BASE_MARKET_ENERGY_PRICE));
                PLAYER_ONE.setPlayerBasePowerMine(new PowerMine("Reactor secreto" + this.BASE_PM_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_PM_BUILD_TIME), this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION, 0, this.BASE_PM_MONEY_PRICE, this.BASE_PM_ENERGY_PRICE));
                PLAYER_ONE.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Campo de entrenamiento" + this.BASE_MB_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.JUPITER_GROUP.buildingTimeModifier(this.BASE_MB_BUILD_TIME), this.BASE_MB_MONEY_PRICE, this.BASE_MB_ENERGY_PRICE));

                PLAYER_ONE.setPlayerBaseLAV(unitInteraction.setupPlayerLAV("Tanque centurion" + " " + this.BASE_LAV_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_LAV_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_LAV_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_LAV_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_LAV_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_LAV_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_LAV_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseHeavy(unitInteraction.setupPlayerHeavy("Tanque Praetoriano" + " " + this.BASE_HEAVY_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_HEAVY_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_HEAVY_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_HEAVY_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_HEAVY_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_HEAVY_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_HEAVY_MONEY_PRICE)));
                break;
            case 3:
                PLAYER_ONE.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Enjambre de Guardianes" + " " + this.BASE_SQUAD_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Titan" + " " + this.BASE_SPEC_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseFactory(new Factory("Cultivo Subterraneo " + this.BASE_FACTORY_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME), this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION, 0, this.BASE_FACTORY_MONEY_PRICE, this.BASE_FACTORY_ENERGY_PRICE));
                PLAYER_ONE.setPlayerBaseMarket(new Market("Centro de acopio " + this.BASE_MARKET_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME), this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION, 0, this.BASE_MARKET_MONEY_PRICE, this.BASE_MARKET_ENERGY_PRICE));
                PLAYER_ONE.setPlayerBasePowerMine(new PowerMine("Geiser " + this.BASE_PM_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_PM_BUILD_TIME), this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION, 0, this.BASE_PM_MONEY_PRICE, this.BASE_PM_ENERGY_PRICE));
                PLAYER_ONE.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Nido " + this.BASE_MB_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.THE_INVADERS.buildingTimeModifier(this.BASE_MB_BUILD_TIME), this.BASE_MB_MONEY_PRICE, this.BASE_MB_ENERGY_PRICE));

                PLAYER_ONE.setPlayerBaseLAV(unitInteraction.setupPlayerLAV("Goliath" + " " + this.BASE_LAV_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_LAV_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_LAV_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_LAV_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_LAV_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_LAV_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_LAV_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseHeavy(unitInteraction.setupPlayerHeavy("Coloso" + " " + this.BASE_HEAVY_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_HEAVY_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_HEAVY_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_HEAVY_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_HEAVY_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_HEAVY_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_HEAVY_MONEY_PRICE)));
                break;
        }

        PLAYER_TWO = new Player(p2Name, p2Choice, new ComandCenter(this.BASE_CC_HITPOINTS, this.BASE_CC_RM_CAPACITY, this.BASE_CC_MONEY_CAPACITY, this.BASE_CC_ENERGY_CAPACITY, this.START_CC_RM, this.START_CC_MONEY, this.START_CC_ENERGY));
        //configurando las unidades y edificios del jugador 1
        switch (p2Choice) {
            case 1:
                PLAYER_TWO.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Division de Asalto" + " " + this.BASE_SQUAD_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Mech Tipo 002" + " " + this.BASE_SPEC_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseFactory(new Factory("Planta de produccion " + this.BASE_FACTORY_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME), this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION, 0, this.BASE_FACTORY_MONEY_PRICE, this.BASE_FACTORY_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBaseMarket(new Market("Centro de negocios" + this.BASE_MARKET_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME), this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION, 0, this.BASE_MARKET_MONEY_PRICE, this.BASE_MARKET_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBasePowerMine(new PowerMine("Mina Industrial" + this.BASE_PM_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_PM_BUILD_TIME), this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION, 0, this.BASE_PM_MONEY_PRICE, this.BASE_PM_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Laboratorio Militar" + this.BASE_MB_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_MB_BUILD_TIME), this.BASE_MB_MONEY_PRICE, this.BASE_MB_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBaseLAV(unitInteraction.setupPlayerLAV("Infanteria acorazada" + " " + this.BASE_LAV_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_LAV_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_LAV_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_LAV_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_LAV_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_LAV_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_LAV_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseHeavy(unitInteraction.setupPlayerHeavy("Mech Tipo 001" + " " + this.BASE_HEAVY_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_HEAVY_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_HEAVY_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_HEAVY_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_HEAVY_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_HEAVY_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_HEAVY_MONEY_PRICE)));
                break;
            case 2:
                PLAYER_TWO.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Division Gladio" + " " + this.BASE_SQUAD_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Mech Tipo Atlas" + " " + this.BASE_SPEC_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseFactory(new Factory("Fabrica clandestina " + this.BASE_FACTORY_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME), this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION, 0, this.BASE_FACTORY_MONEY_PRICE, this.BASE_FACTORY_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBaseMarket(new Market("Mercado negro " + this.BASE_MARKET_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME), this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION, 0, this.BASE_MARKET_MONEY_PRICE, this.BASE_MARKET_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBasePowerMine(new PowerMine("Reactor secreto" + this.BASE_PM_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_PM_BUILD_TIME), this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION, 0, this.BASE_PM_MONEY_PRICE, this.BASE_PM_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Campo de entrenamiento" + this.BASE_MB_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.JUPITER_GROUP.buildingTimeModifier(this.BASE_MB_BUILD_TIME), this.BASE_MB_MONEY_PRICE, this.BASE_MB_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBaseLAV(unitInteraction.setupPlayerLAV("Tanque centurion" + " " + this.BASE_LAV_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_LAV_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_LAV_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_LAV_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_LAV_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_LAV_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_LAV_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseHeavy(unitInteraction.setupPlayerHeavy("Tanque Praetoriano" + " " + this.BASE_HEAVY_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_HEAVY_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_HEAVY_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_HEAVY_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_HEAVY_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_HEAVY_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_HEAVY_MONEY_PRICE)));
                break;
            case 3:
                PLAYER_TWO.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Enjambre de Guardianes" + " " + this.BASE_SQUAD_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Titan" + " " + this.BASE_SPEC_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseFactory(new Factory("Cultivo Subterraneo " + this.BASE_FACTORY_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME), this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION, 0, this.BASE_FACTORY_MONEY_PRICE, this.BASE_FACTORY_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBaseMarket(new Market("Centro de acopio " + this.BASE_MARKET_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME), this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION, 0, this.BASE_MARKET_MONEY_PRICE, this.BASE_MARKET_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBasePowerMine(new PowerMine("Geiser " + this.BASE_PM_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_PM_BUILD_TIME), this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION, 0, this.BASE_PM_MONEY_PRICE, this.BASE_PM_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Nido " + this.BASE_MB_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.THE_INVADERS.buildingTimeModifier(this.BASE_MB_BUILD_TIME), this.BASE_MB_MONEY_PRICE, this.BASE_MB_ENERGY_PRICE));
                PLAYER_TWO.setPlayerBaseLAV(unitInteraction.setupPlayerLAV("Goliath" + " " + this.BASE_LAV_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_LAV_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_LAV_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_LAV_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_LAV_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_LAV_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_LAV_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseHeavy(unitInteraction.setupPlayerHeavy("Coloso" + " " + this.BASE_HEAVY_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_HEAVY_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_HEAVY_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_HEAVY_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_HEAVY_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_HEAVY_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_HEAVY_MONEY_PRICE)));
                break;
        }

        //Definiendo quien iniciara la partida
        this.TURNO = ThreadLocalRandom.current().nextInt(1, 2 + 1);

        if (TURNO == 1) {
            //El Jugador 1 comienza la partida
            this.players.add(PLAYER_ONE);
            this.players.add(PLAYER_TWO);
        } else {
            //El Jugador 2 comienza la partida
            this.players.add(PLAYER_TWO);
            this.players.add(PLAYER_ONE);
        }
        this.userInteraction.showMessage(this.userInteraction.INFO_MESSAGE, "El jugador " + TURNO + " iniciara la partida");

        this.TURNO = 0;
    }

    /*
    Metodo initEngine
    Sirve para inicializar el juego, este lleva toda la logica y la secuencia
    del juego
     */
    public void initEngine() {
        /*
        FASE DE CONFIGURACION
        La fase inicial consta del registro de los jugadores al sistema
        este es una parte separada del "flow" del juego, ya que cuenta como
        una configuracion preliminar del juego
         */
        //Configuracion de jugadores
        setupPlayers();
        int HPP1CC, HPP2CC, jugadorInactivo;
        boolean menu = true;
        /* FIN FASE DE CONFIGURACION*/
        ///////////////////////////////////////////////////////////////////////
        /*
        Inicio de la logica y ejecucion del juego
         */
        while (RUNNING) {
            try {

                /*
                FASE INICIAL
                La fase inicial consta en realizar las validaciones generales del
                turno                    
                 */
                //Validando la vida de los centros de mando de los jugadores
                HPP1CC = this.players.get(0).getCc().getHitpoints();
                HPP2CC = this.players.get(1).getCc().getHitpoints();

                if (HPP1CC <= 0 && HPP2CC <= 0) {
                    System.out.println("EMPATE");
                    RUNNING = false;
                    break;
                }
                if (HPP1CC <= 0) {
                    System.out.println("EL JUGADOR 2 HA SALIDO VICTORIOSO");
                    RUNNING = false;
                    break;
                }
                if (HPP2CC <= 0) {
                    System.out.println("EL JUGADOR 1 HA SALIDO VICTORIOSO");
                    RUNNING = false;
                    break;
                }

                System.out.println("");
                System.out.println("");
                System.out.println("-----------------------------------------");
                System.out.println("RONDA #" + this.RONDA);
                System.out.println("-----------------------------------------");

                for (Player activePlayer : this.players) {

                    /* FASE DE MANTENIMIENTO INICIAL
                    La fase de mantenimiento inicial consta de validar el estado de las construcciones del jugador,
                    que sus edificios de produccion generen recursos y que sus bases militares produzcan unidades
                     */
                    if (TURNO == 0) {
                        jugadorInactivo = 1;
                    } else {
                        jugadorInactivo = 0;
                    }
                     //vlidando vida
                     
                    if(activePlayer.getCc().getHitpoints() <= 0){
                        System.out.println("EL JUGADOR " + jugadorInactivo +" HA SALIDO VICTORIOSO");
                        RUNNING = false;
                        break;
                    }

                    System.out.println("");
                    System.out.println("");
                    //Validando la construccion de fabricas
                    activePlayer.setFactories(buildingInteraction.factoryQueueProduction(activePlayer.getFactConstruction(), activePlayer.getFactories(), activePlayer.getPlayerBaseFactory()));
                    //Limpiando las fabricas pendientes que ya fueron terminadas de construir
                    activePlayer.setFactConstruction(buildingInteraction.factoryCleanQueue(activePlayer.getFactConstruction()));
                    //validando la vida de las fabricas
                    activePlayer.setFactories(buildingInteraction.factoryMaintenance(activePlayer.getFactories()));

                    //Validando la construccion de mercados
                    activePlayer.setMarkets(buildingInteraction.marketQueueProduction(activePlayer.getMarketConstruction(), activePlayer.getMarkets(), activePlayer.getPlayerBaseMarket()));
                    //Limpiando los mercados pendientes que ya fueron terminadas de construir
                    activePlayer.setMarketConstruction(buildingInteraction.marketCleanQueue(activePlayer.getMarketConstruction()));
                    //validando la vida de los mercados
                    activePlayer.setMarkets(buildingInteraction.marketMaintenance(activePlayer.getMarkets()));

                    //Validando la construccion de minas
                    activePlayer.setMines(buildingInteraction.powerMineQueueProduction(activePlayer.getMineConstruction(), activePlayer.getMines(), activePlayer.getPlayerBasePowerMine()));
                    //Limpiando las minas pendientes que ya fueron terminadas de construir
                    activePlayer.setMineConstruction(buildingInteraction.powerMineCleanQueue(activePlayer.getMineConstruction()));
                    //validando la vida de las minas
                    activePlayer.setMines(buildingInteraction.powerMineMaintenance(activePlayer.getMines()));

                    //Validando la construccion de bases
                    activePlayer.setMbs(buildingInteraction.militaryBaseQueueProduction(activePlayer.getMbsConstruction(), activePlayer.getMbs(), activePlayer.getPlayerBaseMilitaryBuilding()));
                    //Limpiando las bases pendientes que ya fueron terminadas de construir
                    activePlayer.setMbsConstruction(buildingInteraction.militaryBaseCleanQueue(activePlayer.getMbsConstruction()));
                    //validando la vida de las bases
                    activePlayer.setMbs(buildingInteraction.militaryBaseMaintenance(activePlayer.getMbs()));

                    //Validando la construccion de unidades
                    activePlayer.setSquads(unitInteraction.squadQueueProduction(activePlayer.getSquadConstruction(), activePlayer.getSquads(), activePlayer.getPlayerBaseSquad()));
                    activePlayer.setSpecialist(unitInteraction.specialistQueueProduction(activePlayer.getSpecialistConstruction(), activePlayer.getSpecialist(), activePlayer.getPlayerBaseSpecialist()));
                    activePlayer.setLAVs(unitInteraction.lavQueueProduction(activePlayer.getLAVConstruction(), activePlayer.getLAVs(), activePlayer.getPlayerBaseLAV()));
                    activePlayer.setHeavies(unitInteraction.heavyQueueProduction(activePlayer.getHeavyConstruction(), activePlayer.getHeavies(), activePlayer.getPlayerBaseHeavy()));

                    //Limpiando las unidades pendientes
                    activePlayer.setSquadConstruction(unitInteraction.squadCleanQueue(activePlayer.getSquadConstruction()));
                    activePlayer.setSpecialistConstruction(unitInteraction.specialistCleanQueue(activePlayer.getSpecialistConstruction()));
                    activePlayer.setLAVConstruction(unitInteraction.lavCleanQueue(activePlayer.getLAVConstruction()));
                    activePlayer.setHeavyConstruction(unitInteraction.heavyCleanQueue(activePlayer.getHeavyConstruction()));

                    //validando la vida de las unidades activas
                    activePlayer.setSquads(unitInteraction.squadMaintenance(activePlayer.getSquads()));
                    activePlayer.setSpecialist(unitInteraction.specialistQueueMaintenance(activePlayer.getSpecialist()));
                    activePlayer.setLAVs(unitInteraction.lavMaintenance(activePlayer.getLAVs()));
                    activePlayer.setHeavies(unitInteraction.heavyQueueMaintenance(activePlayer.getHeavies()));

                    /*FASE DE GENERACION DE RECURSOS
                    Esta es la parte en la que los recursos se generan para llenarse en los edificios correspondientes
                     */
                    //Llenando Fabricas
                    activePlayer.setFactories(buildingInteraction.factoryFillResources(activePlayer.getFactories()));
                    //LLenando Mercados
                    activePlayer.setMarkets(buildingInteraction.marketFillResources(activePlayer.getMarkets()));
                    //Llenando Minas
                    activePlayer.setMines(buildingInteraction.powerMineFillResources(activePlayer.getMines()));

                    /*FASE DE INVASION
                    Es donde se resuelven todos los ataques pendientes y tambien se da mantenimiento a los ataques que estan por
                    llegarle al jugador
                     */
                    activePlayer.setAttackCommands(this.attackInteraction.setAttackRemainingTurns(activePlayer.getAttackCommands()));
                    activePlayer = this.attackInteraction.runInvasionPhase(this.players.get(jugadorInactivo).getAttackCommands(), activePlayer) ;
                    
                    /* FASE PRINCIPAL DEL JUGADOR
                    la fase principal es donde el jugador toma todas las decisiones que
                    afectaran a su turno y los turnos siguientes
                    aqui es donde puede construir, recolectar, entrenar soldado
                    y dar ordenes de ataque
                             */
                    while (menu) {
                        switch (this.userInteraction.mainMenu(activePlayer)) {
                            case 0: //Revisando recursos
                                System.out.println("MATERIA PRIMA: " + activePlayer.getCc().getRawMaterialQty() + " | ENERGIA : " + activePlayer.getCc().getEnergyQty() + " | DINERO : " + activePlayer.getCc().getMoneyQty());
                                break;

                            case 1: //Ingresando a menu de fabricas
                                activePlayer = this.buildingInteraction.factoryOperations(activePlayer);
                                break;

                            case 2: //Ingresando a menu de mercados
                                activePlayer = this.buildingInteraction.marketOperations(activePlayer);
                                break;

                            case 3: //Ingresando a menu de Minas
                                activePlayer = this.buildingInteraction.powerMineOperations(activePlayer);
                                break;

                            case 4://Ingresando a Menu de Bases Militares
                                activePlayer = this.buildingInteraction.militaryBaseOperations(activePlayer);
                                break;

                            case 5: //Ingresando a Menu de unidades
                                activePlayer = this.unitInteraction.unitOperations(activePlayer);
                                break;

                            case 6://Preparar ataque a oponente

                                activePlayer = this.attackInteraction.attackOperations(activePlayer, this.players.get(jugadorInactivo));
                                break;

                            case 7:
                                menu = false;
                                break;

                            case 8:
                                activePlayer = this.buildingInteraction.ccOperations(activePlayer);
                                break;
                                
                            case 9:
                                menu = false;
                                break;

                        }
                    }

                    /*
                    FASE DE MANTENIMIENTO FINAL
                    Se resuelve todo lo pendiente relacionado con las
                    colas de produccion, evaluacion de vida de unidades y manejo de capacidad de unidades
                     */
                    //Ralizando pasos de construccion de los edificios que estan en cola
                    this.players.get(TURNO).setFactConstruction(this.buildingInteraction.factoryQueueMaintenance(activePlayer.getFactConstruction()));
                    this.players.get(TURNO).setMarketConstruction(this.buildingInteraction.marketQueueMaintenance(activePlayer.getMarketConstruction()));
                    this.players.get(TURNO).setMineConstruction(this.buildingInteraction.powerMineQueueMaintenance(activePlayer.getMineConstruction()));
                    this.players.get(TURNO).setMbsConstruction(this.buildingInteraction.militaryBaseQueueMaintenance(activePlayer.getMbsConstruction()));

                    //realizando pasos de construccion de unidades que estan en cola
                    this.players.get(TURNO).setSquadConstruction(this.unitInteraction.squadQueueMaintenance(activePlayer.getSquadConstruction()));
                    this.players.get(TURNO).setSpecialistConstruction(this.unitInteraction.specialistQueueMaintenance(activePlayer.getSpecialistConstruction()));
                    this.players.get(TURNO).setLAVConstruction(this.unitInteraction.lavQueueMaintenance(activePlayer.getLAVConstruction()));
                    this.players.get(TURNO).setHeavyConstruction(this.unitInteraction.heavyQueueMaintenance(activePlayer.getHeavyConstruction()));

                    //validando la vida de las unidades desplegadas
                    activePlayer.setSquads(unitInteraction.squadMaintenance(activePlayer.getDeployedSquads()));
                    activePlayer.setSpecialist(unitInteraction.specialistQueueMaintenance(activePlayer.getDeployedSpecialist()));

                    //Realizando ajustes a la capacidad actual de unidades
                    this.players.get(TURNO).getCc().setUnitCapacity(this.buildingInteraction.militaryBaseCurrentCapacity(this.players.get(TURNO).getMbs(), (this.players.get(TURNO).getSquads().size() + this.players.get(TURNO).getSpecialist().size() + this.players.get(TURNO).getLAVs().size() + this.players.get(TURNO).getHeavies().size()), 0));

                    //Realizando ajustes finales para iniciar un nuevo turno
                    menu = true;
                    if (TURNO == 0) {
                        TURNO = 1;
                    } else {
                        TURNO = 0;
                    }
                }

                this.RONDA++;
            } catch (Exception e) {
                this.userInteraction.showMessage(UserInteractions.ERROR_MESSAGE, e.getMessage());
                e.printStackTrace();
            }
        }

    }

}
