package dao;



import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import objetos.*;


/**
 *
 * @author Ruben G.g
 *
 */
public class Dao {


    private Session sesion = HibernateUtil.getSessionFactory().openSession();
    private String paqt = "objetos";

    public List get(String tabla){
        return get(tabla, null);
    }


    public List get(String tabla, String where){


        String condicion = where == null? "": " where " + where;
        return sesion.createQuery("from " + tabla + " "+ condicion).list();

    }

    /**
     * Busca una seccion dependiendo el calid, toma el primero que encuentra
     * toma el maximo id de la tabla cal
     * @param tabla
     * @return
     */

    public List buscaCombo(String tabla,String cal){

          String calId = buscaCal(tabla);

           Query q = sesion.createQuery("from " + tabla + " where " + calId + "=" + cal);


            if(q.list().size() > 0){
                return q.list();
            }

        return null;
    }

    /**
     * obtiena la lista por el primer calid que encuentra
     * @param tabla
     * @return
     */
    public Object[][] obtenLista(String tabla,String cal){

        List list = buscaCombo(tabla,cal);
        return expande(list);
    }


    public Object[][] expande(List pojos){

        try {

            if(pojos.size() < 1){return null;}
            return expande(pojos, -1);

        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }return null;

    }


        public Serializable save(Object o){

            Transaction trans =  sesion.beginTransaction();
            Serializable aGuardar=sesion.save(o);//sesion.saveOrUpdate(o);
            trans.commit();

            return aGuardar;

        }
        



    public Object[][] expande(List pojos, int foo) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

        String[] nombre = pojos.get(0).getClass().getName().split("\\.");
        String get = "get" + nombre[nombre.length - 1];

        Method getDes = pojos.get(0).getClass().getMethod(get + "Des", new Class[]{});
        Method getId = pojos.get(0).getClass().getMethod(get + "Id", new Class[]{});

        Object[] llaves = new Object[pojos.size()];
        Object[] vals = new Object[pojos.size()];

