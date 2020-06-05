var contMuestra = 0;

function agregarMuestra() {
    contMuestra++;
    var muestraAdicional = "";
    
    muestraAdicional += " <div class=\"row\">" +
        "                                    <label class=\"col-sm-2 control-label\">ID cliente de la muestra:</label>" +
        "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
        "                                        <div class=\"form-group\">" +
        "                                            <input type=\"text\" class=\"form-control\" placeholder=\"\">" +
        "                                        </div>" +
        "                                    </div>" +
        "                                    <label class=\"col-sm-2 control-label\">Tipo de muestra:</label>" +
        "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
        "                                        <div class=\"form-group\">" +
        "                                            <input type=\"text\" class=\"form-control\" placeholder=\"\">" +
        "                                        </div>" +
        "                                    </div>" +
        "                                </div>" +
        "" +
        "                                <div class=\"row\">" +
        "                                    <label class=\"col-sm-2 control-label\">Descripción de la muestra:</label>" +
        "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
        "                                        <div class=\"form-group\">" +
        "                                            <input type=\"text\" class=\"form-control\" placeholder=\"\">" +
        "                                        </div>" +
        "                                    </div>" +
        "                                    <label class=\"col-sm-2 control-label\">Método Solicitado:</label>" +
        "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
        "                                        <div class=\"form-group\">" +
        "                                            <select class=\"selectpicker\" name=\"metodo" + contMuestra + "\" id=\"metodo" + contMuestra + "\" required=\"true\"" +
        "                                                    data-title=\"Selecciona un método...\"" +
        "                                                    data-style=\"btn-default btn-outline\"" +
        "                                                    data-menu-style=\"dropdown-blue\"" +
        "                                                    onchange=\"chequeo()\">";

    $.getJSON("https://localhost:8444/method", function (result) {
        $.each(result, function (i, field) {
            muestraAdicional += "<option value = \"" + field.cantidadMuestraEnsayo + "\">" + field.codigoMetodo + "</option>";
        });
        
        muestraAdicional += "</select>" +
            "                                        </div>" +
            "                                    </div>" +
            "                                </div>" +
            "                                <div class=\"row\">" +
            "                                    <label class=\"col-sm-2 control-label\">Condiciones Especiales:</label>" +
            "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
            "                                        <div class=\"form-group\">" +
            "                                            <input type=\"text\" class=\"form-control\" placeholder=\"\">" +
            "                                        </div>" +
            "                                    </div>" +
            "                                    <label class=\"col-sm-2 control-label\">Cantidad de Muestra:</label>" +
            "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
            "                                        <div class=\"form-group\">" +
            "                                            <input type=\"text\" class=\"form-control\"" +
            "                                                   name=\"cantidadMuestra" + contMuestra + "\"" +
            "                                                   id=\"cantidadMuestra" + contMuestra + "\" required=\"true\" disabled>" +
            "                                        </div>" +
            "                                    </div>" +
            "                                </div>" +
            "                                <div class=\"row\">" +
            "                                    <label class=\"col-sm-2 control-label\">Observaciones: </label>" +
            "                                    <div class=\"col-sm-4 col-sm-offset-1\">" +
            "                                        <div class=\"form-group\">" +
            "                                            <input type=\"text\" class=\"form-control\">" +
            "                                        </div>" +
            "                                    </div>" +
            "                                </div>";

        var cadena = "$(" + "\"#metodo" + contMuestra + "\"" + ")" + ".selectpicker(" + "\"refresh\")";

        $("#muestraExtra").append(muestraAdicional);
        eval(cadena);
    });
}

function chequeo() {
    console.log("entro");
    for (var i = 0; i <= contMuestra; i++) {
        document.getElementById("cantidadMuestra"+i).value = document.getElementById("metodo"+i).value;
    }
}