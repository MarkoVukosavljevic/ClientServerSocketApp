/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Vukos
 */
public class StavkaRacuna extends AbstractDomainObject implements Serializable {

    private Racun racun;
    private int rbStavke;
    private double kolicinaStavke;
    private double cenaStavke;
    private Sladoled sladoled;

    public StavkaRacuna(Racun racun, int rbStavke, double kolicina, double cenaStavke, Sladoled sladoled) {
        this.racun = racun;
        this.rbStavke = rbStavke;
        this.kolicinaStavke = kolicina;
        this.cenaStavke = cenaStavke;
        this.sladoled = sladoled;
    }

    public StavkaRacuna() {
    }

    @Override
    public String nazivTabele() {
        return " stavkaRacuna ";
    }

    @Override
    public String alijas() {
        return " sr ";
    }

    @Override
    public String join() {
        return " JOIN racun r ON (r.racunid = sr.racunid) "
                + "JOIN kupac k ON (k.kupacid = r.kupacid) "
                + "JOIN administrator a ON (a.administratorid = r.administratorid) "
                + "JOIN sladoled s ON (s.SladoledID = sr.SladoledID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("ImeKupca"), rs.getString("PrezimeKupca"),
                    rs.getString("Email"), rs.getString("BrojTelefona"));
            
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Racun r = new Racun(rs.getLong("RacunID"), 
                    rs.getTimestamp("DatumVreme"), rs.getDouble("UkupanIznos"), k, a, null);
            
            Sladoled s = new Sladoled(rs.getLong("SladoledID"),
                    rs.getString("NazivSladoleda"), rs.getString("Opis"),
                    rs.getDouble("CenaPoKG"),  null);
            
            StavkaRacuna sr = new StavkaRacuna(r, rs.getInt("RbStavke"), 
                    rs.getDouble("KolicinaStavke"), rs.getDouble("CenaStavke"), s);

            lista.add(sr);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (RacunID, RbStavke, KolicinaStavke, CenaStavke, SladoledID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " RacunID = " + racun.getRacunID();
    }

    @Override
    public String vrednostiZaInsert() {
        return racun.getRacunID() + ", " + rbStavke + ", "
                + kolicinaStavke + ", " + cenaStavke + ", " + sladoled.getSladoledID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return " WHERE R.RACUNID = " + racun.getRacunID();
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public double getKolicinaStavke() {
        return kolicinaStavke;
    }

    public void setKolicinaStavke(double kolicinaStavke) {
        this.kolicinaStavke = kolicinaStavke;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public Sladoled getSladoled() {
        return sladoled;
    }

    public void setSladoled(Sladoled sladoled) {
        this.sladoled = sladoled;
    }

  
}
