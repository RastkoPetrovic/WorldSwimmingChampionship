/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Trka;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import kontroler.KontrolerKI;

/**
 *
 * @author Rastko
 */
public class ModelTabeleTrke extends AbstractTableModel{
    ArrayList<Trka> listaTrka;
    private KontrolerKI kontrolerKI;

    public ModelTabeleTrke(ArrayList<Trka> listaTrka) {
        this.listaTrka = listaTrka;
        kontrolerKI = new KontrolerKI();
    }
    

    @Override
    public int getRowCount() {
        if(listaTrka == null) {
            return 0;
        }
        return listaTrka.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trka t = listaTrka.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch(columnIndex) {
            case 0: return t.getDuzina();
            case 1: return t.getDrzava();
            case 2: return sdf.format(t.getDatumOdrzavanja());
            case 3: return t.getVremeOdrzavanja();
            case 4: return t.getNazivPlivalista();
            case 5: return t.getBrojUcesnika();
            case 6: return t.getFazaTakmicenja();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0: return "Dužina";
            case 1: return "Država";
            case 2: return "Datum";
            case 3: return "Vreme";
            case 4: return "Naziv plivališta";
            case 5: return "Broj ucesnika";
            case 6: return "Faza takmičenja";
            default: return "n/a";
        }
    }

    public ArrayList<Trka> getListaTrka() {
        return listaTrka;
    }
    
    public void obrisi(int red) {
        listaTrka.remove(red);
        fireTableDataChanged();
    }
    
    public Trka getTrka(int red) {
        return listaTrka.get(red);
    }
    
    
    
    
}
