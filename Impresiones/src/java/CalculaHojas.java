
import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;


/**
 *
 * @author jhonjaider1000
 */
public class CalculaHojas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cadena = "1,-2";        
    }
    
    public static Respuesta calcularHojas(String cadena){
        Respuesta respuesta = new Respuesta();        
        cadena = corregirCadena(cadena);        
        cadena = verificaComasRepetidas(cadena);        
        String resultado = validarNumeroPaginas(cadena);        
        
        if(!esNumerico(resultado)){
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje(resultado);
        }else{
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(respuesta);
            respuesta.setMensaje("Se ha calculado el número de hojas satisfactoriamente.");
        }
        return respuesta;
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
                System.out.println("Tamaño: " + numeros.length);

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
