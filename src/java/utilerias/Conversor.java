package utilerias;


import dao.GestorBase;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.*;

/**
 *
 * @author Ruben G.g
 */
public class Conversor {

    private GestorBase gbas = new GestorBase();
    public static int cuentale = 0;
    
    public String hashXML(String raiz,LinkedHashMap mapa){

         XML xml = new XML();
         xml.creaXml(raiz);

        for (Object llave: mapa.keySet()) {

            xml.pegaNodo(xml.getRaiz(), String.valueOf(llave), String.valueOf( mapa.get(llave)));
        }
        return xml.xmlString();
    }  

    //raiz= EntradaCalculadoraCG
    public String convierte(String raiz,CliGol gol ){

         LinkedHashMap mapeo = genMapeo();
         LinkedHashMap golVals = golMapeo(mapeo,gol);

         LinkedHashMap xmlHt = invierteLlaves(golVals, mapeo);
         String xmlEnt = hashXML(raiz,xmlHt);

         return xmlEnt;

     }
   
    public LinkedHashMap invierteLlaves(LinkedHashMap objetivo, LinkedHashMap mapa) {

       LinkedHashMap  xmlHt = new LinkedHashMap();       

       for(Object llave: mapa.keySet()){
           xmlHt.put(mapa.get(llave), objetivo.get(llave));
       }
       return xmlHt;
    }

/**
 * metodo que nos genera el mapeo de (nombre en base,nombres en xml);
 * el nombre en base es vacio en caso de que no exista el nombre o se calcule el dato
 * @return
 */
    private LinkedHashMap genMapeo() {

        LinkedHashMap mapeo = new LinkedHashMap();
        
        String[] nombresBase={"CliId","CLI_SAP","CliApePat","CliApeMat","CliNom","CliFecNac","CliDomCal",
                               "CliDomNumExt","CliDomNumInt","CliDomCol","CodPosId","SexId","RelFamId",
                                "FreRelId","NivEstId","ActUltId","CliGolP1","CliGolP8","CliGolP10",
                                "CliGolP11","CliGolP15","CliGolP17","CliGolP18",
                                "CliGolP25","DinExtId","CliGolResAct","ServIndId","BurActId","BurHisId",
                                "BurAntId","BuroPagId","BurUsoId","CliBurNumAbi","CliValor","CliIngreso","ProAnoGtoId",
                                "GtoAlmId","GtoVesId","GtoSrvVivId","GtoRenHId","PbmId","PtmId",
                                "SitPrxAnId","CliGolNumTar","CliGolCtaChe",
                                "CliGolCrePer","CliGolCreAut","CliGolCreHip","CliGolOtrCre","CliMensualidad","PagRntId","CliGolBurCre"};
        
        
        String[] nombresXml={"CLI_ID","CLI_SAP","CliApePat","CliApeMat","CliNom","CLI_FEC_NAC","CLI_DOM_CAL",
                            "CLI_DOM_NUM_EXT","CLI_DOM_NUM_INT","CLI_DOM_COL","CLI_POS_ID","Sex","RelFam",
                            "FreRel","NivEst","ActUlt","CliGolP1","CliGolP8","CliGolP10",
                            "CliGolP11","CliGolP15","CliGolP17","CliGolP18",
                            "CliGolP25","DinExt","CliGolResAct","Srv","BurAct","BurHis",
                            "BurAnt","BurPag","BurUso","BurAbt","CliBurValr","CliGolIng","GtoImp",
                            "GtoAlm","GtoVes","GtoSerViv","CliGolImpRen","Pbm","Ptm",
                            "SitPrxAn","CliGolNumTar","CliGolCtaChe",
                            "CliGolCrePer","CliGolCreAut","CliGolCreHip","CliGolOtrCre","CliBurMens","PagRnt","CliGolBurCre"};

            for(int t=0;t<nombresBase.length;t++){
                
                mapeo.put(nombresBase[t], nombresXml[t]);
                
            }

        return mapeo;

    }

  
    public static void main(String[] args) {


        Conversor c = new Conversor();
        GestorBase g = new GestorBase();
        c.convierte("cosa", g.getCliGol(1)); 
                
    
/*
        LinkedHashMap<String, Object> objetivo = new LinkedHashMap<String, Object>();

        objetivo.put("Sex", 15);
        objetivo.put("ProAnoGto", 20);
        objetivo.put("Srv", 25);




        LinkedHashMap<String, Object> llaves = new LinkedHashMap<String, Object>();
        llaves.put("Sex", "Genero");
        llaves.put("ProAnoGto", "GtoImp");
        llaves.put("Srv", "SrvicioCerca");


         System.out.println(objetivo);

         System.out.println(c.invierteLlaves(objetivo, llaves));*/

    }

    /**
     * metodo que nos llena un mapeo con (nombreEnBase,valor)
     *
     * @param mapeo = (nombre en base , nombre en xml)
     * @param gol =cliente
     * @return
     */
    private LinkedHashMap golMapeo(LinkedHashMap mapeo, CliGol gol) {        

        LinkedHashMap juntador = new LinkedHashMap();
        
       Cli cli = gbas.getCli(gol.getCliId());
       CliBur bur = gbas.getCliBur(gol.getCliId());        
        
        for(Object llave : mapeo.keySet()){
          

            Object valor = obtenerValor(gol, cli, bur, (String)llave);            
            valor = valor == null ? "-1" : valor.toString();
            //System.out.println((String) llave+":"+valor);            
            juntador.put((String)llave, valor );
        }
        return juntador;
    }

    private Object obtenerValor(CliGol gol, Cli cli, CliBur bur, String llave) {

       Object valor=buscarEnClase(gol,llave);


            if(valor!=null){

                    return valor;
            }

            valor=buscarEnClase(cli,llave);

             if(valor!=null){

                    return valor;
            }

            if(bur!=null){


                if(gol.getBurUso()==null && llave.equals("CliGolBurCre")){
                   return 2;
                }

            valor=buscarEnClase(bur,llave);

                if(llave.equals("CliGolBurCre")){
                    return 1;
                }

                 if(valor!=null){

                    return valor;
                }



            }

            return null;



    }
    
    private Object obtenerValor(CliGol gol, String llave) {

        
       Cli cli = gbas.getCli(gol.getCliId());
       CliBur bur = gbas.getCliBur(gol.getCliId());
       
        
       
       Object valor=buscarEnClase(gol,llave);


            if(valor!=null){

                    return valor;
            }

            valor=buscarEnClase(cli,llave);

             if(valor!=null){

                    return valor;
            }

            if(bur!=null){


            valor=buscarEnClase(bur,llave);

                if(llave.equals("CliGolBurCre")){
                    return 1;
                }

                 if(valor!=null){

                    return valor;
                }
            }

            return null;
    }




    private Object  buscarEnClase(Object cliente, String llave) {
        try {

            Method metodo=Espejo.getMetodo(cliente, llave);


            if (metodo == null) {
                return null;
            }

            return  metodo.invoke(cliente, null);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
