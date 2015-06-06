package com.company.data;

import java.io.*;
import java.util.Properties;

/**
 * Created by Andrea on 06/06/2015.
 */
public class Credential {
    private float puntiGoalAttaccante;
    private float puntiGoalCentrocampista;
    private float puntiGoalDifensore;
    private float puntiGoalPortiere;
    private float puntiAutogoal;
    private float puntiAssist;
    private float puntiAmmonimento;
    private float puntiEspulzione;
    private float puntiRigoreSubito;
    private float puntiRigoreParato;
    private float puntiRigoreTrasformato;

    public Credential(){
        Properties prop=new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the property value and print it out
        puntiGoalAttaccante = new Float(prop.getProperty("puntiGoalAttaccante"));
        puntiGoalCentrocampista = new Float(prop.getProperty("puntiGoalCentrocampista"));
        puntiGoalDifensore  = new Float(prop.getProperty("puntiGoalDifensore"));
        puntiGoalPortiere = new Float(prop.getProperty("puntiGoalPortiere"));
        puntiAutogoal = new Float(prop.getProperty("puntiAutogoal"));
        puntiAssist = new Float(prop.getProperty("puntiAssist"));
        puntiAmmonimento = new Float(prop.getProperty("puntiAmmonimento"));
        puntiEspulzione = new Float(prop.getProperty("puntiEspulzione"));
        puntiRigoreSubito = new Float(prop.getProperty("puntiRigoreSubito"));
        puntiRigoreParato = new Float(prop.getProperty("puntiRigoreParato"));
        puntiRigoreTrasformato = new Float(prop.getProperty("puntiRigoreTrasformato"));

    }

    public float getPuntiGoalAttaccante() {
        return puntiGoalAttaccante;
    }

    public float getPuntiGoalCentrocampista() {
        return puntiGoalCentrocampista;
    }

    public float getPuntiGoalDifensore() {
        return puntiGoalDifensore;
    }

    public float getPuntiGoalPortiere() {
        return puntiGoalPortiere;
    }

    public float getPuntiAutogoal() {
        return puntiAutogoal;
    }

    public float getPuntiAssist() {
        return puntiAssist;
    }

    public float getPuntiAmmonimento() {
        return puntiAmmonimento;
    }

    public float getPuntiEspulzione() {
        return puntiEspulzione;
    }

    public float getPuntiRigoreSubito() {
        return puntiRigoreSubito;
    }

    public float getPuntiRigoreParato() {
        return puntiRigoreParato;
    }

    public float getPuntiRigoreTrasformato() {
        return puntiRigoreTrasformato;
    }
}
