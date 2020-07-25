package com.example.proyecto.ui.pedido.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String login;
    private String password;

}
