package objetos;
// Generated 1/06/2012 02:12:04 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ArbGolAu generated by hbm2java
 */
public class ArbGolAu  implements java.io.Serializable {


     private Integer id;
     private Integer usuId;
     private Integer cliId;
     private String cve;
     private Date fec;

    public ArbGolAu() {
    }

    public ArbGolAu(Integer usuId, Integer cliId, String cve, Date fec) {
       this.usuId = usuId;
       this.cliId = cliId;
       this.cve = cve;
       this.fec = fec;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUsuId() {
        return this.usuId;
    }
    
    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }
    public Integer getCliId() {
        return this.cliId;
    }
    
    public void setCliId(Integer cliId) {
        this.cliId = cliId;
    }
    public String getCve() {
        return this.cve;
    }
    
    public void setCve(String cve) {
        this.cve = cve;
    }
    public Date getFec() {
        return this.fec;
    }
    
    public void setFec(Date fec) {
        this.fec = fec;
    }




}


