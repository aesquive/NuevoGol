
package utilerias;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ruben G.g
 */
public class Espejo {


    /**
     * Si no existe el metodo devuelve null
     * @param objetivo
     * @param metodoGet
     * @return
     */
    public static Method getMetodo(Object objetivo, String metodoGet){

        String nombre = genGet(metodoGet);

        for(Method m: objetivo.getClass().getMethods()){

            if(m.getName().equals(nombre)){
                return m;
            }
        }return null;
    }

    public static String genGet(String nombre){
        return "get" + nombre.substring(0,1).toUpperCase() + nombre.substring(1);
    }

    public static Object invocaGet(Object objetivo, String getNom){
        
        Method m = getMetodo(objetivo, getNom);
        
        if(m != null){
            
            try {
                
               return m.invoke(objetivo, new Object[]{});
                
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        throw new IllegalArgumentException("Metodo " + getNom + " no encontrado");
                
    }
    
    
    public static Object invocaGet(Object objetivo, Method get){
        
        try {
            
            return get.invoke(objetivo, (Object[]) null);
            
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Espejo.class.getName()).log(Level.SEVERE, null, ex);
        }return null;
    }
    
    

    public static void main(String[] args) {

       

    }

}
