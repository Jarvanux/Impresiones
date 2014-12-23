/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var peticionesRegistrar = {
    registrar: function(){
        var url = 'registrarUsuario';
        var metodo = 'POST';
        var divContent = $('div#contentForm');
        var objetoJSON;
        var array = {};
        
        array.idUsuario = divContent.find('#usuario').val();
        array.nombre = divContent.find().val();
        array.correo = divContent.find().val();
        array.clave = divContent.find().val();
        array.apellido = divContent.find().val();
        array.usuario = divContent.find().val();
        array.direccion = divContent.find().val();
        array.celular = divContent.find().val();
        array.telefono = divContent.find().val();
        array.documento = divContent.find().val();
        array.idZona = divContent.find().val();
        array.estado = 1;
        array.idRol = 1;
        array.idCiudad = divContent.find().val();
        
        objetoJSON = {'registrarUsuario':JSON.stringify(array)};
        controlPeticiones.insertar(url,metodo,objetoJSON,divContent);
    },
     enviarMensaje: function() {
        var url = 'insertarmensaje';
        var metodo = 'POST';
        var divMensaje = $('div#divMensaje');
        var objetoJSON;
        var mensaje = {}; //Array de configuración que llevará los datos del objeto json para insertar.
        //Parametrizamos el array.
        mensaje.nombre = divContactenos.find('input#txtNombre').val();
        mensaje.correo = divContactenos.find('input#txtCorreo').val();
        mensaje.telefono = divContactenos.find('input#txtTelefono').val();
        mensaje.asunto = divContactenos.find('input#txtAsunto').val();
        mensaje.mensaje = divContactenos.find('#txtMensaje').val();
        //Convertimos el array en el objeto que pasaremos a la función global.
        //Se compone del nombre del objeto del servlet : y el array en formato json.
        objetoJSON = {'mensaje': JSON.stringify(mensaje)};
        controlPeticiones.insertar(url, metodo, objetoJSON, divMensaje);
    },
};
