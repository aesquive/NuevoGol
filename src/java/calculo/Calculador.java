/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculo;

import dao.Dao;
import java.util.List;
import objetos.*;
import utilerias.Conversor;

/**
 *
 * @author Quants
 */
public class Calculador {

    private static Conversor conversor =new Conversor();
    private static Dao dao;
    private static CalculadoraAu calcAu;

    private final String raiz="EntradaCalculadora";

    public Calculador(){
        conversor=new Conversor();
        dao=new Dao();
        calcAu=new CalculadoraAu();
    }

    /*
     * calcula al cliente con el idGol correspondiente
     */
    public String calcula(String idGol) {

        CliGol gol=dao.getCliGol(Integer.parseInt(idGol));

        String xmlEntrada=conversor.convierte(raiz, gol);

        System.out.println(xmlEntrada);

        String xmlSalida=calcAu.CalcularCG(xmlEntrada);

        System.out.println(xmlSalida);

        return xmlSalida;
    }

    /*
     * Regresa las variables del calculo que estaran guardadas en el xmlSalida
     */
    public List<String> getNotasCalculo(){
            List<String> vals=calcAu.getValoresCalculo();
            //creamos nueva instancia para evitar que el valor previo de las variables influya
            calcAu=new CalculadoraAu();

            return vals;

    }

    public String obtenerNombre(String xmlSalida) {

        return calcAu.nombre;

    }

    public static void main(String[] args) {
        Calculador cal= new Calculador();
        cal.calcula("821");
    }


}
