/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;


import calculo.Calculador;
import calculo.CalculadoraAu;
import calculo.CalculadoraCGWS;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objetos.Cli;
import objetos.CliGol;
import utilerias.Conversor;
/**
 *
 * @author Quants
 */
public class AccionesServlet extends HttpServlet {

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

            String accion=request.getParameter("accion");

            if(accion.equals("lista")){

                lista(response);
            }
            if(accion.equals("calcularGol")){

                calcular(request,response);

            }
            //lo unico que hace es limpiar la sesion para que la grafica no se vea llena
            if(accion.equals("irCalcular")){
                limpiarSesion(request);
            }


        } finally {
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


    /**
     * metodo que regresa la lista de clientesGol
     * @param response
     */
    private void lista(HttpServletResponse response) {
        try {
            System.out.println("generandoLista");

            response.getWriter().print(getLista());
        } catch (IOException ex) {
            Logger.getLogger(AccionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * metodo que genera los json de cligols
     *
     * @param regs registros de gol
     * @return
     */
    public String getLista(){
        
        List<Object[]> gols = dao.obtenClis();
        JsonArray lista = new JsonArray();
        
        
        for (Object[] gol : gols) {
        
            
            JsonObject cli = new JsonObject();            
            cli.add("idGol", new JsonPrimitive(String.valueOf(gol[0])));
            String nombre = String.valueOf(gol[1]) + " " + String.valueOf(gol[2]) + " " + String.valueOf(gol[3]);
            cli.add("nombre", new JsonPrimitive(nombre));
            lista.add(cli);
        }               
        return lista.toString();
 }


    /**
     * metodo que convierte a json
     *
     * "nombre":"nombreCliente","idGol":idDelClienteGol,"idCli":"idDelCliente"
     *
     * @param gol , el cliente que se desea convertir a json
     * @return
     */
    private String gol2json(CliGol gol) {

        Gson gson = new Gson();
        JsonObject el = new JsonObject();


        StringBuilder builder=new StringBuilder("{");
        String comillas="\"";

        //sacamos el id del cligol
        Integer idG=gol.getCliGolId();

        //sacamos el id del cli
        Integer idCli=gol.getCliId();

        //buscamos al cliente
        Cli cli=dao.getCli(idCli);

        //sacamos su nombre
        String nombre=cli.getCliApePat()+" "+cli.getCliApeMat()+" "+cli.getCliNom();

        //convertimos a json
        builder.append(comillas+"nombre"+comillas+":"+comillas+nombre+comillas+","+
                        comillas+"idGol"+comillas+":"+comillas+idG+comillas);

        builder.append("}");

        return builder.toString();

    }

    private void calcular(HttpServletRequest request, HttpServletResponse response) {

            String idGol=request.getParameter("idG");

            Calculador calculador=new Calculador();


            String xmlSalida=calculador.calcula(idGol);

             String nombre=calculador.obtenerNombre(xmlSalida);

            String respuesta=valoresCalculo(request,calculador,nombre);

            System.out.println(respuesta);

            regresarCalculo(respuesta, response);

    }

    private String valoresCalculo(HttpServletRequest request,Calculador calculador,String nombre ) {

        List<String> vars=calculador.getNotasCalculo();

        StringBuilder builder=new StringBuilder("{");

        String comillas="\"";

        for(int t=0;t<vars.size();t++){
                System.out.println(t+" "+vars.get(t));
            meterEnSesion(request,vars.get(t),t);

            builder.append(comillas+t+comillas+":"+comillas+vars.get(t)+comillas+",");
        }


        builder.append(comillas+vars.size()+comillas+":"+comillas+nombre+comillas+"}");

        return builder.toString();

    }


    private void regresarCalculo(String jsonCalculo, HttpServletResponse response) {
        try {


            response.getWriter().print(jsonCalculo);
        } catch (IOException ex) {
            Logger.getLogger(AccionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void meterEnSesion(HttpServletRequest request,String valor, int t) {
        //t=2 genero
        //t=3 comportamiento
        //t=4 arraigo
        //t=5 aspectos diferenciadores



        if(t==2){
            request.getSession().setAttribute("genero", valor);
        }
        if(t==3){
            request.getSession().setAttribute("comportamiento", valor);
        }
        if(t==4){
            request.getSession().setAttribute("arraigo", valor);
        }
        if(t==5){
            request.getSession().setAttribute("aspectos", valor);
        }
        


    }

    private void limpiarSesion(HttpServletRequest request) {

        HttpSession sesion=request.getSession();

        sesion.removeAttribute("comportamiento");
        sesion.removeAttribute("genero");
        sesion.removeAttribute("arraigo");
        sesion.removeAttribute("aspectos");


    }



}
