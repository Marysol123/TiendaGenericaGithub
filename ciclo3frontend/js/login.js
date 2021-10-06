$(document).ready(function () {

    $("#btnLoginC").click(function () {
        let la_usuario = $("#usuario").val();
        let la_password = $("#password").val();


        // El .val asigna el valor de la variable cedula a cc
        $.post(urlBackend + "loginUsuario", {usuario: la_usuario, password: la_password}, function (data, status) {
            if (data == true) {
                swal("Bienvenido", "inicio de sesion correcto", "success")
                    .then((value) => {
                        window.location.assign("bienvenida.html");
                    });

            } else {
                swal("OOPSSSS", "usuario o clave incorrecta", "error");
            }
        });
    });

});
