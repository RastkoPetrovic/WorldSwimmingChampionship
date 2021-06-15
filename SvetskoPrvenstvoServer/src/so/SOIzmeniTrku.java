/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Trka;

/**
 *
 * @author Rastko
 */
public class SOIzmeniTrku extends OpstaSO{
    private Trka trka;

    public SOIzmeniTrku(Trka trka) {
        super(trka);
    }

    @Override
    protected void proveriPreduslov() throws Exception {
        trka = (Trka) objekat;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        db.update(trka);
    }
    
    
    
    
    
}
