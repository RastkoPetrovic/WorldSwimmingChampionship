/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import dbb.DBBroker;
import domen.Korisnik;
import domen.Plivac;
import domen.Trka;
import domen.Ucesce;
import domen.VrstaTrka;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.SOIzmeniTrku;
import so.SONadjiKorisnika;
import so.SOObrisiTrku;
import so.SOSacuvajRezultate;
import so.SOVratiPlivace;
import so.SOVratiTrke;
import so.SOVratiUcesca;
import so.SOVratiVrsteTrka;
import so.SOZapamtiPlivaca;
import so.SOZapamtiTrku;
import transfer.TransferObjekatOdgovor;

/**
 *
 * @author Rastko
 */
public class Kontroler {
    private static Kontroler instance;
    private DBBroker db;
    ArrayList<Socket> listaKlijenata;

    private Kontroler() {
        db = new DBBroker();
        listaKlijenata=new ArrayList<>();
    }
    

    public static Kontroler getInstance() {
        
        if(instance == null) {
            instance = new Kontroler();
                    
        }
        return instance;
    }
    
    public ArrayList<Socket> getListaKlijenata() {
        return listaKlijenata;
    }

    public Korisnik nadjiKorisnika(Korisnik korisnik) {
        SONadjiKorisnika so = new SONadjiKorisnika(korisnik);
        
        try {
            so.opsteIzvrsenjeSO();
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return so.getUlogovani();
      
    }
    
    public  TransferObjekatOdgovor zapamtiPlivaca(Plivac plivac) {
        TransferObjekatOdgovor too = new TransferObjekatOdgovor();
        try {
            SOZapamtiPlivaca so = new SOZapamtiPlivaca(plivac);
            so.opsteIzvrsenjeSO();
            too.setRezultat(true);
        } catch (Exception ex) {
            too.setRezultat(false);
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return too;
        
    }

    public ArrayList<VrstaTrka> vratiVrsteTrka() {
        SOVratiVrsteTrka so = new SOVratiVrsteTrka();
        
        try {
            so.opsteIzvrsenjeSO();
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so.getListeVrstaTrka();
    }

    public ArrayList<Plivac> vratiPlivace() {
        SOVratiPlivace so=new SOVratiPlivace();
        try {
            so.opsteIzvrsenjeSO();
        } catch (Exception ex) {
            return null;
        }
        return so.getListaPlivaca();
    }

    public TransferObjekatOdgovor zapamtiTrku(Trka trka, ArrayList<Ucesce> ucescaTrke) {
        TransferObjekatOdgovor too=new TransferObjekatOdgovor();
        try {
            SOZapamtiTrku so = new SOZapamtiTrku(trka, ucescaTrke);
            so.opsteIzvrsenjeSO();
            too.setRezultat(true);
        } catch (Exception ex) {
            too.setRezultat(false);
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return too;
    }

    public ArrayList<Trka> vratiTrke() {
        SOVratiTrke so = new SOVratiTrke();
        try {
            so.opsteIzvrsenjeSO();
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so.getListaTrka();
    }

    public ArrayList<Ucesce> vratiUcesca(Trka t) {
        SOVratiUcesca so = new SOVratiUcesca(t);
        try {
            so.opsteIzvrsenjeSO();
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
       return so.getListaUcesca();
    }

    public TransferObjekatOdgovor obrisiTrku(Trka trkaObrisi, ArrayList<Ucesce> ucesceBrisanje) {
        TransferObjekatOdgovor too = new TransferObjekatOdgovor();
        
        
        try {
            SOObrisiTrku so = new SOObrisiTrku(trkaObrisi, ucesceBrisanje);
            so.opsteIzvrsenjeSO();
            too.setRezultat(true);
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return too;
    }

    public TransferObjekatOdgovor sacuvajRezultate(ArrayList<Ucesce> ucescaRezultati) {
        TransferObjekatOdgovor too = new TransferObjekatOdgovor();
        try {
            SOSacuvajRezultate so=new SOSacuvajRezultate(ucescaRezultati);
            so.opsteIzvrsenjeSO();
            too.setRezultat(true);
        } catch (Exception ex) {
            too.setRezultat(false);
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return too;
    }

    public TransferObjekatOdgovor izmeniTrku(Trka trkaIzmena) {
        TransferObjekatOdgovor too =  new TransferObjekatOdgovor();
        try {
            SOIzmeniTrku so = new SOIzmeniTrku(trkaIzmena);
            so.opsteIzvrsenjeSO();
            too.setRezultat(true);
        } catch (Exception ex) {
            too.setRezultat(false);
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return too;
    }

    
    
    
    
}  


