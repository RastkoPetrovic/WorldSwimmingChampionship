/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Ucesce;
import java.util.ArrayList;

/**
 *
 * @author Rastko
 */
public class SOSacuvajRezultate extends OpstaSO {
    private ArrayList<Ucesce> listaUcesca;

    public SOSacuvajRezultate(ArrayList<Ucesce> listaRezultati) {
        super(listaRezultati);
    }

    @Override
    protected void proveriPreduslov() throws Exception {
        listaUcesca = (ArrayList<Ucesce>) objekat;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        for (Ucesce ucesce : listaUcesca) {
            db.update(ucesce);
            db.update(ucesce.getPlivac());
            
        }
    }
    
    
}
