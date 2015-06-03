package com.company.utl;

import com.company.data.Giocatore;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by Andrea on 01/06/2015.
 *
 */
public class GiocatoriInteressati {
    public Vector<Giocatore> GiocatoriInteressati(String nomi){
        StringTokenizer s=new StringTokenizer(nomi,"\n");
        Vector<Giocatore> giocatoriUtente= new Vector<Giocatore>();
        Vector<Giocatore> giocatoriCaricati=new VotiReader().getGiocatori();

            while(s.countTokens()>0){
                int a=thereIs(s.nextToken(), giocatoriCaricati);
                if(a>=0) {
                    giocatoriUtente.add(giocatoriCaricati.get(a));
                }
            }

        return giocatoriUtente;
    }

    private int thereIs(String s,Vector<Giocatore> s1){
        int ret=-1;

        for(int i=0;i<s1.size();i++){
            if(s1.get(i).getCognome().equals(s)) {
                ret = i;
            }
        }

        return ret;
    }
}
