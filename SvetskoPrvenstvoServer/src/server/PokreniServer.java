/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dbb.UcitavanjePodesavanja;
import forme.ServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import logika.Kontroler;

/**
 *
 * @author Ivan
 */
public class PokreniServer extends Thread{
    ServerskaForma sf;

    public PokreniServer(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        try {
            int port =Integer.parseInt(UcitavanjePodesavanja.getInstanca().getProperty("port"));
            ServerSocket ss = new ServerSocket(port);
            sf.serverPokrenut();
            System.out.println("Server je pokrenut");
            NitZatvaranje nz = new NitZatvaranje(ss, this);
            nz.start();
            while (!isInterrupted()) {                
                Socket s = ss.accept();
                Kontroler.getInstance().getListaKlijenata().add(s);
                System.out.println("Klijent se povezao");
                ObradaZahteva oz = new ObradaZahteva(s);
                oz.start();
            }
            
        } catch (IOException ex) {
            System.out.println("Zaustavljen server");
            sf.serverNijePokrenut();
        }
    }
    
    
    
}
