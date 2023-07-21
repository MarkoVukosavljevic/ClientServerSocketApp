/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vukos
 */
public class Sladoled extends AbstractDomainObject implements Serializable {

    private Long sladoledID;
    private String nazivSladoleda;
    private String opis;
    private double cenaPoKG;
    private ArrayList<Sastojak> sastojci;
    

    public Sladoled(Long sladoledID, String nazivSladoleda, String opis, double cenaPoKG, ArrayList<Sastojak> sastojci) {
        this.sladoledID = sladoledID;
        this.nazivSladoleda = nazivSladoleda;
        this.opis = opis;
        this.cenaPoKG = cenaPoKG;
        this.sastojci = sastojci;
       
    }

    public Sladoled() {
    }

    @Override
    public String toString() {
        return nazivSladoleda
                + ", (CenaPoKG: " + cenaPoKG + "din)";
    }

    @Override
    public String nazivTabele() {
        return " sladoled ";
    }

    @Override
    public String alijas() {
        return " s ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Sladoled s = new Sladoled(rs.getLong("SladoledID"),
                    rs.getString("NazivSladoleda"), rs.getString("Opis"),
                    rs.getDouble("CenaPoKG"), null);

            lista.add(s);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (NazivSladoleda, Opis, CenaPoKG) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " SladoledID = " + sladoledID;
    }

    @Override
    public String vrednostiZaInsert() {
        
        return "'" + nazivSladoleda + "', '" + opis + "', "
                + cenaPoKG  ;
    }

    @Override
    public String vrednostiZaUpdate() {
        return " nazivSladoleda = '" + nazivSladoleda + "', opis = '" + opis + "', "
                + "cenaPoKG = " + cenaPoKG;
    }

    @Override
    public String getByID() {
            return "";
    }

    public Long getSladoledID() {
        return sladoledID;
    }

    public void setSladoledID(Long sladoledID) {
        this.sladoledID = sladoledID;
    }

    public String getNazivSladoleda() {
        return nazivSladoleda;
    }

    public void setNazivSladoleda(String nazivSladoleda) {
        this.nazivSladoleda = nazivSladoleda;
    }


    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCenaPoKG() {
        return cenaPoKG;
    }

    public void setCenaPoKG(double cenaPoKG) {
        this.cenaPoKG = cenaPoKG;
    }


    public ArrayList<Sastojak> getSastojci() {
        return sastojci;
    }

    public void setSastojci(ArrayList<Sastojak> sastojci) {
        this.sastojci = sastojci;
    }

}
