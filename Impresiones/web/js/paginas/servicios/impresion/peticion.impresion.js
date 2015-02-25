/* 
 Generalemente este archivo ha sido creado para configurar las consultas y/o comunicaciones
 ajax que tenga la página con el servlet.
 */
tiposImpresion = {'datos': [
        {'idTipoImpresion':'1','tipoImpresion':'Impresión Laser'},
        {'idTipoImpresion':'2','tipoImpresion':'LaserImpresión Inject'},
        {'idTipoImpresion':'3','tipoImpresion':"Impresión y Grabacion de CD's"},
        {'idTipoImpresion':'4','tipoImpresion':'Impresión de Plotter(Banner,Vinilo.etc)'},
        {'idTipoImpresion':'5','tipoImpresion':'Impresión de Planos'},
        {'idTipoImpresion':'6','tipoImpresion':'Proyectos especiales'},
]};
var peticionImpresion = {
    tiposImpresion: function(cmbTipoImpresion) {        
                cmbTipoImpresion.append(new Option('Selecciona', '-1'));                
                    $.each(tiposImpresion.datos, function(indice, valor) {
                        cmbTipoImpresion.append(new Option(valor.tipoImpresion, valor.idTipoImpresion));
                    });
                    $('#imgLoader').hide();
                    $('#smsLoader').hide();
                    $('.subTitulo1').show(500);
                    $('#cmbTipoImpresion').show(500);
                    $('#btnTipoImpresion').show(500);                                                       
    }
};


