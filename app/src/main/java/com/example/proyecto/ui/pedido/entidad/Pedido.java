package com.example.proyecto.ui.pedido.entidad;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private int idPedido;
    private Date fechaRegistro;
    private Date fechaEntrega;
    private String lugarEntrega;
    private String estado;
    private Cliente cliente;
    private Ubigeo ubigeo;
    private Usuario usuario;
    private ArrayList<PedidoDetalle> detalles;


    public void setFechaEntrega(String toString) {
    }

    public void setFechaRegistro(String toString) {
    }

    public void setCliente(String toString) {
    }

    public void setUsuario(String toString) {
    }

    public void setUbigeo(String toString) {
    }
}
