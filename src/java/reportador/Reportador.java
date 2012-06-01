
package reportador;

import calculo.*;
import dao.Dao;
import dao.GestorBase;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import objetos.CliGol;
import org.w3c.dom.*;
import org.xml.sax.*;
import utilerias.*;

/**
 *
 * @author Ruben
 */
public class Reportador {
    
    
        private GestorBase geBa = new GestorBase();
    private CalculadoraCGWS calcu = new CalculadoraCGWS();
    private Conversor conversor = new Conversor();        

    private static List<String> Hit=new ArrayList<String>();
    private static List<String> NoHit=new ArrayList<String>();
    private static List<String> Buro=new ArrayList<String>();

    private final String rutaHit="Hit.csv";
    private final String rutaNoHit="NoHit.csv";
    private final String rutaBuro="Buro.csv";
    
    public void reporta(String ids) throws ParserConfigurationException, SAXException, IOException{
        
        CliGol[] clis = leeClis("("+ids+")");
        
        for (CliGol cliGol : clis) {
            
            String xml = genXml(cliGol);                                    
            String resu = llamaCalc(xml);            
            System.out.println(resu);
            guardaRes(resu, cliGol);
        }

        escribeArchivo(this.Hit,rutaHit);
        escribeArchivo(this.NoHit,rutaNoHit);
        escribeArchivo(this.Buro,rutaBuro);
        System.out.println("calculados " + clis.length);
    }

    private CliGol[] leeClis(String ids) {
        
        return geBa.getClis(ids);
    }
    
    private CliGol[] leeClis() {
        
        return geBa.getClis();
    }

    private String genXml(CliGol cliGol) {        
        
        return conversor.convierte("EntradaCalculadora", cliGol);
    }

    private String llamaCalc(String xml) {
        
        return calcu.CalcularCG(xml);        
    }

    private void guardaRes(String resu, CliGol cliGol) throws ParserConfigurationException, SAXException, IOException {

        String[] nodos={"NoHit","Hit","Buro"};


        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(resu));
        Document xml = db.parse(is);



        Element raiz = xml.getDocumentElement();
    
        String nombre=obtenerNombre(raiz);

            for(String s:nodos){
                
                Node nodo=raiz.getElementsByTagName(s)==null ? null :raiz.getElementsByTagName(s).item(0);
            
                if(nodo!=null){
                    String informacion=sustraerInformacionNodo(cliGol, nodo);
                    
                    guardarEnListas(nodo.getNodeName(),nombre+","+informacion);
                }
                
            }

    
    }
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        
        Reportador r = new Reportador();
        r.reporta("1");
        
    }

    private String buscaValEnCli(String nom, CliGol cliGol) {
        
        return String.valueOf(Espejo.invocaGet(cliGol, nom));
        
        
    }

    private String sustraerInformacionNodo(CliGol cliGol, Node nodo) {

        String[] excluye = {"CLI_ID","CLI_SAP","CliApePat","CliApeMat","CliNom","CLI_FEC_NAC","CLI_DOM_CAL",
                            "CLI_DOM_NUM_EXT","CLI_DOM_NUM_INT","CLI_DOM_COL","CLI_POS_ID","CliGolP1",
                            "CliGolP8","CliGolP10","CliGolP11","CliGolP15","CliGolP17","CliGolP18",
                            "CliGolP25","CliGolResAct",
                            "CliGolNumTar","CliGolCtaChe",
                            "CliGolCrePer","CliGolCreAut","CliGolCreHip","CliGolOtrCre","CliBurMens",
                            "PagRnt","CliGolBurCre","CliBurValr","CliGolIng","CliGolImpRen"};

        NodeList variables = nodo.getChildNodes();

        LinkedHashMap[] mapas=obtenerMapeos(cliGol,variables,excluye);


        Dao dao = new Dao();
        Object[] descripciones = dao.ObtenLista(mapas[0],Arrays.asList(excluye));

        //<variable,puntos>,descripciones
        return unirMapeos(mapas[1], descripciones);

    }

    private LinkedHashMap[] obtenerMapeos(CliGol cliGol,NodeList variables,String[] excluye) {

        LinkedHashMap<String, Object > mapa = new LinkedHashMap<String, Object>();

        LinkedHashMap<String , String> puntos=new LinkedHashMap<String , String>();

        List<String> ex = Arrays.asList(excluye);

        for (int i = 0; i < variables.getLength(); i++) {

            String nom = variables.item(i).getNodeName();


            if(!ex.contains(nom)){

                String golMapdijo = GolMap.xmlGol(nom);

                String val = null;
                if(golMapdijo != null){
                     val = buscaValEnCli(golMapdijo  , cliGol);
                     mapa.put(nom, val);


                     puntos.put(nom, variables.item(i).getTextContent());
                }
            }
        }

        return new LinkedHashMap[]{mapa,puntos};

    }


    

    private String unirMapeos(LinkedHashMap<String, String> mapa, Object[] descripciones) {


        //mapa(llave,punto)
        //descripciones en el mismo orden que el mapeo
        String comillas="\"";
        StringBuilder builder=new StringBuilder();

        Iterator<String> iterador=mapa.keySet().iterator();

        int indice=0;

        while(iterador.hasNext()){
            String llave=iterador.next();

            String puntaje=mapa.get(llave);

            String descripcion=descripciones[indice].toString().equals("null")?"":descripciones[indice].toString();


            builder.append(comillas+descripcion+comillas+","+comillas+puntaje+comillas+",");

            indice++;
        }
        return builder.toString();
    }

    
    private void escribeArchivo(List<String> lista, String ruta) {
        try {

            BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta));

            for(String s:lista){
                escritor.write(s);
            }

            escritor.close();

        } catch (IOException ex) {
            Logger.getLogger(Reportador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void guardarEnListas(String nodeName, String informacion) {
        System.out.println(informacion);

        if(nodeName.equals("NoHit")){
            NoHit.add(informacion);
            return;
        }
        if(nodeName.equals("Hit")){
            Hit.add(informacion);
            return;
        }
        if(nodeName.equals("Buro")){
            Buro.add(informacion);
            return;
        }
        
    }

    private String obtenerNombre(Element raiz) {

        String comillas="\"";

        StringBuilder nombre=new StringBuilder(comillas);

        String[] vars={"CliApePat","CliApeMat","CliNom"};

        for(String s:vars){
            nombre.append(raiz.getElementsByTagName(s).item(0).getTextContent()+" ");
        }
        nombre.append(comillas);

        return nombre.toString();

    }
    
    
    
}
