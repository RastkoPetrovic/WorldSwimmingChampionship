/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Plivac;

/**
 *
 * @author Rastko
 */
public class SOZapamtiPlivaca extends OpstaSO{
    Plivac plivac;

    public SOZapamtiPlivaca(Plivac plivac) {
        super(plivac);
    }

    @Override
    protected void proveriPreduslov() throws Exception {
        plivac = (Plivac) objekat;
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        db.insert(plivac);
    }
    
}
