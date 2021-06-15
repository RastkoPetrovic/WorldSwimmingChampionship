/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.Korisnik;
import domen.Plivac;
import domen.Trka;
import domen.Ucesce;
import domen.VrstaTrka;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logika.Kontroler;
import transfer.Operacije;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;

/**
 *
 * @author Rastko
 */
public class ObradaZahteva extends Thread{
    
    Socket s;

    public ObradaZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while(true){
            TransferObjekatZahtev toz = primiZahtev();
            TransferObjekatOdgovor too = new TransferObjekatOdgovor();
            
                switch(toz.getOperacija()) {
                    case Operacije.LOGIN:
                        Korisnik korisnik = (Korisnik) toz.getParametar();
                        Korisnik ulogovani = Kontroler.getInstance().nadjiKorisnika(korisnik);
                        too.setRezultat(ulogovani);
                        break;
                    case Operacije.ZAPAMTI_PLIVACA:
                        Plivac plivac = (Plivac) toz.getParametar();
                        too = Kontroler.getInstance().zapamtiPlivaca(plivac);
                        break;
                    case Operacije.VRATI_VRSTE:
                        ArrayList<VrstaTrka> lista = Kontroler.getInstance().vratiVrsteTrka();
                        too.setRezultat(lista);
                        break;
                    case Operacije.VRATI_PLIVACE:
                        ArrayList<Plivac> plivaci=Kontroler.getInstance().vratiPlivace();
                        too.setRezultat(plivaci);
                        break;
                    case Operacije.ZAPAMTI_TRKU:
                        Trka trka = (Trka) toz.getParametar();
                        ArrayList<Ucesce> ucescaTrke = (ArrayList<Ucesce>) toz.getParametar2();
                        too = Kontroler.getInstance().zapamtiTrku(trka,ucescaTrke);
                        break;
                    case Operacije.VRATI_TRKE:
                        ArrayList<Trka> trke = Kontroler.getInstance().vratiTrke();
                        too.setRezultat(trke);
                        break;
                    case Operacije.VRATI_UCESCA: 
                        Trka t = (Trka) toz.getParametar();
                        ArrayList<Ucesce> ucesca = Kontroler.getInstance().vratiUcesca(t);
                        too.setRezultat(ucesca);
                        break;
                    case Operacije.OBRISI_TRKU:
                        Trka trkaObrisi = (Trka) toz.getParametar();
                        ArrayList<Ucesce> ucesceBrisanje = (ArrayList<Ucesce>) toz.getParametar2();
                        too = Kontroler.getInstance().obrisiTrku(trkaObrisi, ucesceBrisanje);
                        break;
                    case Operacije.SACUVAJ_REZULTATE:
                        ArrayList<Ucesce> ucescaRezultati = (ArrayList<Ucesce>) toz.getParametar2();
                        too = Kontroler.getInstance().sacuvajRezultate(ucescaRezultati);
                        break;
                    case Operacije.IZMENI_TRKU:
                        Trka trkaIzmena = (Trka) toz.getParametar();
                        too = Kontroler.getInstance().izmeniTrku(trkaIzmena);
                        break;
                        
                     
                }
                
            
            posaljiOdgovor(too);
        }
    }

    private TransferObjekatZahtev primiZahtev() {
        TransferObjekatZahtev toz = new TransferObjekatZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            toz = (TransferObjekatZahtev) ois.readObject();
        } catch (IOException ex) {
            Kontroler.getInstance().getListaKlijenata().remove(s);
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toz;         
    }

    private void posaljiOdgovor(TransferObjekatOdgovor too) {
        
        try {  
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(too);
        } catch (IOException ex) {
            Kontroler.getInstance().getListaKlijenata().remove(s);
            Logger.getLogger(ObradaZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
