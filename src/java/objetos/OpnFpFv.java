package objetos;
// Generated 13-may-2011 8:17:31 by Hibernate Tools 3.2.1.GA



/**
 * OpnFpFv generated by hbm2java
 */
public class OpnFpFv  implements java.io.Serializable {


     private int opnFpFvId;
     private Cal cal;
     private String opnFpFvDes;
     private Float opnFpFvLimInf;
     private Float opnFpFvLimSup;
     private Integer opnFpFvOrdPre;
     private Float opnFpFvPto;
     private Float opnFpFvWoe;
     private String opnFpFvDesLar;

    public OpnFpFv() {
    }

	
    public OpnFpFv(int opnFpFvId) {
        this.opnFpFvId = opnFpFvId;
    }
    public OpnFpFv(int opnFpFvId, Cal cal, String opnFpFvDes, Float opnFpFvLimInf, Float opnFpFvLimSup, Integer opnFpFvOrdPre, Float opnFpFvPto, Float opnFpFvWoe, String opnFpFvDesLar) {
       this.opnFpFvId = opnFpFvId;
       this.cal = cal;
       this.opnFpFvDes = opnFpFvDes;
       this.opnFpFvLimInf = opnFpFvLimInf;
       this.opnFpFvLimSup = opnFpFvLimSup;
       this.opnFpFvOrdPre = opnFpFvOrdPre;
       this.opnFpFvPto = opnFpFvPto;
       this.opnFpFvWoe = opnFpFvWoe;
       this.opnFpFvDesLar = opnFpFvDesLar;
    }
   
    public int getOpnFpFvId() {
        return this.opnFpFvId;
    }
    
    public void setOpnFpFvId(int opnFpFvId) {
        this.opnFpFvId = opnFpFvId;
    }
    public Cal getCal() {
        return this.cal;
    }
    
    public void setCal(Cal cal) {
        this.cal = cal;
    }
    public String getOpnFpFvDes() {
        return this.opnFpFvDes;
    }
    
    public void setOpnFpFvDes(String opnFpFvDes) {
        this.opnFpFvDes = opnFpFvDes;
    }
    public Float getOpnFpFvLimInf() {
        return this.opnFpFvLimInf;
    }
    
    public void setOpnFpFvLimInf(Float opnFpFvLimInf) {
        this.opnFpFvLimInf = opnFpFvLimInf;
    }
    public Float getOpnFpFvLimSup() {
        return this.opnFpFvLimSup;
    }
    
    public void setOpnFpFvLimSup(Float opnFpFvLimSup) {
        this.opnFpFvLimSup = opnFpFvLimSup;
    }
    public Integer getOpnFpFvOrdPre() {
        return this.opnFpFvOrdPre;
    }
    
    public void setOpnFpFvOrdPre(Integer opnFpFvOrdPre) {
        this.opnFpFvOrdPre = opnFpFvOrdPre;
    }
    public Float getOpnFpFvPto() {
        return this.opnFpFvPto;
    }
    
    public void setOpnFpFvPto(Float opnFpFvPto) {
        this.opnFpFvPto = opnFpFvPto;
    }
    public Float getOpnFpFvWoe() {
        return this.opnFpFvWoe;
    }
    
    public void setOpnFpFvWoe(Float opnFpFvWoe) {
        this.opnFpFvWoe = opnFpFvWoe;
    }
    public String getOpnFpFvDesLar() {
        return this.opnFpFvDesLar;
    }
    
    public void setOpnFpFvDesLar(String opnFpFvDesLar) {
        this.opnFpFvDesLar = opnFpFvDesLar;
    }




}

