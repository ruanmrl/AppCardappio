package com.example.ruanc.appcardappio;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruanc.appcardappio.Classes.Comida;
import com.example.ruanc.appcardappio.Classes.Dados;
import com.example.ruanc.appcardappio.Firebase.Database;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MeusDados extends AppCompatActivity {
    final Context context = this;


    TextView nome;
    TextView descricao;
    TextView telefone ;
    TextView endereco;

    Database database;


    final public static String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);


        nome =(TextView) findViewById(R.id.textNewName);
        descricao =(TextView) findViewById(R.id.textNewDescricao);
        telefone = (TextView) findViewById(R.id.textNewTelefone);
        endereco= (TextView) findViewById(R.id.textNewEndereco);


        String Nome_Atual;
        String Desc_Atual;
        String Tele_Atual;
        String Ende_Atual;
      //  Nome_Atual = database.DadosAtuais();

      //  nome.setText(Nome_Atual);
    }


    public void mudaNome(View v) {

       final TextView Temp = (TextView) findViewById(R.id.textNewName);

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_config, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);


        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
           //     Toast.makeText(MeusDados.this,userInput.getText() ,Toast.LENGTH_SHORT).show();
                Temp.setText(userInput.getText());
                String Name = userInput.getText().toString();
                database.setaNome(Name);
            }
        })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public void mudaDescricao(View v) {

        final TextView Temp = (TextView) findViewById(R.id.textNewDescricao);

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_config, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);


        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                //     Toast.makeText(MeusDados.this,userInput.getText() ,Toast.LENGTH_SHORT).show();
                Temp.setText(userInput.getText());
                String Descr = userInput.getText().toString();
                database.setaDescricao(Descr);
            }
        })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void mudaEndereco(View v) {

        final TextView Temp = (TextView) findViewById(R.id.textNewEndereco);

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_config, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);


        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                //     Toast.makeText(MeusDados.this,userInput.getText() ,Toast.LENGTH_SHORT).show();
                Temp.setText(userInput.getText());
                String Endereco = userInput.getText().toString();
                database.setaEndereco(Endereco);
            }
        })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    public void mudaTelefone(View v) {

        final TextView Temp = (TextView) findViewById(R.id.textNewTelefone);

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_config, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);


        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                //     Toast.makeText(MeusDados.this,userInput.getText() ,Toast.LENGTH_SHORT).show();
                Temp.setText(userInput.getText());
                String Telefone = userInput.getText().toString();
                database.setaTelefone(Telefone);
            }
        })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}