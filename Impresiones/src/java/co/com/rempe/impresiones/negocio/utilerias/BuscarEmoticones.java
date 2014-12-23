/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.utilerias;

/**
 *
 * @author jhonjaider1000
 */
public class BuscarEmoticones {

    public static void main(String[] args) {
        String cadena = "Hola que hace :) :) :) :) :) :D ;) :3";
        buscarEmoticones(cadena);
    }

    public static String buscarEmoticones(String cadena) {        
        System.out.println(cadena);
        while (cadena.contains(":)") || cadena.contains(":D") || cadena.contains(";)") || cadena.contains(":3") || cadena.contains("(y)") || cadena.contains("(Y)")
                || cadena.contains("<3") || cadena.contains(":P") || cadena.contains(":p")) {
            cadena = cadena.replace(":)", "<img src=\"img/chat/emoticones/sonrie.png\" style=\"width: 17px\"/>");
            cadena = cadena.replace(":D", "<img src=\"img/chat/emoticones/reir.png\" style=\"width: 17px\"/>");
            cadena = cadena.replace(":3", "<img src=\"img/chat/emoticones/sonriente.png\" style=\"width: 17px\"/>");
            cadena = cadena.replace(";)", "<img src=\"img/chat/emoticones/pick-ojo.png\" style=\"width: 17px\"/>");
            cadena = cadena.replace("(y)", "<img src=\"img/chat/emoticones/like.png\" style=\"width: 17px\"/>");
            cadena = cadena.replace("(Y)", "<img src=\"img/chat/emoticones/like.png\" style=\"width: 17px\"/>");
            cadena = cadena.replace("<3", "<img src=\"img/chat/emoticones/corazon.png\" style=\"width: 17px\"/>");
            cadena = cadena.replace(":P", "<img src=\"img/chat/emoticones/muecas.png\" style=\"width: 17px\"/>");
            cadena = cadena.replace(":p", "<img src=\"img/chat/emoticones/muecas.png\" style=\"width: 17px\"/>");
        }        
        return cadena;
    }

}
