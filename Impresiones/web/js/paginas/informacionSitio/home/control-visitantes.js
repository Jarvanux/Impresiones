/* 
 * Un script que contiene funciones interesantes como tomar el ip de la computadora visitante,
 * guarar en la BD la información del vistante, para estadisticas. ;)S
 */

ipVisitante = null;

$(document).ready(function() {
    controlVisitantes.init();
});

var controlVisitantes = {
    init: function() {
        controlVisitantes.guardarVisita();
    },
    guardarVisita: function() {
        calculaIP();
        var x = "jkfs";
        x.hasOwnProperty("s")
        if (location.href == "http://localhost:8080/impresiones/") {
            $.ajax({
                'url': 'guardarVisitante',
                'type': 'POST',
                'data': {'ipVisitante': ipVisitante},
                success: function(data) {
                    var respuesta = JSON.parse(data);
                    console.log(respuesta);
                }
            });
        }else{
        }
    }
};

function calculaIP() {
    var script = document.createElement("script");
    script.type = "text/javascript";
//    script.src = "http://www.telize.com/jsonip?callback=DisplayIP";
//    document.getElementsByTagName("head")[0].appendChild(script);
    DisplayIP('982.934.234');
}
;

function DisplayIP(response) {
//    var ip = response.ip;
    ipVisitante = response.replace(/\./g, '');
}