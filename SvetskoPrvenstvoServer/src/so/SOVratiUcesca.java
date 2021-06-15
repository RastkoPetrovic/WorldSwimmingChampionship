/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Trka;
import domen.Ucesce;
import java.util.ArrayList;

/**
 *
 * @author Rastko
 */
public class SOVratiUcesca extends OpstaSO{
    private ArrayList<Ucesce> listaUcesca;
    private ArrayList<Ucesce> listaPotrebnihUcesca = new ArrayList<>();
    Trka t;

    public SOVratiUcesca(Trka t) {
        super(t);
    }

    @Override
    protected void proveriPreduslov() throws Exception {
        t = (Trka) objekat;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        listaUcesca = (ArrayList<Ucesce>) db.select(new Ucesce());
        for (Ucesce ucesce : listaUcesca) {
            if(ucesce.getTrka().getTrkaID() == t.getTrkaID()) {
                listaPotrebnihUcesca.add(ucesce);
            }
        }
    }

    public ArrayList<Ucesce> getListaUcesca() {
        return listaPotrebnihUcesca;
    }
    
    
    
    
}
