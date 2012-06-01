
package utilerias;

import java.util.LinkedHashMap;

/**
 *
 * @author Ruben
 */
public class GolMap {
    
    private static LinkedHashMap<String, String> golXml;
    private static LinkedHashMap<String, String> xmlGol;
        
    public static String golXml(String golTag){
        
        return genMapGolXml().get(golTag);
    }
        
    public static String xmlGol(String xmlTag){
        
        return genMapXmlGol().get(xmlTag);
    }
    
    public static LinkedHashMap<String, String> genMapGolXml(){
        
        return golXml == null? genMap(true): golXml;        
    }
    
    public static LinkedHashMap<String, String> genMapXmlGol(){
        
        return xmlGol == null? genMap(false): xmlGol;        
    }
    
    private static LinkedHashMap<String, String> genMap(boolean golXml){
        
                
        LinkedHashMap<String, String> mapa = new LinkedHashMap<String, String>();
        
        String[] nombresBase={"CliId","CLI_SAP","CliApePat","CliApeMat","CliNom","CliFecNac","CliDomCal",
                               "CliDomNumExt","CliDomNumInt","CliDomCol","CodPosId","SexId","RelFamId",
                                "FreRelId","NivEstId","ActUltId","CliGolP1","CliGolP8","CliGolP10",
                                "CliGolP11","CliGolP15","CliGolP17","CliGolP18",
                                "CliGolP25","DinExtId","CliGolResAct","ServIndId","BurActId","BurHisId",
                                "BurAntId","BuroPagId","BurUsoId","CliBurNumAbi","CliBurValr","CliGolIng","ProAnoGtoId",
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
            
            mapa.put(golXml? nombresBase[t]: nombresXml[t], golXml? nombresXml[t]: nombresBase[t]);                
        }
        return mapa;              
        
    }    
}
