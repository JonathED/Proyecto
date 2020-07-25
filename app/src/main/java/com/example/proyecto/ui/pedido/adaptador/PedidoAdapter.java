package com.example.proyecto.ui.pedido.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyecto.ui.pedido.PedidoActivity;
import com.example.proyecto.R;
import com.example.proyecto.ui.pedido.adaptador.PedidoAdapter;
import com.example.proyecto.ui.pedido.entidad.Pedido;

import java.util.List;

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    private Context context;
    private List<Pedido> pedido;

    public PedidoAdapter(Context context, int resource, List<Pedido> pedido) {
        super(context, resource, pedido);
        this.context = context;
        this.pedido = pedido;
    }

    @Override
    public View getView(final int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list, parent, false);

        TextView txtProId = (TextView) rowView.findViewById(R.id.txtProListId);
        TextView txtProFechaRegistro = (TextView) rowView.findViewById(R.id.txtProListFechaRegistro);
        TextView txtProFechaEntrega = (TextView) rowView.findViewById(R.id.txtProListFechaEntrega);


        txtProId.setText(String.format("#ID: %d", pedido.get(pos).getIdPedido()));
        txtProFechaRegistro.setText(String.format("FECHA REGISTRO: %s", pedido.get(pos).getFechaRegistro()));
        txtProFechaEntrega.setText(String.format("FECHA ENTREGA: %s", pedido.get(pos).getFechaEntrega()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PedidoActivity.class);
                intent.putExtra("var_id", String.valueOf(pedido.get(pos).getIdPedido()));
                intent.putExtra("var_fechaRegistro", pedido.get(pos).getFechaRegistro());
                intent.putExtra("var_fechaEntrega", pedido.get(pos).getFechaEntrega());
                intent.putExtra("var_lugarEntrega", pedido.get(pos).getLugarEntrega());
                intent.putExtra("var_estado", pedido.get(pos).getEstado());
              //  intent.putExtra("var_ubigeo", pedido.get(pos).getUbigeo().getIdUbigeo());
                intent.putExtra("var_cliente", pedido.get(pos).getCliente().getIdCliente());
                intent.putExtra("var_usuario", pedido.get(pos).getUsuario().getIdUsuario());
                intent.putExtra("var_metodo", "VER");
                context.startActivity(intent);

            }
        });
        return rowView;
    }
}
