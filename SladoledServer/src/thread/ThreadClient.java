/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.Sladoled;
import domain.Kupac;
import domain.PreferencijaKupca;
import domain.Racun;
import domain.Sastojak;
import domain.StavkaRacuna;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Vukos
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request req = (Request) in.readObject();
                Response res = handleRequest(req);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request req) {
        Response res = new Response(null, null, ResponseStatus.Success);
        try {
            switch (req.getOperation()) {
                case Operation.ADD_SLADOLED:
                    ServerController.getInstance().addSladoled((Sladoled) req.getData());
                    break;
                case Operation.ADD_KUPAC:
                    ServerController.getInstance().addKupac((Kupac) req.getData());
                    break;
                case Operation.ADD_RACUN:
                    ServerController.getInstance().addRacun((Racun) req.getData());
                    break;
                case Operation.ADD_SASTOJAK:
                    ServerController.getInstance().addSastojak((Sastojak) req.getData());
                    break;
                case Operation.ADD_STAVKA_RACUNA:
                    ServerController.getInstance().addStavkaRacuna((StavkaRacuna) req.getData());
                    break;
                case Operation.DELETE_SLADOLED:
                    ServerController.getInstance().deleteSladoled((Sladoled) req.getData());
                    break;
                case Operation.DELETE_KUPAC:
                    ServerController.getInstance().deleteKupac((Kupac) req.getData());
                    break;
                case Operation.DELETE_SASTOJAK:
                    ServerController.getInstance().deleteSastojak((Sastojak) req.getData());
                    break;
                case Operation.UPDATE_SLADOLED:
                    ServerController.getInstance().updateSladoled((Sladoled) req.getData());
                    break;
                case Operation.UPDATE_KUPAC:
                    ServerController.getInstance().updateKupac((Kupac) req.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    res.setData(ServerController.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_SLADOLED:
                    res.setData(ServerController.getInstance().getAllSladoled());
                    break;
                case Operation.GET_ALL_KUPAC:
                    res.setData(ServerController.getInstance().getAllKupac());
                    break;
               
                case Operation.GET_ALL_RACUN:
                    res.setData(ServerController.getInstance().getAllRacun());
                    break;
                case Operation.GET_ALL_SASTOJAK:
                    res.setData(ServerController.getInstance().getAllSastojak((Sladoled)req.getData()));
                    break;
                case Operation.GET_ALL_STAVKA_RACUNA:
                    res.setData(ServerController.getInstance().getAllStavkaRacuna());
                    break;
                case Operation.ADD_PREFERENCIJA:
                    ServerController.getInstance().addPreferencija((PreferencijaKupca) req.getData());
                    break;
                case Operation.LOGIN:
                    ArrayList<Administrator> lista = ServerController.getInstance().getAllAdministrator();
                    Administrator a = (Administrator) req.getData();
                    for (Administrator administrator : lista) {
                        if (administrator.getUsername().equals(a.getUsername())
                                && administrator.getPassword().equals(a.getPassword())) {
                            res.setData(administrator);
                        }
                    }
                    if (res.getData() == null) {
                        throw new Exception("Sistem ne može da nadje administratora na osnovu unetih vrednosti.");
                    } else {
                        break;
                    }
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setResponseStatus(ResponseStatus.Error);
        }
        return res;
    }

}
