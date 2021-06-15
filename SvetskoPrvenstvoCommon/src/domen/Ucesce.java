/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rastko
 */
public class Ucesce implements Serializable, OpstiDomenskiObjekat{
    private Trka trka;
    private Plivac plivac;
    private int brojStaze;
    private String vreme;
    private int plasman;
    private Korisnik korisnik;

    public Ucesce() {
    }

    public Ucesce(Trka trka, Plivac plivac, int brojStaze, String vreme, int plasman, Korisnik korisnik) {
        this.trka = trka;
        this.plivac = plivac;
        this.brojStaze = brojStaze;
        this.vreme = vreme;
        this.plasman = plasman;
        this.korisnik = korisnik;
    }
   
    

    public Trka getTrka() {
        return trka;
    }

    public void setTrka(Trka trka) {
        this.trka = trka;
    }

    public Plivac getPlivac() {
        return plivac;
    }

    public void setPlivac(Plivac plivac) {
        this.plivac = plivac;
    }

    public int getBrojStaze() {
        return brojStaze;
    }

    public void setBrojStaze(int brojStaze) {
        this.brojStaze = brojStaze;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public int getPlasman() {
        return plasman;
    }

    public void setPlasman(int plasman) {
        this.plasman = plasman;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "((select max(trkaID) from trka), "+getPlivac().getPlivacID()+", 0, 'n/a', 0, "+getKorisnik().getKorisnikID()+")";
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "brojStaze="+getBrojStaze()+",vreme='"+getVreme()+"',plasman="+getPlasman()+"";
    }

    @Override
    public String vratiImeKlase() {
        return "ucesce";
    }

    @Override
    public String vratiAtributeKlase() {
        return "(trkaID, plivacID, brojStaze, vreme, plasman, korisnikID)";
    }

    @Override
    public String vratiUslovPretrage() {
        return "order by u.plasman asc";
    }

    @Override
    public String vratiUslovIzmene() {
        return "where trkaID="+getTrka().getTrkaID()+" and plivacID="+getPlivac().getPlivacID()+"";
    }

    @Override
    public String vratiUslovBrisanja() {
        return "where trkaID="+getTrka().getTrkaID()+"";
    }

    @Override
    public String vratiTabeluPretrage() {
        return "ucesce u join korisnik k on u.korisnikID=k.korisnikID join trka t on u.trkaID=t.TrkaID join plivac p on u.plivacID=p.plivacID";      
    }

    @Override
    public String vratiAtributePretrage() {
        return "*";
    }

    @Override
    public ArrayList<?> napuni(ResultSet rs) {
        ArrayList<Ucesce> lista = new ArrayList<>();
        try {
            while(rs.next()) {
                int trkID = rs.getInt("trkaID");
                int plivaciD = rs.getInt("plivacID");
                int brojStaze = rs.getInt("brojStaze");
                String vreme = rs.getString("vreme");
                int plasman = rs.getInt("plasman");
                
                int korisnikID = rs.getInt("korisnikID");
                String korisnickoIme = rs.getString("korisnickoIme");
                String sifra = rs.getString("sifra");
                String imePrezime = rs.getString("imePrezime");
                
                Korisnik k = new Korisnik(korisnikID, korisnickoIme, sifra, imePrezime);
                
                int duzina = rs.getInt("duzina");
                String drzava = rs.getString("drzava");
                Date datumOdrzavanja = rs.getDate("datumOdrzavanja");
                String vremeOdrzavanja = rs.getString("vremeOdrzavanja");
                String nazivPlivalista = rs.getString("nazivPlivalista");
                int brojUcesnika = rs.getInt("brojUcesnika");
                String fazaTakmicenja = rs.getString("fazaTakmicenja");
                String evropskiRekord = rs.getString("evropskiRekord");
                String svetskiRekord = rs.getString("svetskiRekord");
                String olimpijskiRekord = rs.getString("olimpijskiRekord");
                int vrstaID = rs.getInt("vrstaID");
                
                //String nazivStila = rs.getString("nazivStila");
                //String opis = rs.getString("opis");
                
                VrstaTrka vt = new VrstaTrka(vrstaID, "", "");
                Trka t = new Trka(trkID, duzina, drzava, datumOdrzavanja, vremeOdrzavanja, nazivPlivalista, brojUcesnika, fazaTakmicenja, evropskiRekord, svetskiRekord, olimpijskiRekord, vt);
                
                String imePrezimePlivaca = rs.getString("imePrezimePlivaca");
                String drzavaPlivaca  = rs.getString("drzava");
                Date datumRodjenja = rs.getDate("datumRodjenja");
                int brojMedaljaEvropska = rs.getInt("brojMedaljaEvropskaPrvenstva");
                int brojMedaljaSvetska = rs.getInt("brojMedaljaSvetskaPrvenstva");
                int brojMedaljaOlimpijske = rs.getInt("brojMedaljaOlimpijskeIgre");
                
                Plivac p = new Plivac(plivaciD, imePrezimePlivaca, drzavaPlivaca, datumRodjenja, brojMedaljaEvropska, brojMedaljaSvetska, brojMedaljaOlimpijske);
                Ucesce u = new Ucesce(t, p, brojStaze, vreme, plasman, k);
                lista.add(u);

                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ucesce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    
    
}
