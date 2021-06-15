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
public class TransferObjekatOdgovor implements Serializable{
    private Object rezultat;
    private Object rezultat2;
    private Exception izuzetak;
    private String poruka;

    public TransferObjekatOdgovor() {
    }

    public TransferObjekatOdgovor(Object rezultat, Object rezultat2, Exception izuzetak, String poruka) {
        this.rezultat = rezultat;
        this.rezultat2 = rezultat2;
        this.izuzetak = izuzetak;
        this.poruka = poruka;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Object getRezultat() {
        return rezultat;
    }

    public void setRezultat(Object rezultat) {
        this.rezultat = rezultat;
    }

    public Object getRezultat2() {
        return rezultat2;
    }

    public void setRezultat2(Object rezultat2) {
        this.rezultat2 = rezultat2;
    }

    public Exception getIzuzetak() {
        return izuzetak;
    }

    public void setIzuzetak(Exception izuzetak) {
        this.izuzetak = izuzetak;
    }
    
    
    
   
}
