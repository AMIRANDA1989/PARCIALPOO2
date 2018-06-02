/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.engine;

import com.amiranda.parcial2.classes.functional.races.Invaders;
import com.amiranda.parcial2.classes.functional.races.Jupiter;
import com.amiranda.parcial2.classes.functional.races.NWO;

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
    String BASE_SQUAD_NAME = "(Escuadron)";
    int BASE_SQUAD_HITPOINTS = 100; //Vida base de los escuadrones
    int BASE_SQUAD_ATTACKPOINTS = 50; //puntos de ataque base de los escuadrones
    int BASE_SQUAD_SUCCESS_RATE = 75; //probabilidad de exito de ataque de los escuadrones
    int BASE_SQUAD_RM_PRICE = 150; //costo de  materias primas de los escuadrones
    int BASE_SQUAD_MONEY_PRICE = 100; //costo de dinero de los escuadrones
    int BASE_SQUAD_BUILD_TIME = 3; //tiempo base de construccion de los escuadrones
    
    //valores basicos para Especialistas
    String BASE_SPEC_NAME = "(Especialista)";
    int BASE_SPEC_HITPOINTS = 500; //Vida base de los especialistas
    int BASE_SPEC_ATTACKPOINTS = 100; //puntos de ataque base de los especialistas
    int BASE_SPEC_SUCCESS_RATE = 80; //probabilidad de exito de ataque de los especialistas
    int BASE_SPEC_RM_PRICE = 300; //costo de  materias primas de los especialistas
    int BASE_SPEC_MONEY_PRICE = 1000; //costo de dinero de los especialistas
    int BASE_SPEC_BUILD_TIME = 4; // Costo de dinero de los especialistas
    
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

    public Engine() {
        this.NEW_WORLD_ORDER = new NWO(1, "Nuevo Orden Mundial");
        this.JUPITER_GROUP = new Jupiter(2,"Grupo Jupiter");
        this.THE_INVADERS = new Invaders(2,"Los Invasores");
    }

    
}
