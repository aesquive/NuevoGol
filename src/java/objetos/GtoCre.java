package objetos;
// Generated 13-may-2011 8:17:31 by Hibernate Tools 3.2.1.GA



/**
 * GtoCre generated by hbm2java
 */
public class GtoCre  implements java.io.Serializable {


     private int gtoCreId;
     private Cal cal;
     private String gtoCreDes;
     private Float gtoCreLimInf;
     private Float gtoCreLimSup;
     private Integer gtoCreOrdPre;
     private Float gtoCrePto;
     private Float gtoCreWoe;
     private String gtoCreDesLar;

    public GtoCre() {
    }

	
    public GtoCre(int gtoCreId) {
        this.gtoCreId = gtoCreId;
    }
    public GtoCre(int gtoCreId, Cal cal, String gtoCreDes, Float gtoCreLimInf, Float gtoCreLimSup, Integer gtoCreOrdPre, Float gtoCrePto, Float gtoCreWoe, String gtoCreDesLar) {
       this.gtoCreId = gtoCreId;
       this.cal = cal;
       this.gtoCreDes = gtoCreDes;
       this.gtoCreLimInf = gtoCreLimInf;
       this.gtoCreLimSup = gtoCreLimSup;
       this.gtoCreOrdPre = gtoCreOrdPre;
       this.gtoCrePto = gtoCrePto;
       this.gtoCreWoe = gtoCreWoe;
       this.gtoCreDesLar = gtoCreDesLar;
    }
   
    public int getGtoCreId() {
        return this.gtoCreId;
    }
    
    public void setGtoCreId(int gtoCreId) {
        this.gtoCreId = gtoCreId;
    }
    public Cal getCal() {
        return this.cal;
    }
    
    public void setCal(Cal cal) {
        this.cal = cal;
    }
    public String getGtoCreDes() {
        return this.gtoCreDes;
    }
    
    public void setGtoCreDes(String gtoCreDes) {
        this.gtoCreDes = gtoCreDes;
    }
    public Float getGtoCreLimInf() {
        return this.gtoCreLimInf;
    }
    
    public void setGtoCreLimInf(Float gtoCreLimInf) {
        this.gtoCreLimInf = gtoCreLimInf;
    }
    public Float getGtoCreLimSup() {
        return this.gtoCreLimSup;
    }
    
    public void setGtoCreLimSup(Float gtoCreLimSup) {
        this.gtoCreLimSup = gtoCreLimSup;
    }
    public Integer getGtoCreOrdPre() {
        return this.gtoCreOrdPre;
    }
    
    public void setGtoCreOrdPre(Integer gtoCreOrdPre) {
        this.gtoCreOrdPre = gtoCreOrdPre;
    }
    public Float getGtoCrePto() {
        return this.gtoCrePto;
    }
    
    public void setGtoCrePto(Float gtoCrePto) {
        this.gtoCrePto = gtoCrePto;
    }
    public Float getGtoCreWoe() {
        return this.gtoCreWoe;
    }
    
    public void setGtoCreWoe(Float gtoCreWoe) {
        this.gtoCreWoe = gtoCreWoe;
    }
    public String getGtoCreDesLar() {
        return this.gtoCreDesLar;
    }
    
    public void setGtoCreDesLar(String gtoCreDesLar) {
        this.gtoCreDesLar = gtoCreDesLar;
    }




}

