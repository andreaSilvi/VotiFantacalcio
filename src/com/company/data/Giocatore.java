package com.company.data;

import org.w3c.dom.NodeList;

/**
 * Created by Andrea on 01/06/2015.
 */
public class Giocatore {
    private int id;
    private String cognome;
    private String squadra;
    private String ruolo;
    private float[] voti;
    private Credential c;

    /*
    CONTENUTO VETTORE

    private float gV;
    private float gfV;
    private float gsV;
    private float autV;
    private float assV;
    private float csR;
    private float gfR;
    private float gsR;
    private float autR;
    private float assR;
    private float tsB;
    private float gfB;
    private float gsB;
    private float autB;
    private float assB;
    private float m2;
    private float m3;
    private float amm;
    private float esp;
    private float gdv;
    private float gdp;
    private float rigs;
    private float rogp;
    private float rt;
    private float rs;
    private float t;
    private float vg;
    private float vc;
    private float vts;*/

    public Float getVoto(){
        float voto=voti[0];
        voto+=voti[4]*c.getPuntiAssist();
        voto-=voti[3]*c.getPuntiAutogoal();
        voto-=voti[18]*c.getPuntiEspulzione();
        voto-=voti[17]*c.getPuntiAmmonimento();
        voto+=voti[23]*c.getPuntiRigoreTrasformato();
        voto+=voti[22]*c.getPuntiRigoreParato();
        voto-=voti[21]*c.getPuntiRigoreSubito();


        if(ruolo.equals("A")){
            voto+=voti[1]*c.getPuntiGoalAttaccante();
        }

        if(ruolo.equals("C")){
            voto+=voti[1]*c.getPuntiGoalCentrocampista();
        }

        if(ruolo.equals("D")){
            voto+=voti[1]*c.getPuntiGoalDifensore();
        }
        if(ruolo.equals("P")){
            voto+=voti[1]*c.getPuntiGoalPortiere();
        }

        return voto;
    }

    public Giocatore() {
        voti=new float[29];
        c=new Credential();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome.toUpperCase();
    }

    public String getSquadra() {
        return squadra;
    }

    public void setSquadra(String squadra) {
        this.squadra = squadra;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public float[] getVoti() {
        return voti;
    }

    public void setVoti(float[] voti) {
        this.voti = voti;
    }

    public Giocatore(NodeList a){
        c=new Credential();
        voti=new float[29];
        int pointer=0;

        pointer+=2;
        try {
            id = toInt(a.item(pointer).getNodeValue());
        }catch (Exception e){
            System.out.println(a.item(pointer).getNodeValue());
        }

        if(id==-1)
            pointer=0;

        pointer+=2;
        cognome = a.item(pointer).getNodeValue().toUpperCase();

        pointer+=2;
        ruolo = a.item(pointer).getNodeValue();

        pointer+=2;
        squadra = a.item(pointer).getNodeValue();

        int j = 0;
        for (int i = pointer+1; j < 29; i++) {
            if (a.item(i).getNodeValue().hashCode() != 0) {
                voti[j] = toFloat(a.item(i).getNodeValue());
                j++;
            }
        }

    }

    public String toString(){
        String ret=id+" "+cognome+" "+squadra+" "+ruolo+" ";
        for(int i=0;i<voti.length;i++){
            ret+=voti[i]+" ";
        }
        return ret;
    }

    private float toFloat(String a){
        String app="";

        for(int i=0;i<a.length();i++){
            if(!(a.charAt(i) ==','))
                app+=a.charAt(i);
            else
                app+='.';
        }
        if(a.equals("s.v."))
            return 0;
        try {
            return new Float(app);
        }catch(Exception e){
            return -1;
        }
    }

    private static int toInt(String a){
        String ret="";

        for(int i=0;i<a.length();i++){
            if(a.charAt(i)!= 176 && a.charAt(i)!= 32) {
                ret += a.charAt(i);
            }
        }
        try {
            return new Integer(ret);
        }catch (Exception e){
            return -1;
        }
    }
}
