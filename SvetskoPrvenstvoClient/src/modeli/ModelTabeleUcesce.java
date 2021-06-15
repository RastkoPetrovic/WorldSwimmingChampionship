/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Plivac;
import domen.Trka;
import domen.Ucesce;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import komunikacija.KomunikacijaSaServerom;

/**
 *
 * @author Rastko
 */
public class ModelTabeleUcesce extends AbstractTableModel{
    ArrayList<Ucesce> listaUcesca;
    private boolean promenljivo = false;
    private Trka t= null;

    public ModelTabeleUcesce() {
        listaUcesca = new ArrayList<>();
    }

    public ModelTabeleUcesce(ArrayList<Ucesce> listaUcesca) {
        this.listaUcesca = listaUcesca;
    }
    
    @Override
    public int getRowCount() {
        return listaUcesca.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ucesce ucesce = listaUcesca.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return ucesce.getPlivac().getImePrezime();
            case 1: return ucesce.getBrojStaze();
            case 2: return ucesce.getVreme();
            case 3: return ucesce.getPlasman();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Plivač";
            case 1: return "Broj staze";
            case 2: return "Vreme";
            case 3: return "Plasman";
            default: return "n/a";
        }
    }

    public ArrayList<Ucesce> getListaUcesca() {
        return listaUcesca;
    }
    
    public void obrisi(int red){
        listaUcesca.remove(red);
        fireTableDataChanged();
    }

    public void dodaj(Plivac p) throws IOException {
        Ucesce u = new Ucesce(null, p, 0, "n/a", 0, KomunikacijaSaServerom.getInstanca().getUlogovaniKorisnik());
        listaUcesca.add(u);
        fireTableDataChanged();
    }
    
    public boolean isPromenljivo() {
        return promenljivo;
    }

    public void setPromenljivo(boolean promenljivo) {
        this.promenljivo = promenljivo;
    }

    public void setT(Trka t) {
        this.t = t;
    }

    public Trka getT() {
        return t;
    }
    
    public boolean daLiJeSveUneto() {
        for (Ucesce ucesce : listaUcesca) {
            if((ucesce.getPlasman() == 0)) {
                return false;
            }
        }
        return true;
    }
    
    public Ucesce getRed(int red) {
        return listaUcesca.get(red);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(isPromenljivo()){
            if(columnIndex==0)
                return false;
            return true;
        }
        return false;
       
    }

    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Ucesce u = listaUcesca.get(rowIndex);
        
        switch(columnIndex) {
            case 1: String staza = (String) aValue;
            
                try {
                    
                    int brojStaze = Integer.parseInt(staza);
                    if(brojStaze < 1 || brojStaze > 8) {
                        u.setBrojStaze(0);
                        JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuću stazu!");
                        return;
                    }
                    
                    for (Ucesce ucesce : listaUcesca) {
                        if(ucesce.getBrojStaze() == brojStaze) {
                            u.setBrojStaze(0);
                            JOptionPane.showMessageDialog(null, "Uneli ste dve iste staze!");
                            return;
                        }
                        
                    }
                    u.setBrojStaze(brojStaze);
                            
                } catch (NumberFormatException e) {
                    u.setBrojStaze(0);
                    JOptionPane.showMessageDialog(null, "Nije unet broj!");
                }
                fireTableCellUpdated(rowIndex, 1);
                break;
            case 2: 
                String vreme = (String) aValue;
                u.setVreme(vreme);
                fireTableCellUpdated(rowIndex, 2);
                break;
            case 3:
                String plasmanStr = (String) aValue;
                
                try {
                    int plasman = Integer.parseInt(plasmanStr);
                    
                    if(plasman > listaUcesca.size() || plasman < 1) {
                        u.setPlasman(0);
                        JOptionPane.showMessageDialog(null, "Niste uneli odgovarajući plasman(<0 ili veći od broja učesnika)!");
                        return;
                    }
                    
                    for (Ucesce ucesce : listaUcesca) {
                        if(ucesce.getPlasman() == plasman && (ucesce != u)) {
                            u.setPlasman(0);
                            JOptionPane.showMessageDialog(null, "Uneta su dva ista plasmana!");
                            return;
                        }
                    }
                    u.setPlasman(plasman);
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Nije unet broj!");
                }
                fireTableCellUpdated(rowIndex, 3);
                break;
 

        }
    }
    
    
    
}
