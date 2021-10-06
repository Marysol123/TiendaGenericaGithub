$(document).ready(function () {

    /**
     * Metodo Buscar por documento
     */
    $("#btnBuscarCC").click(function () {
        let la_cc = $("#cedula").val();   // El .val asigna el valor de la variable cedula a cc
        $.post(urlBackend + "traerUsuario", {cedula_usuario: la_cc}, function (data, status) {
            let longitud = data.length;
            if (longitud > 0) {
                $("#password").val(data[0].password);
                $("#email").val(data[0].email_usuario);
                $("#nombre").val(data[0].nombre_usuario);
                $("#usuario").val(data[0].usuario);
            } else {
                swal("OOOPSSS", "EL USUARIO NO SE ENCONTRO", "error");
                limpiarFormulario();
            }
        });
    });


    /**
     * Metodo para limpiar el formulario
     */
    function limpiarFormulario() {
        $('input[type="text"]').val('');
    }

    /**
     * Metodo parea validar si el campo esta vacio
     * @param campo
     * @returns {boolean}
     */
    function notificarVacio(campo) {
        let tmp = $.trim(campo);
        if (tmp.length == 0) {
            swal("OOOPSSS", "no estan diligenciados todos los campos", "error")
                .then((value) => {
                    $('input[type="text"]').val('');
                });
            return false;
        }
        return true;
    }


    /**
     * Metodo para crear un usuario
     */
    $("#btnCrear").click(function () {
        let la_cc = $("#cedula").val();
        if (!notificarVacio(la_cc)) return;
        let usuario = $("#usuario").val();
        if (!notificarVacio(usuario)) return;
        let nombre = $("#nombre").val();
        if (!notificarVacio(nombre)) return;
        let email = $("#email").val();
        if (!notificarVacio(email)) return;
        let el_telefono = $("#telefono").val();
        $.post(urlBackend + "crearCliente", {
            cedula: la_cc, direccion: la_direccion,
            email: el_email, nombre: el_nombre, telefono: el_telefono
        }, function (data, status) {
            if (data == true) {
                $("#mensaje").html("El cliente fue creado");
            } else {
                $("#mensaje").html("<b style = 'color:red;'>No se pudo crear, ya existe!</b>");
            }

        });
    });

    $("#btnBorrar").click(function () {
        let la_cc = $("#cedula").val();
        $.post(urlBackend + "borrarCliente", {cedula: la_cc}, function (data, status) {
            if (data == true) {
                $("#mensaje").html("El cliente fue eliminado");
            } else {
                $("#mensaje").html("<b style = 'color:red;'>No se pudo eliminar, NO existe!</b>");
            }
        });
    });
    $("#btnActualizar").click(function () {
        let la_cc = $("#cedula").val();
        let la_direccion = $("#direccion").val();
        let el_email = $("#email").val();
        let el_nombre = $("#nombre").val();
        let el_telefono = $("#telefono").val();
        $.post(urlBackend + "actualizarCliente", {
            cedula: la_cc, direccion: la_direccion,
            email: el_email, nombre: el_nombre, telefono: el_telefono
        }, function (data, status) {
            if (data == true) {
                $("#mensaje").html("El cliente fue actualizado");
            } else {
                $("#mensaje").html("<b style = 'color:red;'>No se pudo actualizar, NO existe!</b>");
            }
        });
    });
});