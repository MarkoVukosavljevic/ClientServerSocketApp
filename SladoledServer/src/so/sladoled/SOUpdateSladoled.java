/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sladoled;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Sladoled;
import domain.Sastojak;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Vukos
 */
public class SOUpdateSladoled extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Sladoled)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sladoled!");
        }

        Sladoled s = (Sladoled) ado;
        
        if(s.getSastojci().size() < 3){
            throw new Exception("Sladoled mora imati barem 3 sastojka!");
        }

        ArrayList<Sladoled> sladoledi = (ArrayList<Sladoled>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Sladoled sladoled : sladoledi) {
            if (!s.getSladoledID().equals(sladoled.getSladoledID())) {
                if (s.getNazivSladoleda().equals(sladoled.getNazivSladoleda())) {
                    throw new Exception("Postoji sladoled sa tim nazivom!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        
        // updatujemo jak objekat
        DBBroker.getInstance().update(ado);
        
        Sladoled s = (Sladoled) ado;
        // prvo obrisemo stare sastojke
        // ovo ce poslati naredbu
        
        DBBroker.getInstance().delete(s.getSastojci().get(0));
        
        // posle dodamo nove
        for (Sastojak sastojak : s.getSastojci()) {
            DBBroker.getInstance().insert(sastojak);
        }
        
    }

}
