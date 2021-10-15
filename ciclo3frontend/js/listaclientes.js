$(document).ready(function () {
    listarusuarios();

    function listarusuarios() {
        $.get(urlBackend+"listaClientes", function (data, status) {
            if (status == "success") {
                let longitud = data.length;
                if(longitud > 0) {
                    let salidaDatos = "<table class=\"table\">";
                    salidaDatos = salidaDatos + "<thead class=\"table-dark\"><tr><th>Cedula</th><th>Nombre</th><th>Correo Electronico</th><th>Direccion</th><th>Telefono</th></tr></thead>";
                    for (let i = 0; i < longitud; i++) {
                        salidaDatos = salidaDatos + "<tr>";
                        salidaDatos = salidaDatos + "<td>" + data[i].cedula_cliente + "</td>";
                        salidaDatos = salidaDatos + "<td>" + data[i].nombre_cliente + "</td>";
                        salidaDatos = salidaDatos + "<td>" + data[i].email_cliente + "</td>";
                        salidaDatos = salidaDatos + "<td>" + data[i].direccion_cliente + "</td>";
                        salidaDatos = salidaDatos + "<td>" + data[i].telefono_cliente + "</td>";
                        salidaDatos = salidaDatos + "</tr>";
                    }
                    salidaDatos = salidaDatos + "</table>";
                    $("#listaClientes").html(salidaDatos);
                }
                else {
                    swal("OOOPSSS", "NO HAY CLIENTES CREADOS EN EL SISTEMA", "error");
                }
            }
        });
    }


});
