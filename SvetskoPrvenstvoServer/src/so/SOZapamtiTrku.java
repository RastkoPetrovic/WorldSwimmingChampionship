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
public class SOZapamtiTrku extends OpstaSO{
    private Trka trka;
    private ArrayList<Ucesce> ucescaTrke;

    public SOZapamtiTrku(Trka trka, ArrayList<Ucesce> ucescaTrke) {
        super(trka,ucescaTrke);
    }

    @Override
    protected void proveriPreduslov() throws Exception {
        trka=(Trka)objekat;
        ucescaTrke=(ArrayList<Ucesce>) objekat2;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        db.insert(trka);
        for (Ucesce ucesce : ucescaTrke) {
             db.insert(ucesce);
        }
    }
    
    
    
    
    
    
    
}
