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
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Vukos
 */
public class SOGetAllSladoled extends AbstractSO {

    private ArrayList<Sladoled> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Sladoled)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Sladoled!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> sladoledi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Sladoled>) (ArrayList<?>) sladoledi;
    }

    public ArrayList<Sladoled> getLista() {
        return lista;
    }

}
