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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rastko
 */
public class Korisnik implements Serializable, OpstiDomenskiObjekat{
    private int korisnikID;
    private String korisnickoIme;
    private String sifra;
    private String imePrezime;

    public int getKorisnikID() {
        return korisnikID;
    }

    public Korisnik(int korisnikID, String korisnickoIme, String sifra, String imePrezime) {
        this.korisnikID = korisnikID;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.imePrezime = imePrezime;
    }

    public Korisnik(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    
    
    

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "('"+getKorisnickoIme()+"','"+getSifra()+"')";
    }

    @Override
    public String postaviVrednostiAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiImeKlase() {
        return "korisnik";
    }

    @Override
    public String vratiAtributeKlase() {
        return "";
    }

    @Override
    public String vratiUslovPretrage() {
        return "where korisnickoIme='"+getKorisnickoIme()+"' and sifra='"+getSifra()+"'";
    }

    @Override
    public String vratiUslovIzmene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslovBrisanja() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiTabeluPretrage() {
        return "korisnik k";
    }

    @Override
    public String vratiAtributePretrage() {
        return "*";
    }

    @Override
    public ArrayList<?> napuni(ResultSet rs) {
        Korisnik ulogovaniKorisnik;
        ArrayList<Korisnik> rezultat = new ArrayList<>();
        
        try {
            while(rs.next()) {
                int korisnikID = rs.getInt("korisnikID");
                String korisnickoIme = rs.getString("korisnickoIme");
                String sifra = rs.getString("sifra");
                String imePrezime = rs.getString("imePrezime");
                ulogovaniKorisnik = new Korisnik(korisnikID, korisnickoIme, sifra, imePrezime);
                rezultat.add(ulogovaniKorisnik);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rezultat;
    }
    
    
}
