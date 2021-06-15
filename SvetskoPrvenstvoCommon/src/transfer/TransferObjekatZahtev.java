/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Rastko
 */
public class TransferObjekatZahtev implements Serializable{
    private int operacija;
    private Object parametar;
    private Object parametar2;

    public TransferObjekatZahtev() {
    }

    public TransferObjekatZahtev(int operacija, Object parametar, Object parametar2) {
        this.operacija = operacija;
        this.parametar = parametar;
        this.parametar2 = parametar2;
    }

    public Object getParametar2() {
        return parametar2;
    }

    public void setParametar2(Object parametar2) {
        this.parametar2 = parametar2;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
    
    
}
