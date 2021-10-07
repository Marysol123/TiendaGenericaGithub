$(document).ready(function () {

    /**
     * Buscar Cliente por documento
     */
    $("#btnBuscarCC").click(function () {
        let la_cc = $("#cedula").val();   // El .val asigna el valor de la variable cedula a cc
        $.post(urlBackend + "traerCliente", {cedula: la_cc}, function (data, status) {
            let longitud = data.length;
            if (longitud > 0) {
                $("#direccion").val(data[0].direccion_cliente);
                $("#email").val(data[0].email_cliente);
                $("#nombre").val(data[0].nombre_cliente);
                $("#telefono").val(data[0].telefono_cliente);
            } else {
                swal("OOOPSSS", "CLIENTE NO ENCONTRADO", "error");
                limpiarFormulario();
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
            swal("OOOPSSS", "no estan diligenciados todos los campos", "error")
                .then((value) => {
                    limpiarFormulario();
                });
            return false;
        }
        return true;
    }

    /**
     * Crear Cliente
     */
    $("#btnCrear").click(function () {
        let la_cc = $("#cedula").val();
        if(!notificarVacio(la_cc)) return;
        let la_direccion = $("#direccion").val();
        if(!notificarVacio(la_direccion)) return;
        let el_email = $("#email").val();
        if(!notificarVacio(el_email)) return;
        let el_nombre = $("#nombre").val();
        if(!notificarVacio(el_nombre)) return;
        let el_telefono = $("#telefono").val();
        if(!notificarVacio(el_telefono)) return;
        $.post(urlBackend + "crearCliente", {
            cedula: la_cc, direccion: la_direccion,
            email: el_email, nombre: el_nombre, telefono: el_telefono
        }, function (data, status) {
            if (data == true) {
                swal("CLIENTE CREADO!!", "CLIENTE CREADO CON EXITO", "success");
                limpiarFormulario();
            } else {
                swal("OOOPSSS", "NO SE PUDO CREAR EL CLIENTE", "error");
                limpiarFormulario();
            }

        });
    });


    /**
     * BORRAR CLIENTE
     */
    $("#btnBorrar").click(function () {
        let la_cc = $("#cedula").val();
        if(!notificarVacio(la_cc)) return;
        let la_direccion = $("#direccion").val();
        if(!notificarVacio(la_direccion)) return;
        let el_email = $("#email").val();
        if(!notificarVacio(el_email)) return;
        let el_nombre = $("#nombre").val();
        if(!notificarVacio(el_nombre)) return;
        let el_telefono = $("#telefono").val();
        if(!notificarVacio(el_telefono)) return;
        $.post(urlBackend + "borrarCliente", {cedula: la_cc}, function (data, status) {
            if (data == true) {
                swal("CLIENTE ELIMINADO!!", "CLIENTE ELIMINADO CON EXITO", "success");
                limpiarFormulario();
            } else {
                swal("OOOPSSS", "NO SE PUDO ELIMINAR EL CLIENTE", "error");
                limpiarFormulario();
            }
        });
    });

    /**
     * ACTUALIZAR UN CLIENTE
     */
    $("#btnActualizar").click(function () {
        let la_cc = $("#cedula").val();
        if(!notificarVacio(la_cc)) return;
        let la_direccion = $("#direccion").val();
        if(!notificarVacio(la_direccion)) return;
        let el_email = $("#email").val();
        if(!notificarVacio(el_email)) return;
        let el_nombre = $("#nombre").val();
        if(!notificarVacio(el_nombre)) return;
        let el_telefono = $("#telefono").val();
        if(!notificarVacio(el_telefono)) return;
        $.post(urlBackend + "actualizarCliente", {
            cedula: la_cc, direccion: la_direccion,
            email: el_email, nombre: el_nombre, telefono: el_telefono
        }, function (data, status) {
            if (data == true) {
                swal("CLIENTE ACTUALIZADO!!", "CLIENTE ACTUALIZADO CON EXITO", "success");
                limpiarFormulario();
            } else {
                swal("OOOPSSS", "NO SE PUDO ACTUALIZAR EL CLIENTE", "error");
                limpiarFormulario();
            }
        });
    });
});