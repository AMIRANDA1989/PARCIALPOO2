/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amiranda.parcial2.classes.core;

import com.amiranda.parcial2.classes.functional.buildings.ComandCenter;
import com.amiranda.parcial2.classes.functional.buildings.Factory;
import com.amiranda.parcial2.classes.functional.buildings.Market;
import com.amiranda.parcial2.classes.functional.buildings.MilitaryBuilding;
import com.amiranda.parcial2.classes.functional.buildings.PowerMine;
import com.amiranda.parcial2.classes.functional.units.Specialist;
import com.amiranda.parcial2.classes.functional.units.Squad;
import java.util.ArrayList;

/**
 *
 * @author allan
 */
public class Player {
    private String name;  //nombre del jugador
    private int raceCode; //La raza del jugador se manejara por codigo dentro de la clase del jugador
    
    //Edificios que tiene el jugador
    private Factory playerBaseFactory; //fabrica base del jugador
    private Market playerBaseMarket; //mercado base
    private PowerMine playerBasePowerMine; //mina base
    private MilitaryBuilding playerBaseMilitaryBuilding; //base...em...base
    private ComandCenter cc; //centro de mando del jugador
    private ArrayList<Factory> factories = new ArrayList();
    private ArrayList<Market> markets = new ArrayList();
    private ArrayList<PowerMine> mines = new ArrayList();
    private ArrayList<MilitaryBuilding> mbs = new ArrayList();
    
    //Unidades que tiene el jugador
    private Squad playerBaseSquad; //Unidad básica que manejará el jugador
    private Specialist playerBaseSpecialist; //especialista básico que manejará el jugador
    private ArrayList<Squad> squads = new ArrayList();
    private ArrayList<Specialist> specialist = new ArrayList();  
    private ArrayList<Squad> deployedSquads = new ArrayList();
    private ArrayList<Specialist> deployedSpecialist = new ArrayList();
    

    public Player(String name, int raceCode, ComandCenter cc) {
        this.name = name;
        this.raceCode = raceCode;
        this.cc = cc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRaceCode() {
        return raceCode;
    }

    public void setRaceCode(int raceCode) {
        this.raceCode = raceCode;
    }

    public ComandCenter getCc() {
        return cc;
    }

    public void setCc(ComandCenter cc) {
        this.cc = cc;
    }

    public ArrayList<Factory> getFactories() {
        return factories;
    }

    public void setFactories(ArrayList<Factory> factories) {
        this.factories = factories;
    }

    public ArrayList<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(ArrayList<Market> markets) {
        this.markets = markets;
    }

    public ArrayList<PowerMine> getMines() {
        return mines;
    }

    public void setMines(ArrayList<PowerMine> mines) {
        this.mines = mines;
    }

    public ArrayList<MilitaryBuilding> getMbs() {
        return mbs;
    }

    public void setMbs(ArrayList<MilitaryBuilding> mbs) {
        this.mbs = mbs;
    }

    public ArrayList<Squad> getSquads() {
        return squads;
    }

    public void setSquads(ArrayList<Squad> squads) {
        this.squads = squads;
    }

    public ArrayList<Specialist> getSpecialist() {
        return specialist;
    }

    public void setSpecialist(ArrayList<Specialist> specialist) {
        this.specialist = specialist;
    }

    public ArrayList<Squad> getDeployedSquads() {
        return deployedSquads;
    }

    public void setDeployedSquads(ArrayList<Squad> deployedSquads) {
        this.deployedSquads = deployedSquads;
    }

    public ArrayList<Specialist> getDeployedSpecialist() {
        return deployedSpecialist;
    }

    public void setDeployedSpecialist(ArrayList<Specialist> deployedSpecialist) {
        this.deployedSpecialist = deployedSpecialist;
    }

    public Squad getPlayerBaseSquad() {
        return playerBaseSquad;
    }

    public void setPlayerBaseSquad(Squad playerBaseSquad) {
        this.playerBaseSquad = playerBaseSquad;
    }

    public Specialist getPlayerBaseSpecialist() {
        return playerBaseSpecialist;
    }

    public void setPlayerBaseSpecialist(Specialist playerBaseSpecialist) {
        this.playerBaseSpecialist = playerBaseSpecialist;
    }

    public Factory getPlayerBaseFactory() {
        return playerBaseFactory;
    }

    public void setPlayerBaseFactory(Factory playerBaseFactory) {
        this.playerBaseFactory = playerBaseFactory;
    }

    public Market getPlayerBaseMarket() {
        return playerBaseMarket;
    }

    public void setPlayerBaseMarket(Market playerBaseMarket) {
        this.playerBaseMarket = playerBaseMarket;
    }

    public PowerMine getPlayerBasePowerMine() {
        return playerBasePowerMine;
    }

    public void setPlayerBasePowerMine(PowerMine playerBasePowerMine) {
        this.playerBasePowerMine = playerBasePowerMine;
    }

    public MilitaryBuilding getPlayerBaseMilitaryBuilding() {
        return playerBaseMilitaryBuilding;
    }

    public void setPlayerBaseMilitaryBuilding(MilitaryBuilding playerBaseMilitaryBuilding) {
        this.playerBaseMilitaryBuilding = playerBaseMilitaryBuilding;
    }
    
    
}