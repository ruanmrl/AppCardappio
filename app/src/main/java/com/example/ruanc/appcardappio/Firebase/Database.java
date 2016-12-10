package com.example.ruanc.appcardappio.Firebase;

import android.util.Log;
import android.view.View;

import com.example.ruanc.appcardappio.Classes.Comida;
import com.example.ruanc.appcardappio.Classes.Dados;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by ruanc on 08/12/2016.
 */

public class Database {


    Dados d;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference restauranteRef;

    static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    static String Usuario ;

    public Database(FirebaseDatabase db){
        firebaseDatabase = db;

    }

    public static void addLanche(Comida c){
        String nome = c.getNome();
        String categoria = c.getCategoria();
        restauranteRef.child("Cardapio").child(categoria).child(nome).setValue(c);
    }

    public void setaRestaurante(){
        Usuario = user.getEmail();
        Usuario = Usuario.substring(0,Usuario.lastIndexOf("@"));
        String ref = "Restaurantes/" +Usuario;
        restauranteRef = firebaseDatabase.getReference(ref);
    }

    public void setaNome(String Nome){


    }
    public void setaDescricao(String Descricao){

    }
    public void setaEndereco(String Endereco){

    }
    public void setaTelefone(String Telefone){

    }

}
