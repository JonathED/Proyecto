package com.example.proyecto.ui.pedido;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;
import com.example.proyecto.ui.pedido.entidad.Pedido;
import com.example.proyecto.ui.pedido.servicio.ServicioRest;
import com.example.proyecto.ui.pedido.util.ConnectionRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoActivity extends AppCompatActivity {

    ServicioRest servicio;
    EditText edtUId, edtPFechaRegistro, edtPFechaEntrega, edtPLugarEntrega, edtPUbigeo,
            edtPCliente, edtPUsuario;
    Spinner spnEstado;
    Button btnSave;
    Button btnDel;
    TextView txtUId;
    final String metodo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        setTitle("Registrar de Pedido");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtTId);
        edtUId = (EditText) findViewById(R.id.edtTId);
        edtPFechaRegistro = (EditText) findViewById(R.id.edtTfechaRegistro);
        edtPFechaEntrega = (EditText) findViewById(R.id.edtTfechaEntrega);
        edtPLugarEntrega = (EditText) findViewById(R.id.edtTlugarEntrega);
       // edtPUbigeo = (EditText) findViewById(R.id.edtTidUbigeo);
        edtPCliente = (EditText) findViewById(R.id.edtTidCliente);
        edtPUsuario = (EditText) findViewById(R.id.edtTidCliente);
        spnEstado = (Spinner) findViewById(R.id.spnProEstado);
        btnSave = (Button) findViewById(R.id.btnProSave);
        btnDel = (Button) findViewById(R.id.btnProDel);
        servicio = ConnectionRest.getConnection().create(ServicioRest.class);
        Bundle extras = getIntent().getExtras();
        final String metodo = extras.getString("var_metodo");
        final String var_id = extras.getString("var_id");

        if (metodo.equals("VER")) {

            String var_fechaRegistro = extras.getString("var_fechaRegistro");
            String var_fechaEntrega = extras.getString("var_fechaEntrega");
            String var_lugarEntrega = extras.getString("var_lugarEntrega");
           // String var_idUbigeo = extras.getString("var_idUbigeo");
            String var_idCliente = extras.getString("var_idCliente");
            String var_idUsuario = extras.getString("var_idUsuario");
            String var_estado = extras.getString("var_estado");

            edtUId.setText(var_id);
            edtPFechaRegistro.setText(var_fechaRegistro);
            edtPFechaEntrega.setText(var_fechaEntrega);
            edtPLugarEntrega.setText(var_lugarEntrega);
           // edtPUbigeo.setText(var_idUbigeo);
            edtPCliente.setText(var_idCliente);
            edtPUsuario.setText(var_idUsuario);
            selectValue(spnEstado, var_estado);
            edtUId.setFocusable(false);
        }else if (metodo.equals("REGISTRAR")) {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pedido p = new Pedido();
                p.setFechaRegistro(edtPFechaRegistro.getText().toString());
                p.setFechaEntrega(edtPFechaEntrega.getText().toString());
                p.setLugarEntrega(edtPLugarEntrega.getText().toString());
                p.setUbigeo(edtPUbigeo.getText().toString());
                p.setCliente(edtPCliente.getText().toString());
                p.setUsuario(edtPUsuario.getText().toString());
                p.setEstado(spnEstado.getSelectedItem().toString());
                if (metodo.equals("VER")) {
                    p.setIdPedido(Integer.parseInt(var_id));
                    mensaje("Se pulsó  actualizar");
                    add(p);
                } else if (metodo.equals("REGISTRAR")) {
                    mensaje("Se pulsó agregar");
                    add(p);
                }

                Intent intent = new Intent(PedidoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        btnDel.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            mensaje("Se pulsó eliminar");
//            delete(Integer.parseInt(var_id));
//            Intent intent = new Intent(PedidoActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
//    });
}

    public void add(Pedido p) {
        Call<Pedido> call = servicio.registroPedido(p);
        call.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if (response.isSuccessful()) {
                    mensaje("Registro exitoso");
                }
            }
         @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

//    public void update(Pedido p) {
//        Call<Pedido> call = servicio.actualizaRol(p);
//        call.enqueue(new Callback<Pedido>() {
//            @Override
//            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
//                if (response.isSuccessful()) {
//                    mensaje("Actualización exitosa");
//                }
//            }
//         @Override
//            public void onFailure(Call<Pedido> call, Throwable t) {
//                 Log.e("ERROR: ", t.getMessage());
//            }
//        });
//    }
//
//    public void delete(int id) {
//        Call<Pedido> call = servicio.eliminaRol(id);
//        call.enqueue(new Callback<Pedido>() {
//            @Override
//            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
//                if (response.isSuccessful()) {
//                    mensaje("Eliminación exitosa");
//                }
//            }
//            @Override
//            public void onFailure(Call<Pedido> call, Throwable t) {
//                Log.e("ERROR: ", t.getMessage());
//            }
//        });
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void mensaje(String msg) {
        Toast toast1 = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast1.show();
    }

    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }

}
