package objetos;
// Generated 13-may-2011 8:17:31 by Hibernate Tools 3.2.1.GA



/**
 * SitAct generated by hbm2java
 */
public class SitAct  implements java.io.Serializable {


     private int sitActId;
     private Integer calId;
     private String sitActDes;
     private Float sitActLimInf;
     private Float sitActLimSup;
     private Integer sitActOrdPre;
     private Float sitActPto;
     private Float sitActWoe;
     private String sitActDesLar;

    public SitAct() {
    }

	
    public SitAct(int sitActId) {
        this.sitActId = sitActId;
    }
    public SitAct(int sitActId, Integer calId, String sitActDes, Float sitActLimInf, Float sitActLimSup, Integer sitActOrdPre, Float sitActPto, Float sitActWoe, String sitActDesLar) {
       this.sitActId = sitActId;
       this.calId = calId;
       this.sitActDes = sitActDes;
       this.sitActLimInf = sitActLimInf;
       this.sitActLimSup = sitActLimSup;
       this.sitActOrdPre = sitActOrdPre;
       this.sitActPto = sitActPto;
       this.sitActWoe = sitActWoe;
       this.sitActDesLar = sitActDesLar;
    }
   
    public int getSitActId() {
        return this.sitActId;
    }
    
    public void setSitActId(int sitActId) {
        this.sitActId = sitActId;
    }
    public Integer getCalId() {
        return this.calId;
    }
    
    public void setCalId(Integer calId) {
        this.calId = calId;
    }
    public String getSitActDes() {
        return this.sitActDes;
    }
    
    public void setSitActDes(String sitActDes) {
        this.sitActDes = sitActDes;
    }
    public Float getSitActLimInf() {
        return this.sitActLimInf;
    }
    
    public void setSitActLimInf(Float sitActLimInf) {
        this.sitActLimInf = sitActLimInf;
    }
    public Float getSitActLimSup() {
        return this.sitActLimSup;
    }
    
    public void setSitActLimSup(Float sitActLimSup) {
        this.sitActLimSup = sitActLimSup;
    }
    public Integer getSitActOrdPre() {
        return this.sitActOrdPre;
    }
    
    public void setSitActOrdPre(Integer sitActOrdPre) {
        this.sitActOrdPre = sitActOrdPre;
    }
    public Float getSitActPto() {
        return this.sitActPto;
    }
    
    public void setSitActPto(Float sitActPto) {
        this.sitActPto = sitActPto;
    }
    public Float getSitActWoe() {
        return this.sitActWoe;
    }
    
    public void setSitActWoe(Float sitActWoe) {
        this.sitActWoe = sitActWoe;
    }
    public String getSitActDesLar() {
        return this.sitActDesLar;
    }
    
    public void setSitActDesLar(String sitActDesLar) {
        this.sitActDesLar = sitActDesLar;
    }




}


