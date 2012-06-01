
package dao;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import objetos.Cal;
import objetos.Cli;
import objetos.CliBur;
import objetos.CliGol;
import org.hibernate.Session;

/**
 *
 * @author Ruben
 */
public class GestorBase {

    
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    
    
    /**
     * Obtiene un arreglo de CliGol que tienen los ids indicados
     * @param ids (id1, id2, id3....idn)
     * @return 
     */
    public CliGol[] getClis(String ids){
        
        List<CliGol> l = sesion.createQuery("from CliGol where cliGolId in " + ids).list();
        return l.toArray(new CliGol[]{});   
    }
    
    
    public CliGol[] getClis(){
        
        List<CliGol> l = sesion.createQuery("from CliGol").list();
        return l.toArray(new CliGol[]{});   
    }
    
    public CliGol getCliGol(Integer id){
        
        return (CliGol) sesion.get(CliGol.class, id);
    }
   
    public CliBur getCliBur(Integer cliId){
        
        List<CliBur> brs =  sesion.createQuery("from CliBur where cliId = " + cliId).list();
        
        return brs.isEmpty()? null: brs.get(brs.size() - 1);
    }
   
    public Cli getCli(Integer id){
        
        return (Cli) sesion.get(Cli.class, id);
    }
        
    
    
    public static void main(String[] args) {
        
        GestorBase base = new GestorBase();
        System.out.println( base.getClis().length);
       
        
    }

    public CliBur getCliBur(BigDecimal cliBurId) {
        
        return (CliBur) sesion.get(CliBur.class, cliBurId);
    }

    

    public Cli getCli(BigDecimal cliId) {
        
        return (Cli) sesion.get(Cli.class, cliId);
    }
}
