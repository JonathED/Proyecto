package com.example.proyecto.ui.pedido.entidad;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ubigeo {

    private int idUbigeo;
    private String departamento;
    private String provincia;
    private String distrito;

    @Override
    public String toString(){
        return distrito;
    }

}
