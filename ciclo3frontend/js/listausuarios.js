$(document).ready(function () {
    listarusuarios();

    function listarusuarios() {
        $.get(urlBackend+"listaUsuario", function (data, status) {
            if (status == "success") {
                let longitud = data.length;
                if(longitud > 0) {
                    let salidaDatos = "<table class=\"table\">";
                    salidaDatos = salidaDatos + "<thead class=\"table-dark\"><tr><th>cedula usuario</th><th>nombre usuario</th><th>email usuario</th><th>usuario</th><th>password</th></tr></thead>";
                    for (let i = 0; i < longitud; i++) {
                        salidaDatos = salidaDatos + "<tr>";
                        salidaDatos = salidaDatos + "<td>" + data[i].cedula_usuario + "</td>";
                        salidaDatos = salidaDatos + "<td>" + data[i].nombre_usuario + "</td>";
                        salidaDatos = salidaDatos + "<td>" + data[i].email_usuario + "</td>";
                        salidaDatos = salidaDatos + "<td>" + data[i].usuario + "</td>";
                        salidaDatos = salidaDatos + "<td>" + data[i].password + "</td>";
                        salidaDatos = salidaDatos + "</tr>";
                    }
                    salidaDatos = salidaDatos + "</table>";
                    $("#listaUsuario").html(salidaDatos);
                }
                else {
                    swal("OOOPSSS", "NO HAY USUARIOS CREADOS EN EL SISTEMA", "error");
                }
            }
        });
    }


});
