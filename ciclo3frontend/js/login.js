$(document).ready(function () {
    sessionStorage.clear();

    $("#btnLoginC").click(function () {
        let la_usuario = $("#usuario").val();
        let la_password = $("#password").val();


        $.post(urlBackend + "loginUsuario", {usuario: la_usuario, password: la_password}, function (data, status) {
            if (data == true) {
                $.post(urlBackend + "traerInfoUsuario", {
                    usuario: la_usuario,
                    password: la_password
                }, function (data, status) {
                    if (data) {
                        console.log(data)
                        sessionStorage.cedulaUser = data[0].cedula_usuario
                        sessionStorage.nombreUsuario = data[0].nombre_usuario
                    } else {
                        console.log("ERROR, NO LLEGO NADA")
                    }
                });
                swal.fire({
                    icon: 'success',
                    title: 'BIENVENIDO',
                    html: '<strong> BIENVENIDO </strong>'
                }).then((value) => {
                        window.location.assign("bienvenida.html");
                    });

            } else {
                swal.fire("OOPSSSS", "usuario o clave incorrecta", "error");
            }
        });
    });

});
