$(document).ready(function () {


    /**
     * Buscar Usuario por documento
     */
    $("#btnBuscarCC").click(function () {
        let la_cc = $("#cedula").val();   // El .val asigna el valor de la variable cedula a cc
        $.post(urlBackend + "traerUsuario", {cedula_usuario: la_cc}, function (data, status) {
            let longitud = data.length;
            if (longitud > 0) {
                $("#email").val(data[0].email_usuario);
                $("#nombre").val(data[0].nombre_usuario);
                $("#password").val(data[0].password);
                $("#usuario").val(data[0].usuario);
            } else {
                swal("OOOPSSS", "USUARIO NO ENCONTRADO", "error");
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
     * Crear Usuario
     */
    $("#btnCrear").click(function () {
        let la_cc = $("#cedula").val();
        if (!notificarVacio(la_cc)) return;
        let email = $("#email").val();
        if (!notificarVacio(email)) return;
        let nombre_usuario = $("#nombre").val();
        if (!notificarVacio(nombre_usuario)) return;
        let usuario = $("#usuario").val();
        if (!notificarVacio(usuario)) return;
        let password = $("#password").val();
        if (!notificarVacio(password)) return;

        $.post(urlBackend + "crearUsuario", {
            cedula_usuario: la_cc, email_usuario: email,
            nombre_usuario: nombre_usuario, usuario: usuario,
            password: password
        }, function (data, status) {
            if (data == true) {
                swal("USUARIO CREADO!!", "USUARIO CREADO CON EXITO", "success");
                limpiarFormulario();
            } else {
                swal("OOOPSSS", "NO SE PUDO CREAR EL USUARIO", "error");
                limpiarFormulario();
            }

        });
    });


    /**
     * BORRAR USUARIO
     */
    $("#btnBorrar").click(function () {
        let la_cc = $("#cedula").val();
        if (!notificarVacio(la_cc)) return;
        let email = $("#email").val();
        if (!notificarVacio(email)) return;
        let nombre_usuario = $("#nombre").val();
        if (!notificarVacio(nombre_usuario)) return;
        let usuario = $("#usuario").val();
        if (!notificarVacio(usuario)) return;
        let password = $("#password").val();
        if (!notificarVacio(password)) return;
        $.post(urlBackend + "borrarUsuario", {cedula: la_cc}, function (data, status) {
            if (data == true) {
                swal("USUARIO ELIMINADO!!", "USUARIO ELIMINADO CON EXITO", "success");
                limpiarFormulario();
            } else {
                swal("OOOPSSS", "NO SE PUDO ELIMINAR EL USUARIO", "error");
                limpiarFormulario();
            }
        });
    });

    /**
     * ACTUALIZAR UN USUARIO
     */
    $("#btnActualizar").click(function () {
        let la_cc = $("#cedula").val();
        if (!notificarVacio(la_cc)) return;
        let email = $("#email").val();
        if (!notificarVacio(email)) return;
        let nombre_usuario = $("#nombre").val();
        if (!notificarVacio(nombre_usuario)) return;
        let usuario = $("#usuario").val();
        if (!notificarVacio(usuario)) return;
        let password = $("#password").val();
        if (!notificarVacio(password)) return;
        $.post(urlBackend + "actualizarUsuario", {
            cedula_usuario: la_cc, email_usuario: email,
            nombre_usuario: nombre_usuario, usuario: usuario,
            password: password
        }, function (data, status) {
            if (data == true) {
                swal("USUARIO ACTUALIZADO!!", "USUARIO ACTUALIZADO CON EXITO", "success");
                limpiarFormulario();
            } else {
                swal("OOOPSSS", "NO SE PUDO ACTUALIZAR EL USUARIO", "error");
                limpiarFormulario();
            }
        });
    });
});