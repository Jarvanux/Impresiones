/* 
 * Un script que contiene funciones interesantes como tomar el ip de la computadora visitante,
 * guarar en la BD la información del vistante, para estadisticas. ;)S
 */

ipVisitante = 0;

$(document).ready(function() {
    controlVisitantes.init();
});

var controlVisitantes = {
    init: function() {
//        controlVisitantes.guardarVisita();
    },
    guardarVisita: function() {
        if (location.href == "http://localhost:8080/impresiones/") {
            $.ajax({
                'url': 'guardarVisitante',
                'type': 'POST',
                'data': {'ipVisitante': 0},
                success: function(data) {
                    var respuesta = JSON.parse(data);
//                    console.log(respuesta);
                }
            });
        } else {
        }
    }
};

function calculaIP() {
//    var script = document.createElement("script");
//    script.type = "text/javascript";
//    script.src = "http://www.telize.com/jsonip?callback=DisplayIP"; //Con internet, generalmente usa una app de internet.
//    DisplayIP(response);//Sin internet.    
//    document.getElementsByTagName("head")[0].appendChild(script);
//    $.ajax({
//        'url': 'opteneripusuario',
//        'type': 'POST',
//        success: function(data) {
//            console.log(data);
////            alert(data);
//            var respuesta = JSON.parse(data);
//            ipVisitante = respuesta.datos;            
//            ipVisitante = respuesta.datos.replace(/\./g, '');
////            console.info(ipVisitante);
//            
//        }
//    });
}
;