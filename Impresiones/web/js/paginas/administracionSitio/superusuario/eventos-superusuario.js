/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var eventosSuperUsuario = {
    init: function() {
    },
    cerrarNewTipo: function() {
        $('#newTipo').hide();
        $('#confirmar').hide();      
        $('#configPanel').hide();      
    },
    eventos: function() {
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
});