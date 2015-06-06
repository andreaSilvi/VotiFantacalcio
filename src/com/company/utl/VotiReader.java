package com.company.utl;

import com.company.data.Giocatore;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Andrea on 01/06/2015.
 */
public class VotiReader {
    private String path;

    public VotiReader() {
        path = "http://www.pianetafantacalcio.it/Voti_Ufficiosi.asp";
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public Vector<Giocatore> getGiocatori(){
        Vector<Giocatore> giocatori=new Vector<Giocatore>();
        URL url;
        InputStream is;
        BufferedReader br;

        System.out.println("Carico tutti i giocatori...");

        try {
            url = new URL("http://www.pianetafantacalcio.it/Voti_Ufficiosi.asp");
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            Tidy tidy = new Tidy();
            tidy.setQuiet(true);
            tidy.setShowWarnings(false);
            Document response = tidy.parseDOM(br,null);
            XPathFactory factory = XPathFactory.newInstance();
            XPath xPath=factory.newXPath();
            String pattern = "//h3//strong/node()";

            NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);
            int numeroGiornata=toInt(nodes.item(0).getNodeValue()+" ");
            System.out.println("Numero giornata = "+numeroGiornata);

            url = new URL("http://www.pianetafantacalcio.it/Voti_UfficiosiTuttiExcel.asp?giornataScelta="+numeroGiornata);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            tidy = new Tidy();
            tidy.setQuiet(true);
            tidy.setShowWarnings(false);
            response = tidy.parseDOM(br,null);
            factory = XPathFactory.newInstance();
            xPath=factory.newXPath();

            int i=5;
            Giocatore g;
            do{
                pattern = "//table/tr["+i+"]//node()";
                nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);
                if(nodes.getLength()>0) {
                    g = new Giocatore(nodes);
                    giocatori.add(g);
                }
                i++;
            }while(nodes.getLength()>0);

        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        System.out.println("Giocatori caricati con successo!!");
        return giocatori;
    }

    private static int toInt(String a){
        String ret="";

        for(int i=0;i<a.length();i++){
            if(a.charAt(i)!= 176 && a.charAt(i)!= 32) {
                ret += a.charAt(i);
            }
        }

        return new Integer(ret);
    }

    private boolean isString(String a){
        String app="";

        for(int i=0;i<a.length();i++){
            if(!(a.charAt(i) ==','))
                app+=a.charAt(i);
            else
                app+='.';
        }
        try {
            new Float(app);
        }catch (Exception e){
            return true;
        }
        return false;
    }
}
