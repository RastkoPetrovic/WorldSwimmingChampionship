/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rastko
 */
public class Plivac implements Serializable, OpstiDomenskiObjekat{
    private int plivacID;
    private String imePrezime;
    private String drzava;
    private Date datumRodjenja;
    private int brojMedaljaEvropksaPrvenstva;
    private int brojMedaljaSvetskaPrvenstva;
    private int brojMedaljaOlimpijskeIgre;

    public Plivac() {
    }

    public Plivac(int plivacID, String imePrezime, String drzava, Date datumRodjenja, int brojMedaljaEvropksaPrvenstva, int brojMedaljaSvetskaPrvenstva, int brojMedaljaOlimpijskeIgre) {
        this.plivacID = plivacID;
        this.imePrezime = imePrezime;
        this.drzava = drzava;
        this.datumRodjenja = datumRodjenja;
        this.brojMedaljaEvropksaPrvenstva = brojMedaljaEvropksaPrvenstva;
        this.brojMedaljaSvetskaPrvenstva = brojMedaljaSvetskaPrvenstva;
        this.brojMedaljaOlimpijskeIgre = brojMedaljaOlimpijskeIgre;
    }
    
    

    public int getPlivacID() {
        return plivacID;
    }

    public void setPlivacID(int plivacID) {
        this.plivacID = plivacID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getBrojMedaljaEvropksaPrvenstva() {
        return brojMedaljaEvropksaPrvenstva;
    }

    public void setBrojMedaljaEvropksaPrvenstva(int brojMedaljaEvropksaPrvenstva) {
        this.brojMedaljaEvropksaPrvenstva = brojMedaljaEvropksaPrvenstva;
    }

    public int getBrojMedaljaSvetskaPrvenstva() {
        return brojMedaljaSvetskaPrvenstva;
    }

    public void setBrojMedaljaSvetskaPrvenstva(int brojMedaljaSvetskaPrvenstva) {
        this.brojMedaljaSvetskaPrvenstva = brojMedaljaSvetskaPrvenstva;
    }

    public int getBrojMedaljaOlimpijskeIgre() {
        return brojMedaljaOlimpijskeIgre;
    }

    public void setBrojMedaljaOlimpijskeIgre(int brojMedaljaOlimpijskeIgre) {
        this.brojMedaljaOlimpijskeIgre = brojMedaljaOlimpijskeIgre;
    }

    @Override
    public String toString() {
        return imePrezime;   
    
    }

    @Override
    public String vratiVrednostiAtributa() {
        Date date=new java.sql.Date(getDatumRodjenja().getTime());
        return "('"+getImePrezime()+"','"+getDrzava()+"','"+date+"',"+getBrojMedaljaEvropksaPrvenstva()+","+getBrojMedaljaSvetskaPrvenstva()+","+getBrojMedaljaSvetskaPrvenstva()+")";
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "brojMedaljaSvetskaPrvenstva = "+getBrojMedaljaSvetskaPrvenstva()+"";
    }

    @Override
    public String vratiImeKlase() {
        return "plivac";
    }

    @Override
    public String vratiAtributeKlase() {
        return "(imePrezimePlivaca, drzava, datumRodjenja, brojMedaljaEvropskaPrvenstva, brojMedaljaSvetskaPrvenstva, brojMedaljaOlimpijskeIgre)";
    }

    @Override
    public String vratiUslovPretrage() {
        return "";
    }

    @Override
    public String vratiUslovIzmene() {
        return "where plivacID="+getPlivacID()+"";
    }

    @Override
    public String vratiUslovBrisanja() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiTabeluPretrage() {
        return "plivac";
    }

    @Override
    public String vratiAtributePretrage() {
        return "*";
    }

    @Override
    public ArrayList<?> napuni(ResultSet rs) {
        ArrayList<Plivac> rezultat = new ArrayList<>();
        try {
             while (rs.next()) {
                int plivacID = rs.getInt(1);
                String imePrezimePlivaca = rs.getString(2);
                String drzava = rs.getString(3);
                Date datumRodjenja = rs.getDate(4);
                int brojMedaljaEvropska = rs.getInt(5);
                int brojMedaljaSvetska = rs.getInt(6);
                int brojMedaljaOlimpijske =  rs.getInt(7);
                Plivac p = new Plivac(plivacID, imePrezimePlivaca, drzava, datumRodjenja, brojMedaljaEvropska, brojMedaljaSvetska, brojMedaljaOlimpijske);
                rezultat.add(p);
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Plivac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rezultat;
    }
    
}
