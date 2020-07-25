package com.example.proyecto.ui.pedido;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;
import com.example.proyecto.ui.pedido.adaptador.PedidoAdapter;
import com.example.proyecto.ui.pedido.entidad.Pedido;
import com.example.proyecto.ui.pedido.entidad.Ubigeo;
import com.example.proyecto.ui.pedido.servicio.ServicioRest;
import com.example.proyecto.ui.pedido.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapterDep, adapterPro;
    ArrayAdapter<Ubigeo> adapterDis;


    Button btnAdd;
    Button btnLista;
    ListView listView;
    PedidoAdapter adaptadorListView;


    Spinner spnDep, spnPro,spnDis;
    ServicioRest servicio;
    String selDep, selPro;
    int idUbigeo = -1;

    List<Pedido> list = new ArrayList<Pedido>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("REGISTRO PEDIDO");

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnLista = (Button) findViewById(R.id.btnLista);

        // Al adaptador se le pasa la data y el diseño
        listView = (ListView) findViewById(R.id.listView);

       // Se crea la conexion al servicio
        servicio = ConnectionRest.getConnection().create(ServicioRest.class);

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó el listado");
                listData();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó el agregar");
                Intent intent = new Intent(MainActivity.this, PedidoActivity.class);
                intent.putExtra("var_metodo", "REGISTRAR");
                startActivity(intent);
            }
        });

        //Para cargar los datos al inicio
        listData();

    adapterDep =  new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item);
        adapterDep.add("[ Seleccione Departamento ]");
    spnDep =  findViewById(R.id.spnDepartamentos);
        spnDep.setAdapter(adapterDep);

    adapterPro =  new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item);
        adapterPro.add("[ Seleccione Provincia ]");
    spnPro =  findViewById(R.id.spnProvincias);
        spnPro.setAdapter(adapterPro);

    adapterDis =  new ArrayAdapter<Ubigeo>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item);
        adapterDis.add(new Ubigeo(0,"","","[ Selecciona Distrito ]"));
    spnDis =  findViewById(R.id.spnDistritos);
        spnDis.setAdapter(adapterDis);

    servicio = ConnectionRest.getConnection().create(ServicioRest.class);
    cargaDepartamento();

        spnDep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position != 0){ //Si no es el primer elemento
                selDep = spnDep.getSelectedItem().toString();
                cargaProvincia(selDep);
            }else{
                adapterPro.clear();
                adapterPro.add("[ Selecciona Provincia ]");
                adapterPro.notifyDataSetChanged();
                adapterDis.clear();
                adapterDis.add(new Ubigeo(0,"","","[ Selecciona Distrito ]"));
                adapterDis.notifyDataSetChanged();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    });

        spnPro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position != 0){ //Si no es el primer elemento
                selPro = spnPro.getSelectedItem().toString();
                cargaDistrito(selDep, selPro);
            }else{
                adapterDis.clear();
                adapterDis.add(new Ubigeo(0,"","","[ Selecciona Distrito ]"));
                adapterDis.notifyDataSetChanged();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    });

        spnDis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position != 0){
                idUbigeo = adapterDis.getItem(position).getIdUbigeo();
                String mensaje = "Ubigeo : " + idUbigeo ;
                mostrarMensaje("Selección de Datos", mensaje);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    });
}

    public void cargaDepartamento(){
        Call<List<String>> call = servicio.listaDepartamentos();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> lista = response.body();
                if(lista != null){
                    adapterDep.addAll(lista);
                    adapterDep.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("ERROR", "onFailure: ", t);
            }
        });
    }


    public void cargaProvincia(String dep){
        Log.e("INFO", "->"+  dep +"<-");
        Call<List<String>> call = servicio.listaProvincias(dep);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> lista = response.body();
                if(lista != null){
                    adapterPro.clear();
                    adapterPro.add("[ Selecciona Provincia ]");
                    adapterPro.addAll(lista);
                    adapterPro.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("ERROR", "onFailure: ", t);
            }
        });
    }


    public void cargaDistrito(String dep, String dis){
        Log.e("INFO", "->"+  dep +"<-");
        Log.e("INFO", "->"+  dis +"<-");
        Call<List<Ubigeo>> call = servicio.listaDistritos(dep, dis);
        call.enqueue(new Callback<List<Ubigeo>>() {
            @Override
            public void onResponse(Call<List<Ubigeo>> call, Response<List<Ubigeo>> response) {
                List<Ubigeo> lista = response.body();
                if(lista != null){
                    adapterDis.clear();
                    adapterDis.add(new Ubigeo(0,"","","[ Selecciona Distrito ]"));
                    adapterDis.addAll(lista);
                    adapterDis.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Ubigeo>> call, Throwable t) {
                Log.e("ERROR", "onFailure: ", t);
            }
        });
    }


    public void mostrarMensaje(String titulo, String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(msg);
        alertDialog.setTitle(titulo);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    public void listData(){
        Call<List<Pedido>> call = servicio.listaPedido();
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(response.isSuccessful()){
                    //Aqui es donde obtiene la data y se coloca en el list
                    mensaje("Listado exitoso");
                    list = response.body();
                    adaptadorListView = new PedidoAdapter(MainActivity.this, R.layout.activity_list, list);
                    listView.setAdapter(adaptadorListView);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    void mensaje(String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }
}
