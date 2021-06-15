/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Plivac;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rastko
 */
public class ModelTabelePlivac extends AbstractTableModel{
    ArrayList<Plivac> lista;

    public ModelTabelePlivac(ArrayList<Plivac> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
       if(lista == null)
           return 0;
       return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Plivac p =lista.get(rowIndex);
        switch(columnIndex){
            case 0: return p.getImePrezime();
            case 1: return p.getDrzava();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Plivac";
            case 1: return "Drzava";
            default: return "n/a";
        }
    }
    
    public Plivac getPlivac(int red) {
        return lista.get(red);
    }
    
    
    
    
        
    
}
    
    
    
    

