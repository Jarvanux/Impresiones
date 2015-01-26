/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var eventosSuperUsuario = {
    init: function() {        
    },    
    eventos: function() {
//        $(window).click(function (){
//            $('#tbcostosMantenimiento input').hide();
//            $('#tbcostosMantenimiento span').show();
//        });

        $('#minimizarMenu').click(function() {
            $('#menuAvanzadoList').hide('slow');
            $('#menuAvanzado').show('slow');
        });
        $('#menuAvanzado').click(function() {
            $('#menuAvanzado').hide('slow');
            $('#menuAvanzadoList').show('slow');
        });
        $('#precios').click(function() {
            inicio.cargar('paginas/administracionsSitio/panel-usuario/precios.html');
            $('#visitantes').hide();
        });
    }
};
$(document).ready(function() {
    eventosSuperUsuario.init();
    eventosSuperUsuario.eventos();
//    $('td#clipColor input').val(($('td#clipColor span').html()).replace('$', ''));
//    $('td#rIColor input').val(($('td#rIColor span').html()).replace('$', ''));
//    $('td#operColor input').val(($('td#operColor span').html()).replace('$', ''));
//
//    $('td#clipBn input').val(($('td#clipBn span').html()).replace('$', ''));
//    $('td#rIBn input').val(($('td#rIBn span').html()).replace('$', ''));
//    $('td#operBn input').val(($('td#operBn span').html()).replace('$', ''));
});