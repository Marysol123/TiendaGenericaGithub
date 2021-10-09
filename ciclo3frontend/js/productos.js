$(document).ready(function () {

    $("#btnSubmit").click(function (event) {
        //detener el envio para hacer de forma manual por jquery.
        event.preventDefault();

        // Get form
        var form = $('#my-form')[0];

        // FormData object
        var data = new FormData(form);
        let archivo = $("#archivo").val();
        if (!notificarVacio(archivo)) return;
        var extensiones = archivo.substring(archivo.lastIndexOf("."));
        if (extensiones != ".csv") {
            swal("OOOPSSS", "EL ARCHIVO CARGADO NO ES UN CSV", "error");
            limpiarFormulario();
            return;
        }


        // adicionar un campo extra en el envio
        //data.append("CustomField", "This is some extra data, testing");

        // deshabilitar el boton de envio
        $("#btnSubmit").prop("disabled", true);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: urlBackend + "cargarArchivo",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 800000,
            success: function (resp) {
                console.log(resp);
                if (resp == "true") {
                    swal("LISTADO CARGADO!!", "LISTADO CARGADO CON EXITO", "success");
                    limpiarFormulario();
                } else {
                    swal("OOOPSSS", "NO SE PUDO CARGAR EL LISTADO", "error");
                    limpiarFormulario();
                }
                $("#btnSubmit").prop("disabled", false);
            },
            error: function (e) {
                $("#output").text(e.responseText);
                console.log("ERROR : ", e);
                $("#btnSubmit").prop("disabled", false);
            }
        });
    });


    /**
     * Limpiar el formulario
     */
    function limpiarFormulario() {
        $('input[type="text"]').val('');
    }

    /**
     * Verificar Campos vacios
     * @param campo
     * @returns {boolean}
     */
    function notificarVacio(campo) {
        let tmp = $.trim(campo);
        if (tmp.length == 0) {
            swal("OOOPSSS", "NO SE HA SELECCIONADO UN ARCHIVO", "error")
                .then((value) => {
                    limpiarFormulario();
                });
            return false;
        }
        return true;
    }


});