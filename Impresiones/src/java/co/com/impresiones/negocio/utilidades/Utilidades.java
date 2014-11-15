/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.negocio.utilidades;

import java.util.Random;

/**
 *
 * @author JuanCamilo
 */
public class Utilidades {
    
    
    /**
     * @param longitud es la cantidad de caracteres que tendrá el String
     * @return regresa un String con x número de caracteres
     */
   public String getCadenaAlfanumAleatoria(int longitud) {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    } 
   
}