        for (int i = 0; i < vals.length; i++) {

            llaves[i] = getDes.invoke(pojos.get(i), new Object[]{});
            vals[i] = getId.invoke(pojos.get(i), new Object[]{});
        }
        return  new Object[][]{llaves,vals};
    }





    public Object[][] obtenListaSrv( int limInf, int limSup) {

        List lista=obtenerTodoComboConLimites(limInf,limSup);

        return expande(lista);

    }

    public List obtenerTodoComboConLimites( int limInf, int limSup){

        List<Object> lista=new ArrayList<Object>();

        int max = 12;

        for (int i = 0; i <= max  ; i++) {

            Query q = sesion.createQuery("from " + "Srv" + " where (calId=" + i +" and (srvId>="+limInf+" and srvId<="+limSup+" ))");

             if(q.list().size() > 0){

                 for(Object o:q.list()){
                     lista.add(o);
                 }
            }

        }


        if(lista.size() > 0){

            return lista;
           }


        return null;
    }

    public Object[][] obtenListaOcp(int limInf, int limSup) {


        List lista=obtenerTodoComboConLimitesOcp(limInf,limSup);

        return expande(lista);

    }

    private List obtenerTodoComboConLimitesOcp(int limInf, int limSup) {

        List<Object> lista=new ArrayList<Object>();

        int max = 12;

        for (int i = 0; i <= max  ; i++) {

            Query q = sesion.createQuery("from " + "Ocp" + " where (cal=" + i +" and (ocpId>="+limInf+" and ocpId<="+limSup+" ))");

             if(q.list().size() > 0){

                 for(Object o:q.list()){
                     lista.add(o);
                 }
            }

        }


        if(lista.size() > 0){

            return lista;
           }


        return null;
    }



    public String buscarCp(String valor) {

        CodPos cp=(CodPos)sesion.createQuery("from CodPos where codPosId="+valor).uniqueResult();

        return convertirCodPosJson(cp);

    }

    private String convertirCodPosJson(CodPos cp) {

        String mun=cp.getCodPosNomMun();

        String edo=getEdo(cp);

        return "{\"mun\":\""+mun+"\",\"edo\":\""+edo+"\"}";

    }

    private String getEdo(CodPos cp) {

        int idEdo=cp.getEdo().getEdoId();

        Edo edo=(Edo)sesion.createQuery("from Edo where edoId="+idEdo).uniqueResult();

        return edo.getEdoDes();
    }


    public List<Object[]>obtenClis(){


        List<Object[]> clis = sesion.createQuery("select  gol.cliGolId, cli.cliApePat, cli.cliApeMat, cli.cliNom from CliGol gol ,Cli cli where gol.cliId = cli.cliId").list();       
        return clis;

    }

    public static void main(String[] args) {

        Dao dao = new Dao();
        List l=dao.getCriteria(ArbGolAu.class);
        
    }

    //regresa el cliente cli con el id
    public Cli getCli(Integer idCli) {

        return (Cli)sesion.createQuery("from Cli where cliId="+idCli).uniqueResult();

    }

    public CliGol getCliGol(Integer idGol) {

        return (CliGol) sesion.createQuery("from CliGol where cliGolId="+idGol).uniqueResult();
    }

    public CliBur getCliBur(int idCli) {

        return (CliBur) sesion.createQuery("from CliBur where cliId="+idCli).uniqueResult();
    }

    private String buscaCal(String tabla) {


        try {

            Class c = Class.forName(paqt + "." +tabla);


            for(Method m: c.getMethods()){

                String nombre=m.getName();

                if(nombre.contains("CalId")){
                    return "calId";
                }

            }

            return "cal";

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String[][] obtenerClientes() {

        //[0][0] idGol
        //[0][1] nombre
        String[][] clientes;

       List<Object[]> lista =this.sesion.createQuery("select cliGolId,cliId from CliGol").list();

       clientes=new String[lista.size()][2];

       String[][] nombresClientes=obtenerNombresClis(lista);


       return formarClientes(lista,nombresClientes);
    }
/**
 * en la parte de regreso[0] viene el idcli , esto xq no todos los clientes que regresa el query estan en la base
 * entonces se corre el riesgo de que la base regrese menos
 * en la parte de regreso[1] viene el nombre
 * @param lista
 * @return
 */
    private String[][] obtenerNombresClis(List<Object[]> lista) {
        //el objeto[0] es el idGol
        //el objeto[1] es el idCli
        String[][] arreglo;

        StringBuilder builder= new StringBuilder();

        for(int t=0;t<lista.size();t++){
            builder.append(lista.get(t)[1].toString()+",");
        }

        String numeros=builder.toString().substring(0,builder.toString().length()-1);


        List<Object[]> nombres=this.sesion.createQuery("select cliApePat,cliApeMat,cliNom,cliId from Cli where cliId in ("+numeros+")").list();

        arreglo=new String[nombres.size()][2];

        for(int t=0;t<nombres.size();t++){
            arreglo[t][0]=nombres.get(t)[3].toString();
            arreglo[t][1]=nombres.get(t)[0].toString()+" "+nombres.get(t)[1].toString()+" "+nombres.get(t)[2].toString();
        }

        return arreglo;
    }

    private String[][] formarClientes(List<Object[]> lista, String[][] nombresClientes) {
        //lista[0][0] idGol
        //lista[0][1] idCli
        //nombrescLIENTES [0][0] idCli
        //nombresClientes[0][1] nombreCliente

        String[][] arreglo=new String[nombresClientes.length][2];
        //arreglo[0][0] idgol
        //arreglo[0][1] nombre
        int indice=0;

        for(int t=0;t<nombresClientes.length;t++){

            String idArrNom=nombresClientes[t][0];

            //el cliente no puede estar antes de la lista , entonces buscamos solo en los posteriores a su id
            for(int k=t;k<lista.size();k++){

                String idCliLista=lista.get(k)[1].toString();


                if(idArrNom.equals(idCliLista)){

                    arreglo[indice][0]=lista.get(k)[0].toString();
                    arreglo[indice][1]=nombresClientes[t][1];
                    indice++;
                    break;
                }

            }   

        }

        return arreglo;
    }



   private String expandeDes(Map<String, Object> vals, List<String> excluye){
        
        StringBuilder colums = new StringBuilder("");
        StringBuilder tablas = new StringBuilder("");
        StringBuilder ids = new StringBuilder("");        
        
        for(Iterator<String> i = vals.keySet().iterator(); i.hasNext();){
                        
            String k = i.next();
            
            if(!excluye.contains(k)){
               
                
                String alias = k.substring(0, 1).toLowerCase().concat(k.substring(1));
                
                String prefixTab = tablas.length() == 0? " from ": ", ";
                String prefixId = ids.length() == 0? " ": " AND ";                
                
                colums.append(alias).append("_alias").append(".").append(alias).append("Des, ");
                tablas.append(prefixTab).append(k).append(" as ").append(alias).append("_alias");                        
                ids.append(prefixId).append(alias).append("_alias.").append(alias).append("Id").append(" = ").append(vals.get(k));                 
            }
        }
        System.out.println(" las columnas " + colums.toString());
        
        return tablas.append(" where ").append(ids).toString();
    }
   
   /*public void pegaPedazos(String k, String ){
         
       String alias = k.substring(0, 1).toLowerCase().concat(k.substring(1));
                
        String prefixTab = tablas.length() == 0? " from ": ", ";
        String prefixId = ids.length() == 0? " ": " AND ";                

        tablas.append(prefixTab).append(k).append(" as ").append(alias).append("_alias");                        
        ids.append(prefixId).append(alias).append("_alias.").append(alias).append("Id").append(" = ").append(vals.get(k));                 

   }*/
    
      /**
     * Genera la consulta hql de la lista de tablas
     * @param vals
     * @return 
     */
    private String expande(Map<String, Object> vals, List<String> excluye){
        
        StringBuilder tablas = new StringBuilder("");
        StringBuilder ids = new StringBuilder("");        
        
        for(Iterator<String> i = vals.keySet().iterator(); i.hasNext();){
                        
            String k = i.next();
            
            if(!excluye.contains(k)){
               
                String alias = k.substring(0, 1).toLowerCase().concat(k.substring(1));
                
                String prefixTab = tablas.length() == 0? " from ": ", ";
                String prefixId = ids.length() == 0? " ": " AND ";                
                
                tablas.append(prefixTab).append(k).append(" as ").append(alias).append("_alias");                        
                ids.append(prefixId).append(alias).append("_alias.").append(alias).append("Id").append(" = ").append(vals.get(k));                 
            }
        }
        
        return tablas.append(" where ").append(ids).toString();
    }
    
    
    public Object[] ObtenLista(Map<String, Object> vals, List<String> excluye){
        
        String q = expandeDes(vals, excluye);
        List<Object[]> l = sesion.createQuery(q).list();
        return l.get(0);
    }

    private List getCriteria(Class<ArbGolAu> aClass) {
        return sesion.createCriteria(aClass).list();
    }

    public List getTabla(Class aClass) {
        return sesion.createCriteria(aClass).list();
    }

    public void cerrarSesion() {
        sesion=null;
    }

    public void guardar(Object o) {
        Transaction tran=sesion.beginTransaction();
        sesion.save(o);
        tran.commit();
    }
  
    
    

}
