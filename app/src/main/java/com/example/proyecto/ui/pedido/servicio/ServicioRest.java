package com.example.proyecto.ui.pedido.servicio;

import com.example.proyecto.ui.pedido.entidad.Cliente;
import com.example.proyecto.ui.pedido.entidad.Pedido;
import com.example.proyecto.ui.pedido.entidad.Ubigeo;
import com.example.proyecto.ui.pedido.entidad.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ServicioRest {

//    //Crud de Rol
//    @GET("pedido")
//    public abstract Call<List<Pedido>> listaRol();
//
//    @POST("pedido")
//    public abstract Call<Pedido> agregaRol(@Body Pedido pedido);
//
//    @PUT("pedido")
//    public abstract Call<Pedido> actualizaRol(@Body Pedido pedido);
//
//    @DELETE("pedido/{idPedido}")
//    public abstract Call<Pedido> eliminaRol(@Path("idPedido") int id);

    @GET("departamentos")
    Call<List<String>> listaDepartamentos();

    @GET("provincias/{dep}")
    Call<List<String>> listaProvincias(@Path("dep") String idDep);

    @GET("distritos/{dep}/{pro}")
    Call<List<Ubigeo>> listaDistritos(@Path("dep") String idDep, @Path("pro") String idPro);

    //@GET("listaPedido")
    //public abstract Call<List<Pedido>> listaPedido();

    @POST("pedido")
    public abstract Call<Pedido> registroPedido(@Body Pedido pedido);

    @POST("cliente")
    public abstract Call<Cliente> registroCliente(@Body Cliente cliente);

    @GET("listaPedido/{pedido}")
    public abstract Call<List<Pedido>> listaPedido();


}
