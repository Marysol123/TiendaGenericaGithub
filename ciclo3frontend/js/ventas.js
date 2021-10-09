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
     * Crear Proveedor
     */
    $("#btnCrear").click(function () {
        let nit = $("#nit").val();
        if(!notificarVacio(nit)) return;
        let telefono = $("#telefono").val();
        if(!notificarVacio(telefono)) return;
        let nombre = $("#nombre").val();
        if(!notificarVacio(nombre)) return;
        let ciudad = $("#ciudad").val();
        if(!notificarVacio(ciudad)) return;
        let direccion = $("#direccion").val();
        if(!notificarVacio(direccion)) return;
        $.post(urlBackend + "crearProveedor", {
            nitproveedor: nit,
            ciudad_proveedor: ciudad,
            direccion_proveedor:direccion,
            nombre_proveedor:nombre,
            telefono_proveedor:telefono
        }, function (data, status) {
            if (data == true) {
                swal("PROVEEDOR CREADO!!", "PROVEEDOR CREADO CON EXITO", "success");
                limpiarFormulario();
            } else {
                swal("OOOPSSS", "NO SE PUDO CREAR EL PROVEEDOR", "error");
                limpiarFormulario();
            }

        });
    });


    /**
     * BORRAR PROVEEDOR
     */
    $("#btnBorrar").click(function () {
        let nit = $("#nit").val();
        if(!notificarVacio(nit)) return;
        let telefono = $("#telefono").val();
        if(!notificarVacio(telefono)) return;
        let nombre = $("#nombre").val();
        if(!notificarVacio(nombre)) return;
        let ciudad = $("#ciudad").val();
        if(!notificarVacio(ciudad)) return;
        let direccion = $("#direccion").val();
        if(!notificarVacio(direccion)) return;
        $.post(urlBackend + "borrarProveedor", {nitproveedor: nit}, function (data, status) {
            if (data == true) {
                swal("PROVEEDOR ELIMINADO!!", "PROVEEDOR ELIMINADO CON EXITO", "success");
                limpiarFormulario();
            } else {
                swal("OOOPSSS", "NO SE PUDO ELIMINAR EL PROVEEDOR", "error");
                limpiarFormulario();
            }
        });
    });

    /**
     * ACTUALIZAR UN PROVEEDOR
     */
    $("#btnActualizar").click(function () {
        let nit = $("#nit").val();
        if(!notificarVacio(nit)) return;
        let telefono = $("#telefono").val();
        if(!notificarVacio(telefono)) return;
        let nombre = $("#nombre").val();
        if(!notificarVacio(nombre)) return;
        let ciudad = $("#ciudad").val();
        if(!notificarVacio(ciudad)) return;
        let direccion = $("#direccion").val();
        if(!notificarVacio(direccion)) return;
        $.post(urlBackend + "actualizarProveedor", {
            nitproveedor: nit,
            ciudad_proveedor: ciudad,
            direccion_proveedor:direccion,
            nombre_proveedor:nombre,
            telefono_proveedor:telefono
        }, function (data, status) {
            if (data == true) {
                swal("PROVEEDOR ACTUALIZADO!!", "PROVEEDOR ACTUALIZADO CON EXITO", "success");
                limpiarFormulario();
            } else {
                swal("OOOPSSS", "NO SE PUDO ACTUALIZAR EL PROVEEDOR", "error");
                limpiarFormulario();
            }
        });
    });
});