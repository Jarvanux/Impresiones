/* 
 * Eventos globales.
 */

var eventos = {
    /**
     * Contiene todos los eventos para clases del sistema.     
     */
    eventos: function() {
    }
};
eventos.eventos();

$.ajax({
    'url': servlet,
    'data': {color: 2},
    success: function(data) {
        var respuesta = JSON.parse(data);
        console.log(respuesta);
    }
});