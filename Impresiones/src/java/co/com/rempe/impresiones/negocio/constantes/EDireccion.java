/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.constantes;

/**
 *
 * @author jhonjaider1000
 */
public enum EDireccion {

    INSERTAR_MENSAJE("/insertarmensaje", "Se inserta en la tabla de contacto"),
    LEER_MENSAJE("/leermensaje", "Se cambia el estado de los mensajes a 1"),
    CONSULTAR_POR_ID("/consultarporid","Se consulta un objeto."),
    INSERTAR_CHAT("/insertarchat", "Se insertar en la tabla chat"),
    LEER_CHAT("/leerchat", "Lista el chat de una conversación."),
    SERVLET("/servlet", "Una prueba"),    
    REFRES_PANEL("/refresPanel", "Consultará todos los datos necesarios para refrescar todo el contexto del panel del administrador."),    
    CONSULTA_CLIENTES("/consultaClientes", "Consulta los clientes asociados a un administrador por el sistema."),
    MENSAJE_BUZON("/mensajeDeBuzon", "Mensajes dejados por un cliente cuando no hay nadie conectado al chat del sistema."),
    LISTA_MENSAJE_BUZON("/listaMensajesBuzon", "Lista mensajes de buzón."),    
    ACTUALIZA_MENSAJE_BUZON("/actualizarSMSLeido", "Actualiza el estado(leido) de un mensaje."),
    INSERTAR_CLIP("/insertarclip", "Se inserta en la tabla clip"),
    TIPO_PAPEL("/tipopapel", "Consulta todos los tipos de papel de acuerdo al tipo de impresión"),
    TAMANO_PAPEL("/tamanopapel", "Consulta los diferentes tipos de papel"),
    TIPO_IMPRESION("/tipoimpresion", "Lista los tipos de impresiones almacenados en la base de datos"),
    TIPO_IMPRESIONID("/tipoimpresionid", "Devuelve un objeto de el tipo de impresión consultado por id."),
    MODO_IMPRESION("/modoimpresion","Lista los modos de impresión (Una cara, Dos caras, etc.)"),
    INSERTAR_PRIMERFORM("/insertarprimerform","Guardará los primeros datos ingresados en el primer y más importante formulario."),
    CONSULTAR_IMG("/imgmodoimpre","Generalmente consultamos por id el modo de impresión seleccionado, para tomar el url de la imagen respectiva del mismo."),
    CALCULAR_VALOR_IMPRESION("/calcularvalorimpre","Generalmente consultará el valor de una ipresión con todos los parámetros enviados desde la vista."),
    SUBIR_ARCHIVO("/subirArchivosServlet","Sube un archivo al sistema."),        
    ELIMINAR_ARCHIVO("/eliminarArchivo","elimina un archivo al sistema."),        
    COLORES("/colores","Colores de anillos y tapas."),    
    CALCULAR_PAGINAS("/calcularpaginas","Sube un archivo al sistema."),
    TIPO_PLASTICO("/tipoPlastico","Lista de los tipos de plastico."),
    TIPO_CORTE("/serviciosCorte","Lista tipos de corte."),
    REGISTRAR_USUARIO("/registrarUsuarios","Registrar usuarios."),
    INGRESAR("/ingresar","Inisiar sesión."),
    ACTUALIZAR("/actualizaPerfil","Actualiza los datos de un usuario."),
    SOPORTE_CONECTADO("/soporteconectado","Inisiar sesión."),
    CONSULTAR_USUARIOS("/consultarUsuarios","Consulta un usuario por id."),
    CONSULTAR_USUARIO_LOGEADO("/consultarUsuarioLogeado","Consulta un usuario logeado en el sistema"),
    BUSCAR_ASESORES("/buscarAsesores","Consulta los asesores conectados al sistema."),
    OPTENER_IP_USUARIO("/opteneripusuario","Optener la ip del usuario cliente"),
    GUARDAR_VISITANTE("/guardarVisitante","Guardar el ip de un visitante, generalmente el sistema lo hace para el control de visitas y productividad de la página."),
    GUARDAR_VISITANTE_TEMPORAL("/visitanteTemporal","Guarda los datos de un visitante que abre un chat nuevo."),
    CAMBIAR_ESTADO_ADMIN("/cambiarEstadoAdmin","Cambiar el estado de un administrador EJ: (Conectado,Desconectado...) solo para los administradores del sistema"),
    CERRAR_SESION("/cerrarSesion","Cierra la sesión de un usuario."),
    ASIG_DATOS_IMPRE("/asignarDatosImpre","El servidor proveerá unos datos únicos de la impresión que se esté realizando.."),
    CAMBIAR_ESTADO("/cambiarEstado","Se cambia el estado del usuario (1 = conectado- 0 = desconectado), se ha dejado un entero en la base de datos por si es necesario clasificar otros estados."),
    CONSULTA_VALOR_ANILLADO("/consultarValorAnillado","Consulta el valor de un anillado con el parámetro de número de copias recibido."),
    CONSULTA_VALOR_PLASTIFICADO("/consultarValorPlastificado","Consulta el valor de un plastificado con el parámetro de número de páginas recibido."),
    CONSULTA_VALOR_CORTE("/consultarValorCorte","Consulta el valor de un corte con el parámetro de número de páginas recibido."),
    FINAL("/final","Ninguna funcionalidad, solo sirve para no estár borrando la última linea.");
    private String url;
    private String descripcion;

    private EDireccion(String url, String descripcion) {
        this.url = url;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static EDireccion getDireccion(String url) {
        EDireccion direcciones[] = values();
        for (EDireccion direccion : direcciones) {
            if (direccion.getUrl().equalsIgnoreCase(url)) {
                return direccion;
            }
        }
        return null;
    }
}
