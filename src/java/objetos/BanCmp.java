package objetos;
// Generated 13-may-2011 8:17:31 by Hibernate Tools 3.2.1.GA



/**
 * BanCmp generated by hbm2java
 */
public class BanCmp  implements java.io.Serializable {


     private int banCmpId;
     private Cal cal;
     private String banCmpDes;
     private Float banCmpLimInf;
     private Float banCmpLimSup;
     private Integer banCmpOrdPre;
     private Float banCmpPto;
     private Float banCmpWoe;
     private String banCmpDesLar;

    public BanCmp() {
    }

	
    public BanCmp(int banCmpId) {
        this.banCmpId = banCmpId;
    }
    public BanCmp(int banCmpId, Cal cal, String banCmpDes, Float banCmpLimInf, Float banCmpLimSup, Integer banCmpOrdPre, Float banCmpPto, Float banCmpWoe, String banCmpDesLar) {
       this.banCmpId = banCmpId;
       this.cal = cal;
       this.banCmpDes = banCmpDes;
       this.banCmpLimInf = banCmpLimInf;
       this.banCmpLimSup = banCmpLimSup;
       this.banCmpOrdPre = banCmpOrdPre;
       this.banCmpPto = banCmpPto;
       this.banCmpWoe = banCmpWoe;
       this.banCmpDesLar = banCmpDesLar;
    }
   
    public int getBanCmpId() {
        return this.banCmpId;
    }
    
    public void setBanCmpId(int banCmpId) {
        this.banCmpId = banCmpId;
    }
    public Cal getCal() {
        return this.cal;
    }
    
    public void setCal(Cal cal) {
        this.cal = cal;
    }
    public String getBanCmpDes() {
        return this.banCmpDes;
    }
    
    public void setBanCmpDes(String banCmpDes) {
        this.banCmpDes = banCmpDes;
    }
    public Float getBanCmpLimInf() {
        return this.banCmpLimInf;
    }
    
    public void setBanCmpLimInf(Float banCmpLimInf) {
        this.banCmpLimInf = banCmpLimInf;
    }
    public Float getBanCmpLimSup() {
        return this.banCmpLimSup;
    }
    
    public void setBanCmpLimSup(Float banCmpLimSup) {
        this.banCmpLimSup = banCmpLimSup;
    }
    public Integer getBanCmpOrdPre() {
        return this.banCmpOrdPre;
    }
    
    public void setBanCmpOrdPre(Integer banCmpOrdPre) {
        this.banCmpOrdPre = banCmpOrdPre;
    }
    public Float getBanCmpPto() {
        return this.banCmpPto;
    }
    
    public void setBanCmpPto(Float banCmpPto) {
        this.banCmpPto = banCmpPto;
    }
    public Float getBanCmpWoe() {
        return this.banCmpWoe;
    }
    
    public void setBanCmpWoe(Float banCmpWoe) {
        this.banCmpWoe = banCmpWoe;
    }
    public String getBanCmpDesLar() {
        return this.banCmpDesLar;
    }
    
    public void setBanCmpDesLar(String banCmpDesLar) {
        this.banCmpDesLar = banCmpDesLar;
    }




}


