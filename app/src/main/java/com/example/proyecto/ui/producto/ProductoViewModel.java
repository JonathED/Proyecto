package com.example.proyecto.ui.producto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProductoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("-->Producto");
    }

    public LiveData<String> getText() {
        return mText;
    }
}