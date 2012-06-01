package servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import objetos.*;

/**
 *
 * @author Quants
 */
public class NuevoGolServlet extends HttpServlet {


    private CliGol gol;
    private CliBur bur;
    private Cli cli;
    private Dao dao=new Dao();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            guarda(request, response);
        }

        finally {
            out.close();
        }
    } 


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


    private void guarda(HttpServletRequest request, HttpServletResponse response){

        this.cli=new Cli();
        this.gol=new CliGol();
        this.bur=new CliBur();

        inyectarGol( request,  response);

        //guardarFecha(request,response);

        guardarGol(request,response);

        cli=null;
        gol=null;
        bur=null;
    }

    private void inyectarGol(HttpServletRequest request, HttpServletResponse response) {

            Enumeration<String> variables=request.getParameterNames();

            while(variables.hasMoreElements()){



                String varActual = variables.nextElement();

                Object valor = request.getParameter(varActual);
                

                //en 0=tipoCliente , 1=metodoSet , 2=tipoDeValor

                Object[] attr = buscarAtributos(varActual);

                System.out.println("var Actual = " + varActual);
                
                System.out.println("valor = "+valor);

                if(attr!=null){

                            Object cliente = attr[0];


                        Method metodo = (Method) attr[1];

                        
                        Class regreso = (Class) attr[2];

                        
                        Object args=regresarTipoArgumento(regreso,valor);

                        if(args!=null){

                            invocar(cliente,metodo,args);

                        }

                   
                }


            }


    }




    private void guardarGol(HttpServletRequest request , HttpServletResponse response) {
        
            //por default
            cli.setCalId(4);
            asignacionesEspeciales(request);
            Serializable cliGuardado = dao.save(cli);
            gol.setCliId((Integer) cliGuardado);
            bur.setCliId((Integer) cliGuardado);
            dao.save(gol);
            dao.save(bur);

            System.out.println("Guardado cliente cliId "+gol.getCliId());
        
    }


    /**
     * para los campos que se repiten en las tablas
     */
    private void asignacionesEspeciales(HttpServletRequest request){

        //tip emp=tipoact
        gol.setTipEmpId(Integer.parseInt(cli.getCliTipoAct()));
        //srv=sector
        gol.setSrvId(Integer.parseInt(cli.getCliSector()));
        //ocp=subsector
        gol.setOcpId(Integer.parseInt(cli.getCliSubsector()));
        //ocuact=ocupacion
        gol.setCliGolOcuAct(cli.getCliOcupacion());
        //rol=clirol
        gol.setRolId(Integer.parseInt(cli.getCliRol()));
        //cligolnumdep=clidependientes
        gol.setCliGolNumDep(Integer.parseInt(cli.getCliDependientes()));
        //cligoldesing=cliingreso
        gol.setCliGolDesIng(cli.getCliIngreso());

        //burUso se guarda en bur , pero con los ids de bur entonces debemos asignarle su respectivo valor en gol %11

        
            Integer uso= bur.getBurUsoId();

            if(uso!=null){

                     gol.setBurUso(uso%11);
            }

           

        
        
        //caso muy muy especial
        gol.setEstId(Integer.parseInt(request.getParameter("setEstId")));
    }




    //0=tipocliente , 1=metodoSet ,2=tipoDeValorDeRegreso
    private Object[] buscarAtributos(String varActual) {

        Class golCl=gol.getClass();
        Class cliCl=cli.getClass();
        Class burCl=bur.getClass();



        if(varActual.equals("dinExt") || varActual.equals("relFam")
                || varActual.equals("freRel") || varActual.equals("rel")){
            return parche(varActual);
        }

       Object[] arreglo=buscarEnClase(golCl,varActual);


            if(arreglo!=null){

                    return arreglo;
            }

            arreglo=buscarEnClase(cliCl,varActual);

             if(arreglo!=null){

                    return arreglo;
            }

            arreglo=buscarEnClase(burCl,varActual);

             if(arreglo!=null){

                    return arreglo;
            }
            else{
                    return null;
            }


    }

        //0=tipocliente , 1=metodoSet ,2=tipoDeValorDeRegreso
    private Object[] buscarEnClase(Class clase, String varActual) {

            Object[] array=new Object[3];

            Method metodo=null;

            Method get=null;

          //var actual=sex -----> Sex
          String variable=varActual.substring(0,1).toUpperCase()+varActual.substring(1, varActual.length());

          for( Method m : clase.getMethods() ){

                if(m.getName().contains(variable) && m.getName().contains("set")){
                    metodo=m;
                }
                if(m.getName().contains(variable) && m.getName().contains("get")){
                    get=m;
                }
                if(metodo!=null){
                    break;
                }
          }

          if(metodo==null){
              return null;
          }

          //buscamos el tipo de regreso del metodo
          Class regreso=get==null ?String.class : get.getReturnType();

           

          if(gol.getClass()==clase){
                array[0]=gol;
          }
          else if(cli.getClass()==clase){
              array[0]=cli;
          }
          else if(bur.getClass()==clase){
              array[0]=bur;
          }


           array[1]=metodo;
           array[2]=regreso;

          return array;
    }

    private Object regresarTipoArgumento(Class tipoDeRegreso, Object valorVariable) {
        try {
            
            boolean esCaracter = tipoDeRegreso.equals(Character.class);
            Object valor = esCaracter ? valorVariable.toString().charAt(0) : valorVariable.toString().toUpperCase();
            Class clase = esCaracter ? char.class : String.class;
            Object ob = tipoDeRegreso.getConstructor(clase).newInstance(valor);

            return ob;
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(NuevoGolServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(NuevoGolServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(NuevoGolServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NuevoGolServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(NuevoGolServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(NuevoGolServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

           return null;

    }

    private void invocar(Object cliente, Method metodo, Object args) {
        try {
            metodo.invoke(cliente, args);

        } catch (IllegalAccessException ex) {
            return;
        } catch (IllegalArgumentException ex) {
            return;
        } catch (InvocationTargetException ex) {
            return;
        }finally{

             return;
        }
    }

    private Object[] parche(String varActual) {
          Object[] array=new Object[3];

          array[0]=gol;

          array[1]=metodoParche(varActual);

          array[2]=Integer.class;

          return array;
    }

    private Method metodoParche(String varActual) {

        try {

        Class clase=gol.getClass();

        String nombre="";

        if(varActual.equals("dinExt"))

            nombre="setDinExtId";
        else if(varActual.equals("relFam")) {
            nombre="setRelFamId";
        }
        else if(varActual.equals("rel")){
            nombre="setRelId";
        }
        else if(varActual.equals("freRel")){
            nombre="setFreRelId";
        }

            return clase.getMethod(nombre, Integer.class);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(NuevoGolServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(NuevoGolServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void guardarFecha(HttpServletRequest request, HttpServletResponse response) {
        String dia=request.getParameter("dia");
        String mes=request.getParameter("mes");
        String anio=request.getParameter("anio");

        if(dia!=null && mes!=null && anio!=null){
            try {
                SimpleDateFormat dt = new SimpleDateFormat("dd-mm-aaaa");
                Date dte = dt.parse(dia + "-" + mes + "-" + anio);
                cli.setCliFecNac(dte);
            } catch (ParseException ex) {
                Logger.getLogger(NuevoGolServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }



}
