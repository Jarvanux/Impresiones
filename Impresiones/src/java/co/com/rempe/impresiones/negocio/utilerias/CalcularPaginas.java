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
import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;

/**
 *
 * @author jhonjaider1000
 */
public class CalcularPaginas {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cadena = "10-1";
//        calcularHojas(cadena);
//        System.out.println(controlCalculo(cadena));
        System.out.println(validarNumeroPaginas(cadena));
    }

    public static String controlCalculo(String cadena) {
        cadena = corregirCadena(cadena);
        System.out.println(cadena);
        cadena = verificaComasRepetidas(cadena);
        System.out.println(cadena);
        cadena = datosRepetidos(cadena);
        System.out.println(cadena);
        String arrayTemp[] = cadena.split("/");
        System.out.println(arrayTemp.length);
        for (int i = 0; i < arrayTemp.length; i++) {
            System.out.println(arrayTemp[i]);
        }
        if (cadena.contains("|")) {
            return cadena;
        } else {
            String resultValid = validarNumeroPaginas(arrayTemp[2]);
            if (esNumerico(resultValid)) {
                cadena = resultValid + "/" + arrayTemp[0] + "/" + arrayTemp[2];
            }else{
                cadena = 0 + "/" + resultValid + "/" + arrayTemp[2];
            }
            return cadena;
        }
    }

