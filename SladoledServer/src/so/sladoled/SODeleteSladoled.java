/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sladoled;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Sladoled;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author Vukos
 */
public class SODeleteSladoled extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Sladoled)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sladoled!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        // zbog ON DELETE CASCADE u bazi, ovom naredbom se obrise sladoled
        // i svi njeni slabi objekti odnosno SASTOJCI
        DBBroker.getInstance().delete(ado);
    }

}
