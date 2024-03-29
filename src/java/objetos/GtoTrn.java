package objetos;
// Generated 13-may-2011 8:17:31 by Hibernate Tools 3.2.1.GA



/**
 * GtoTrn generated by hbm2java
 */
public class GtoTrn  implements java.io.Serializable {


     private int gtoTrnId;
     private Cal cal;
     private String gtoTrnDes;
     private Float gtoTrnLimInf;
     private Float gtoTrnLimSup;
     private Integer gtoTrnOrdPre;
     private Float gtoTrnPto;
     private Float gtoTrnWoe;
     private String gtoTrnDesLar;

    public GtoTrn() {
    }

	
    public GtoTrn(int gtoTrnId) {
        this.gtoTrnId = gtoTrnId;
    }
    public GtoTrn(int gtoTrnId, Cal cal, String gtoTrnDes, Float gtoTrnLimInf, Float gtoTrnLimSup, Integer gtoTrnOrdPre, Float gtoTrnPto, Float gtoTrnWoe, String gtoTrnDesLar) {
       this.gtoTrnId = gtoTrnId;
       this.cal = cal;
       this.gtoTrnDes = gtoTrnDes;
       this.gtoTrnLimInf = gtoTrnLimInf;
       this.gtoTrnLimSup = gtoTrnLimSup;
       this.gtoTrnOrdPre = gtoTrnOrdPre;
       this.gtoTrnPto = gtoTrnPto;
       this.gtoTrnWoe = gtoTrnWoe;
       this.gtoTrnDesLar = gtoTrnDesLar;
    }
   
    public int getGtoTrnId() {
        return this.gtoTrnId;
    }
    
    public void setGtoTrnId(int gtoTrnId) {
        this.gtoTrnId = gtoTrnId;
    }
    public Cal getCal() {
        return this.cal;
    }
    
    public void setCal(Cal cal) {
        this.cal = cal;
    }
    public String getGtoTrnDes() {
        return this.gtoTrnDes;
    }
    
    public void setGtoTrnDes(String gtoTrnDes) {
        this.gtoTrnDes = gtoTrnDes;
    }
    public Float getGtoTrnLimInf() {
        return this.gtoTrnLimInf;
    }
    
    public void setGtoTrnLimInf(Float gtoTrnLimInf) {
        this.gtoTrnLimInf = gtoTrnLimInf;
    }
    public Float getGtoTrnLimSup() {
        return this.gtoTrnLimSup;
    }
    
    public void setGtoTrnLimSup(Float gtoTrnLimSup) {
        this.gtoTrnLimSup = gtoTrnLimSup;
    }
    public Integer getGtoTrnOrdPre() {
        return this.gtoTrnOrdPre;
    }
    
    public void setGtoTrnOrdPre(Integer gtoTrnOrdPre) {
        this.gtoTrnOrdPre = gtoTrnOrdPre;
    }
    public Float getGtoTrnPto() {
        return this.gtoTrnPto;
    }
    
    public void setGtoTrnPto(Float gtoTrnPto) {
        this.gtoTrnPto = gtoTrnPto;
    }
    public Float getGtoTrnWoe() {
        return this.gtoTrnWoe;
    }
    
    public void setGtoTrnWoe(Float gtoTrnWoe) {
        this.gtoTrnWoe = gtoTrnWoe;
    }
    public String getGtoTrnDesLar() {
        return this.gtoTrnDesLar;
    }
    
    public void setGtoTrnDesLar(String gtoTrnDesLar) {
        this.gtoTrnDesLar = gtoTrnDesLar;
    }




}


