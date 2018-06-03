/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine;

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
import com.amiranda.parcial2.classes.functional.units.Squad;

/**
 *
 * @author allan
 */
public class Engine {

    //Valores basicos para centro de mando
    int BASE_CC_RM_CAPACITY = 10000; //capacidad base de materias primas para el centro de mando
    int BASE_CC_ENERGY_CAPACITY = 5000; //capacidad base de energia para el centro de mando
    int BASE_CC_MONEY_CAPACITY = 3000; //capacidad base de dinero para el centro de mando
    int BASE_CC_HITPOINTS = 10000; //vida basica del centro de mando
    int START_CC_RM = 1000; //valor inicial de materias primas para el centro de mando
    int START_CC_ENERGY = 500; //valor inicial de energia del centro de mando
    int START_CC_MONEY = 500; //valor inicial de dinero del centro de mando

    //Valores basicos para unidades
    final String BASE_SQUAD_NAME = "(Escuadron)";
    final int BASE_SQUAD_HITPOINTS = 100; //Vida base de los escuadrones
    final int BASE_SQUAD_ATTACKPOINTS = 50; //puntos de ataque base de los escuadrones
    final int BASE_SQUAD_SUCCESS_RATE = 75; //probabilidad de exito de ataque de los escuadrones
    final int BASE_SQUAD_RM_PRICE = 150; //costo de  materias primas de los escuadrones
    final int BASE_SQUAD_MONEY_PRICE = 100; //costo de dinero de los escuadrones
    final int BASE_SQUAD_ENERGY_PRICE = 100; //costo de dinero de los escuadrones
    final int BASE_SQUAD_BUILD_TIME = 3; //tiempo base de construccion de los escuadrones

    //valores basicos para Especialistas
    final String BASE_SPEC_NAME = "(Especialista)";
    final int BASE_SPEC_HITPOINTS = 500; //Vida base de los especialistas
    final int BASE_SPEC_ATTACKPOINTS = 100; //puntos de ataque base de los especialistas
    final int BASE_SPEC_SUCCESS_RATE = 80; //probabilidad de exito de ataque de los especialistas
    final int BASE_SPEC_RM_PRICE = 300; //costo de  materias primas de los especialistas
    final int BASE_SPEC_MONEY_PRICE = 1000; //costo de dinero de los especialistas
    final int BASE_SPEC_ENERGY_PRICE = 100; //costo de dinero de los escuadrones
    final int BASE_SPEC_BUILD_TIME = 4; // Costo de dinero de los especialistas

    //valores basicos para fabricas
    String BASE_FACTORY_NAME = "(Fabrica)";
    int BASE_FACTORY_HITPOINTS = 700; //Vida base de las fabricas
    int BASE_FACTORY_BUILD_TIME = 4; //cantidad de turnos de construccion de una fabrica
    int BASE_FACTORY_CAPACITY = 500; //Capacidad base de las fabricas
    int BASE_FACTORY_PRODUCTION = 100; //Capacidad de produccion de las fabricas por turno

    //valores basicos para Mercados
    String BASE_MARKET_NAME = "(Mercado)";
    int BASE_MARKET_HITPOINTS = 300; //Vida base de los mercados
    int BASE_MARKET_BUILD_TIME = 3; //cantidad de turnos de construccion de un mercado
    int BASE_MARKET_CAPACITY = 600; //Capacidad base de los Mercados
    int BASE_MARKET_PRODUCTION = 150; //Capacidad de produccion de las mercados por turno

    //valores basicos para Minas de Energia
    String BASE_PM_NAME = "(Mina de Energia)";
    int BASE_PM_HITPOINTS = 500; //Vida base de las minas
    int BASE_PM_BUILD_TIME = 4; //cantidad de turnos de construccion de las minas
    int BASE_PM_CAPACITY = 1000; //Capacidad base de las minas
    int BASE_PM_PRODUCTION = 100;//Capacidad de produccion de las minas 

