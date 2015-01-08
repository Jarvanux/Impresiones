/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//
//$(document).ready(function() {
////    alert('Resolución de tu pantalla: '+screen.width + " x " + screen.height);
////    alert('Resolución del navegador:'+$(window).width()+' x '+$(window).height());    
////    controlUsuario.init();
//    controlUsuario.dimencionPantalla();
//});

var controlUsuario = {
    init: function() {
        controlUsuario.consultarUsuarioLogeado();
//        alert('HEllo');
    },
    dimencionPantalla: function() {
        $(window).resize(function() {
            console.log('Dimención de pantalla cambiada');
            if ($(window).width() <= 1024) {
                $('ul#menu1').hide();
                $('ul#menu2').show();
                $('img#logo').css({'margin-left': '30px'});
            } else if ($(window).width() > 1024 && $(window).width() <= 1366 || $(window).width() > 1366) {
                $('ul#menu2').hide();
                $('ul#menu1').show();
                $('img#logo').css({'margin-left': '0px'});
            }
        });
    },
    consultarUsuarioLogeado: function() {
        var resultado = null;
        $.ajax({
            'url': 'consultarUsuarioLogeado',
            'type': 'POST',
            'async': false,
            success: function(data) {
                resultado = data;
            }
        });
        console.log(resultado);
        var respuesta = JSON.parse(resultado);
        var lugarPagina = location.href;
        var posSearch = lugarPagina.search("/impresiones/");
        lugarPagina = lugarPagina.substring(posSearch);
        console.log(respuesta);
        if (respuesta.datos != null) {
            console.log("Existe un usuario logeado");
            console.log(lugarPagina);
//            alert('Se ejecuto esa joa');          
            if (lugarPagina == '/impresiones/') {
                if (respuesta.datos.idRol == 2) {
                    location.href = '/impresiones/panelusuario.html';
                } else if (respuesta.datos.idRol == 1) {
                    location.href = '/impresiones/paneladministrador.html';
                }
            }
        } else {
            console.log("No hay nadie logueado");
            if (lugarPagina == '/impresiones/login.html' || lugarPagina.search('paneladministrador.html') > -1 || lugarPagina.search('panelusuario.html') > -1) {
                location.href = '/impresiones/login.html';
            }
        }
    },
    cerrarSesion: function() {
        $.ajax({
            'url': 'cerrarSesion',
            type: 'POST',
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(data);
                if (respuesta.codigo > 0) {
                    location.href = '/impresiones/login.html';
                }
            }
        });
    },
    editarDatosUsuario: function() {
        $.ajax({
            'url': 'consultarUsuarioLogeado',
            'type': 'POST',
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
                if (respuesta.datos != null) {
                    var datosUsuarios = respuesta.datos;
                    console.log(datosUsuarios);
                    $('#nombres').val(datosUsuarios.nombres);
                    $('#apellidos').val(datosUsuarios.apellidos);
                    $('#email').val(datosUsuarios.email);
                    $('#cedula').val(datosUsuarios.cedula);
                    $('#celular').val(datosUsuarios.celular);
                    $('#telefono').val(datosUsuarios.telefono);
                    $('#direccion').val(datosUsuarios.direccion);
                    $('#ciudad').val(datosUsuarios.ciudad);
                    $('#zona').val(datosUsuarios.zona);
                } else {
                    console.log("No hay nadie logueado");
                    location.href = '/impresiones/login.html';
                }
            }
        });
    },
    confirmadaEdicion: function() {
        $.ajax({
            'url': 'actualizarUsuario',
            'type': 'POST',
            'data': {
            },
            success: function(data) {
            }
        });
    }
};