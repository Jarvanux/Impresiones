var vistaRegistro = {
    
    init:function(){
        $('#txtNacimiento').datepicker({
            format:'dd/mm/yyyy',
            endDate:new Date().toString(),
            startView:2,
            orientation: "bottom left"
            
        });
    }
};

vistaRegistro.init();