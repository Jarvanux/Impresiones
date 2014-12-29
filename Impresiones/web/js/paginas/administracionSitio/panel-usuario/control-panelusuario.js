/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function() {
//    alert('Resolución de tu pantalla: '+screen.width + " x " + screen.height);
//    alert('Resolución del navegador:'+$(window).width()+' x '+$(window).height());    
    controlUsuario.init();
});

var controlUsuario = {
    init: function() {
        console.log('Dimención de pantalla cambiada');
        if ($(window).width() <= 1024) {
            $('ul#menu1').hide();
            $('ul#menu2').show();
            $('img#logo').css({'margin-left': '30px'});
        } else if ($(window).width() > 1024 && $(window).width() <= 1366 || $(window).width() > 1366) {
            $('ul#menu2').hide();
            $('ul#menu1').show();
            $('img#logo').css({'margin-left': '0px'});
        }
    },
};