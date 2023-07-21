/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class PreferencijaKupca extends AbstractDomainObject implements Serializable {
private int ocenaKupca;
private Kupac kupac;
private Sladoled sladoled;

    public PreferencijaKupca(int ocenaKupca, Kupac kupac, Sladoled sladoled) {
        this.ocenaKupca = ocenaKupca;
        this.kupac = kupac;
        this.sladoled = sladoled;
    }

    public PreferencijaKupca() {
    }

    @Override
    public String nazivTabele() {
return " preferencijakupca ";
    }

    @Override
    public String alijas() {
return " pk ";
    }

    @Override
    public String join() {
  return " JOIN kupac k USING (kupacID) "
                + " JOIN sladoled s USING (sladoledID) ";

    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
 ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Kupac k = new Kupac(rs.getLong("KupacID"),
                    rs.getString("ImeKupca"), rs.getString("PrezimeKupca"),
                    rs.getString("Email"), rs.getString("BrojTelefona"));
            
             Sladoled s = new Sladoled(rs.getLong("SladoledID"),
                    rs.getString("NazivSladoleda"), rs.getString("Opis"),
                    rs.getDouble("CenaPoKG"), null);
             
             
             PreferencijaKupca pk = new PreferencijaKupca(rs.getInt("ocenaKupca"), k, s);


            lista.add(pk);
        }

        rs.close();
        return lista;

    }

    @Override
    public String koloneZaInsert() {
        return " (ocenaKupca, KupacID, SladoledID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " SladoledID = " + sladoled.getSladoledID()+ " and KupacID = "+kupac.getKupacID();

    }

    @Override
    public String vrednostiZaInsert() {
              return " " + ocenaKupca + ", " + kupac.getKupacID() + ", "
                + sladoled.getSladoledID();

    }

    @Override
    public String vrednostiZaUpdate() {
 return " ocenaKupca = " + ocenaKupca + ", KupacID = " + kupac.getKupacID() + ", SladoledID = "
                + sladoled.getSladoledID();    }

    @Override
    public String getByID() {
return "";

    }

    public int getOcenaKupca() {
        return ocenaKupca;
    }

    public void setOcenaKupca(int ocenaKupca) {
        this.ocenaKupca = ocenaKupca;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Sladoled getSladoled() {
        return sladoled;
    }

    public void setSladoled(Sladoled sladoled) {
        this.sladoled = sladoled;
    }
    
    
}
