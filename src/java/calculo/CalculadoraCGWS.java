/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculo;

import dao.HibernateUtil;
import java.io.IOException;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import objetos.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class CalculadoraCGWS
{
  static String xmlVacioExpt = "Estructura de Xml Vacia";
  static String xmlExpt = "Estructura de Xml  con Error";
  static String tabNoEncontradoExpt = "Tab No encontrado: ";
  static String tabVacioExpt = "Valor Tab Vacio: ";
  static String accesoDatosExpt = "Ocurrio un error en acceso a Datos";
  static String datosErrorExpt = "Valor Tab debe ser superior a 0:";

  double GENERO_FACTOR = 0.0D;
  double GENERO_WOE = 0.0D;
  String GENERO_DES = "";
  double FRECUENCIA_RELIGION_FACTOR = 0.0D;
  double FRECUENCIA_RELIGION_WOE = 0.0D;
  String FRECUENCIA_RELIGION_DES = "";
  double SITUACION_PROXIMO_ANO_FACTOR = 0.0D;
  double SITUACION_PROXIMO_ANO_WOE = 0.0D;
  String SITUACION_PROXIMO_ANO_DES = "";
  double DINERO_EXTRA_FACTOR = 0.0D;
  double DINERO_EXTRA_WOE = 0.0D;
  String DINERO_EXTRA_DES = "";
  double VIVIENDA_ACTUAL_FACTOR = 0.0D;
  double VIVIENDA_ACTUAL_WOE = 0.0D;
  String VIVIENDA_ACTUAL_DES = "";
  double SERVICIO_HOGAR_FACTOR = 0.0D;
  double SERVICIO_HOGAR_WOE = 0.0D;
  double ACT_FACTOR = 0.0D;
  double ACT_WOE = 0.0D;
  String ACT_TEXTO = "";
  double USO_FACTOR = 0.0D;
  double USO_WOE = 0.0D;
  double CUEN_ABIER_CAT_FACTOR = 0.0D;
  double CUEN_ABIER_CAT_WOE = 0.0D;
  String CUEN_ABIER_CAT_DES = "";
  double VALOR_VIVIENDA_FACTOR = 0.0D;
  double VALOR_VIVIENDA_WOE = 0.0D;
  double NO_CREDITOS_FACTOR = 0.0D;
  double NO_CREDITOS_WOE = 0.0D;
  String NO_CREDITOS_DES = "";
  double I_PSICO_FACTOR = 0.0D;
  double I_PSICO_WOE = 0.0D;
  String I_PSICO_DES = "";

  double RELIGION_FACTOR = 0.0D;
  double RELIGION_WOE = 0.0D;
  double ESCOLARIDAD_FACTOR = 0.0D;
  double ESCOLARIDAD_WOE = 0.0D;
  String ESCOLARIDAD_DES = "";
  double TRABAJO_ANTERIOR_FACTOR = 0.0D;
  double TRABAJO_ANTERIOR_WOE = 0.0D;
  String TRABAJO_ANTERIOR_DES = "";

  double INGRESO_TOTAL_FACTOR = 0.0D;
  double INGRESO_TOTAL_WOE = 0.0D;
  String INGRESO_TOTAL_DES = "";
  double GASTOS_FACTOR = 0.0D;
  double GASTOS_WOE = 0.0D;
  String GASTOS_DES = "";
  double CUBRIR_ALIMETOS_FACTOR = 0.0D;
  double CUBRIR_ALIMETOS_WOE = 0.0D;
  String CUBRIR_ALIMETOS_DES = "";
  double VESTIDO_FACTOR = 0.0D;
  double VESTIDO_WOE = 0.0D;
  String VESTIDO_DES = "";
  double PRINCIPAL_PROBLEMA_FACTOR = 0.0D;
  double PRINCIPAL_PROBLEMA_WOE = 0.0D;
  String PRINCIPAL_PROBLEMA_DES = "";
  double SOLICITAR_PRESTAMO_FACTOR = 0.0D;
  double SOLICITAR_PRESTAMO_WOE = 0.0D;
  double VCS_VIV_FACTOR = 0.0D;
  double VCS_VIV_WOE = 0.0D;
  double I_RENTA_FACTOR = 0.0D;
  double I_RENTA_WOE = 0.0D;
  double SERVICIOS_VIVIENDA_FACTOR = 0.0D;
  double SERVICIOS_VIVIENDA_WOE = 0.0D;
  String SERVICIOS_VIVIENDA_DES = "";
  double RENTA_FACTOR = 0.0D;
  double RENTA_WOE = 0.0D;
  String RENTA_DES = "";

  String SERVICIO_HOGAR_DES = "";

  String VALOR_VIVIENDA_DES = "";

  double HIST_FACTOR = 0.0D;
  double HIST_WOE = 0.0D;
  String HIST_TEXTO = "";

  double ANT_FACTOR = 0.0D;
  double ANT_WOE = 0.0D;
  String ANT_TEXTO = "";

  String USO_TEXTO = "";

  double PAGO_FACTOR = 0.0D;
  double PAGO_WOE = 0.0D;
  String PAGO_TEXTO = "";

  double AGRESION_1_FACTOR = 0.0D;
  double AGRESION_1_WOE = 0.0D;
  double AGRESION_4_FACTOR = 0.0D;
  double AGRESION_4_WOE = 0.0D;
  double ADHESION_GRUPO3_FACTOR = 0.0D;
  double ADHESION_GRUPO3_WOE = 0.0D;
  double RESENTIMIENTO_SOCIAL3_FACTOR = 0.0D;
  double RESENTIMIENTO_SOCIAL3_WOE = 0.0D;
  double CONDUCTA_DELICTIVA2_FACTOR = 0.0D;
  double CONDUCTA_DELICTIVA2_WOE = 0.0D;
  double RESENTIMIENTO_SOCIAL4_FACTOR = 0.0D;
  double RESENTIMIENTO_SOCIAL4_WOE = 0.0D;
  double RESPONSABILIDAD4_FACTOR = 0.0D;
  double RESPONSABILIDAD4_WOE = 0.0D;
  double CONDUCTA_DELICTIVA5_FACTOR = 0.0D;
  double CONDUCTA_DELICTIVA5_WOE = 0.0D;

  double PRIMER_PESO_COMPORTAMIENTO = 0.0D;
  String PRIMER_TEXTO_COMPORTAMIENTO = "";
  double SEGUNDO_PESO_COMPORTAMIENTO = 0.0D;
  String SEGUNDO_TEXTO_COMPORTAMIENTO = "";
  double PRIMER_PESO_COMPORTAMIENTO_NH = 0.0D;
  String PRIMER_TEXTO_COMPORTAMIENTO_NH = "";
  double SEGUNDO_PESO_COMPORTAMIENTO_NH = 0.0D;
  String SEGUNDO_TEXTO_COMPORTAMIENTO_NH = "";
  double SCORE_ARRAIGO = 0.0D;
  double SCORE_GENERO = 0.0D;
  double SCORE_COMPORTAMIENTO = 0.0D;
  double SCORE_ASPECTOS = 0.0D;

  double SCORE_ARRAIGO_NH = 0.0D;
  double SCORE_COMPORTAMIENTO_NH = 0.0D;

  double PRIMER_PESO_ARRAIGO = 0.0D;
  String PRIMER_TEXTO_ARRAIGO = "";
  double SEGUNDO_PESO_ARRAIGO = 0.0D;
  String SEGUNDO_TEXTO_ARRAIGO = "";

  double SCORE_IPSICO_FACTOR = 0.0D;
  double SCORE_IPSICO_WOE = 0.0D;


  double SCORE_NO_HIT  = 0.0D;

  String I_RENTA_DES = "";
    private double SCORE_HIT_FACTOR;
    private double SCORE_BURO;


    

     String RESULTADO="";

     private int REGBURO=0;



  @WebMethod(operationName="CalcularCG")
  public String CalcularCG(@WebParam(name="xml") String xml)
  {


    try
    {
      if (xml == null)
        return xmlVacioExpt;
      InputSource is = new InputSource(new StringReader(xml));
      //ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
      DocumentBuilderFactory dbfacIN = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilderIN = dbfacIN.newDocumentBuilder();
      Document docIN = docBuilderIN.parse(is);

      if (docIN.getDocumentElement().getNodeName().equals("EntradaCalculadora"))
      {


        String cliApePat = GetValue(docIN, "CliApePat");
        String cliApeMat = GetValue(docIN, "CliApeMat");
        String cliNom = GetValue(docIN, "CliNom");

        int GENERO = GetValueInt(docIN, "Sex");
        int RELIGION = GetValueInt(docIN, "RelFam");
        int FRECUENCIA_RELIGION = GetValueInt(docIN, "FreRel");
        int ESCOLARIDAD = GetValueInt(docIN, "NivEst");
        int TRABAJO_ANTERIOR = GetValueInt(docIN, "ActUlt");
        int AGRESION_1 = GetValueInt(docIN, "CliGolP1");
        int AGRESION_4 = GetValueInt(docIN, "CliGolP8");
        int ADHESION_GRUPO3 = GetValueInt(docIN, "CliGolP10");
        int RESENTIMIENTO_SOCIAL3 = GetValueInt(docIN, "CliGolP11");
        int CONDUCTA_DELICTIVA2 = GetValueInt(docIN, "CliGolP15");
        int RESENTIMIENTO_SOCIAL4 = GetValueInt(docIN, "CliGolP17");
        int RESPONSABILIDAD4 = GetValueInt(docIN, "CliGolP18");
        int CONDUCTA_DELICTIVA5 = GetValueInt(docIN, "CliGolP25");
        int DINERO_EXTRA = GetValueInt(docIN, "DinExt");
        int VIVIENDA_ACTUAL = GetValueInt(docIN, "CliGolResAct");
        int SERVICIO_HOGAR = GetValueInt(docIN, "Srv");
        int ACT = GetValueInt(docIN, "BurAct");
        int HIST = GetValueInt(docIN, "BurHis");
        int ANT = GetValueInt(docIN, "BurAnt");
        int PAGO = GetValueInt(docIN, "BurPag");
        int USO = GetValueInt(docIN, "BurUso");
        int CUEN_ABIER_CAT = GetValueInt(docIN, "BurAbt");


        int VALOR_VIVIENDA = GetValueInt(docIN, "CliBurValr");


        int INGRESO_TOTAL = GetValueInt(docIN, "CliGolIng");
        int GASTOS = GetValueInt(docIN, "GtoImp");
        int CUBRIR_ALIMETOS = GetValueInt(docIN, "GtoAlm");
        int VESTIDO = GetValueInt(docIN, "GtoVes");
        int SERVICIOS_VIVIENDA = GetValueInt(docIN, "GtoSerViv");
        int RENTA = GetValueInt(docIN, "CliGolImpRen");

        int PRINCIPAL_PROBLEMA = GetValueInt(docIN, "Pbm");
        int SOLICITAR_PRESTAMO = GetValueInt(docIN, "Ptm");

        int I_RENTA = 0;// = GetValueInt(docIN, "I_RENTA");

        int SITUACION_PROX_ANO = GetValueInt(docIN, "SitPrxAn");

        int TARJETA_CREDITO = GetValueInt(docIN, "CliGolNumTar");

        int CUENTA_CHEQUES = GetValueInt(docIN, "CliGolCtaChe");
        int CREDITO_PERSONAL = GetValueInt(docIN, "CliGolCrePer");
        int CREDITO_AUTO = GetValueInt(docIN, "CliGolCreAut");
        int CREDITO_HIPO = GetValueInt(docIN, "CliGolCreHip");
        int CREDITO_OTROS = GetValueInt(docIN, "CliGolOtrCre");
        int MENSUALIDAD = GetValueInt(docIN, "CliBurMens");//*;
        int PAGA_RENTA = GetValueInt(docIN, "PagRnt");//*;

        int REG_BURO = GetValueInt(docIN, "CliGolBurCre");

        int resNum = -1;

        Session s = HibernateUtil.getSessionFactory().openSession();


               int suma = 0;
		suma = suma + TARJETA_CREDITO;

		if (CUENTA_CHEQUES == 1)
			suma++;
		if (CREDITO_PERSONAL == 1)
			suma++;
		if (CREDITO_AUTO == 1)
			suma++;
		if (CREDITO_HIPO == 1)
			suma++;
		if (CREDITO_OTROS == 1)
			suma++;
		int NO_CREDITOS = 0;

		if (suma == 0)
			NO_CREDITOS = 0;
		else if (suma == 1)
			NO_CREDITOS = 1;
		else if (suma == 2)
			NO_CREDITOS = 2;
		else if (suma == 3)
			NO_CREDITOS = 3;
		else if (suma == 4)
			NO_CREDITOS = 4;
		else if (suma == 5)
			NO_CREDITOS = 5;
		else if (suma > 6)
			NO_CREDITOS = 6;


        double VECES_VIVIENDA = VALOR_VIVIENDA / INGRESO_TOTAL;

        double VCS_VIV= VECES_VIVIENDA;

                    // Pregunta 1

                AGRESION_1 = AGRESION_1 <= 2? 1: 2;
                AGRESION_4 = AGRESION_4 <= 2? 1: 2;
                ADHESION_GRUPO3 = ADHESION_GRUPO3 <= 2? 1: 2;
                RESENTIMIENTO_SOCIAL3 = RESENTIMIENTO_SOCIAL3 <= 2? 1: 2;
                CONDUCTA_DELICTIVA2 = CONDUCTA_DELICTIVA2 <= 2? 1: 2;
                RESENTIMIENTO_SOCIAL4 = RESENTIMIENTO_SOCIAL4 <= 2? 1: 2;
                RESPONSABILIDAD4 = RESPONSABILIDAD4 <= 2? 1: 2;
                CONDUCTA_DELICTIVA5 = CONDUCTA_DELICTIVA5 <= 2? 1: 2;
		// Pregunta8

     
        PsicoSocial(s, AGRESION_1, AGRESION_4, ADHESION_GRUPO3, RESENTIMIENTO_SOCIAL3, CONDUCTA_DELICTIVA2, RESENTIMIENTO_SOCIAL4, RESPONSABILIDAD4, CONDUCTA_DELICTIVA5);

        int I_PSICO =(int) this.SCORE_IPSICO_FACTOR;

        String resultado = "";

        Document doc = creaXml("SalidaCalculadora");

        if (REG_BURO == 1)
        {

            this.REGBURO=1;
          this.SCORE_HIT_FACTOR = EsHit(doc, s, GENERO, FRECUENCIA_RELIGION, SITUACION_PROX_ANO, DINERO_EXTRA, VIVIENDA_ACTUAL, SERVICIO_HOGAR, ACT, USO, CUEN_ABIER_CAT, VALOR_VIVIENDA, NO_CREDITOS, I_PSICO);



          if (SCORE_HIT_FACTOR >= 800.0D) {
            resultado = "Derechohabiente Alternativa Urbi";
            resNum = 1;
          }
          else {
            this.SCORE_NO_HIT = EsNoHit(doc, s, RELIGION, ESCOLARIDAD, TRABAJO_ANTERIOR, DINERO_EXTRA, INGRESO_TOTAL, GASTOS, CUBRIR_ALIMETOS, VESTIDO, PRINCIPAL_PROBLEMA, SOLICITAR_PRESTAMO, VCS_VIV, I_RENTA, SERVICIOS_VIVIENDA, RENTA, SERVICIO_HOGAR, VALOR_VIVIENDA, NO_CREDITOS, I_PSICO, MENSUALIDAD, PAGA_RENTA);



            if (SCORE_NO_HIT >= 800.0D)
            {
            this.SCORE_BURO = CalculoBuroMeta(doc, s, ACT, HIST, ANT, USO, PAGO);
              if (SCORE_BURO >= 800.0D){
                resultado = "Derechohabiente gol con Recomendacion a Reparar";
                resNum = 3;
              }
              else
                resultado = "Derechohabiente gol con Recomendacion a Declinar";
                resNum = 2;
            }
            else {
              resultado = "Derechohabiente Gol";
              resNum = 0;
            }
          }


        }
        else {

            this.REGBURO=0;
          this.SCORE_NO_HIT = EsNoHit(doc, s, RELIGION, ESCOLARIDAD, TRABAJO_ANTERIOR, DINERO_EXTRA, INGRESO_TOTAL, GASTOS, CUBRIR_ALIMETOS, VESTIDO, PRINCIPAL_PROBLEMA, SOLICITAR_PRESTAMO, VCS_VIV, I_RENTA, SERVICIOS_VIVIENDA, RENTA, SERVICIO_HOGAR, VALOR_VIVIENDA, NO_CREDITOS, I_PSICO, MENSUALIDAD, PAGA_RENTA);



          if (SCORE_NO_HIT >= 800.0D)
            resultado = "CLIENTE ALTERNATIVO URBI";
          else {
            resultado = "CLIENTE GOL";
          }

        }


        this.RESULTADO=resultado;

        AgregarNodo(doc.getDocumentElement(), doc, "CliApePat", cliApePat );
        AgregarNodo(doc.getDocumentElement(), doc, "CliApeMat", cliApeMat );
        AgregarNodo(doc.getDocumentElement(), doc, "CliNom", cliNom );

       AgregarNodo(doc.getDocumentElement(), doc, "ResText", resultado);
       AgregarNodo(doc.getDocumentElement(), doc, "ResNum", resNum);
       return xmlString(doc);
      }

      return tabNoEncontradoExpt + "EntradaCalculadoraCG";
    }
    catch (ParserConfigurationException parE)
    {
      return xmlExpt;
    } catch (SAXException saxE) {
      return xmlExpt;
    } catch (IOException ioE) {
      return xmlExpt; } catch (Exception e) {
    }
    return null;
  }

  private double EsHit(Document doc, Session s, int GENERO, int FRECUENCIA_RELIGION, int SITUACION_PROX_ANO, int DINERO_EXTRA, int VIVIENDA_ACTUAL, int SERVICIO_HOGAR, int ACT, int USO, int CUEN_ABIER_CAT, int VALOR_VIVIENDA, int NO_CREDITOS, int I_PSICO)
  {

      System.out.println("por hit");

   Query q = s
				.createQuery("from Sex as sex where sex.calId= '3' and sex.sexId="
						+ GENERO);
		List lista = (List) q.list();
		GENERO_FACTOR = ((Sex) lista.get(0)).getSexPto();
		GENERO_WOE = ((Sex) lista.get(0)).getSexWoe();
		GENERO_DES = ((Sex) lista.get(0)).getSexDes();




		q = s
				.createQuery("from FreRel as frerel where frerel.cal = '3' and frerel.freRelId="
						+ FRECUENCIA_RELIGION);
		lista = (List) q.list();
		FRECUENCIA_RELIGION_FACTOR = ((FreRel) lista.get(0)).getFreRelPto();
		FRECUENCIA_RELIGION_WOE = ((FreRel) lista.get(0)).getFreRelWoe();
		FRECUENCIA_RELIGION_DES = ((FreRel) lista.get(0)).getFreRelDes();




		q = s
				.createQuery("from SitPrxAn as sex where sex.calId = '3' and sex.sitPrxAnId="
						+ SITUACION_PROX_ANO);
		lista = (List) q.list();
		SITUACION_PROXIMO_ANO_FACTOR = ((SitPrxAn) lista.get(0))
				.getSitPrxAnPto();
		SITUACION_PROXIMO_ANO_WOE = ((SitPrxAn) lista.get(0)).getSitPrxAnWoe();
		SITUACION_PROXIMO_ANO_DES = ((SitPrxAn) lista.get(0)).getSitPrxAnDes();

      

		if (DINERO_EXTRA == 8)
			DINERO_EXTRA = 1;
		if (DINERO_EXTRA == 9)
			DINERO_EXTRA = 2;
		if (DINERO_EXTRA == 10)
			DINERO_EXTRA = 4;
		if (DINERO_EXTRA == 11)
			DINERO_EXTRA = 3;
		if (DINERO_EXTRA == 12)
			DINERO_EXTRA = 5;
		if (DINERO_EXTRA == 13)
			DINERO_EXTRA = 6;
		if (DINERO_EXTRA == 14)
			DINERO_EXTRA = 7;
		q = s
				.createQuery("from DinExt as dinext where dinext.cal= '3' and dinext.dinExtId="
						+ DINERO_EXTRA);
		lista = (List) q.list();
		DINERO_EXTRA_FACTOR = ((DinExt) lista.get(0)).getDinExtPto();
		DINERO_EXTRA_WOE = ((DinExt) lista.get(0)).getDinExtWoe();
		DINERO_EXTRA_DES = ((DinExt) lista.get(0)).getDinExtDes();


              
		// duda
		// sacar el id de vivienda de la tabla rng_res_ant por medio de los
		// limites o rangos
		q = s
				.createQuery("from RngResAnt as sex where sex.calId = '3' and (sex.rngResAntLimInf<"
						+ VIVIENDA_ACTUAL
						+ " and sex.rngResAntLimSup>="
						+ VIVIENDA_ACTUAL + ")");
		lista = (List) q.list();

		VIVIENDA_ACTUAL_FACTOR = ((RngResAnt) lista.get(0)).getRngResAntPto();
		VIVIENDA_ACTUAL_WOE = ((RngResAnt) lista.get(0)).getRngResAntWoe();
		VIVIENDA_ACTUAL_DES = ((RngResAnt) lista.get(0)).getRngResAntDes();

		q = s
				.createQuery("from Srv as sex where sex.srvId="
						+ (SERVICIO_HOGAR -11));
		lista = (List) q.list();
		SERVICIO_HOGAR_FACTOR = ((Srv) lista.get(0)).getSrvPto();
		SERVICIO_HOGAR_WOE = ((Srv) lista.get(0)).getSrvWoe();


    
		q = s
				.createQuery("from BurAct as ba where ba.cal='3' and ba.burActId="
						+ ACT);
		lista = (List) q.list();
		ACT_FACTOR = ((BurAct) lista.get(0)).getBurActPto();
		ACT_WOE = ((BurAct) lista.get(0)).getBurActWoe();
		ACT_TEXTO = ((BurAct) lista.get(0)).getBurActDes();




		q = s
				.createQuery("from BurUso as bu where bu.cal='3' and bu.burUsoId="
						+ USO);
		lista = (List) q.list();
		USO_FACTOR = ((BurUso) lista.get(0)).getBurUsoPto();
		USO_WOE = ((BurUso) lista.get(0)).getBurUsoWoe();

		q = s
				.createQuery("from CueAbr as sex where sex.cal = '3' and sex.cueAbrId="
						+ CUEN_ABIER_CAT);
		lista = (List) q.list();
		CUEN_ABIER_CAT_FACTOR = ((CueAbr) lista.get(0)).getCueAbrPto();
		CUEN_ABIER_CAT_WOE = ((CueAbr) lista.get(0)).getCueAbrWoe();
		CUEN_ABIER_CAT_DES = ((CueAbr) lista.get(0)).getCueAbrDes();

    
		if (VALOR_VIVIENDA < 310000)
			VALOR_VIVIENDA = 1;
		else
			VALOR_VIVIENDA = 2;


                q = s

				.createQuery("from VlrViv as sex where sex.calId = '3' and sex.vlrVivId ="
						+ VALOR_VIVIENDA);
		lista = (List) q.list();
		VALOR_VIVIENDA_FACTOR = ((VlrViv) lista.get(0)).getVlrVivPto();
		VALOR_VIVIENDA_WOE = ((VlrViv) lista.get(0)).getVlrVivWoe();



		q = s.createQuery("from NumCre as sex where sex.cal = '3'");
		lista = (List) q.list();
		NO_CREDITOS_FACTOR = ((NumCre) lista.get(NO_CREDITOS)).getNumCrePto();
		NO_CREDITOS_WOE = ((NumCre) lista.get(NO_CREDITOS)).getNumCreWoe();
		NO_CREDITOS_DES = ((NumCre) lista.get(NO_CREDITOS)).getNumCreDes();




		q = s
				.createQuery("from IPsi as ips where ips.cal = '3' and ips.IPsiLimInf<="
						+ SCORE_IPSICO_FACTOR
						+ " and ips.IPsiLimSup>="
						+ SCORE_IPSICO_FACTOR);
		lista = (List) q.list();
		I_PSICO_FACTOR = ((IPsi) lista.get(0)).getIPsiPto();
		I_PSICO_WOE = ((IPsi) lista.get(0)).getIPsiWoe();
		I_PSICO_DES = ((IPsi) lista.get(0)).getIPsiDesLar();

        	double SCORE_HIT_FACTOR = GENERO_FACTOR + FRECUENCIA_RELIGION_FACTOR
				+ SITUACION_PROXIMO_ANO_FACTOR + DINERO_EXTRA_FACTOR
				+ VIVIENDA_ACTUAL_FACTOR + SERVICIO_HOGAR_FACTOR + ACT_FACTOR
				+ USO_FACTOR + CUEN_ABIER_CAT_FACTOR + VALOR_VIVIENDA_FACTOR
				+ NO_CREDITOS_FACTOR + I_PSICO_FACTOR;

    this.SCORE_GENERO = (this.GENERO_WOE + this.FRECUENCIA_RELIGION_WOE);

    this.SCORE_COMPORTAMIENTO = (this.DINERO_EXTRA_WOE + this.SERVICIO_HOGAR_WOE + this.ACT_WOE + this.USO_WOE + this.CUEN_ABIER_CAT_WOE + this.VALOR_VIVIENDA_WOE + this.NO_CREDITOS_WOE);

    this.SCORE_ARRAIGO = (this.VIVIENDA_ACTUAL_WOE + this.SITUACION_PROXIMO_ANO_WOE);

    this.SCORE_ASPECTOS = this.I_PSICO_WOE;

    Element hit = doc.createElement("Hit");
    AgregarNodo(hit, doc, "Sex", GENERO_FACTOR);
    AgregarNodo(hit, doc, "FreRel", FRECUENCIA_RELIGION_FACTOR);
    AgregarNodo(hit, doc, "SitPrxAn", SITUACION_PROXIMO_ANO_FACTOR);
    AgregarNodo(hit, doc, "DinExt", DINERO_EXTRA_FACTOR);
    AgregarNodo(hit, doc, "CliGolResAct", VIVIENDA_ACTUAL_FACTOR);
    AgregarNodo(hit, doc, "Srv", SERVICIO_HOGAR_FACTOR);
    AgregarNodo(hit, doc, "BurAct", ACT_FACTOR);
    AgregarNodo(hit, doc, "BurUso", USO_FACTOR);
    AgregarNodo(hit, doc, "BurAbt", CUEN_ABIER_CAT_FACTOR);
    AgregarNodo(hit, doc, "CliBurValr", VALOR_VIVIENDA_FACTOR);
    AgregarNodo(hit, doc, "NoCreditos", NO_CREDITOS_FACTOR);
    AgregarNodo(hit, doc, "IPsico", I_PSICO_FACTOR);
    AgregarNodo(hit, doc, "Suma", SCORE_HIT_FACTOR);

    doc.getDocumentElement().appendChild(hit);
    return SCORE_HIT_FACTOR;
  }

  private double EsNoHit(Document doc, Session s, int RELIGION, int ESCOLARIDAD, int TRABAJO_ANTERIOR, int DINERO_EXTRA, int INGRESO_TOTAL, int GASTOS, int CUBRIR_ALIMETOS, int VESTIDO, int PRINCIPAL_PROBLEMA, int SOLICITAR_PRESTAMO, double VCS_VIV, int I_RENTA, int SERVICIOS_VIVIENDA, int RENTA, int SERVICIO_HOGAR, int VALOR_VIVIENDA, int NO_CREDITOS, int I_PSICO, int MENSUALIDAD, int PAGA_RENTA)
  {

      System.out.println("por no hit");
            	Query q = s
				.createQuery("from RelFam as sex where sex.calId = '5' and sex.relFamId="
						+ RELIGION);
		List lista = (List) q.list();
		RELIGION_FACTOR = ((RelFam) lista.get(0)).getRelFamPto();
		RELIGION_WOE = ((RelFam) lista.get(0)).getRelFamWoe();


		q = s
				.createQuery("from NivEst as sex where sex.cal = '5' and sex.nivEstId="
						+ ESCOLARIDAD);
		lista = (List) q.list();
		ESCOLARIDAD_FACTOR = ((NivEst) lista.get(0)).getNivEstPto();
		ESCOLARIDAD_WOE = ((NivEst) lista.get(0)).getNivEstWoe();
		ESCOLARIDAD_DES = ((NivEst) lista.get(0)).getNivEstDesLar();



		q = s
				.createQuery("from ActUlt as sex where sex.cal = '5' and sex.actUltId="
						+ TRABAJO_ANTERIOR);
		lista = (List) q.list();
		TRABAJO_ANTERIOR_FACTOR = ((ActUlt) lista.get(0)).getActUltPto();
		TRABAJO_ANTERIOR_WOE = ((ActUlt) lista.get(0)).getActUltWoe();
		TRABAJO_ANTERIOR_DES = ((ActUlt) lista.get(0)).getActUltDesLar();




		q = s
				.createQuery("from DinExt as dinext where dinext.cal= '5' and dinext.dinExtId="
						+ DINERO_EXTRA);
		lista = (List) q.list();
		DINERO_EXTRA_FACTOR = ((DinExt) lista.get(0)).getDinExtPto();
		DINERO_EXTRA_WOE = ((DinExt) lista.get(0)).getDinExtWoe();
		DINERO_EXTRA_DES = ((DinExt) lista.get(0)).getDinExtDesLar();




		// duda
		q = s
				.createQuery("from Ing as sex where sex.cal = '5' and (sex.ingLimInf<"
						+ INGRESO_TOTAL
						+ " and sex.ingLimSup>"
						+ INGRESO_TOTAL
						+ ")");

		lista = (List) q.list();
		INGRESO_TOTAL_FACTOR = ((Ing) lista.get(0)).getIngPto();
		INGRESO_TOTAL_WOE = ((Ing) lista.get(0)).getIngWoe();
		INGRESO_TOTAL_DES = ((Ing) lista.get(0)).getIngDesLar();



		q = s
				.createQuery("from GtoImp as sex where sex.cal = '5' and sex.gtoImpId="
						+ GASTOS);
		lista = (List) q.list();
		GASTOS_FACTOR = ((GtoImp) lista.get(0)).getGtoImpPto();
		GASTOS_WOE = ((GtoImp) lista.get(0)).getGtoImpWoe();


  	q = s
				.createQuery("from GtoAlm as sex where sex.cal = '5' and sex.gtoAlmId="
						+ CUBRIR_ALIMETOS);
		lista = (List) q.list();
		CUBRIR_ALIMETOS_FACTOR = ((GtoAlm) lista.get(0)).getGtoAlmPto();
		CUBRIR_ALIMETOS_WOE = ((GtoAlm) lista.get(0)).getGtoAlmWoe();




		q = s
				.createQuery("from GtoVes as sex where sex.cal = '5' and sex.gtoVesId="
						+ VESTIDO);
		lista = (List) q.list();
		VESTIDO_FACTOR = ((GtoVes) lista.get(0)).getGtoVesPto();
		VESTIDO_WOE = ((GtoVes) lista.get(0)).getGtoVesWoe();
		VESTIDO_DES = ((GtoVes) lista.get(0)).getGtoVesDesLar();




		q = s
				.createQuery("from Pbm as sex where sex.calId = '5' and sex.pbmId="
						+ PRINCIPAL_PROBLEMA);
		lista = (List) q.list();
		PRINCIPAL_PROBLEMA_FACTOR = ((Pbm) lista.get(0)).getPbmPto();
		PRINCIPAL_PROBLEMA_WOE = ((Pbm) lista.get(0)).getPbmWoe();



              q = s
				.createQuery("from Ptm as sex where sex.calId = '5' and sex.ptmId="
						+ SOLICITAR_PRESTAMO);
		lista = (List) q.list();
		SOLICITAR_PRESTAMO_FACTOR = ((Ptm) lista.get(0)).getPtmPto();
		SOLICITAR_PRESTAMO_WOE = ((Ptm) lista.get(0)).getPtmWoe();






		// duda
		q = s
				.createQuery("from VcsViv as sex where sex.calId = '5' and (sex.vcsVivLimInf<"
						+ VCS_VIV + " and sex.vcsVivLimSup>" + VCS_VIV + ")");
		lista = (List) q.list();
		VCS_VIV_FACTOR = ((VcsViv) lista.get(0)).getVcsVivPto();
		VCS_VIV_WOE = ((VcsViv) lista.get(0)).getVcsVivWoe();


	// duda
		if ("2".equals(String.valueOf(PAGA_RENTA)))
			I_RENTA = 3;
		else if ("1".equals(String.valueOf(PAGA_RENTA))) {
			int renta = RENTA;

			if (renta > MENSUALIDAD)
				I_RENTA = 1;
			else
				I_RENTA = 2;
		}


		q = s
				.createQuery("from IRnt as sex where sex.calId = '5' and sex.IRntId="
						+ I_RENTA);


		lista = (List) q.list();

		I_RENTA_FACTOR = ((IRnt) lista.get(0)).getIRntPto();
		I_RENTA_WOE = ((IRnt) lista.get(0)).getIRntWoe();
		I_RENTA_DES = ((IRnt) lista.get(0)).getIRntDesLar();


    
		q = s
				.createQuery("from GtoSerViv as sex where sex.cal = '5' and sex.gtoSerVivId="
						+ SERVICIOS_VIVIENDA);
		lista = (List) q.list();
		SERVICIOS_VIVIENDA_FACTOR = ((GtoSerViv) lista.get(0))
				.getGtoSerVivPto();
		SERVICIOS_VIVIENDA_WOE = ((GtoSerViv) lista.get(0)).getGtoSerVivWoe();
		SERVICIOS_VIVIENDA_DES = ((GtoSerViv) lista.get(0))
				.getGtoSerVivDesLar();


       
                 //R.G.g -> cambio de la obtencion de la renta

                double men = MENSUALIDAD;
                double ing = INGRESO_TOTAL;
                double renta = men/ing;

		q = s
				.createQuery("from GtoRen as sex where sex.cal = '5' and (" +
                                "sex.gtoRenLimInf < " + renta + " and " +
                                "sex.gtoRenLimSup >= " + renta + ")");
		/*q = sº
				.createQuery("from GtoRen as sex where sex.cal.calId = '5' and sex.gtoRenId="
						+ RENTA);*/
                // -> Termina cambio


		lista = (List) q.list();

		RENTA_FACTOR = ((GtoRen) lista.get(0)).getGtoRenPto();
		RENTA_WOE = ((GtoRen) lista.get(0)).getGtoRenWoe();
		RENTA_DES = ((GtoRen) lista.get(0)).getGtoRenDesLar();



		q = s
				.createQuery("from Srv as sex where sex.calId = '5' and sex.srvId="
						+ SERVICIO_HOGAR);
		lista = (List) q.list();
		SERVICIO_HOGAR_FACTOR = ((Srv) lista.get(0)).getSrvPto();
		SERVICIO_HOGAR_WOE = ((Srv) lista.get(0)).getSrvWoe();
		SERVICIO_HOGAR_DES = ((Srv) lista.get(0)).getSrvDesLar();



		if (VALOR_VIVIENDA < 310000)
			VALOR_VIVIENDA = 3;
		else
			VALOR_VIVIENDA = 4;

		q = s
				.createQuery("from VlrViv as sex where sex.calId = '5' and sex.vlrVivId="
						+ VALOR_VIVIENDA);
		lista = (List) q.list();
		VALOR_VIVIENDA_FACTOR = ((VlrViv) lista.get(0)).getVlrVivPto();
		VALOR_VIVIENDA_WOE = ((VlrViv) lista.get(0)).getVlrVivWoe();
		VALOR_VIVIENDA_DES = ((VlrViv) lista.get(0)).getVlrVivDesLar();



		q = s.createQuery("from NumCre as sex where sex.cal = '5'");
		lista = (List) q.list();
		NO_CREDITOS_FACTOR = ((NumCre) lista.get(NO_CREDITOS)).getNumCrePto();
		NO_CREDITOS_WOE = ((NumCre) lista.get(NO_CREDITOS)).getNumCreWoe();
		NO_CREDITOS_DES = ((NumCre) lista.get(NO_CREDITOS)).getNumCreDesLar();




		q = s
				.createQuery("from IPsi as ips where ips.cal = '5' and ips.IPsiLimInf<="
						+ SCORE_IPSICO_FACTOR
						+ " and ips.IPsiLimSup>="
						+ SCORE_IPSICO_FACTOR);
		lista = (List) q.list();
		I_PSICO_FACTOR = ((IPsi) lista.get(0)).getIPsiPto();
		I_PSICO_WOE = ((IPsi) lista.get(0)).getIPsiWoe();


     	double SCORE_NO_HIT = RELIGION_FACTOR + ESCOLARIDAD_FACTOR
				+ TRABAJO_ANTERIOR_FACTOR + DINERO_EXTRA_FACTOR
				+ INGRESO_TOTAL_FACTOR + GASTOS_FACTOR + CUBRIR_ALIMETOS_FACTOR
				+ VESTIDO_FACTOR + SERVICIOS_VIVIENDA_FACTOR + RENTA_FACTOR
				+ SERVICIO_HOGAR_FACTOR + PRINCIPAL_PROBLEMA_FACTOR
				+ SOLICITAR_PRESTAMO_FACTOR + VCS_VIV_FACTOR + I_RENTA_FACTOR
				+ VALOR_VIVIENDA_FACTOR + NO_CREDITOS_FACTOR + I_PSICO_FACTOR;

    double SCORE_NO_HIT_WOE = this.SOLICITAR_PRESTAMO_WOE + this.VCS_VIV_WOE + this.I_RENTA_WOE + this.VALOR_VIVIENDA_WOE + this.I_PSICO_WOE;

    this.SCORE_ARRAIGO_NH = (this.TRABAJO_ANTERIOR_WOE + this.PRINCIPAL_PROBLEMA_WOE + this.NO_CREDITOS_WOE);
    this.SCORE_GENERO = (this.RELIGION_WOE + this.ESCOLARIDAD_WOE);
    this.SCORE_COMPORTAMIENTO_NH = (this.DINERO_EXTRA_WOE + this.INGRESO_TOTAL_WOE + this.GASTOS_WOE + this.CUBRIR_ALIMETOS_WOE + this.VESTIDO_WOE + this.SERVICIOS_VIVIENDA_WOE + this.RENTA_WOE + this.SERVICIO_HOGAR_WOE);

    this.SCORE_ASPECTOS = this.I_PSICO_WOE;



    Element noHit = doc.createElement("NoHit");

    AgregarNodo(noHit, doc, "RelFam", RELIGION_FACTOR);
    AgregarNodo(noHit, doc, "NivEst", ESCOLARIDAD_FACTOR);
    AgregarNodo(noHit, doc, "ActUlt", TRABAJO_ANTERIOR_FACTOR);
    AgregarNodo(noHit, doc, "DinExt", DINERO_EXTRA_FACTOR);
    AgregarNodo(noHit, doc, "CliGolIng", INGRESO_TOTAL_FACTOR);
    AgregarNodo(noHit, doc, "GtoImp", GASTOS_FACTOR);
    AgregarNodo(noHit, doc, "GtoAlm", CUBRIR_ALIMETOS_FACTOR);
    AgregarNodo(noHit, doc, "GtoVes", VESTIDO_FACTOR);
    AgregarNodo(noHit, doc, "Pbm", PRINCIPAL_PROBLEMA_FACTOR);
    AgregarNodo(noHit, doc, "Ptm", SOLICITAR_PRESTAMO_FACTOR);
    AgregarNodo(noHit, doc, "VcsViv", VCS_VIV_FACTOR);
    AgregarNodo(noHit, doc, "IRenta", I_RENTA_FACTOR);
    AgregarNodo(noHit, doc, "GtoSerViv", SERVICIOS_VIVIENDA_FACTOR);
    AgregarNodo(noHit, doc, "CliGolImpRen", RENTA_FACTOR);
    AgregarNodo(noHit, doc, "Srv", SERVICIO_HOGAR_FACTOR);
    AgregarNodo(noHit, doc, "CliBurValr", VALOR_VIVIENDA_FACTOR);
    AgregarNodo(noHit, doc, "NoCreditos", NO_CREDITOS_FACTOR);
    AgregarNodo(noHit, doc, "IPsico", I_PSICO_FACTOR);
    AgregarNodo(noHit, doc, "Suma", SCORE_NO_HIT);

    doc.getDocumentElement().appendChild(noHit);

    return SCORE_NO_HIT;
  }

  private double CalculoBuroMeta(Document doc, Session s, int ACT, int HIST, int ANT, int USO, int PAGO)
  {

      System.out.println("entre a buro");
                  ACT = ACT - 13;

                    USO = USO - 24;


		Query q = s
				.createQuery("from BurAct as ba where ba.cal='4' and ba.burActId="
						+ ACT);
		List lista = (List) q.list();
		ACT_FACTOR = ((BurAct) lista.get(0)).getBurActPto();
		ACT_WOE = ((BurAct) lista.get(0)).getBurActWoe();
		ACT_TEXTO = ((BurAct) lista.get(0)).getBurActDesLar();



		q = s.createQuery("from BurHis as bh where bh.cal='4' and bh.burHisId="
				+ HIST);
		lista = (List) q.list();
		HIST_FACTOR = ((BurHis) lista.get(0)).getBurHisPto();
		HIST_WOE = ((BurHis) lista.get(0)).getBurHisWoe();
		HIST_TEXTO = ((BurHis) lista.get(0)).getBurHisDesLar();


		q = s.createQuery("from BurAnt as ba where ba.cal='4' and ba.burAntId="
				+ ANT);
		lista = (List) q.list();
		ANT_FACTOR = ((BurAnt) lista.get(0)).getBurAntPto();
		ANT_WOE = ((BurAnt) lista.get(0)).getBurAntWoe();
		ANT_TEXTO = ((BurAnt) lista.get(0)).getBurAntDesLar();


		q = s.createQuery("from BurUso as bu where bu.cal='4' and bu.burUsoId="
				+ USO);
		lista = (List) q.list();
		USO_FACTOR = ((BurUso) lista.get(0)).getBurUsoPto();
		USO_WOE = ((BurUso) lista.get(0)).getBurUsoWoe();
		USO_TEXTO = ((BurUso) lista.get(0)).getBurUsoDesLar();


		q = s.createQuery("from BurPag as bp where bp.cal='4' and bp.burPagId="
				+ PAGO);
		lista = (List) q.list();
		PAGO_FACTOR = ((BurPag) lista.get(0)).getBurPagPto();
		PAGO_WOE = ((BurPag) lista.get(0)).getBurPagWoe();
		PAGO_TEXTO = ((BurPag) lista.get(0)).getBurPagDesLar();


		double SCORE_BURO = ACT_FACTOR + HIST_FACTOR + ANT_FACTOR + USO_FACTOR
				+ PAGO_FACTOR;

    Element buro = doc.createElement("Buro");
    AgregarNodo(buro, doc, "BurAct", ACT_FACTOR);
    AgregarNodo(buro, doc, "BurHis", HIST_FACTOR);
    AgregarNodo(buro, doc, "BurAnt", ANT_FACTOR);
    AgregarNodo(buro, doc, "BurUso", USO_FACTOR);
    AgregarNodo(buro, doc, "BurPag", PAGO_FACTOR);
    AgregarNodo(buro, doc, "Suma", SCORE_BURO);

    doc.getDocumentElement().appendChild(buro);

    return SCORE_BURO;
  }

  private void PsicoSocial(Session s, int AGRESION_1, int AGRESION_4, int ADHESION_GRUPO3, int RESENTIMIENTO_SOCIAL3, int CONDUCTA_DELICTIVA2, int RESENTIMIENTO_SOCIAL4, int RESPONSABILIDAD4, int CONDUCTA_DELICTIVA5)
  {
      System.out.println("por psico");

	Query q = s.createQuery("from RelPsiSoc as rps where rps.catPsi = '1' "
				+ getValorPsico(AGRESION_1));
		List lista = (List) q.list();
		AGRESION_1_FACTOR = ((RelPsiSoc) lista.get(0)).getRelPsiSocPto();
		AGRESION_1_WOE = ((RelPsiSoc) lista.get(0)).getRelPsiSocWoe();

		q = s.createQuery("from RelPsiSoc as rps where rps.catPsi = '8' "
				+ getValorPsico(AGRESION_4));
		lista = (List) q.list();
		AGRESION_4_FACTOR = ((RelPsiSoc) lista.get(0)).getRelPsiSocPto();
		AGRESION_4_WOE = ((RelPsiSoc) lista.get(0)).getRelPsiSocWoe();

		q = s.createQuery("from RelPsiSoc as rps where rps.catPsi = '10' "
				+ getValorPsico(ADHESION_GRUPO3));
		lista = (List) q.list();
		ADHESION_GRUPO3_FACTOR = ((RelPsiSoc) lista.get(0)).getRelPsiSocPto();
		ADHESION_GRUPO3_WOE = ((RelPsiSoc) lista.get(0)).getRelPsiSocWoe();

		q = s.createQuery("from RelPsiSoc as rps where rps.catPsi = '11' "
				+ getValorPsico(RESENTIMIENTO_SOCIAL3));
		lista = (List) q.list();
		RESENTIMIENTO_SOCIAL3_FACTOR = ((RelPsiSoc) lista.get(0))
				.getRelPsiSocPto();
		RESENTIMIENTO_SOCIAL3_WOE = ((RelPsiSoc) lista.get(0))
				.getRelPsiSocWoe();

		q = s.createQuery("from RelPsiSoc as rps where rps.catPsi = '15' "
				+ getValorPsico(CONDUCTA_DELICTIVA2));
		lista = (List) q.list();
		CONDUCTA_DELICTIVA2_FACTOR = ((RelPsiSoc) lista.get(0))
				.getRelPsiSocPto();
		CONDUCTA_DELICTIVA2_WOE = ((RelPsiSoc) lista.get(0)).getRelPsiSocWoe();

		q = s.createQuery("from RelPsiSoc as rps where rps.catPsi = '17' "
				+ getValorPsico(RESENTIMIENTO_SOCIAL4));
		lista = (List) q.list();
		RESENTIMIENTO_SOCIAL4_FACTOR = ((RelPsiSoc) lista.get(0))
				.getRelPsiSocPto();
		RESENTIMIENTO_SOCIAL4_WOE = ((RelPsiSoc) lista.get(0))
				.getRelPsiSocWoe();

		q = s.createQuery("from RelPsiSoc as rps where rps.catPsi = '18' "
				+ getValorPsico(RESPONSABILIDAD4));
		lista = (List) q.list();
		RESPONSABILIDAD4_FACTOR = ((RelPsiSoc) lista.get(0)).getRelPsiSocPto();
		RESPONSABILIDAD4_WOE = ((RelPsiSoc) lista.get(0)).getRelPsiSocWoe();

		q = s.createQuery("from RelPsiSoc as rps where rps.catPsi = '25'"
				+ getValorPsico(CONDUCTA_DELICTIVA5));
		lista = (List) q.list();
		CONDUCTA_DELICTIVA5_FACTOR = ((RelPsiSoc) lista.get(0))
				.getRelPsiSocPto();
		CONDUCTA_DELICTIVA5_WOE = ((RelPsiSoc) lista.get(0)).getRelPsiSocWoe();

		// IPSICO
		SCORE_IPSICO_FACTOR = AGRESION_1_FACTOR + AGRESION_4_FACTOR
				+ ADHESION_GRUPO3_FACTOR + RESENTIMIENTO_SOCIAL3_FACTOR
				+ CONDUCTA_DELICTIVA2_FACTOR + RESENTIMIENTO_SOCIAL4_FACTOR
				+ RESPONSABILIDAD4_FACTOR + CONDUCTA_DELICTIVA5_FACTOR;

  }

  /**
	 *
	 * @param valor
	 * @return
	 */
	public String getValorPsico(int valor) {
		String cadena = "";
		if (valor == 1)
			cadena = "and (relPsiSocNumRes=1 or relPsiSocNumRes=2)";
		else
			cadena = "and (relPsiSocNumRes=3 or relPsiSocNumRes=4)";
		return cadena;
	}



  private Object getValue(Document doc, String nodo, Boolean valInt){

       NodeList list = doc.getElementsByTagName(nodo);

      if(list.getLength() != 0){

          Object val =  list.item(0).getFirstChild().getNodeValue();
          return valInt? Integer.parseInt(String.valueOf(val)): val;
      }
      return null;
    }

  private String GetValue(Document doc, String nodo){
        return (String) getValue(doc, nodo, Boolean.FALSE);
     }

  private int GetValueInt(Document doc, String nodo) throws Exception{
      return (Integer) getValue(doc, nodo, Boolean.TRUE);
  }

  private void AgregarNodo(Element raiz, Document doc, String tab, Object valor)  {
  
      //vemos si es numero para reducirle sus decimales
      DecimalFormat truncador=new DecimalFormat("####.##");
      valor=valor.getClass()==Double.class ? truncador.format(valor) :valor;


      Element child = doc.createElement(tab);
    Text text = doc.createTextNode(String.valueOf(valor));
    child.appendChild(text);
    raiz.appendChild(child);
  }

  private String xmlString(Document doc) {

        try {


           StringWriter stw = new StringWriter();
           Transformer serializer = TransformerFactory.newInstance().newTransformer();
           serializer.transform(new DOMSource(doc), new StreamResult(stw));
           return stw.toString();

        } catch (TransformerException ex) {
            Logger.getLogger(CalculadoraCGWS.class.getName()).log(Level.SEVERE, null, ex);
        } return null;
    }

  private Document creaXml(String root) {

        try {

            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element raiz = doc.createElement(root);
            doc.appendChild(raiz);
            return doc;

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CalculadoraCGWS.class.getName()).log(Level.SEVERE, null, ex);
        }return null;
    }

    public static void main(String[] args) {

        Session s;
        String alguna;
        while(true){

            HibernateUtil.getSessionFactory().openSession().createQuery("from NivEst").list();

        }






    }



    public List<String> getValoresCalculo(){

            List<String> valoresCalc=new ArrayList<String>();

                obtenerDescripcion();
		obtenerDescripcion_NH();
		obtenerArraigo();

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(0);

		if (SCORE_GENERO < 0) {
			if (SCORE_GENERO < -150.0)
				SCORE_GENERO = 150.0;
		} else {
			if (SCORE_GENERO > 150.0)
				SCORE_GENERO = 150.0;
		}

		if (SCORE_COMPORTAMIENTO < 0) {
			if (SCORE_COMPORTAMIENTO < -150.0)
				SCORE_COMPORTAMIENTO = 150.0;
		} else {
			if (SCORE_COMPORTAMIENTO > 150.0)
				SCORE_COMPORTAMIENTO = 150.0;
		}

		if (SCORE_ARRAIGO < 0) {
			if (SCORE_ARRAIGO < -150.0)
				SCORE_ARRAIGO = 150.0;
		} else {
			if (SCORE_ARRAIGO > 150.0)
				SCORE_ARRAIGO = 150.0;
		}

		if (SCORE_ASPECTOS < 0) {
			if (SCORE_ASPECTOS < -150.0)
				SCORE_ASPECTOS = 150.0;
		} else {
			if (SCORE_ASPECTOS > 150.0)
				SCORE_ASPECTOS = 150.0;
		}

		valoresCalc.add("-1");
		valoresCalc.add("-1");
		valoresCalc.add(nf.format(SCORE_GENERO));// GENERO
		valoresCalc.add(nf.format(SCORE_COMPORTAMIENTO));// COMPORTAMIENTO_PAGO
		valoresCalc.add(nf.format(SCORE_ARRAIGO));// ARRAIGO
		valoresCalc.add(nf.format(SCORE_ASPECTOS));// ASPECTOS_DIF

		if (REGBURO == 1) {// SI ES UNO ES HIT
			valoresCalc.add(((GENERO_DES == null) ? "" : GENERO_DES));// GENERO_CLASE_TEXTO_1
			valoresCalc.add(((FRECUENCIA_RELIGION_DES == null) ? ""
					: FRECUENCIA_RELIGION_DES));// GENERO_CLASE_TEXTO_2

			valoresCalc.add(((PRIMER_TEXTO_COMPORTAMIENTO == null) ? ""
					: PRIMER_TEXTO_COMPORTAMIENTO));// COMPORTAMIENTO_DE_PAGO_TEXTO_1

			valoresCalc.add(((SEGUNDO_TEXTO_COMPORTAMIENTO == null) ? ""
					: SEGUNDO_TEXTO_COMPORTAMIENTO));// COMPORTAMIENTO_DE_PAGO_TEXTO_2

			valoresCalc.add(((VIVIENDA_ACTUAL_DES == null) ? ""
					: VIVIENDA_ACTUAL_DES));// ARRAIGO_TEXTO_1

			valoresCalc.add(((SITUACION_PROXIMO_ANO_DES == null) ? ""
					: SITUACION_PROXIMO_ANO_DES));// ARRAIGO_TEXTO_2

			valoresCalc.add(((I_PSICO_DES == null) ? "" : I_PSICO_DES));// ASPECTOS_TEXTO_1

			valoresCalc.add("");// ASPECTOS_TEXTO_2

		} else {// NO HIT
			valoresCalc.add(((GENERO_DES == null) ? "" : GENERO_DES));// GENERO_CLASE_TEXTO_1

			valoresCalc.add(((ESCOLARIDAD_DES == null) ? "" : ESCOLARIDAD_DES));// GENERO_CLASE_TEXTO_2

			valoresCalc.add(((PRIMER_TEXTO_COMPORTAMIENTO == null) ? ""
					: PRIMER_TEXTO_COMPORTAMIENTO));// COMPORTAMIENTO_DE_PAGO_TEXTO_1

			valoresCalc.add(((SEGUNDO_TEXTO_COMPORTAMIENTO == null) ? ""
					: SEGUNDO_TEXTO_COMPORTAMIENTO));// COMPORTAMIENTO_DE_PAGO_TEXTO_2

			valoresCalc.add(((PRIMER_TEXTO_ARRAIGO == null) ? ""
					: PRIMER_TEXTO_ARRAIGO));// ARRAIGO_TEXTO_1

			valoresCalc.add(((SEGUNDO_TEXTO_ARRAIGO == null) ? ""
					: SEGUNDO_TEXTO_ARRAIGO));// ARRAIGO_TEXTO_2

			valoresCalc.add(((I_PSICO_DES == null) ? "" : I_PSICO_DES));// ASPECTOS_TEXTO_1

			valoresCalc.add("");// ASPECTOS_TEXTO_2
		}
		valoresCalc.add(((this.RESULTADO == null) ? "" : this.RESULTADO));// RESULTADO

                return valoresCalc;
    }



    private void obtenerDescripcion_NH() {
		DINERO_EXTRA_FACTOR = ABSOLUTO(DINERO_EXTRA_FACTOR);
		INGRESO_TOTAL_FACTOR = ABSOLUTO(DINERO_EXTRA_FACTOR);
		GASTOS_FACTOR = ABSOLUTO(DINERO_EXTRA_FACTOR);
		CUBRIR_ALIMETOS_FACTOR = ABSOLUTO(DINERO_EXTRA_FACTOR);
		VESTIDO_FACTOR = ABSOLUTO(DINERO_EXTRA_FACTOR);
		SERVICIOS_VIVIENDA_FACTOR = ABSOLUTO(DINERO_EXTRA_FACTOR);
		RENTA_FACTOR = ABSOLUTO(RENTA_FACTOR);
		SERVICIO_HOGAR_FACTOR = ABSOLUTO(DINERO_EXTRA_FACTOR);

		String arregloTex[] = { DINERO_EXTRA_DES, INGRESO_TOTAL_DES,
				GASTOS_DES, CUBRIR_ALIMETOS_DES, VESTIDO_DES,
				SERVICIOS_VIVIENDA_DES, RENTA_DES, SERVICIO_HOGAR_DES };
		double arregloDes[] = { DINERO_EXTRA_FACTOR, INGRESO_TOTAL_FACTOR,
				GASTOS_FACTOR, CUBRIR_ALIMETOS_FACTOR, VESTIDO_FACTOR,
				SERVICIOS_VIVIENDA_FACTOR, RENTA_FACTOR, SERVICIO_HOGAR_FACTOR };

		ordena(arregloDes, arregloTex);
		PRIMER_PESO_COMPORTAMIENTO_NH = arregloDes[0];
		PRIMER_TEXTO_COMPORTAMIENTO_NH = arregloTex[0];
		SEGUNDO_PESO_COMPORTAMIENTO_NH = arregloDes[1];
		SEGUNDO_TEXTO_COMPORTAMIENTO_NH = arregloTex[1];
	}

	private void obtenerDescripcion() {

		DINERO_EXTRA_FACTOR = ABSOLUTO(DINERO_EXTRA_FACTOR);
		SERVICIO_HOGAR_FACTOR = ABSOLUTO(SERVICIO_HOGAR_FACTOR);
		ACT_FACTOR = ABSOLUTO(ACT_FACTOR);
		USO_FACTOR = ABSOLUTO(USO_FACTOR);
		CUEN_ABIER_CAT_FACTOR = ABSOLUTO(CUEN_ABIER_CAT_FACTOR);
		VALOR_VIVIENDA_FACTOR = ABSOLUTO(VALOR_VIVIENDA_FACTOR);
		NO_CREDITOS_FACTOR = ABSOLUTO(NO_CREDITOS_FACTOR);

		String arregloTex[] = { DINERO_EXTRA_DES, SERVICIO_HOGAR_DES,
				ACT_TEXTO, USO_TEXTO, CUEN_ABIER_CAT_DES, VALOR_VIVIENDA_DES,
				NO_CREDITOS_DES, };
		double arregloDes[] = { DINERO_EXTRA_FACTOR, SERVICIO_HOGAR_FACTOR,
				ACT_FACTOR, USO_FACTOR, CUEN_ABIER_CAT_FACTOR,
				VALOR_VIVIENDA_FACTOR, NO_CREDITOS_FACTOR };
		ordena(arregloDes, arregloTex);
		PRIMER_PESO_COMPORTAMIENTO = arregloDes[0];
		PRIMER_TEXTO_COMPORTAMIENTO = arregloTex[0];
		SEGUNDO_PESO_COMPORTAMIENTO = arregloDes[1];
		SEGUNDO_TEXTO_COMPORTAMIENTO = arregloTex[1];
	}

	private void obtenerArraigo() {

		TRABAJO_ANTERIOR_FACTOR = ABSOLUTO(TRABAJO_ANTERIOR_FACTOR);
		PRINCIPAL_PROBLEMA_FACTOR = ABSOLUTO(PRINCIPAL_PROBLEMA_FACTOR);
		NO_CREDITOS_FACTOR = ABSOLUTO(NO_CREDITOS_FACTOR);

		String arregloTex[] = { TRABAJO_ANTERIOR_DES, PRINCIPAL_PROBLEMA_DES,
				NO_CREDITOS_DES };

		double arregloDes[] = { TRABAJO_ANTERIOR_FACTOR,
				PRINCIPAL_PROBLEMA_FACTOR, NO_CREDITOS_FACTOR };

		ordena(arregloDes, arregloTex);
		PRIMER_PESO_ARRAIGO = arregloDes[0];
		PRIMER_TEXTO_ARRAIGO = arregloTex[0];
		SEGUNDO_PESO_ARRAIGO = arregloDes[1];
		SEGUNDO_TEXTO_ARRAIGO = arregloTex[1];
	}

	private double ABSOLUTO(double variable) {
		return Math.abs(variable);
	}

	private void ordena(double[] arregloDes, String[] arregloTex) {
		for (int i = 0; i < arregloDes.length - 1; i++)
			for (int j = i + 1; j < arregloDes.length; j++)
				if (arregloDes[i] < arregloDes[j]) {
					double tempD = arregloDes[i];
					arregloDes[i] = arregloDes[j];
					arregloDes[j] = tempD;
					String tempS = arregloTex[i];
					arregloTex[i] = arregloTex[j];
					arregloTex[j] = tempS;
				}
	}

}