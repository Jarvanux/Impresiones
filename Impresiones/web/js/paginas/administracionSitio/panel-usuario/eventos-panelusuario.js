/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var timeSesion = 0;
$(document).ready(function() {
    eventosUsuario.eventos();
    setInterval(consultaSesion, 2000);
});

var chateando = false;

function consultaSesion() {
    if (!chateando) {
        controlUsuario.consultarUsuarioLogeado();
    }
    timeSesion++;
    if ((timeSesion * 2) == 600) {
        alert('La sesión ha caducado por inactividad');
        controlUsuario.cerrarSesion();
        console.log(timeSesion);
    }
}


var eventosUsuario = {
    regresarfondoLis: function() {
        $('.menu li a#lnk-inicio').removeAttr('class');
        $('.menu li a#lnk-impresiones').removeAttr('class');
        $('.menu li a#lnk-conversaciones').removeAttr('class');
        $('.menu li a#lnk-configuracion').removeAttr('class');
        $('.menu li a#lnk-perfil').removeAttr('class');
        $('.menu li a#lnk-ayuda').removeAttr('class');
        $('.menu li a#lnk-contacto').removeAttr('class');
    },
    eventos: function() {
        $('a#lnk-inicio').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            inicio.cargar('paginas/servicios/impresion.html');
            eventosUsuario.regresarfondoLis();
            $('.menu li a#lnk-inicio').attr({'class': 'fondo-rojo'});
        });
        $('img#logo').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            inicio.cargar('paginas/servicios/impresion.html');
        });
        $('#lnk-impresiones').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            inicio.cargar('paginas/resumen-impresion/impresiones-realizadas.html');
            eventosUsuario.regresarfondoLis();
            $('.menu li a#lnk-impresiones').attr({'class': 'fondo-rojo'});
        });
        $('#lnk-conversaciones').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            eventosUsuario.regresarfondoLis();
            $('.menu li a#lnk-conversaciones').attr({'class': 'fondo-rojo'});
        });
        $('#lnk-contacto').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            $('#info div#cuerpo p').html(' <br/>' +
                    '<span style="display:block; margin-bottom: 10px;"><b>Teléfono:</b> 321-249-1941xx</span>' +
                    '<span style="display: block; margin-bottom: 10px;"><b>Dirección:</b>' +
                    ' Calle San-Fason carrera 22</span>' +
                    '<span style="display: block; margin-bottom: 10px;"><b>Correo:</b>' +
                    ' impresionesadomicilio.rempe.com</span>'
                    );
            $('#info').slideDown(500);
        });
        $('#lnk-telefono').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            $('#info div#cuerpo p').html(' <br/>' +
                    '<b>Teléfono:</b> 321-249-1941');
            $('#info').slideDown(500);
        });
        $('#lnk-oficinas').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            $('#info div#cuerpo p').html(' <br/>' +
                    '<b>Dirección:</b> Calle san-fason carrera 22');
            $('#info').slideDown(500);
        });
        $('#imgCerrar').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            $('#info').hide();
        });
        $('#lnk-perfil').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            inicio.cargar('paginas/administracionsSitio/panel-usuario/perfil-usuario.html');
        });
        $('#lnk-cs').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            $(location).attr('href', '/impresiones/login.html');
        });
        $('#lnk-salir').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            controlUsuario.cerrarSesion();
        });
        $('#lnk-perfil').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            eventosUsuario.regresarfondoLis();
            $('.menu li a#lnk-configuracion').attr({'class': 'fondo-rojo'});
            $('.menu li a#lnk-perfil').attr({'class': 'fondo-rojo'});
            controlUsuario.editarDatosUsuario();
        });
        $(window).resize(function() {
            controlUsuario.consultarUsuarioLogeado();
            controlUsuario.init();
        });
        $('#btnActualizar').click(function() {
            controlUsuario.consultarUsuarioLogeado();
            controlUsuario
        });
    }
};