/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.preferencijaKupca;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.PreferencijaKupca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Vukos
 */
public class SOAddPreferencijaKupca extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PreferencijaKupca)) {
            throw new Exception("Prosledjeni objekat nije instanca klase PreferencijaKupca!");

        }

        PreferencijaKupca pk = (PreferencijaKupca) ado;
        ArrayList<PreferencijaKupca> preferencije = (ArrayList<PreferencijaKupca>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (PreferencijaKupca p : preferencije) {
            if ((p.getKupac().getKupacID().equals( pk.getKupac().getKupacID()))
                    && (p.getSladoled().getSladoledID().equals( pk.getSladoled().getSladoledID()))) {
                throw new Exception("Vec postoji preferencija ovog kupca za ovaj sladoled!");
            }

        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().insert(ado);

    }

}
