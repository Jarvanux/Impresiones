<%-- 
    Document   : tablasPrueba
    Created on : 25/11/2014, 02:01:08 AM
    Author     : jhonjaider1000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery-1.11.1.min.js"></script>
        <script>
//            function agregarTD() {
//                var nuevaFila = $("#tablaSMS tbody tr:eq(0)").clone().removeClass('fila-base').appendTo("#tablaSMS tbody");
//                nuevaFila.css({'visibility': 'visible'});
//            }

            function soy(id) {
                alert('Soy '+id);
            }

            function cargarDatos() {
                $.ajax({
                    'url': 'leermensaje',
                    success: function(data) {
                        var respuesta = JSON.parse(data);
                        $.each(respuesta.datos, function(indice, valor) {
                            var nuevaFila = $("#tablaSMS tbody tr:eq(0)").clone().removeClass('fila-base').appendTo("#tablaSMS tbody");
                            nuevaFila.css({'visibility': 'visible'});
                            nuevaFila.find('#nombre').val(valor.nombre);
                            nuevaFila.find('#correo').val(valor.correo);
                            nuevaFila.find('#asunto').val(valor.asunto);
                            nuevaFila.find('#editar').val(valor.idContacto);
                            nuevaFila.find('#del').val(valor.idContacto);
                        });
                    }
                });
            }

            function consultarID() {
                if ($('#idContacto').val().length == 0) {
                    $('#idContacto').css({'border': '1px solid red'});
                    alert('Debe ingresar el id :D');
                } else {
                    var idContacto = $('#idContacto').val();
                    $.ajax({
                        'url': 'consultarporid',
                        'data': {
                            'idContacto': idContacto
                        },
                        success: function(data) {
                            var respuesta = JSON.parse(data);
                            if (respuesta.datos != null) {                                
                                var nuevaFila = $("#tablaSMS tbody tr:eq(0)").clone().removeClass('fila-base').appendTo("#tablaSMS tbody");
                                nuevaFila.css({'visibility': 'visible'});
                                nuevaFila.find('#nombre').val(respuesta.datos.nombre);
                                nuevaFila.find('#correo').val(respuesta.datos.correo);
                                nuevaFila.find('#asunto').val(respuesta.datos.asunto);
                                nuevaFila.find('#editar').val(respuesta.datos.idContacto);
                                nuevaFila.find('#editar').attr({"onclick": "soy("+respuesta.datos.idContacto+")"});
                                nuevaFila.find('#del').val(respuesta.datos.idContacto);
                            } else {
                                alert('No se encontró ningún dato con el id ingresado :D');
                            }
                            $('#idContacto').css({'border': '1px solid #CCC'});
                        }
                    });
                }
            }

        </script>
    </head>
    <body>
        <h1>Pruebas Jquery!</h1>
        <input type="number" id="idContacto" name="idContacto" value="" placeholder="idContacto" />
        <div id="tabla">
            <table id="tablaSMS">
                <thead>
                    <tr>
                        <th> Nombre </th>
                        <th> Correo </th>
                        <th> Asunto </th>
                        <th> &nbsp; </th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="fila-base" style="visibility: hidden;">
                        <td> <input type="text" name="nombre" id="nombre" disabled="disabled" /> </td>
                        <td> <input type="text" name="correo" id="correo" disabled="disabled" /> </td>
                        <td> <input type="text" name="asunto" id="asunto" disabled="disabled" /> </td>
                        <td> <input type="button" name="editar" id="editar" title="Editar" /></td>
                        <td> <input type="button" name="del" id="del" title="Eliminar" /></td>                        
                    </tr>
                </tbody>
            </table>
            <input type="button" value="Consultar todos" onclick="cargarDatos()" />
            <input type="button" value="Consultar por id" onclick="consultarID()" />
        </div>
    </body>
</html>
