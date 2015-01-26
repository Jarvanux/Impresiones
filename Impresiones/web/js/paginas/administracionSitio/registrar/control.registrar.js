/* 
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var controlRegistrar = {
    temp: function(formulario) {
        var divContent = $(formulario).find('div')[0].id;

        var dataFinds = $(formulario).find('input[type="text"],input[type="password"]');
        if (dataFinds.length > 0) {
            //Inicio For            
            data = null;
            for (var i = 0; i < dataFinds.length; i++) {
                data = dataFinds[i];
                var tempName = data.id;
                $(formulario).find('#' + data.id).val('Prueba');
            }
            //Fin For           
            //Despues del for ya tendríasmos armado el array que vamos a convertir.
        } else {
            console.log('El formulario no cuenta con elementos para crear el objeto JSON');
        }
    },
    init: function() {
        controlRegistrar.eventos();
        controlRegistrar.posScroll();
        $('div#mensaje').hide();
        $('div#mensaje').css({"text-align": "center"});
        $('div.contet-modal div#bodyForm').css({'width': '90%'});
        $('div.campo1 input[type="text"]').css({'width': '94%'});
        $('div.campo1 input[type="password"]').css({'width': '94%'});
        $('div.campo2 input[type="text"]').css({'width': '94%'});
        $('div.campo2 input[type="password"]').css({'width': '94%'});
        $('div#foot').css({'width': '94%'});
        $('div#mensaje').css({'width': '94%', 'height': '20px'});
        $('div#info div#content').css({height: '84%'});
//        controlRegistrar.temp('#registroUsuario');
    },
    eventos: function() {
        $('#ciudad').change(function() {
            if ($('#contrasena').val() != $('#contrasenaConfirm').val()) {
                $('div#mensaje').slideDown();
                $('div#mensaje span').html('Error: Los contraseñas no coinciden!.');
                controlRegistrar.posScroll();
            }
        });
        $('#zona').change(function() {
            if ($('#contrasena').val() != $('#contrasenaConfirm').val()) {
                $('div#mensaje').slideDown();
                $('div#mensaje span').html('Error: Los contraseñas no coinciden!.');
                controlRegistrar.posScroll();
            }
        });
        $('#contrasenaConfirm').bind("cut copy paste", function(e) {
            e.preventDefault();
            $('div#mensaje').slideDown();
            $('div#mensaje span').html('Error: Opción deshabilitada!.');
            controlRegistrar.posScroll();
        });
        $('#email').change(function() {
            $('div#mensaje').slideUp();
        });
        $('#emailconfirm').change(function() {
            $('div#mensaje').slideUp();
            if ($('#email').val() != $('#emailconfirm').val()) {
                $('div#mensaje').slideDown();
                $('div#mensaje span').html('Error: Los correos no coinciden!.');
                controlRegistrar.posScroll();
            }
        });
        $('#contrasenaConfirm').change(function() {
            if ($('#contrasena').val() != $('#contrasenaConfirm').val()) {
                $('div#mensaje').slideDown();
                $('div#mensaje span').html('Error: Los contraseñas no coinciden!.');
                controlRegistrar.posScroll();
            }
        });
        $('#emailconfirm').bind("cut copy paste", function(e) {
            e.preventDefault();
            $('div#mensaje').slideDown();
            $('div#mensaje span').html('Error: Opción deshabilitada!.');
            controlRegistrar.posScroll();
        });

        $('#btnRegistrar').click(function() {
            controlRegistrar.validarForm();
        });
    },
    validarForm: function() {
        var respuesta = (validacionesGlobales.validar('bodyForm', 2));
        if (respuesta) {
            $('div#mensaje').slideUp();
            controlRegistrar.enviarPeticion('form#registroUsuario', {"estado": "1", "idRol": "2"});
        } else {
            $('div#mensaje').slideDown();
            $('div#mensaje span').html('Error: No has completado el formulario.');
            controlRegistrar.posScroll();
        }
    },
    enviarPeticion: function(formulario, array) { //Documentación en enviarPeticiones.enviarPeticion();
        var instanciaServlet = $(formulario).attr("class");
        var url = $(formulario).attr("action");
        var metodo = $(formulario).attr("method");
        var objetoJSON;

        console.info(instanciaServlet+' '+url+' '+metodo+' ');
        var dataFinds = $(formulario).find('input[type="text"],input[type="password"],select');
        if (dataFinds.length > 0) {
            //Inicio For            
            data = null;
            for (var i = 0; i < dataFinds.length; i++) {
                data = dataFinds[i];
                var tempName = data.id;
                array[tempName] = $(formulario).find('#' + data.id).val();
            }
            //Fin For           
            //Despues del for ya tendríasmos armado el array que vamos a convertir.
        } else {
            console.log('El formulario no cuenta con elementos para crear el objeto JSON');
        }

        if (array != null) {
            //Si todo salió bien, pasamos a convertir el array en un objeto JSON.     
            objetoJSON = {};
            objetoJSON[instanciaServlet] = JSON.stringify(array);
            console.log('data');
            console.log(objetoJSON);
            controlPeticiones.registrarEIngresar(url, metodo, objetoJSON, null);
        } else {
            console.log('Imposible completar la petición, el array de conversión está vacio.');
        }
    },
    posScroll: function() {
        var myCont = document.getElementById('cuerpo');
        vary = -9999999999999999999;
        myCont.scrollTop = vary;
    }

};

controlRegistrar.init();