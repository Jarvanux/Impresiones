/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    eventosUsuario.eventos();
});

var eventosUsuario = {
    eventos: function() {
        $('a#lnk-inicio').click(function() {
            inicio.cargar('paginas/servicios/impresion.html');
        });
        $('img#logo').click(function() {
            inicio.cargar('paginas/servicios/impresion.html');
        });
        $('#lnk-impresiones').click(function() {
            console.log('df');
            inicio.cargar('paginas/resumen-impresion/impresiones-realizadas.html');
        });
        $('#lnk-cs').click(function() {
            $(location).attr('href', '/impresiones/login.html');
        });
        $(window).resize(function() {
            if (($(window).width() < 1000) && ($(window).width() > 900)) {
                console.log('1000');
            } else if ($(window).width() < 900) {
                console.log('900');
            }
        });
    }
};