    //valores basicos para Edificios Militares
    String BASE_MB_NAME = "(Edificio Militar)";
    int BASE_MB_HITPOINTS = 500; //Vida base de las minas
    int BASE_MB_BUILD_TIME = 4; //cantidad de turnos de construccion de las minas
    int BASE_MB_CAPACITY = 1000; //Capacidad base de las minas
    int BASE_MB_PRODUCTION = 100;//Capacidad de produccion de las minas 

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

    /*
    Constructor ENGINE
    Este se ejecuta siempre al declarar una engine en el main
     */
    public Engine() {
        this.NEW_WORLD_ORDER = new NWO(1, "Nuevo Orden Mundial");
        this.JUPITER_GROUP = new Jupiter(2, "Grupo Jupiter");
        this.THE_INVADERS = new Invaders(3, "Los Invasores");
        this.userInteraction = new UserInteractionsImpl();
        this.unitInteraction = new UnitInteractionsImpl();
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
                PLAYER_ONE.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Mech de Asalto" + " " + this.BASE_SPEC_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseFactory(new Factory("Planta de produccion " + this.BASE_FACTORY_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME),this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION));
                PLAYER_ONE.setPlayerBaseMarket(new Market("Centro de negocios" + this.BASE_MARKET_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME),this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION));
                PLAYER_ONE.setPlayerBasePowerMine(new PowerMine("Mina Industrial" + this.BASE_PM_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_PM_BUILD_TIME),this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION));
                PLAYER_ONE.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Laboratorio Militar" + this.BASE_MB_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_MB_BUILD_TIME)));
                break;
            case 2:
                PLAYER_ONE.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Falange" + " " + this.BASE_SQUAD_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Atlas" + " " + this.BASE_SPEC_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseFactory(new Factory("Fabrica clandestina " + this.BASE_FACTORY_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME),this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION));
                PLAYER_ONE.setPlayerBaseMarket(new Market("Mercado negro " + this.BASE_MARKET_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME),this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION));
                PLAYER_ONE.setPlayerBasePowerMine(new PowerMine("Reactor secreto" + this.BASE_PM_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_PM_BUILD_TIME),this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION));
                PLAYER_ONE.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Campo de entrenamiento" + this.BASE_MB_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.JUPITER_GROUP.buildingTimeModifier(this.BASE_MB_BUILD_TIME)));
                break;
            case 3:
                PLAYER_ONE.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Raptor" + " " + this.BASE_SQUAD_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Titan" + " " + this.BASE_SPEC_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_ONE.setPlayerBaseFactory(new Factory("Fabrica clandestina " + this.BASE_FACTORY_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME),this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION));
                PLAYER_ONE.setPlayerBaseMarket(new Market("Mercado negro " + this.BASE_MARKET_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME),this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION));
                PLAYER_ONE.setPlayerBasePowerMine(new PowerMine("Reactor secreto" + this.BASE_PM_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_PM_BUILD_TIME),this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION));
                PLAYER_ONE.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Campo de entrenamiento" + this.BASE_MB_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.THE_INVADERS.buildingTimeModifier(this.BASE_MB_BUILD_TIME)));
                break;
        }

        PLAYER_TWO = new Player(p2Name, p2Choice, new ComandCenter(this.BASE_CC_HITPOINTS, this.BASE_CC_RM_CAPACITY, this.BASE_CC_MONEY_CAPACITY, this.BASE_CC_ENERGY_CAPACITY, this.START_CC_RM, this.START_CC_MONEY, this.START_CC_ENERGY));
        //configurando las unidades y edificios del jugador 1
        switch (p2Choice) {
            case 1:
                PLAYER_TWO.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Division de Asalto" + " " + this.BASE_SQUAD_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Mech de Asalto" + " " + this.BASE_SPEC_NAME, this.NEW_WORLD_ORDER.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.NEW_WORLD_ORDER.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.NEW_WORLD_ORDER.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.NEW_WORLD_ORDER.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.NEW_WORLD_ORDER.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseFactory(new Factory("Planta de produccion " + this.BASE_FACTORY_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME),this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION));
                PLAYER_TWO.setPlayerBaseMarket(new Market("Centro de negocios" + this.BASE_MARKET_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME),this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION));
                PLAYER_TWO.setPlayerBasePowerMine(new PowerMine("Mina Industrial" + this.BASE_PM_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_PM_BUILD_TIME),this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION));
                PLAYER_TWO.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Laboratorio Militar" + this.BASE_MB_NAME, this.NEW_WORLD_ORDER.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.NEW_WORLD_ORDER.buildingTimeModifier(this.BASE_MB_BUILD_TIME)));
                break;
            case 2:
                PLAYER_TWO.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Falange" + " " + this.BASE_SQUAD_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Atlas" + " " + this.BASE_SPEC_NAME, this.JUPITER_GROUP.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.JUPITER_GROUP.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.JUPITER_GROUP.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.JUPITER_GROUP.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.JUPITER_GROUP.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseFactory(new Factory("Fabrica clandestina " + this.BASE_FACTORY_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME),this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION));
                PLAYER_TWO.setPlayerBaseMarket(new Market("Mercado negro " + this.BASE_MARKET_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME),this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION));
                PLAYER_TWO.setPlayerBasePowerMine(new PowerMine("Reactor secreto" + this.BASE_PM_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.JUPITER_GROUP.buildingTimeModifier(this.BASE_PM_BUILD_TIME),this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION));
                PLAYER_TWO.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Campo de entrenamiento" + this.BASE_MB_NAME, this.JUPITER_GROUP.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.JUPITER_GROUP.buildingTimeModifier(this.BASE_MB_BUILD_TIME)));
                break;
            case 3:
                PLAYER_TWO.setPlayerBaseSquad(unitInteraction.setupPlayerSquad("Raptor" + " " + this.BASE_SQUAD_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_SQUAD_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_SQUAD_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_SQUAD_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_SQUAD_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_SQUAD_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_SQUAD_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseSpecialist(unitInteraction.setupPlayerSpecialist("Titan" + " " + this.BASE_SPEC_NAME, this.THE_INVADERS.soldierHitpointModifier(this.BASE_SPEC_HITPOINTS), this.THE_INVADERS.soldierDamageModifier(this.BASE_SPEC_ATTACKPOINTS), this.THE_INVADERS.soldierTimeModifier(this.BASE_SPEC_BUILD_TIME), this.THE_INVADERS.successRateModifier(this.BASE_SPEC_SUCCESS_RATE), this.THE_INVADERS.resourcePriceModifier(this.BASE_SPEC_RM_PRICE), 0, this.THE_INVADERS.resourcePriceModifier(this.BASE_SPEC_MONEY_PRICE)));
                PLAYER_TWO.setPlayerBaseFactory(new Factory("Fabrica clandestina " + this.BASE_FACTORY_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_FACTORY_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_FACTORY_BUILD_TIME),this.BASE_FACTORY_CAPACITY, this.BASE_FACTORY_PRODUCTION));
                PLAYER_TWO.setPlayerBaseMarket(new Market("Mercado negro " + this.BASE_MARKET_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_MARKET_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_MARKET_BUILD_TIME),this.BASE_MARKET_CAPACITY, this.BASE_MARKET_PRODUCTION));
                PLAYER_TWO.setPlayerBasePowerMine(new PowerMine("Reactor secreto" + this.BASE_PM_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_PM_HITPOINTS), this.THE_INVADERS.buildingTimeModifier(this.BASE_PM_BUILD_TIME),this.BASE_PM_CAPACITY, this.BASE_PM_PRODUCTION));
                PLAYER_TWO.setPlayerBaseMilitaryBuilding(new MilitaryBuilding("Campo de entrenamiento" + this.BASE_MB_NAME, this.THE_INVADERS.buildingHitpointModifier(this.BASE_MB_HITPOINTS), this.BASE_MB_CAPACITY, this.THE_INVADERS.buildingTimeModifier(this.BASE_MB_BUILD_TIME)));
                break;
        }
    }

    /*
    Metodo initEngine
    Sirve para inicializar el juego, este lleva toda la logica y la secuencia
    del juego
     */
    public void initEngine() {
        /*
        FASE INICIAL
        La fase inicial consta del registro de los jugadores al sistema
        este es una parte separada del "flow" del juego, ya que cuenta como
        una configuracion preliminar del juego
         */
        setupPlayers();
    }

}
