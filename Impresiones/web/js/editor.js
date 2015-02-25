  /**
             * Cuando la url cambie, se llama esta función con el onclick de los links, para
             * realizar los cambios esperados.
             */

            /**
             * El objetivo por el cual me he complicado al hacer esta función es 
             * que deseo que la página sea basada en cargas ajax, pero todo el tiempo también permita 
             * que un usuario se desplace con los botones atrás y siguiente del 
             * navegador, pues los links cambian la dirección del url con el cual
             * se obtiene la posición actual en el sistema del usuario y de esta misma
             * manera si recargara la página no lo regresaría enseguida a un home.
             */
            var functions = {
                loadPage: function(page) {
                    try {
                        $('div#loading').show();
                        $.ajax({
                            'url': page,
                            'type': 'POST',
                            success: function(data) {
                                $('#contentLoaded').html(data);
                                $('div#loading').hide();
                            },
                            error: function(data) {
                                $('#contentLoaded').html('<h1>ERROR 404 - Page Not Found. </h1>');
                            }
                        });
                    } catch (d) {

                    }
                },
                loadEditor: function(page) {
                    try {
                        $('div#loading').show();
                        $.ajax({
                            'url': page,
                            'type': 'POST',
                            success: function(data) {
                                $('#contentEditor').html(data);
                                $('div#loading').hide();
                            },
                            error: function(data) {
                                $('#contentEditor').html('<h1>NO SE PUDO CARGAR EL EDITOR. </h1>');
                            }
                        });
                    } catch (d) {

                    }
                }
            }

            var consolen = {
                log: function() {

                }
            }


            function editorShow() {
                $('#editorPanel').show();
            }
            function editorHide() {
                $('#editorPanel').hide();
            }

            $(document).ready(function() {
                functions.loadEditor('editor.html');
            });