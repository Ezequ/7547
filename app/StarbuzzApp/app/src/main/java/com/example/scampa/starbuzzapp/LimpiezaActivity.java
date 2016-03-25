package com.example.scampa.starbuzzapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.scampa.starbuzzapp.pojo.Cliente;
import com.example.scampa.starbuzzapp.services.ClienteService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class LimpiezaActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listClientesView = getListView();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ClienteService cs = new ClienteService();

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Cliente>> call = cs.clienteByVendIdService.clienteByVendIdService(1);

        // Fetch and print a list of the contributors to the library.
        List<Cliente> listClientes = new ArrayList<Cliente>();
        try {
            listClientes = call.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }


        ArrayAdapter<Cliente> listAdapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1,
                listClientes);
        listClientesView.setAdapter(listAdapter);
    }

}
