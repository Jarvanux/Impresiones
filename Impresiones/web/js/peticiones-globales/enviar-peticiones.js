/* 
 * En este script encontramos algo interesante, básicamente se trata de
 * un pequeño scritp que nos permitirá enviar las peticiones respectivas
 * capturando la información necesaria de nuestro formulario :D
 */

var enviarPeticiones = {
    /**
     * Esta pequeña función recibe el nombre de un formulario, y un array, básicamente
     * el array se ha involucrado como parametro si necesitamos enviar datos adicionales
     * de insersión que no van a estar contemplados en el formulario.
     * EJEMPLO     
     * En mi caso necesito enviar dos párametros adicionales (un idRol y un estado)
     * lo cual lo he solucionado de la siguiente manera:     
     * enviarPeticiones.enviarPeticion('#IDForm',{"estado":"1","idRol":"2"})
     * 
     * El formulario de envio debe contener la siguiente estructura:
     * ----- <\form id="idReferencia" class="instanciaServlet" action="urlServlet" method="POST" \>
     * teniendo encuenta las etiquetas html y su adecuada escritura, pues he modificado la estructura para que sea
     * visible en la documentación.
     * De esta manera todo debería salir a la perfección si el servlet está funcionando perfectamente.
     * @param formulario = id del formulario EJEMPLO: '#form1'
     * @param array = datos Adicionales EJEMPLO: {"datoAdicional":"DATA"}, si no necestamos usarlo lo inicializamos así {}
     * de esta manera nuestra llamada queda de la siguiente manera: enviarPeticion('#form1', {"datoAdicional":"DATA"})
     * @returns {undefined}
     */
    enviarPeticion: function(formulario, array) {
        var instanciaServlet = $(formulario).attr("class");
        var url = $(formulario).attr("action");
        var metodo = $(formulario).attr("method");
        var objetoJSON;

        var dataFinds = $(formulario).find('input[type="text"],input[type="password"],select');
        if (dataFinds.length > 0) {
            //Inicio For            
            data = null;
            for (var i = 0; i < dataFinds.length; i++) {
                data = dataFinds[i];
                var tempName = data.id;
                array[tempName] = $(formulario).find('#' + data.id).val();
            }
            //Fin For           
            //Despues del for ya tendríasmos armado el array que vamos a convertir.
        } else {
            console.log('El formulario no cuenta con elementos para crear el objeto JSON');
        }

        if (array != null) {
            //Si todo salió bien, pasamos a convertir el array en un objeto JSON.     
            objetoJSON = {};
            objetoJSON[instanciaServlet] = JSON.stringify(array);
            console.log(objetoJSON);
            controlPeticiones.insertar(url, metodo, objetoJSON, null);
        } else {
            console.log('Imposible completar la petición, el array de conversión está vacio.');
        }
    },    
};


