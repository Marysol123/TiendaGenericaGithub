$(document).ready(function () {
    listarusuarios();

    function listarusuarios() {
        $.get(urlBackend+"listaVentas", function (data, status) {
            if (status == "success") {
                let longitud = data.length;
                if(longitud > 0) {
                    let total = 0;
                    let salidaDatos = "<table class=\"table\">";
                    salidaDatos = salidaDatos + "<thead class=\"table-dark\"><tr><th>Cedula</th><th>Nombre</th><th>Valor Total</th></tr></thead>";
                    for (let i = 0; i < longitud; i++) {
                        total = total + data[i].valorTotalVentas;
                        salidaDatos = salidaDatos + "<tr>";
                        salidaDatos = salidaDatos + "<td>" + data[i].cedula_cliente + "</td>";
                        salidaDatos = salidaDatos + "<td>" + data[i].nombre_cliente + "</td>";
                        salidaDatos = salidaDatos + "<td>" + data[i].valorTotalVentas + "</td>";
                        salidaDatos = salidaDatos + "</tr>";
                    }
                    salidaDatos = salidaDatos + "</table>";
                    $("#listaVentasCliente").html(salidaDatos);
                    $("#valtotal").val(total);
                }
                else {
                    swal("OOOPSSS", "NO HAY VENTAS REGISTRADAS EN EL SISTEMA", "error");
                }
            }
        });
    }


});
