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
        $('div#mensaje').hide();
        $('div#mensaje').css({"text-align": "center"});
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
        $('#email').bind("cut copy paste", function(e) {
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
            enviarPeticiones.enviarPeticion('#registroUsuario', {"estado": "1", "idRol": "2"});
        } else {
            $('div#mensaje').slideDown();
            $('div#mensaje span').html('Error: No has completado el formulario.');
            controlRegistrar.posScroll();
        }
    },
    posScroll: function() {
        var myCont = document.getElementById('cuerpo');
        vary = -999;
        myCont.scrollTop = vary;
    }

};

controlRegistrar.init();