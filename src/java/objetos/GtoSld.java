package objetos;
// Generated 13-may-2011 8:17:31 by Hibernate Tools 3.2.1.GA



/**
 * GtoSld generated by hbm2java
 */
public class GtoSld  implements java.io.Serializable {


     private int gtoSldId;
     private Cal cal;
     private String gtoSldDes;
     private Float gtoSldLimInf;
     private Float gtoSldLimSup;
     private Integer gtoSldOrdPre;
     private Float gtoSldPto;
     private Float gtoSldWoe;
     private String gtoSldDesLar;

    public GtoSld() {
    }

	
    public GtoSld(int gtoSldId) {
        this.gtoSldId = gtoSldId;
    }
    public GtoSld(int gtoSldId, Cal cal, String gtoSldDes, Float gtoSldLimInf, Float gtoSldLimSup, Integer gtoSldOrdPre, Float gtoSldPto, Float gtoSldWoe, String gtoSldDesLar) {
       this.gtoSldId = gtoSldId;
       this.cal = cal;
       this.gtoSldDes = gtoSldDes;
       this.gtoSldLimInf = gtoSldLimInf;
       this.gtoSldLimSup = gtoSldLimSup;
       this.gtoSldOrdPre = gtoSldOrdPre;
       this.gtoSldPto = gtoSldPto;
       this.gtoSldWoe = gtoSldWoe;
       this.gtoSldDesLar = gtoSldDesLar;
    }
   
    public int getGtoSldId() {
        return this.gtoSldId;
    }
    
    public void setGtoSldId(int gtoSldId) {
        this.gtoSldId = gtoSldId;
    }
    public Cal getCal() {
        return this.cal;
    }
    
    public void setCal(Cal cal) {
        this.cal = cal;
    }
    public String getGtoSldDes() {
        return this.gtoSldDes;
    }
    
    public void setGtoSldDes(String gtoSldDes) {
        this.gtoSldDes = gtoSldDes;
    }
    public Float getGtoSldLimInf() {
        return this.gtoSldLimInf;
    }
    
    public void setGtoSldLimInf(Float gtoSldLimInf) {
        this.gtoSldLimInf = gtoSldLimInf;
    }
    public Float getGtoSldLimSup() {
        return this.gtoSldLimSup;
    }
    
    public void setGtoSldLimSup(Float gtoSldLimSup) {
        this.gtoSldLimSup = gtoSldLimSup;
    }
    public Integer getGtoSldOrdPre() {
        return this.gtoSldOrdPre;
    }
    
    public void setGtoSldOrdPre(Integer gtoSldOrdPre) {
        this.gtoSldOrdPre = gtoSldOrdPre;
    }
    public Float getGtoSldPto() {
        return this.gtoSldPto;
    }
    
    public void setGtoSldPto(Float gtoSldPto) {
        this.gtoSldPto = gtoSldPto;
    }
    public Float getGtoSldWoe() {
        return this.gtoSldWoe;
    }
    
    public void setGtoSldWoe(Float gtoSldWoe) {
        this.gtoSldWoe = gtoSldWoe;
    }
    public String getGtoSldDesLar() {
        return this.gtoSldDesLar;
    }
    
    public void setGtoSldDesLar(String gtoSldDesLar) {
        this.gtoSldDesLar = gtoSldDesLar;
    }




}


