package com.company;

import com.company.data.Giocatore;
import com.company.utl.GiocatoriInteressati;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String a ="";
        if(args.length>=1){
            File p=new File(args[0]);
            try {
                List app=Files.readAllLines(p.toPath());
                for (Object anApp : app) {
                    String c = anApp.toString();
                    a += c.toUpperCase() + "\n";
                }
                System.out.println(a);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(args.length==0){
            String app="";
            while(!app.equals("*")){
                Scanner in=new Scanner(System.in);
                System.out.print("Inserisci cognome giocatore('*' per uscire): ");
                app=in.nextLine();
                if(!app.equals("*"))
                    a+=app.toUpperCase()+"\n";
            }
        }

        if(args.length>=2) {
            Stampa(args[1],Tabula(new GiocatoriInteressati().GiocatoriInteressati(a)));
        }


        System.out.println(Tabula(new GiocatoriInteressati().GiocatoriInteressati(a)));

    }

    private static String spazia(String a, int space){
        String ret=" ";
        int dim=space-a.length();
        for(int i=0;i<dim;i++)
            ret+=" ";

        return ret;
    }

    private static String Tabula(Vector<Giocatore> giocatori){

        String ret="";
        float tot=0;
        ret+="_____________________________________________\n";
        ret+="COGNOME"+spazia("COGNOME",20)+"VOTO\n";
        ret+="_____________________________________________\n";
        ret+="\n";
        for(int i=0;i<giocatori.size();i++){
            ret+=giocatori.get(i).getCognome()+spazia(giocatori.get(i).getCognome(),20)+giocatori.get(i).getVoto()+"\n";
            tot+=giocatori.get(i).getVoto();
        }
        ret+="_____________________________________________\n";
        ret+="TOTALE VOTI: "+tot+"\n";
        return ret;
    }

    private static void Stampa(String path, String txt){

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new FileWriter(path));
            writer.write(txt);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
