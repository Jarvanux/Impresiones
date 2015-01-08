/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    setInterval(refrescar(), 2000);
});

function refrescar() {
    refrescaClientes.refrescar();
}

var refrescaClientes = {
    init: function() {
    },
    refrescar: function() {
        $.ajax({
            'url': '',
            'data' : '',
            success: function(data) {
                var respuesta = JSON.parse(data);
                console.log(respuesta);
            }
        });
    }
};