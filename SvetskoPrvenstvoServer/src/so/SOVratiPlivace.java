/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.OpstiDomenskiObjekat;
import domen.Plivac;
import java.util.ArrayList;

/**
 *
 * @author Rastko
 */
public class SOVratiPlivace extends OpstaSO{
    private ArrayList<Plivac> listaPlivaca;
    private ArrayList<OpstiDomenskiObjekat> rezultat;

    @Override
    protected void proveriPreduslov() throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        listaPlivaca = (ArrayList<Plivac>) db.select(new Plivac());
    }

    public ArrayList<Plivac> getListaPlivaca() {
        return listaPlivaca;
    }
    
    
}
