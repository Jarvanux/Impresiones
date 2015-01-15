/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.utilerias;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jhonjaider1000
 */
public class Utilerias {

    public static long redondearDecimal(double decimal) {
        return Math.round(decimal);
    }

    public static Date convertir(String fecha) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.parse(fecha);
        } catch (Exception e) {
        }
        return null;
    }
}
