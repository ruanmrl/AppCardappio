package com.example.ruanc.appcardappio.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ruanc.appcardappio.Classes.Comida;
import com.example.ruanc.appcardappio.Classes.Pedido;
import com.example.ruanc.appcardappio.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by ruanc on 05/12/2016.
 */

public class Pedidos extends Fragment {



    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef = database.getReference("Restaurantes/a/Pedidos");
    private ArrayList<String> listaPedidosNome = new ArrayList<>();
    private ArrayList<Pedido> listaPedidos   = new ArrayList<>();

    private ListView lvPedidos;


    public Pedidos(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pedidos, container, false);



        lvPedidos = (ListView) rootView.findViewById(R.id.lvPedidos);

        final  ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,listaPedidosNome);

        lvPedidos.setAdapter(listViewAdapter);


        lvPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                Pedido p =listaPedidos.get(posicao);
                String dadosPedido = "Comida = "+  p.getComida()+"\n Mesa = " + p.getMesa() +"\nAnotacao = "+ p.getAnotacao();
                Toast.makeText(getActivity(), "Pedido: " + dadosPedido,Toast.LENGTH_SHORT ).show();
            }
        });


        lvPedidos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long l) {


                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.dialog_pedido, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());


                alertDialogBuilder.setView(promptsView);


                alertDialogBuilder.setCancelable(false).setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return true;
            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //String Value = dataSnapshot.getValue(String.class);
                Pedido p = dataSnapshot.getValue(Pedido.class);
                String Value = p.getComida();
                listaPedidos.add(p);
                listaPedidosNome.add("Comida:" +p.getComida() + "\nMesa:"+p.getMesa());
                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                listaPedidos.clear();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return rootView;
    }


}