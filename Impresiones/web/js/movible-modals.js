/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var movibles = {
    init: function() {
        $(window).click(function (event){
            console.log((event));
            console.log($(event.srcElement).parent().attr('id'));
            console.log($(event.srcElement).parent().position());
        });
    },   
};

$(document).ready(function (){
    movibles.init();
});