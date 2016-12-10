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

public class Cardapio extends Fragment {

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef = database.getReference("Restaurantes/a/Cardapio/Cachorro");
    private  ArrayList<String> listaCardapioNomes  = new ArrayList<>();
    private  ArrayList<Comida> listaCardapio  = new ArrayList<>();

    private ListView lvCardapio;

    public Cardapio(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cardapio, container, false);




        lvCardapio = (ListView) rootView.findViewById(R.id.lvCardapio);

         final  ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
             getActivity(),
                 android.R.layout.simple_list_item_1,listaCardapioNomes);

        lvCardapio.setAdapter(listViewAdapter);


        lvCardapio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {

                Comida c =listaCardapio.get(posicao);
                String dadosComida = "Comida = "+  c.getNome()+"\n Descricao = " + c.getDescricao() +"\nValor = "+ c.getValor();
                Toast.makeText(getActivity(), "Pedido: " + dadosComida,Toast.LENGTH_LONG ).show();
            }
        });


        lvCardapio.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int posicao, long l) {

                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.dialog_comida_rmv, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder
                        .setCancelable(false).setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Comida c =listaCardapio.get(posicao);
                        myRef.child(c.getNome()).removeValue();
                    }
                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                        Toast.makeText( getActivity(),"Cancelado" ,Toast.LENGTH_SHORT).show();
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
                Comida c = dataSnapshot.getValue(Comida.class);
                listaCardapio.add(c);
                listaCardapioNomes.add("Nome:" +c.getNome() + "\nValor:"+c.getValor());
                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                listaCardapioNomes.clear();
                listaCardapio.clear();
                listViewAdapter.notifyDataSetChanged();
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
