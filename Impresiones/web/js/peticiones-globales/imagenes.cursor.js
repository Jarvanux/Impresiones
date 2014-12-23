ObjetoImagenes = {"imagenes": [
        {"nombre":"#chat"},
        {"nombre":"#imgCompartirLink"},
        {"nombre":"#imgContinuarPedido"},
        {"nombre":".pedido"},
        {"nombre":".ayuda"}
]};
var imagenesCursor = {
    convertir:function(){
        for (i = 0; i < ObjetoImagenes.imagenes.length; i++){
            $(ObjetoImagenes.imagenes[i].nombre).css({"cursor":"pointer"});
        }
    }
};