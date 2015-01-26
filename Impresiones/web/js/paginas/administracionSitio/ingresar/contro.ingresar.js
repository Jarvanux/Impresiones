/* 
 * Control ingresar.
 */

elementosHiden = {"elementos": [
        {"nombre": "#mensajeL"},
        {"nombre": "#info"},
    ]};


var controlIngresar = {
    init: function() {
        $('div#mensajeL').hide();
        controlIngresar.ocultarObjetos();
        $('#imgCerrar').css({"cursor": "pointer"});
    },
    cargarData: function(pagina) {
        $("div.body-modal p")
                .html('')
                .append($('#cargador').clone().show());
        $.post(pagina, function(data) {
            $("div.body-modal p").html(data);
        });
    },
    registrar: function() {
        if (posicionActual == 1 || posFormulario == 2) {
            $('#contentD3 #loginPedido').hide();
            $.post('paginas/administracionsSitio/registrar.html', function(data) {
                $("#contentD3 #registroUsuario #body").append(data);
                $("#contentD3 #registroUsuario").slideDown(500);
            });            
        }
//        controlIngresar.cargarData('paginas/administracionsSitio/registrar.html');
//        $('#info').slideDown(500);
//        $('div#mensaje').hide();
    },
    ingresar: function() {
        if (!($('#password').val().length != 0)) {
            $('div#mensajeL span').html('Ingresa tú contraseña!');
            $('div#mensajeL').slideDown(500);
            $('p#smsReg').hide();
            $('div#facebook').hide();
        }
        if (!($('#usuario').val().length > 0)) {
            $('div#mensajeL span').html('Ingresa tu correo electrónico!');
            $('div#mensajeL').slideDown(500);
            $('p#smsReg').hide();
            $('div#facebook').hide();
        }

        if (($('#usuario').val().length != 0) && ($('#password').val().length != 0)) {
            peticionesIngresar.ingresar($('#usuario').val(), $('#password').val());
        }
    },
    ocultarObjetos: function() {
        for (var i = 0; i < elementosHiden.elementos.length; i++) {
            $(elementosHiden.elementos[i].nombre).hide();
        }
    },
    eventos: function() {
        $('#btnLogear').click(function() {
            controlIngresar.ingresar();
        });
        $('#imgCerrar').click(function() {
            $('#info').hide();
        });

        $('#usuario').keydown(function(event) {
            if (event.keyCode == 13) {
                controlIngresar.ingresar();
            }
        });
        $('#password').keydown(function(event) {
            if (event.keyCode == 13) {
                controlIngresar.ingresar();
            }
        });
    }

};




$(document).ready(function() {
//    controlIngresar.init();
//    controlIngresar.eventos();
    controlIngresar.init();
    controlIngresar.eventos();
});

