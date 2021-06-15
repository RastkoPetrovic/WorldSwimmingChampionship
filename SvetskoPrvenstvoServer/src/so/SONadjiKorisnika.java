/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Korisnik;
import java.util.ArrayList;

/**
 *
 * @author Rastko
 */
public class SONadjiKorisnika extends OpstaSO{
    
    private Korisnik korisnik;
    private Korisnik ulogovani = null;
    private ArrayList<Korisnik> listaKorisnika;

    public SONadjiKorisnika(Object object) {
        super(object);
        
    }


    @Override
    protected void proveriPreduslov() throws Exception {
        korisnik = (Korisnik) objekat;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        listaKorisnika = (ArrayList<Korisnik>) db.select(korisnik);
        
        if(!listaKorisnika.isEmpty()) {
            ulogovani = listaKorisnika.get(0);
        }
        
        
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }
    
    public Korisnik getUlogovani() {
        return ulogovani;
    }

    
    
    
}
