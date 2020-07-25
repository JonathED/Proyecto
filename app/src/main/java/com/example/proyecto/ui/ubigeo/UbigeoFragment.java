package com.example.proyecto.ui.ubigeo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyecto.R;
import com.example.proyecto.ui.pedido.entidad.Ubigeo;

public class UbigeoFragment extends Fragment {

    private UbigeoViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(UbigeoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ubigeo, container, false);
        final TextView textView = root.findViewById(R.id.text_ubigeo);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
}

}
