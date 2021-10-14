$(document).ready(function () {
    var prod1 = null;
    var prod2 = null;
    var prod3 = null;

    var ivaVal1 = null;
    var ivaVal2 = null;
    var ivaVal3 = null;

    var subtotal = null;
    var iva = null;
    var total = null;

    var valVenta1 = null;
    var valVenta2 = null;
    var valVenta3 = null;

    var cant1 = null;
    var cant2 = null;
    var cant3 = null;

    var cliente = null;

    verificarLogin();


    function verificarLogin() {
        if (!sessionStorage.cedulaUser) {
            swal("OOOPSSS", "NO HAS INICIADO SESION", "error")
                .then((value) => {
                    window.location.assign("index.html");
                });
        }
    }


    /**
     * Buscar Cliente por documento
     */
    $("#btnBuscarCC").click(function () {
        let la_cc = $("#cedula").val();   // El .val asigna el valor de la variable cedula a cc
        $.post(urlBackend + "traerCliente", {cedula: la_cc}, function (data, status) {
            let longitud = data.length;
            if (longitud > 0) {
                $("#cliente").val(data[0].nombre_cliente);
                cliente = data[0];
            } else {
                swal("OOOPSSS", "CLIENTE NO ENCONTRADO", "error");
                limpiarCedula();
                cliente = null;
            }
        });
    });

    function limpiarCedula() {
        $('#cedula').val('');
        $('#cliente').val('');
    }

    function limpiarfila1() {
        $('#codprod1').val('');
        $("#nombreProd1").val('');
        $("#cantProd1").val('');
        $("#totProd1").val('');
        prod1 = null;
        cant1 = 0;
        valVenta1 = 0;
        ivaVal1 = 0;
        calcularTotales();
    }

    function limpiarfila2() {
        $('#codprod2').val('');
        $("#nombreProd2").val('');
        $("#cantProd2").val('');
        $("#totProd2").val('');
        prod2 = null;
        cant2 = 0;
        valVenta2 = 0;
        ivaVal2 = 0;
        calcularTotales();
    }

    function limpiarfila3() {
        $('#codprod3').val('');
        $("#nombreProd3").val('');
        $("#cantProd3").val('');
        $("#totProd3").val('');
        prod3 = null;
        cant3 = 0;
        valVenta3 = 0;
        ivaVal3 = 0;
        calcularTotales();
    }

    $("#btnConProd1").click(function () {
        let codigo = $("#codprod1").val();
        $.post(urlBackend + "traerProducto", {codigo_producto: codigo}, function (data, status) {
            let longitud = data.length;
            if (longitud > 0) {
                console.log(data)
                $("#nombreProd1").val(data[0].nombre_producto);
                prod1 = data[0];
                cant1 = 1;
                $("#cantProd1").val(cant1);
                calcularSubtotal1();
            } else {
                swal("OOOPSSS", "PRODUCTO NO ENCONTRADO", "error");
                limpiarfila1();
            }
        });
    });

    $("#btnConProd2").click(function () {
        let codigo = $("#codprod2").val();
        $.post(urlBackend + "traerProducto", {codigo_producto: codigo}, function (data, status) {
            let longitud = data.length;
            if (longitud > 0) {
                console.log(data)
                $("#nombreProd2").val(data[0].nombre_producto);
                prod2 = data[0];
                cant2 = 1;
                $("#cantProd2").val(cant2);
                calcularSubtotal2();
            } else {
                swal("OOOPSSS", "PRODUCTO NO ENCONTRADO", "error");
                limpiarfila2();

            }
        });
    });

    $("#btnConProd3").click(function () {
        let codigo = $("#codprod3").val();
        $.post(urlBackend + "traerProducto", {codigo_producto: codigo}, function (data, status) {
            let longitud = data.length;
            if (longitud > 0) {
                console.log(data)
                $("#nombreProd3").val(data[0].nombre_producto);
                prod3 = data[0];
                cant3 = 1;
                $("#cantProd3").val(cant3);
                calcularSubtotal3();
            } else {
                swal("OOOPSSS", "PRODUCTO NO ENCONTRADO", "error");
                limpiarfila3();
            }
        });
    });

    $("#cantProd1").change(function () {
        cant1 = $('#cantProd1').val();
        if (prod1) {
            if (cant1 >= 1) {
                calcularSubtotal1()
            } else {
                swal("OOOPSSS", "CANTIDAD NO PUEDE SER VACIO O INFERIOR A 1", "error");
                $("#cantProd1").val(1);
                cant1 = 1;
                calcularSubtotal1()
            }
        }
    });

    $("#cantProd2").change(function () {
        cant2 = $('#cantProd2').val();
        if (prod2) {
            if (cant2 >= 1) {
                calcularSubtotal2()
            } else {
                swal("OOOPSSS", "CANTIDAD NO PUEDE SER VACIO O INFERIOR A 1", "error");
                $("#cantProd2").val(1);
                cant2 = 1;
                calcularSubtotal2()
            }
        }
    });

    $("#cantProd3").change(function () {
        cant3 = $('#cantProd3').val();
        if (prod3) {
            if (cant3 >= 1) {
                calcularSubtotal3()
            } else {
                swal("OOOPSSS", "CANTIDAD NO PUEDE SER VACIO O INFERIOR A 1", "error");
                $("#cantProd3").val(1);
                cant3 = 1;
                calcularSubtotal3()
            }
        }
    });


    function calcularTotales() {
        subtotal = valVenta1 + valVenta2 + valVenta3;
        iva = ivaVal1 + ivaVal2 + ivaVal3;
        total = subtotal + iva;
        $('#totalVenta').val(subtotal);
        $('#totalIva').val(iva);
        $('#totalConIva').val(total);
    }

    function calcularSubtotal1() {
        valVenta1 = prod1.precio_venta * cant1;
        $('#totProd1').val(valVenta1);
        ivaVal1 = valVenta1 * (prod1.ivacompra / 100);
        calcularTotales();
    }

    function calcularSubtotal2() {
        valVenta2 = prod2.precio_venta * cant2;
        $('#totProd2').val(valVenta2);
        ivaVal2 = valVenta2 * (prod2.ivacompra / 100);
        calcularTotales();
    }

    function calcularSubtotal3() {
        valVenta3 = prod3.precio_venta * cant3;
        $('#totProd3').val(valVenta3);
        ivaVal3 = valVenta3 * (prod3.ivacompra / 100);
        calcularTotales();
    }


    $("#btnCrear").click(function () {
        if (!cliente) {
            swal("OOOPSSS", "NO HAS SELECCIONADO UN CLIENTE", "error");
            return;
        }
        if (!prod1 && !prod2 && !prod3) {
            swal("OOOPSSS", "AL MENOS REGISTRA UN PRODUCTO", "error");
            return;
        }
        console.log(cliente)
        console.log(sessionStorage)
        console.log(prod1)
        console.log(prod2)
        console.log(prod3)
        //INFORMACION GENERAL DE LA VENTA
        let cedula_cliente = cliente.cedula_cliente
        let cedula_usuario = sessionStorage.cedulaUser
        let ivaventa = iva
        let total_venta = total
        let valor_venta = subtotal
        //DETALLE DE LA VENTA
        if (prod1) {
            var cantidad_producto1 = cant1
            var codigo_producto1 = prod1.codigo_producto
            var valor_venta1 = prod1.precio_venta * cant1
            var valoriva1 = valor_venta1 * (prod1.ivacompra / 100)
            var valor_total1 = valor_venta1 + valoriva1
        } else {
            var cantidad_producto1 = 0
            var codigo_producto1 = -1
            var valor_venta1 = 0
            var valoriva1 = 0
            var valor_total1 = 0
        }
        if (prod2) {
            var cantidad_producto2 = cant2
            var codigo_producto2 = prod2.codigo_producto
            var valor_venta2 = prod2.precio_venta * cant2
            var valoriva2 = valor_venta2 * (prod2.ivacompra / 100)
            var valor_total2 = valor_venta2 + valoriva2
        } else {
            var cantidad_producto2 = 0
            var codigo_producto2 = -1
            var valor_venta2 = 0
            var valoriva2 = 0
            var valor_total2 = 0
        }
        if (prod3) {
            var cantidad_producto3 = cant3
            var codigo_producto3 = prod3.codigo_producto
            var valor_venta3 = prod3.precio_venta * cant3
            var valoriva3 = valor_venta3 * (prod3.ivacompra / 100)
            var valor_total3 = valor_venta3 + valoriva3
        } else {
            var cantidad_producto3 = 0
            var codigo_producto3 = -1
            var valor_venta3 = 0
            var valoriva3 = 0
            var valor_total3 = 0
        }
        $.post(urlBackend + "registroVentas",
            {
                cedula_cliente: cedula_cliente,
                cedula_usuario: cedula_usuario,
                ivaventa: ivaventa,
                total_venta: total_venta,
                valor_venta: valor_venta,
                cantidad_producto1: cantidad_producto1,
                codigo_producto1: codigo_producto1,
                valor_venta1: valor_venta1,
                valoriva1: valoriva1,
                valor_total1: valor_total1,
                cantidad_producto2: cantidad_producto2,
                codigo_producto2: codigo_producto2,
                valor_venta2: valor_venta2,
                valoriva2: valoriva2,
                valor_total2: valor_total2,
                cantidad_producto3: cantidad_producto3,
                codigo_producto3: codigo_producto3,
                valor_venta3: valor_venta3,
                valoriva3: valoriva3,
                valor_total3: valor_total3,
            },
            function (data, status) {
                if (data >= 0) {
                    swal("OK", "VENTA REGISTRADA", "success");
                    $("#consec").val(data)
                } else {
                    swal("OOOPSSS", "NO SE PUDO REGISTRAR LA VENTA", "error");
                }
            });
    });


});