/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Vukos
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int GET_ALL_ADMINISTRATOR = 1;

    public static final int ADD_KUPAC = 2;
    public static final int DELETE_KUPAC = 3;
    public static final int UPDATE_KUPAC = 4;
    public static final int GET_ALL_KUPAC = 5;
    
    public static final int ADD_SLADOLED = 6;
    public static final int DELETE_SLADOLED = 7;
    public static final int UPDATE_SLADOLED = 8;
    public static final int GET_ALL_SLADOLED = 9;
    
    public static final int ADD_SASTOJAK = 10;
    public static final int DELETE_SASTOJAK = 11;
    public static final int GET_ALL_SASTOJAK = 12;

    public static final int ADD_RACUN = 13;
    public static final int GET_ALL_RACUN = 14;
    
    public static final int ADD_STAVKA_RACUNA = 15;
    public static final int GET_ALL_STAVKA_RACUNA = 16;

    public static final int ADD_PREFERENCIJA = 17;

}
