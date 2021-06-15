/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.VrstaTrka;
import java.util.ArrayList;

/**
 *
 * @author Rastko
 */
public class SOVratiVrsteTrka extends OpstaSO{
    private ArrayList<VrstaTrka> listeVrstaTrka;
    private ArrayList<OpstiDomenskiObjekat> rezultat;

    @Override
    protected void proveriPreduslov() throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        listeVrstaTrka =  (ArrayList<VrstaTrka>) db.select(new VrstaTrka());
    }

    public ArrayList<VrstaTrka> getListeVrstaTrka() {
        return listeVrstaTrka;
    }
    
    
    
}
