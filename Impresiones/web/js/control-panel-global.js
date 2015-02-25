/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var controlGlobal = {
    init: function(){
        
    },
    eventos: function (){        
    },
    mostrarMenu: function (){
        $('div#button_menu').hide();
        $('nav#menu').show('slow');
    },
    esconderMenu: function (){
        $('nav#menu').hide('slow');
        $('div#button_menu').show('slow');
    }
};

$(document).ready(function (){
    controlGlobal.init();
    controlGlobal.eventos();
});