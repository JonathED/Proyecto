package com.example.proyecto.ui.pedido.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDetalle {

    private int idPedido;
    private int idProducto;
    private double precio;
    private int cantidad;


}
