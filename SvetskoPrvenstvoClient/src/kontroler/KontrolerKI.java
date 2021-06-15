/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Korisnik;
import domen.Plivac;
import domen.Trka;
import domen.Ucesce;
import domen.VrstaTrka;
import forme.FormaDodajPlivača;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.TrkeForma;
import forme.TrkePretragaForma;
import forme.TrkePretragaForma;
import forme.UcesceForma;
import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import komunikacija.KomunikacijaSaServerom;
import modeli.ModelTabeleDodajUcesce;
import modeli.ModelTabeleTrke;
import modeli.ModelTabeleUcesce;
import transfer.Operacije;
import transfer.TransferObjekatOdgovor;
import transfer.TransferObjekatZahtev;

/**
 *
 * @author Rastko
 */
public class KontrolerKI {

    public static void ucitajKomboVrste(TrkeForma aThis, JComboBox<Object> cmbVrstaTrke) {
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Operacije.VRATI_VRSTE);
            KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
            ArrayList<VrstaTrka> lista = (ArrayList<VrstaTrka>) too.getRezultat();
            cmbVrstaTrke.removeAllItems();
            
            for (VrstaTrka vrstaTrka : lista) {
                cmbVrstaTrke.addItem(vrstaTrka);
            }
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(aThis, "Server nije pokrenut");
            Logger.getLogger(KontrolerKI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   

    public void nadjiKorisnika(LoginForma aThis, JTextField txtKorisnickoIme, JTextField txtSifra, JButton btnLogin) {
        try {
            String username = txtKorisnickoIme.getText();
            String password = txtSifra.getText();
            
            Korisnik korisnik = new Korisnik(username, password);
            
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Operacije.LOGIN);
            toz.setParametar(korisnik);
            KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
            Korisnik ulogovaniKorisnik = (Korisnik) too.getRezultat();
            
            
            if(ulogovaniKorisnik != null) {
                JOptionPane.showMessageDialog(aThis, "Korisnik je uspesno ulogovan");
                GlavnaForma gf = new GlavnaForma();
                KomunikacijaSaServerom.getInstanca().setUlogovaniKorisnik(ulogovaniKorisnik);
                gf.setUlogovani(ulogovaniKorisnik);
                gf.setVisible(true);
                aThis.setVisible(false);  
            }else {
                
                JOptionPane.showMessageDialog(aThis, "Korisnik nije uneo tacne podatke");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(aThis, "Server nije pokrenut");
            Logger.getLogger(KontrolerKI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zapamtiPlivaca(FormaDodajPlivača aThis, JTextField txtImePrezime, JTextField txtDrzava, JTextField txtDatum, JTextField txtEvropska, JTextField txtSvetska, JTextField txtOlimpijske) {
        try {
            if(txtImePrezime.getText().isEmpty() || txtDrzava.getText().isEmpty() || txtDatum.getText().isEmpty() ||
                    txtEvropska.getText().isEmpty()|| txtSvetska.getText().isEmpty() || txtOlimpijske.getText().isEmpty()) {
                JOptionPane.showMessageDialog(aThis, "Niste popunili sva polja");
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            String imePrezime = txtImePrezime.getText();
            String drzava = txtDrzava.getText();
            String datum = txtDatum.getText();
            Date datumRodjenja = null;
            String evropska = txtEvropska.getText();
            String svetska = txtSvetska.getText();
            String olimpijske = txtOlimpijske.getText();
            
            try {
                datumRodjenja = sdf.parse(datum);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(aThis, "Niste uneli datum u odgovarajucem formatu");
                return;
            }
            
            int brojEvropska = 0;
            
            try {
                brojEvropska = Integer.parseInt(evropska);
            } catch (NumberFormatException x) {
                JOptionPane.showMessageDialog(aThis, "Morate uneti broj u polje evropska prvenstva");
                return;
            }
            
            int brojSvetska = 0;
            try {
                brojSvetska = Integer.parseInt(svetska);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(aThis, "Morate uneti broj u polje svetska prvenstva");
                return;
            }
            
            int brojOlimpijske = 0;
            try {
                brojOlimpijske = Integer.parseInt(olimpijske);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(aThis, "Morate uneti broj u polje olimpijske igre");
                return;
            }
            
            Plivac plivac = new Plivac(-1, imePrezime, drzava, datumRodjenja, brojEvropska, brojSvetska, brojOlimpijske);
            
            TransferObjekatZahtev toz =  new TransferObjekatZahtev();
            toz.setOperacija(Operacije.ZAPAMTI_PLIVACA);
            toz.setParametar(plivac);
            KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
            boolean uspesno = (boolean) too.getRezultat();
            
            if (uspesno) {
                JOptionPane.showMessageDialog(aThis, "Sistem je zapamtio plivaca");
                aThis.dispose();
            }else {
                JOptionPane.showMessageDialog(aThis, "Sistem ne moze da zapamti plivaca");
            }
                    
                    
        } catch (IOException ex) {
            
            JOptionPane.showMessageDialog(aThis, "Server nije pokrenut");
            Logger.getLogger(KontrolerKI.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        
    }

    public void srediTabeluDodajUcesca(UcesceForma aThis, JTable tblPlivaci, ArrayList<Ucesce> listaUcesca) {
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Operacije.VRATI_PLIVACE);
            KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
            ArrayList<Plivac> listaPlivaca = (ArrayList<Plivac>) too.getRezultat();
            ArrayList<Plivac> listaPomocna = new ArrayList<>();
            for (Plivac plivac : listaPlivaca) {
                boolean dodat = true;
                for (Ucesce ucesce: listaUcesca) {
                    if(ucesce.getPlivac().getPlivacID() == plivac.getPlivacID()){
                        dodat = false;
                        
                    }
                }
                if(dodat) {
                    listaPomocna.add(plivac);
                }
                
            }
            
            ModelTabeleDodajUcesce mtdu = new ModelTabeleDodajUcesce(listaPomocna);
            tblPlivaci.setModel(mtdu);
            if(listaPomocna.isEmpty()) 
                JOptionPane.showMessageDialog(aThis, "Uneta su sva moguća učešća");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(aThis, "Server nije pokrenut");
            Logger.getLogger(KontrolerKI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sacuvaTrku(TrkeForma aThis, JTable tabelaUcesce, JTextField txtDuzina, JTextField txtDrzava, JTextField txtPlivaliste, JTextField txtDatum, JTextField txtVreme, JTextField txtFaza, JTextField txtBrojUcesnika, JTextField txtEvropski, JTextField txtSvetski, JTextField txtOlimpijski, JComboBox cmbVrstaTrke, JButton btnSacuvajTrku) {
        try {
            String duzinaTrke = txtDuzina.getText();
            String drzava = txtDrzava.getText();
            String nazivPlivalista = txtPlivaliste.getText();
            String datumTrke = txtDatum.getText();
            String vreme = txtVreme.getText();
            String fazaTakmicenja = txtFaza.getText();
            String brojPlivaca = txtBrojUcesnika.getText();
            String evropskiRekord = txtEvropski.getText();
            String svetskiRekord = txtSvetski.getText();
            String olimpijskiRekord = txtOlimpijski.getText();
        
            if(duzinaTrke.isEmpty() || drzava.isEmpty() || nazivPlivalista.isEmpty() || datumTrke.isEmpty()
                || vreme.isEmpty() || fazaTakmicenja.isEmpty() || brojPlivaca.isEmpty()
                || evropskiRekord.isEmpty() || svetskiRekord.isEmpty() || olimpijskiRekord.isEmpty()) {
                JOptionPane.showMessageDialog(aThis, "Morate uneti sve vrednosti!");
                return;
            }
        
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date datum = null;
            int duzina = 0;
            int brojUcesnika = 0;
        
            try {
                datum = sdf.parse(datumTrke);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(aThis, "Niste uneli datum u pravom formatu");
            }
        
            try {
                duzina = Integer.parseInt(duzinaTrke);
                if(duzina < 0)
                {
                    JOptionPane.showMessageDialog(aThis, "Dužina trke ne sme biti negativna");
                    return;
                } 
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(aThis, "Dužina trke nije uneta u pravom formatu");
                return;
            }
        
            try {
                brojUcesnika = Integer.parseInt(brojPlivaca);
                if(brojUcesnika < 0) 
                {
                    JOptionPane.showMessageDialog(aThis, "Broj ucesnika trke ne može biti manji od nule");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(aThis, "Broj ucesnika trke nije unet u pravom formatu");
                return;
            }
        
            ModelTabeleUcesce mtu = (ModelTabeleUcesce) tabelaUcesce.getModel();
            ArrayList<Ucesce> listaUcesca = mtu.getListaUcesca();
            if(listaUcesca.isEmpty()) {
                JOptionPane.showMessageDialog(aThis, "Morate dodati barem jedno ucesce");
                return;
            }
        
            if(listaUcesca.size() != brojUcesnika) {
                JOptionPane.showMessageDialog(aThis, "Lista ucesca ne sme da se razlikuje od broja učesnika");
                return;
            }
        
            VrstaTrka vt = (VrstaTrka) cmbVrstaTrke.getSelectedItem();
            Trka t = new Trka(-1, duzina, drzava, datum, vreme, nazivPlivalista, brojUcesnika, fazaTakmicenja, evropskiRekord, svetskiRekord, olimpijskiRekord, vt);

            if(btnSacuvajTrku.getText().equals("Sačuvaj trku")) {
                TransferObjekatZahtev toz = new TransferObjekatZahtev();
                toz.setOperacija(Operacije.ZAPAMTI_TRKU);
                toz.setParametar(t);
                toz.setParametar2(listaUcesca);
                KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
                TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
                boolean uspesno = (boolean) too.getRezultat();
                if(uspesno) {
                    JOptionPane.showMessageDialog(aThis, "Sistem je zapamtio trku");
                    aThis.dispose();
                    return;
                }else {
                    JOptionPane.showMessageDialog(aThis, "Sistem ne može da zapamti trku!");
                    return;
                }
            }else {
                boolean uneto = mtu.daLiJeSveUneto();
                if(!uneto) {
                    JOptionPane.showConfirmDialog(aThis, "Niste uneli sve rezultate");
                    return;
                }
                ArrayList<Ucesce> unetaUcesca = srediUnetaUcesca(listaUcesca, txtFaza);
                TransferObjekatZahtev toz = new TransferObjekatZahtev();
                toz.setOperacija(Operacije.SACUVAJ_REZULTATE);
                toz.setParametar(t);
                toz.setParametar2(unetaUcesca);
                KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
                TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
                boolean uspesno = (boolean) too.getRezultat();
                
                if(uspesno) {
                    JOptionPane.showMessageDialog(aThis, "Sistem ja sačuvao rezultate");
                    TrkeForma tf = new TrkeForma();
                    tf.setVisible(true);
                    aThis.dispose();
                    return;
                }else {
                    JOptionPane.showMessageDialog(aThis, "Sistem ne može da sačuva rezultate!");
                    return;
                }
                        
                
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Server nije pokrenut");
            Logger.getLogger(KontrolerKI.class.getName()).log(Level.SEVERE, null,e);
        }
       
    }

    public void srediTabeluTrke(TrkePretragaForma aThis, JTable tabelaTrke) {
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Operacije.VRATI_TRKE);
            KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
            ArrayList<Trka> lista = (ArrayList<Trka>) too.getRezultat();
            ModelTabeleTrke mtt = new ModelTabeleTrke(lista);
            tabelaTrke.setModel(mtt);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(aThis, "Sistem nije pokrenut");
                Logger.getLogger(KontrolerKI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pripremiTabeluTrkeZaSort(TrkePretragaForma aThis, JTable tabelaTrke, JTextField txtPretraziTrke) {
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabelaTrke.getModel());
        tabelaTrke.setRowSorter(rowSorter);

        txtPretraziTrke.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                String filter = txtPretraziTrke.getText();

                if (filter.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filter));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtPretraziTrke.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

   
    
    public void srediFormuPrikaziTrku(int red, JTable tabelaTrke, JTable tabelaUcesce, JTextField txtDuzina, JTextField txtDrzava, JTextField txtPlivaliste, JTextField txtDatum, JTextField txtVreme, JTextField txtFaza, JTextField txtBrojUcesnika, JComboBox cmbVrstaTrke, JTextField txtEvropski, JTextField txtSvetski, JTextField txtOlimpijski, JButton btnSacuvajTrku, JButton btnDodajUcesca, JButton btnObrisiUcesce, JButton btnOtkazi, JButton btnIzmeniTrku) {
        ModelTabeleTrke mtt = (ModelTabeleTrke) tabelaTrke.getModel();
        Trka t = mtt.getTrka(red);
        btnDodajUcesca.setVisible(false);
        btnObrisiUcesce.setVisible(false);
        btnSacuvajTrku.setVisible(false);
        btnIzmeniTrku.setVisible(true);
        btnOtkazi.setVisible(true);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        txtDuzina.setText(t.getDuzina()+"");
        txtDuzina.setEditable(true);
        txtDrzava.setText(t.getDrzava());
        txtDrzava.setEditable(true);
        txtPlivaliste.setText(t.getNazivPlivalista());
        txtPlivaliste.setEditable(true);
        txtDatum.setText(sdf.format(t.getDatumOdrzavanja()));
        txtDatum.setEditable(true);
        txtVreme.setText(t.getVremeOdrzavanja());
        txtVreme.setEditable(true);
        txtFaza.setText(t.getFazaTakmicenja());
        txtFaza.setEditable(true);
        txtBrojUcesnika.setText(t.getBrojUcesnika()+"");
        txtBrojUcesnika.setEditable(false);
        txtEvropski.setText(t.getEvropskiRekord());
        txtEvropski.setEditable(true);
        txtSvetski.setText(t.getSvetskiRekord());
        txtSvetski.setEditable(true);
        txtOlimpijski.setText(t.getOlimpijskiRekord());
        txtOlimpijski.setEditable(true);
        
        cmbVrstaTrke.setSelectedItem(t.getVrstaTrka().getNazivStila());
        
        cmbVrstaTrke.setEditable(false);
        srediTabeluUcesce(tabelaUcesce, t);
        
        
        
                
    }

    private void srediTabeluUcesce(JTable tabelaUcesce, Trka t) {
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setParametar(t);
            toz.setOperacija(Operacije.VRATI_UCESCA);
            KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
            ArrayList<Ucesce> lista = (ArrayList<Ucesce>) too.getRezultat();
            ModelTabeleUcesce mtu =  new ModelTabeleUcesce(lista);
            tabelaUcesce.setModel(mtu);
                    
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Server nije pokrenut");
                Logger.getLogger(KontrolerKI.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    
    public void obrisiTrku(TrkePretragaForma aThis, int red, JTable tabelaTrke) {
        try {
            ModelTabeleTrke mtt = (ModelTabeleTrke) tabelaTrke.getModel();
            Trka t = mtt.getTrka(red);
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            ArrayList<Ucesce> ucesca = vratiUcesca(t);
            toz.setOperacija(Operacije.OBRISI_TRKU);
            toz.setParametar(t);
            toz.setParametar2(ucesca);
            KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
            boolean uspesno = (boolean) too.getRezultat();
            if(uspesno) {
                mtt.obrisi(red);
                JOptionPane.showMessageDialog(aThis, "Sistem je obrisao trku");
            }else {
                 JOptionPane.showMessageDialog(aThis, "Sistem ne može da obriše trku");
            }
        } catch (IOException ex) {
            Logger.getLogger(KontrolerKI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<Ucesce> vratiUcesca(Trka t) {
        ArrayList<Ucesce> lista = null;
        try {
            TransferObjekatZahtev toz = new TransferObjekatZahtev();
            toz.setOperacija(Operacije.VRATI_UCESCA);
            toz.setParametar(t);
            KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
            TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
            lista = (ArrayList<Ucesce>) too.getRezultat();
        } catch (IOException ex) {
            Logger.getLogger(KontrolerKI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;       
    }

    public void srediFormuUnesiRezultate(TrkeForma aThis, int red, JTable tabelaTrke ,JTable tabelaUcesce, JTextField txtDuzina, JTextField txtDrzava, JTextField txtPlivaliste, JTextField txtDatum, JTextField txtVreme, JTextField txtFaza, JTextField txtBrojUcesnika, JTextField txtBrojUcesnika0, JTextField txtEvropski, JTextField txtSvetski, JTextField txtOlimpijski, JComboBox cmbVrstaTrke, JButton btnSacuvajTrku, JButton btnDodajUcesca, JButton btnObrisiUcesce, JButton btnIzmeniTrku, JButton btnOtkazi) throws Exception {
            ModelTabeleTrke mtt = (ModelTabeleTrke) tabelaTrke.getModel();
            Trka t = mtt.getTrka(red);
            btnDodajUcesca.setVisible(false);
            btnObrisiUcesce.setVisible(false);
            btnIzmeniTrku.setVisible(false);
            btnOtkazi.setVisible(true);
            btnSacuvajTrku.setText("Sačuvaj rezultate");
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            txtDuzina.setText(t.getDuzina()+"");
            txtDuzina.setEditable(false);
            txtDrzava.setText(t.getDrzava());
            txtDrzava.setEditable(false);
            txtPlivaliste.setText(t.getNazivPlivalista());
            txtPlivaliste.setEditable(false);
            txtDatum.setText(sdf.format(t.getDatumOdrzavanja()));
            txtDatum.setEditable(false);
            txtVreme.setText(t.getVremeOdrzavanja());
            txtVreme.setEditable(false);
            txtFaza.setText(t.getFazaTakmicenja());
            txtFaza.setEditable(false);
            txtBrojUcesnika.setText(t.getBrojUcesnika()+"");
            txtBrojUcesnika.setEditable(false);
            txtEvropski.setText(t.getEvropskiRekord());
            txtEvropski.setEditable(false);
            txtSvetski.setText(t.getSvetskiRekord());
            txtSvetski.setEditable(false);
            txtOlimpijski.setText(t.getOlimpijskiRekord());
            txtOlimpijski.setEditable(false);
        
            cmbVrstaTrke.setSelectedItem(t.getVrstaTrka());
            cmbVrstaTrke.setEnabled(false);
            
            srediTabeluUcesce(tabelaUcesce, t);
            ModelTabeleUcesce mtu = (ModelTabeleUcesce) tabelaUcesce.getModel();
            mtu.setPromenljivo(true);
            mtu.setT(t);
            boolean uneto = mtu.daLiJeSveUneto();
            
            if(uneto) {
                throw new Exception(); 
            }
            
            
            
    }

    public void izmeniTrku(TrkeForma aThis, Trka trka, JTextField txtDuzina, JTextField txtDrzava, JTextField txtPlivaliste, JTextField txtDatum, JTextField txtVreme, JTextField txtFaza, JTextField txtBrojUcesnika, JTextField txtEvropski, JTextField txtSvetski, JTextField txtOlimpijski, JComboBox cmbVrstaTrke) {
            try {
                String duzinaTrke = txtDuzina.getText();
                String drzava = txtDrzava.getText();
                String nazivPlivalista = txtPlivaliste.getText();
                String datumTrke = txtDatum.getText();
                String vreme = txtVreme.getText();
                String fazaTakmicenja = txtFaza.getText();
                String evropskiRekord = txtEvropski.getText();
                String svetskiRekord = txtSvetski.getText();
                String olimpijskiRekord = txtOlimpijski.getText();
            
                if(duzinaTrke.isEmpty() || drzava.isEmpty() || nazivPlivalista.isEmpty() || datumTrke.isEmpty()
                    || vreme.isEmpty() || fazaTakmicenja.isEmpty()
                    || evropskiRekord.isEmpty() || svetskiRekord.isEmpty() || olimpijskiRekord.isEmpty()) {
                    JOptionPane.showMessageDialog(aThis, "Morate uneti sve vrednosti!");
                    return;
                }
            
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Date datum = null;
                int duzina = 0;
            
        
                try {
                    datum = sdf.parse(datumTrke);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(aThis, "Niste uneli datum u pravom formatu");
                }
        
                try {
                    duzina = Integer.parseInt(duzinaTrke);
                    if(duzina < 0)
                    {
                        JOptionPane.showMessageDialog(aThis, "Dužina trke ne sme biti negativna");
                        return;
                    } 
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(aThis, "Dužina trke nije uneta u pravom formatu");
                    return;
                }
        
                trka.setDuzina(duzina);
                trka.setDrzava(drzava);
                trka.setDatumOdrzavanja(datum);
                trka.setVremeOdrzavanja(vreme);
                trka.setNazivPlivalista(nazivPlivalista);
                trka.setBrojUcesnika(Integer.parseInt(txtBrojUcesnika.getText()));
                trka.setFazaTakmicenja(fazaTakmicenja);
                trka.setEvropskiRekord(evropskiRekord);
                trka.setSvetskiRekord(svetskiRekord);
                trka.setOlimpijskiRekord(olimpijskiRekord);
            
                VrstaTrka vt = (VrstaTrka) cmbVrstaTrke.getSelectedItem();
                trka.setVrstaTrka(vt);

                TransferObjekatZahtev toz = new TransferObjekatZahtev();
                toz.setOperacija(Operacije.IZMENI_TRKU);
                toz.setParametar(trka);
                KomunikacijaSaServerom.getInstanca().posaljiZahtev(toz);
                TransferObjekatOdgovor too = KomunikacijaSaServerom.getInstanca().primiOdgovor();
                boolean uspesno = (boolean) too.getRezultat();
                if(uspesno) {
                    JOptionPane.showMessageDialog(aThis, "Sistem je izmenio trku");
                    aThis.dispose();
                    return;
                }else {
                    JOptionPane.showMessageDialog(aThis, "Sistem ne može da izmeni trku!");
                    return;
                }
                
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Server nije pokrenut");
            Logger.getLogger(KontrolerKI.class.getName()).log(Level.SEVERE, null,e);
        }
            
            
            
            
    }

    private ArrayList<Ucesce> srediUnetaUcesca(ArrayList<Ucesce> listaUcesca, JTextField txtFaza) {
        ArrayList<Ucesce> sredjena = new ArrayList<>();
        
        for (Ucesce ucesce : listaUcesca) {
            if((txtFaza.getText().equals("finale") || txtFaza.getText().equals("Finale") || txtFaza.getText().equals("FINALE")) 
                    && (ucesce.getPlasman() == 1 || ucesce.getPlasman() == 2 || ucesce.getPlasman() == 3)) {
                Plivac p = ucesce.getPlivac();
                p.setBrojMedaljaSvetskaPrvenstva(ucesce.getPlivac().getBrojMedaljaSvetskaPrvenstva() + 1);
                ucesce.setPlivac(p);
                
            }
            sredjena.add(ucesce);
        }
        return sredjena;
    }

    

    
    
}
