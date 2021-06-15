/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public interface OpstiDomenskiObjekat {
    String vratiVrednostiAtributa();
    String postaviVrednostiAtributa();
    String vratiImeKlase();
    String vratiAtributeKlase();
    String vratiUslovPretrage();
    String vratiUslovIzmene();
    String vratiUslovBrisanja();
    String vratiTabeluPretrage();
    String vratiAtributePretrage();
    ArrayList<?> napuni(ResultSet rs);
}
