/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Sladoled;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vukos
 */
public class TableModelSladoleda extends AbstractTableModel implements Runnable {

    private ArrayList<Sladoled> lista;
    private String[] kolone = {"ID", "Naziv", "Cena"};
    private String parametar = "";

    public TableModelSladoleda() {
        try {
            lista = ClientController.getInstance().getAllSladoled();
        } catch (Exception ex) {
            Logger.getLogger(TableModelSladoleda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Sladoled s = lista.get(row);

        switch (column) {
            case 0:
                return s.getSladoledID();
            case 1:
                return s.getNazivSladoleda();
            case 2:
                return s.getCenaPoKG();

            default:
                return null;
        }
    }

    public Sladoled getSelectedSladoled(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelSladoleda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllSladoled();
            if (!parametar.equals("")) {
                ArrayList<Sladoled> novaLista = new ArrayList<>();
                for (Sladoled s : lista) {
                    if (s.getNazivSladoleda().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(s);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
