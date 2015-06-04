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
            url = new URL(path);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            Tidy tidy = new Tidy();
            tidy.setQuiet(true);
            tidy.setShowWarnings(false);
            Document response = tidy.parseDOM(br,null);
            XPathFactory factory = XPathFactory.newInstance();
            XPath xPath=factory.newXPath();
            String pattern = "//div[@id='Voti']//table[@class='voti-m']//tr[@class]//td[@class]//node()";


            NodeList nodes = (NodeList)xPath.evaluate(pattern, response, XPathConstants.NODESET);
            List<List<String>> buffer= new ArrayList<>();
            /*for (int i = 3; i < nodes.getLength(); i++) {
                Node a = nodes.item(i);
                if(isString(a.getNodeValue()) && a.getNodeValue().hashCode()!=0 && a.getNodeValue().hashCode()!=160 && !a.getNodeValue().equals("s.v.")) {
                    if(g!=null)
                        giocatori.add(g);
                    g=new Giocatore();
                    if(buffer.size()>0) {
                        g.setCognome(buffer.get(0));
                        g.setVoto(toFloat(buffer.get(buffer.size()-8)));
                        buffer.removeAll(buffer);
                        System.out.println("-");
                    }
                }
                buffer.add(a.getNodeValue());
            }*/
            int j=0;
            List<String> app=new ArrayList<>();
            for (int i = 3; i < nodes.getLength(); i++) {
                Node a = nodes.item(i);
                if(isString(a.getNodeValue()) && a.getNodeValue().hashCode()!=0 && a.getNodeValue().hashCode()!=160 && !a.getNodeValue().equals("s.v.")) {
                    buffer.add(app);
                    app=new ArrayList<>();
                    }
                if(a.getNodeValue().hashCode()!=160 && a.getNodeValue().hashCode()!=0)
                    app.add(a.getNodeValue());
            }
            buffer.remove(0);

            //popolazione lista "giocatori"
            Giocatore g=null;
            for(int i=0;i<buffer.size();i++){
                g=new Giocatore();
                g.setCognome(buffer.get(i).get(0));
                g.setVoto(toFloat(buffer.get(i).get(buffer.get(i).size()-5)));
                giocatori.add(g);
            }
            System.out.println();
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
