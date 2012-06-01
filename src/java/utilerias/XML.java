/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilerias;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


/**
 *
 * @author Ruben G.g
 */
public class XML {


    private Element raiz;
    private Document doc;

    public Document getDoc() {
        return doc;
    }

    public Element getRaiz() {
        return raiz;
    }




    public void creaXml(String raizNombre){

        try {

            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.newDocument();
            raiz = doc.createElement(raizNombre);
            doc.appendChild(raiz);
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void pegaNodo(Element padre, String nombre, String val){

        Element hijo = doc.createElement(nombre);
        Text texto = doc.createTextNode(val);

        hijo.appendChild(texto);
        padre.appendChild(hijo);
    }



    public String xmlString() {

        try {

           StringWriter stw = new StringWriter();
           Transformer serializer = TransformerFactory.newInstance().newTransformer();
           serializer.transform(new DOMSource(doc), new StreamResult(stw));
           return stw.toString();

        } catch (TransformerException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        } return null;
    }


    public Element creaElemento(Element raiz, String nombre, String contenido){

        Element hijo = doc.createElement(nombre);
        Text texto = doc.createTextNode(contenido);
        hijo.appendChild(texto);
        raiz.appendChild(hijo);

        return hijo;
    }

    

   



    public static void main(String[] args) {


        XML xml = new XML();

        HashMap<String, Object> mapa = new HashMap<String, Object>();

        mapa.put("Yen", 15);
        mapa.put("Jul", 20);
        mapa.put("Dan", 25);




    }

}