//    public static String calcularHojas(String cadena) {
//        Respuesta respuesta = new Respuesta();
//        cadena = corregirCadena(cadena);
//        cadena = verificaComasRepetidas(cadena);
//        String resultado = validarNumeroPaginas(cadena);
//
//        if (!esNumerico(resultado)) {
//            return resultado;
//        } else {
//            return resultado;
//        }
//    }
    public static String datosRepetidos(String cadena) {
        String cadenaTemp = "";
        String cadenaReturn = "";
        String mensajeReturn = "";
        String[] separator = cadena.split(",");
        int numero1 = 0;
        int numero2 = 0;
        for (int i = 0; i < separator.length; i++) {
            if (separator[i].contains("-")) {
                String[] numeros = separator[i].split("-");
                if (numeros.length == 2) {
                    //Si el primer dato encontrado es númerico.
                    if (esNumerico(numeros[0])) {
                        numero1 = Integer.parseInt(numeros[0]);
                    } else {
                        mensajeReturn = "|Uno de los números no es númerico.";
                    }

                    //Si el segundo dato encontradeo es númerico.
                    if (esNumerico(numeros[1])) {
                        numero2 = Integer.parseInt(numeros[1]);
                    } else {
                        mensajeReturn = "|Uno de los números no es númerico.";
                    }

                    if ((numero1 > 0) && (numero2 > 0)) {
                        for (int j = numero1; j <= numero2; j++) {
//                            System.out.println(j);
                            cadenaTemp += j + ",";
                        }
                    }
                } else {
                    mensajeReturn = "|Uno de los rangos contiene un formato incorrecto.";
                }
            } else {
                cadenaTemp += separator[i] + ",";
            }
        }//Fin for principal.
//        System.out.println("Cadena Temp: " + cadenaTemp);
        if (cadenaTemp.length() > 0) {
            String[] arregloTemp = cadenaTemp.split(",");
            for (int i = 0; i < arregloTemp.length; i++) {
                if (!cadenaReturn.contains(arregloTemp[i])) {
                    cadenaReturn += arregloTemp[i] + ",";
                } else {
                    mensajeReturn = "|Se han encontrado páginas repetidas, por favor corrige la información ingresada.";
                }
            }
        }

//        System.out.println(cadenaReturn);        
        return cadenaReturn + "/" + mensajeReturn + "/" + cadena;
    }

    //Validamos si un string ingresado es númerico.
    public static boolean esNumerico(String numero) {
        if (numero == null || numero.isEmpty()) {
            return false;
        }
        int testigo = 0;
        for (int i = 0; i < numero.length(); i++) {
            if (Character.isDigit(numero.charAt(i))) {
                testigo++;
            }
        }
        if (testigo == numero.length()) {
            return true;
        } else {
            return false;
        }
    }
    //Fin valida si un número es númerico.

    //Diccionario de lanzamientos.
    /*
     Si este método lanza:
     -1 : Uno de los rangos no es correcto (Los números ingresados no son enteros.
     -2 : Uno de los rangos contiene un número inicial mayor o igual al final.
     */
    public static String validarNumeroPaginas(String cadena) {
        String[] cadenaDescrip = cadena.split(",");
        //Hasta aquí estariamos validando el número de páginas recogidas o más bien
        //separadas por las comas, ahora necesitamos validar las páginas entre rangos.        
        int numeroPaginas = cadenaDescrip.length;

        //Inicialmente lo que voy a hacer será un for normal para el arreglo que nos ha creado el split.
        for (int i = 0; i < cadenaDescrip.length; i++) {
            //Validamos si existen rangos, si es así, se realizará el respectivo
            //procedimiento para calcular la cantidad de números entre el rango.            
            if (cadenaDescrip[i].contains("-")) {
                String[] numeros = cadenaDescrip[i].split("-");
                int num1 = 0, num2 = 0;
                if (numeros.length == 2) {

                    //Validamos si los dos valores guardados en la cadena encontrada son enteros.
                    if (esNumerico("" + numeros[0])) {
                        num1 = Integer.parseInt(numeros[0]);
                    } else {
                        return "Uno de los rangos no es correcto (Los números ingresados no son enteros.)";
                    }

                    if (esNumerico("" + numeros[1])) {
                        num2 = Integer.parseInt(numeros[1]);
                    } else {
                        return "Uno de los rangos no es correcto (Los números ingresados no son enteros.)";
                    }
                    //Fin valida números enteros.

                    int numerosInRango = 0;

                    if (num1 >= num2) {
                        return "Uno de los rangos contiene un número inicial mayor o igual al final.";
                    } else {
                        for (int j = num1; j <= num2; j++) {
//                        System.out.println("Temp: "+j);                            
                            numerosInRango++;
                        }
//                    System.out.println("El rango de números es de: " + numerosInRango + " números.");
                        numeroPaginas -= 1; //Restamos el rango encontrado.
                        numeroPaginas += numerosInRango; //Sumamos en realidad el valor de la cantidad de páginas encontradas entre ese rango.
                    }
                } else {
                    return "Uno de los rangos contiene un formato incorrecto.";
                }
            } else {
            }
            //Fin validación rangos.
        }
        return numeroPaginas + "";
    }

    public static String verificaComasRepetidas(String cadena) {
        StringBuilder cadenaCorregida = new StringBuilder();
        int contadorDeComas = 0;
        for (int i = 0; i < cadena.length(); i++) {
            contadorDeComas = 0;
            if (cadena.charAt(i) == ',') {
                contadorDeComas++;
                if (cadena.length() == (i + 1)) {
//                    System.out.println("Se supera el número de bits");
//                    System.out.println(i);
//                    System.out.println(i + 1);
                } else {
                    if (cadena.charAt(i + 1) == ',') {
                        contadorDeComas++;
                    }
                }
            }//Fin if global.

            if (contadorDeComas <= 1) {
                cadenaCorregida.append(cadena.charAt(i));
            }
        }
        return cadenaCorregida.toString();
    }

    public static String corregirCadena(String cadena) {
        StringBuilder cadenaCorregida = new StringBuilder();
        String datosAceptados = "0123456789,-";
        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < datosAceptados.length(); j++) {
                if (cadena.charAt(i) == datosAceptados.charAt(j)) {
                    cadenaCorregida.append(cadena.charAt(i));
                }
            }
        }
        return cadenaCorregida.toString();
    }

}
