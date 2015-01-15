/* 
 * En este scritp he hecho algo interesante, inicialmente he hecho un refresh cada 2 segundos,
 * aquí controlaré todas las consultas que necesariamente se van a refrescar durante 
 * la sesión del usuario, generalmente estoy haciendo un refresh en el cual traigo todos los
 * datos consultados de cada cosa, para no hacer que el sistema tenga que hacer varias peticiones
 * desde direntes contextos, de esta manera el servidor solo me mandaría un objeto completo con 
 * todos los datos que necesito refrescar, así seré más amable con el servidor.
 */

$(document).ready(function() {
    setInterval(refrescar(), 2000); //Contador del refresh.
});

//Función que llamará a la función más importante de este scritp.
function refrescar(){   
    console.info('Refrescado...');
    refreshPanelAdmin.refrescarPanel();
}

var refreshPanelAdmin = {
    init: function() {

    },
    refrescarPanel: function() {
        $.ajax({
            'url':'refresPanel',
            'type':'POST',
            'data': {'idUsuario':idUsuarioLogeado},
            success: function(data) {
                console.log(data);
            }
        });
    },
};