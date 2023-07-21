/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Sladoled;
import domain.Kupac;
import domain.PreferencijaKupca;
import domain.Racun;
import domain.Sastojak;
import domain.StavkaRacuna;
import java.util.ArrayList;
import so.AbstractSO;
import so.administrator.SOGetAllAdministrator;
import so.sladoled.SOAddSladoled;
import so.sladoled.SODeleteSladoled;
import so.sladoled.SOGetAllSladoled;
import so.sladoled.SOUpdateSladoled;
import so.kupac.SOAddKupac;
import so.kupac.SODeleteKupac;
import so.kupac.SOGetAllKupac;
import so.kupac.SOUpdateKupac;
import so.preferencijaKupca.SOAddPreferencijaKupca;
import so.racun.SOAddRacun;
import so.racun.SOGetAllRacun;
import so.sastojak.SOAddSastojak;
import so.sastojak.SODeleteSastojak;
import so.sastojak.SOGetAllSastojak;
import so.stavkaRacuna.SOAddStavkaRacuna;
import so.stavkaRacuna.SOGetAllStavkaRacuna;

/**
 *
 * @author Vukos
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void addSladoled(Sladoled sladoled) throws Exception {
        AbstractSO aso = new SOAddSladoled();
        aso.templateExecute(sladoled);
    }

    public void addKupac(Kupac kupac) throws Exception {
        AbstractSO aso = new SOAddKupac();
        aso.templateExecute(kupac);
    }

    public void addRacun(Racun racun) throws Exception {
        AbstractSO aso = new SOAddRacun();
        aso.templateExecute(racun);
    }

    public void addSastojak(Sastojak sastojak) throws Exception {
        AbstractSO aso = new SOAddSastojak();
        aso.templateExecute(sastojak);
    }

    public void addStavkaRacuna(StavkaRacuna stavkaRacuna) throws Exception {
        AbstractSO aso = new SOAddStavkaRacuna();
        aso.templateExecute(stavkaRacuna);
    }

    public void deleteSladoled(Sladoled slad) throws Exception {
        AbstractSO aso = new SODeleteSladoled();
        aso.templateExecute(slad);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        AbstractSO aso = new SODeleteKupac();
        aso.templateExecute(kupac);
    }

    public void deleteSastojak(Sastojak sastojak) throws Exception {
        AbstractSO aso = new SODeleteSastojak();
        aso.templateExecute(sastojak);
    }

    public void updateSladoled(Sladoled slad) throws Exception {
        AbstractSO aso = new SOUpdateSladoled();
        aso.templateExecute(slad);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        AbstractSO aso = new SOUpdateKupac();
        aso.templateExecute(kupac);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Sladoled> getAllSladoled() throws Exception {
        SOGetAllSladoled so = new SOGetAllSladoled();

        Sladoled sl = new Sladoled();

        so.templateExecute(sl);
        return so.getLista();
    }

    public ArrayList<Kupac> getAllKupac() throws Exception {
        SOGetAllKupac so = new SOGetAllKupac();
        so.templateExecute(new Kupac());
        return so.getLista();
    }

    public ArrayList<Racun> getAllRacun() throws Exception {
        SOGetAllRacun so = new SOGetAllRacun();
        so.templateExecute(new Racun());
        return so.getLista();
    }

    public ArrayList<Sastojak> getAllSastojak(Sladoled slad) throws Exception {
        SOGetAllSastojak so = new SOGetAllSastojak();

        Sastojak s = new Sastojak();
        s.setSladoled(slad);

        so.templateExecute(s);
        return so.getLista();
    }

    public ArrayList<StavkaRacuna> getAllStavkaRacuna() throws Exception {
        SOGetAllStavkaRacuna so = new SOGetAllStavkaRacuna();
        so.templateExecute(new StavkaRacuna());
        return so.getLista();
    }

    public void addPreferencija(PreferencijaKupca preferencijaKupca) throws Exception {

        AbstractSO aso = new SOAddPreferencijaKupca();
        aso.templateExecute(preferencijaKupca);

    }

}
