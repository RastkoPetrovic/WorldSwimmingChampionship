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
public class Trka implements Serializable, OpstiDomenskiObjekat{
    private int trkaID;
    private int duzina;
    private String drzava;
    private Date datumOdrzavanja;
    private String vremeOdrzavanja;
    private String nazivPlivalista;
    private int brojUcesnika;
    private String fazaTakmicenja;
    private String evropskiRekord;
    private String svetskiRekord;
    private String olimpijskiRekord;
    private VrstaTrka vrstaTrka; 

    public Trka() {
    }

    public Trka(int trkaID, int duzina, String drzava, Date datumOdrzavanja, String vremeOdrzavanja, String nazivPlivalista, int brojUcesnika, String fazaTakmicenja, String evropskiRekord, String svetskiRekord, String olimpijskiRekord, VrstaTrka vrstaTrka) {
        this.trkaID = trkaID;
        this.duzina = duzina;
        this.drzava = drzava;
        this.datumOdrzavanja = datumOdrzavanja;
        this.vremeOdrzavanja = vremeOdrzavanja;
        this.nazivPlivalista = nazivPlivalista;
        this.brojUcesnika = brojUcesnika;
        this.fazaTakmicenja = fazaTakmicenja;
        this.evropskiRekord = evropskiRekord;
        this.svetskiRekord = svetskiRekord;
        this.olimpijskiRekord = olimpijskiRekord;
        this.vrstaTrka = vrstaTrka;
    }
    
    

    public int getTrkaID() {
        return trkaID;
    }

    public void setTrkaID(int trkaID) {
        this.trkaID = trkaID;
    }

    public int getDuzina() {
        return duzina;
    }

    public void setDuzina(int duzina) {
        this.duzina = duzina;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public Date getDatumOdrzavanja() {
        return datumOdrzavanja;
    }

    public void setDatumOdrzavanja(Date datumOdrzavanja) {
        this.datumOdrzavanja = datumOdrzavanja;
    }

    public String getVremeOdrzavanja() {
        return vremeOdrzavanja;
    }

    public void setVremeOdrzavanja(String vremeOdrzavanja) {
        this.vremeOdrzavanja = vremeOdrzavanja;
    }

    public String getNazivPlivalista() {
        return nazivPlivalista;
    }

    public void setNazivPlivalista(String nazivPlivalista) {
        this.nazivPlivalista = nazivPlivalista;
    }

    public int getBrojUcesnika() {
        return brojUcesnika;
    }

    public void setBrojUcesnika(int brojUcesnika) {
        this.brojUcesnika = brojUcesnika;
    }

    public String getFazaTakmicenja() {
        return fazaTakmicenja;
    }

    public void setFazaTakmicenja(String fazaTakmicenja) {
        this.fazaTakmicenja = fazaTakmicenja;
    }

    
    public String getEvropskiRekord() {
        return evropskiRekord;
    }

    public void setEvropskiRekord(String evropskiRekord) {
        this.evropskiRekord = evropskiRekord;
    }

    public String getSvetskiRekord() {
        return svetskiRekord;
    }

    public void setSvetskiRekord(String svetskiRekord) {
        this.svetskiRekord = svetskiRekord;
    }

    public String getOlimpijskiRekord() {
        return olimpijskiRekord;
    }

    public void setOlimpijskiRekord(String olimpijskiRekord) {
        this.olimpijskiRekord = olimpijskiRekord;
    }

    public VrstaTrka getVrstaTrka() {
        return vrstaTrka;
    }

    public void setVrstaTrka(VrstaTrka vrstaTrka) {
        this.vrstaTrka = vrstaTrka;
    }

    @Override
    public String vratiVrednostiAtributa() {
        Date datum= new java.sql.Date(getDatumOdrzavanja().getTime());
        return "("+getDuzina()+",'"+getDrzava()+"','"+datum+"','"+getVremeOdrzavanja()+"','"+getNazivPlivalista()+"',"+getBrojUcesnika()+",'"+getFazaTakmicenja()+"','"+getEvropskiRekord()+"','"+getSvetskiRekord()+"','"+getOlimpijskiRekord()+"',"+getVrstaTrka().getVrstID()+")";
    }

    @Override
    public String postaviVrednostiAtributa() {
        Date datum= new java.sql.Date(getDatumOdrzavanja().getTime());
        return "duzina="+getDuzina()+",drzava='"+getDrzava()+"',datumOdrzavanja='"+datum+"',vremeOdrzavanja='"+getVremeOdrzavanja()+"',nazivPlivalista='"+getNazivPlivalista()+"',fazaTakmicenja='"+getFazaTakmicenja()+"',evropskiRekord='"+getEvropskiRekord()+"',svetskiRekord='"+getSvetskiRekord()+"',olimpijskiRekord='"+getOlimpijskiRekord()+"'";
    }

    @Override
    public String vratiImeKlase() {
        return "trka";
    }

    @Override
    public String vratiAtributeKlase() {
        return "(duzina, drzava, datumOdrzavanja, vremeOdrzavanja, nazivPlivalista, brojUcesnika, fazaTakmicenja, evropskiRekord, svetskiRekord, olimpijskiRekord, vrstaID)";
    }

    @Override
    public String vratiUslovPretrage() {
        return "";
    }

    @Override
    public String vratiUslovIzmene() {
        return "where trkaID="+getTrkaID()+"";
    }

    @Override
    public String vratiUslovBrisanja() {
        return " where TrkaID="+getTrkaID()+"";
    }

    @Override
    public String vratiTabeluPretrage() {
        return "trka t join vrstatrke vt on t.vrstaID = vt.vrstaID";
    }

    @Override
    public String vratiAtributePretrage() {
        return "*";
    }

    @Override
    public ArrayList<?> napuni(ResultSet rs) {
        ArrayList<Trka> rezultat = new ArrayList<>();
        try {
            
            while (rs.next()) {
                int trkaID = rs.getInt(1);
                int duzina = rs.getInt(2);
                String drzava=rs.getString(3);
                Date datumOdrzavanja=rs.getDate(4);
                String vremeOdrzavanja = rs.getString(5);
                String nazivPlivalista = rs.getString(6);
                int brojUcesnika = rs.getInt(7);
                String fazaTakmicenja = rs.getString(8);
                String evropskiRekord = rs.getString(9);
                String svetskiRekord = rs.getString(10);
                String olimpijskiRekord = rs.getString(11);
                Trka t = new Trka(trkaID, duzina, drzava, datumOdrzavanja, vremeOdrzavanja, nazivPlivalista, brojUcesnika, fazaTakmicenja, evropskiRekord, svetskiRekord, olimpijskiRekord, new VrstaTrka(rs.getInt("vrstaID"), "", ""));
                rezultat.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Trka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rezultat;
    }
    
    
            
}
