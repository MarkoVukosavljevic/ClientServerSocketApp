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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Vukos
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addSladoled(Sladoled sladoled) throws Exception {
        sendRequest(Operation.ADD_SLADOLED, sladoled);
    }
    
    public void addKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.ADD_KUPAC, kupac);
    }
    
    public void addSastojak(Sastojak sastojak) throws Exception {
        sendRequest(Operation.ADD_SASTOJAK, sastojak);
    }
    
    public void addRacun(Racun racun) throws Exception {
        sendRequest(Operation.ADD_RACUN, racun);
    }
    
    public void addStavkaRacuna(StavkaRacuna stavkaRacuna) throws Exception {
        sendRequest(Operation.ADD_STAVKA_RACUNA, stavkaRacuna);
    }

    public void deleteSladoled(Sladoled sladoled) throws Exception {
        sendRequest(Operation.DELETE_SLADOLED, sladoled);
    }
    
    public void deleteKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.DELETE_KUPAC, kupac);
    }
    
    public void deleteSastojak(Sastojak sastojak) throws Exception {
        sendRequest(Operation.DELETE_SASTOJAK, sastojak);
    }

    public void updateSladoled(Sladoled sladoled) throws Exception {
        sendRequest(Operation.UPDATE_SLADOLED, sladoled);
    }
    
    public void updateKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.UPDATE_KUPAC, kupac);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        return (ArrayList<Administrator>) sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
    }
    
    public ArrayList<Sladoled> getAllSladoled() throws Exception {
        return (ArrayList<Sladoled>) sendRequest(Operation.GET_ALL_SLADOLED, null);
    }
    
    public ArrayList<Kupac> getAllKupac() throws Exception {
        return (ArrayList<Kupac>) sendRequest(Operation.GET_ALL_KUPAC, null);
    }
    
    
    public ArrayList<Racun> getAllRacun() throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.GET_ALL_RACUN, null);
    }
    
    public ArrayList<Sastojak> getAllSastojak(Sladoled s) throws Exception {
        return (ArrayList<Sastojak>) sendRequest(Operation.GET_ALL_SASTOJAK, s);
    }
    
    public ArrayList<StavkaRacuna> getAllStavkaRacuna() throws Exception {
        return (ArrayList<StavkaRacuna>) sendRequest(Operation.GET_ALL_STAVKA_RACUNA, null);
    }

 public void addPreferencija(PreferencijaKupca pk) throws Exception {
        sendRequest(Operation.ADD_PREFERENCIJA, pk);

        
    }
 
    private Object sendRequest(int operation, Object data) throws Exception {
        Request req = new Request(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
        
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res = (Response) in.readObject();
        if (res.getResponseStatus().equals(ResponseStatus.Error)) {
            throw res.getError();
        } else {
            return res.getData();
        }
    }

   

  
}
