/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var tabsquerys = {
    init: function() {
        $('.content-tab').hide();
        $('#infor').show(500);
        tabsquerys.eventosTabs();
    },
    eventosTabs: function() {
        $('div#headerPanel #tab1').click(function() {
            $('div#tabBody #tab2').hide();
            $('div#tabBody #infor').hide();
            $('div#tabBody #tab3').hide();
            $('div#tabBody #tab4').hide();
            $('div#tabBody #tab1').show();
            $('div#headerPanel div.tab').css({"color": "#FFF", "font-weight": "normal", "background": "#C45A5A"});
            $('div#headerPanel #tab1').css({"color": "black", "font-weight": "bold", "background": "#FFF", "border": "1px solid #ccc"});
            $('div#clrAnillo span.valorR').html('Selecciona'); //Consultar como pasar string a entero.        
            $('div#clrTp1 span.valorR').html('Selecciona'); //Consultar como pasar string a entero.        
            $('div#clrTp2 span.valorR').html('Selecciona'); //Consultar como pasar string a entero.  


            if ($('#soloBN').is(':checked')) {
                $('div#tipoImpr span.valorR').html('B/N'); //Consultar como pasar string a entero.
                $('div#numHBn span.valorR').html($('#numTotalBN').val());
            } else if ($('#soloColor').is(':checked')) {
                $('div#tipoImpr span.valorR').html('Color'); //Consultar como pasar string a entero.
                $('div#numHClr span.valorR').html($('#numTotalColor').val());
            } else if ($('#mixto').is(':checked')) {
                $('div#tipoImpr span.valorR').html('Mixto');                
                $('div#numHBn span.valorR').html($('#numTotalBN').val());
                $('div#numHClr span.valorR').html($('#numTotalColor').val());
            }
        });
        $('div#headerPanel #tab2').click(function() {
            $('div#tabBody #infor').hide();
            $('div#tabBody #tab1').hide();
            $('div#tabBody #tab3').hide();
            $('div#tabBody #tab4').hide();
            $('div#tabBody #tab2').show();
            $('div#headerPanel div.tab').css({"color": "#FFF", "font-weight": "normal", "background": "#C45A5A"});
            $('div#headerPanel #tab2').css({"color": "black", "font-weight": "bold", "background": "#FFF", "border": "1px solid #ccc"});
        });
        $('div#headerPanel #tab3').click(function() {
            $('div#tabBody #infor').hide();
            $('div#tabBody #tab2').hide();
            $('div#tabBody #tab3').hide();
            $('div#tabBody #tab4').hide();
            $('div#tabBody #tab3').show();
            $('div#headerPanel div.tab').css({"color": "#FFF", "font-weight": "normal", "background": "#C45A5A"});
            $('div#headerPanel #tab3').css({"color": "black", "font-weight": "bold", "background": "#FFF", "border": "1px solid #ccc"});
        });
        $('div#headerPanel #tab4').click(function() {
            $('div#tabBody #infor').hide();
            $('div#tabBody #tab2').hide();
            $('div#tabBody #tab3').hide();
            $('div#tabBody #tab1').hide();
            $('div#tabBody #tab4').show();
            $('div#headerPanel div.tab').css({"color": "#FFF", "font-weight": "normal", "background": "#C45A5A"});
            $('div#headerPanel #tab4').css({"color": "black", "font-weight": "bold", "background": "#FFF", "border": "1px solid #ccc"});
        });
    }
};

tabsquerys.init();