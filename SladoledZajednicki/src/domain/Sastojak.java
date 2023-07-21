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
public class Sastojak extends AbstractDomainObject implements Serializable {

    private Sladoled sladoled;
    private int rbSastojka;
    private String nazivSastojka;

    public Sastojak(Sladoled sladoled, int rbSastojka, String nazivSastojka) {
        this.sladoled = sladoled;
        this.rbSastojka = rbSastojka;
        this.nazivSastojka = nazivSastojka;
    }

    @Override
    public String toString() {
        return nazivSastojka;
    }

    public Sastojak() {
    }

    @Override
    public String nazivTabele() {
        return " sastojak ";
    }

    @Override
    public String alijas() {
        return " sas ";
    }

    @Override
    public String join() {
        return " JOIN sladoled s USING (SladoledID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Sladoled s = new Sladoled(rs.getLong("SladoledID"),
                    rs.getString("NazivSladoleda"), rs.getString("Opis"),
                    rs.getDouble("CenaPoKG"),  null);

            Sastojak sas = new Sastojak(s, rs.getInt("RbSastojka"), rs.getString("NazivSastojka"));

            lista.add(sas);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (SladoledID, RbSastojka, NazivSastojka) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " SladoledID = " + sladoled.getSladoledID();
    }

    @Override
    public String vrednostiZaInsert() {
        return sladoled.getSladoledID() + ", " + rbSastojka + ", "
                + "'" + nazivSastojka + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return " WHERE S.SLADOLEDID = " + sladoled.getSladoledID();
    }

    public Sladoled getSladoled() {
        return sladoled;
    }

    public void setSladoled(Sladoled sladoled) {
        this.sladoled = sladoled;
    }


    public int getRbSastojka() {
        return rbSastojka;
    }

    public void setRbSastojka(int rbSastojka) {
        this.rbSastojka = rbSastojka;
    }

    public String getNazivSastojka() {
        return nazivSastojka;
    }

    public void setNazivSastojka(String nazivSastojka) {
        this.nazivSastojka = nazivSastojka;
    }
}
