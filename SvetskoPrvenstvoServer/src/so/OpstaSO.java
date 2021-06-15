/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import dbb.DBBroker;

/**
 *
 * @author Ivan
 */
public abstract class OpstaSO {
    protected Object objekat;
    protected Object objekat2;
    protected DBBroker db;

    public OpstaSO(Object objekat) {
        this.objekat = objekat;
        this.db = new DBBroker();
    }

    public OpstaSO(Object objekat, Object objekat2) {
        this.objekat = objekat;
        this.objekat2 = objekat2;
        this.db = new DBBroker();
        
    }
    

    public OpstaSO() {
        this.db= new DBBroker();
    }

    public final void opsteIzvrsenjeSO() throws Exception {
        try {
            ucitajDriver();
            otvoriKonekciju();
        }catch(Exception e){
            throw(e);
        }
        try{
            proveriPreduslov();
            izvrsiOperaciju();
            commitTransakcije();
        } catch (Exception e) {
            rollbackTransakcije();
            throw e;
        } finally {
            zatvoriKonekciju();
        }
    }

    private void ucitajDriver() throws Exception {
        db.ucitajDriver();
    }

    private void otvoriKonekciju() throws Exception {
        db.otvoriKonekciju();
    }

    protected abstract void proveriPreduslov() throws Exception;

    protected abstract void izvrsiOperaciju() throws Exception;

    private void commitTransakcije() throws Exception {
        db.commit();
    }

    private void rollbackTransakcije() throws Exception {
        db.rollback();
    }

    private void zatvoriKonekciju() throws Exception {
        db.zatvoriKonekciju();
    }
}
