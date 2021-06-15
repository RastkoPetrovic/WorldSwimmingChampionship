/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import forme.ServerskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rastko
 */
public class NitOsvezi extends Thread{
    ServerskaForma sf;

    public NitOsvezi(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        System.out.println("Osvezio");
        try {
            sleep(6000);
        } catch (InterruptedException ex) {
            Logger.getLogger(NitOsvezi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    
    
    
    
}
