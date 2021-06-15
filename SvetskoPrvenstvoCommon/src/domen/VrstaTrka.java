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
public class VrstaTrka implements Serializable, OpstiDomenskiObjekat{
    private int vrstID;
    private String nazivStila;
    private String opis;

    public VrstaTrka() {
    }

    public VrstaTrka(int vrstID, String nazivStila, String opis) {
        this.vrstID = vrstID;
        this.nazivStila = nazivStila;
        this.opis = opis;
    }


    public int getVrstID() {
        return vrstID;
    }

    public void setVrstID(int vrstID) {
        this.vrstID = vrstID;
    }

    public String getNazivStila() {
        return nazivStila;
    }

    public void setNazivStila(String nazivStila) {
        this.nazivStila = nazivStila;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return nazivStila;
    }

    @Override
    public String vratiVrednostiAtributa() {
         return "('"+getNazivStila()+"','"+getOpis()+"')";
    }

    @Override
    public String postaviVrednostiAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiImeKlase() {
        return "vrstatrke";
    }

    @Override
    public String vratiAtributeKlase() {
         return "(nazivStila,opis)";
    }

    @Override
    public String vratiUslovPretrage() {
        return "";
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
        return "vrstatrke";
    }

    @Override
    public String vratiAtributePretrage() {
        return "*";
    }

    @Override
    public ArrayList<?> napuni(ResultSet rs) {
        ArrayList<VrstaTrka> rezultat = new ArrayList<>();
        
        try {
            while(rs.next()) {
                int vrstaID = rs.getInt(1);
                String naziv =  rs.getString(2);
                String opisStila = rs.getString(3);
                
                VrstaTrka vt =  new VrstaTrka(vrstaID, naziv, opisStila);
                rezultat.add(vt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VrstaTrka.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return rezultat;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VrstaTrka other = (VrstaTrka) obj;
        if (this.vrstID != other.vrstID) {
            return false;
        }
        return true;
    }
    
    
}
