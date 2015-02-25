/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//
//$(document).ready(function() {
//    var refreshId = setInterval(refrescarTablaEstadoSala, 30000);
//    $.ajaxSetup({cache: false});
//});

var refrescarContenidos = {
    refrescar: function() {
        var refresId = setInterval(refrescarContenidos.ejecutarRefresh('divMensajes'), 30000);        
    },
    ejecutarRefresh: function(divContenido, pagina) {
      
    },
};