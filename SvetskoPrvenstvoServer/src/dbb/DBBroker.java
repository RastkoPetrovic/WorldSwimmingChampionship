/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;


import com.mysql.jdbc.Connection;
import domen.OpstiDomenskiObjekat;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author Rastko
 */
public class DBBroker {
    private Connection connection;
    
    public void ucitajDriver() throws Exception {
        
        try {
            Class.forName(UcitavanjePodesavanja.getInstanca().getProperty("driver"));
        } catch (ClassNotFoundException ex) {
            throw new Exception("Neodgovarajuci driver!" + ex.getMessage());
        }    
    }
    
    public void otvoriKonekciju() throws Exception {
        try {
            String url = UcitavanjePodesavanja.getInstanca().getProperty("url");
            String user = UcitavanjePodesavanja.getInstanca().getProperty("username");
            String password = UcitavanjePodesavanja.getInstanca().getProperty("password");
            connection = (Connection) DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new Exception("Konekcija nije uspostavljena!" + ex.getMessage());
        }
        
      
    }
    
    public void zatvoriKonekciju() throws Exception{
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Konekcija nije zatvorena! " + ex.getMessage());
        }
    }
    
    public void commit() throws Exception {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new Exception("Neuspesan commit transakcije! " + ex.getMessage());
        }
    }
    
    public void rollback() throws Exception {
        try {
            connection.rollback();
        } catch (SQLException ex) {
           throw new Exception("Neuspesan rollback transakcije! " + ex.getMessage());
        }
    }

    public void insert(OpstiDomenskiObjekat odo) throws SQLException{
        String sql = "INSERT INTO "+odo.vratiImeKlase()+""+odo.vratiAtributeKlase()+" values"+odo.vratiVrednostiAtributa()+"";
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
    }
    public void update(OpstiDomenskiObjekat odo) throws SQLException{
        String sql = "Update "+odo.vratiImeKlase()+" set "+odo.postaviVrednostiAtributa()+" "+odo.vratiUslovIzmene()+"";
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
    }
    public void delete(OpstiDomenskiObjekat odo) throws SQLException{
        String sql = "Delete from "+odo.vratiImeKlase()+" "+odo.vratiUslovBrisanja()+ "";
        Statement s = connection.createStatement();
        s.executeUpdate(sql);
    
    }
    public ArrayList<?> select(OpstiDomenskiObjekat odo) throws SQLException{
        String sql = "Select "+odo.vratiAtributePretrage()+" from "+odo.vratiTabeluPretrage()+" "+odo.vratiUslovPretrage()+ "";
        Statement s = connection.createStatement();
        ResultSet rs=s.executeQuery(sql); 
        ArrayList<?> rezultat=odo.napuni(rs);
        return rezultat;
    }
    

  
    

    

    

    
    
    
}
