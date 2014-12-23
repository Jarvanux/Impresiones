var app = {
    detenerEvento:function(e){
        if (e.preventDefault) {
            e.preventDefault();
        }else if(e.stopPropagation){
            e.stopPropagation();
        }else{
            e.returnValue = false;
        }
    }
};