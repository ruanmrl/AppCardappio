package com.example.ruanc.appcardappio;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruanc.appcardappio.Classes.Comida;
import com.example.ruanc.appcardappio.Firebase.Database;
import com.example.ruanc.appcardappio.Fragments.Cardapio;
import com.example.ruanc.appcardappio.Fragments.Pedidos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {

    final Context context = this;
    private Database dbRestaurante;

    FloatingActionButton fab_add, fab_config, fab;
    TextView addComida, MudaDados;
    Animation FabOpen,FabClose, FAbRClockWise,FabRantclockwise;
    boolean isOpen =false;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dbRestaurante = new Database(database);

        addComida = (TextView) findViewById(R.id.addComida);
        MudaDados = (TextView) findViewById(R.id.MudaDados);

        fab_config = (FloatingActionButton) findViewById(R.id.fab_config);
        fab_add = (FloatingActionButton) findViewById(R.id.fab_add);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FAbRClockWise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRantclockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlock);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        dbRestaurante.setaRestaurante();



        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    fab_add.startAnimation(FabClose);
                    fab_config.startAnimation(FabClose);
                    fab.startAnimation(FabRantclockwise);
                    fab_add.startAnimation(FabClose);
                    fab_add.startAnimation(FabClose);
                    fab_add.setClickable(false);
                    fab_config.setClickable(false);
                    MudaDados.startAnimation(FabClose);
                    addComida.startAnimation(FabClose);

                    isOpen = false;
                }
                else{
                    fab_add.startAnimation(FabOpen);
                    fab_config.startAnimation(FabOpen);
                    fab.startAnimation(FAbRClockWise);
                    MudaDados.startAnimation(FabOpen);
                    addComida.startAnimation(FabOpen);

                    fab_add.startAnimation(FabOpen);
                    fab_add.startAnimation(FabOpen);
                    fab_add.setClickable(true);
                    fab_config.setClickable(true);
                    isOpen = true;
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(Home.this,"Logged Out" ,Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Pedidos pedidos = new Pedidos();
                    return pedidos;
                case 1:
                    Cardapio cardapio = new Cardapio();
                    return cardapio;
                default:
                    return null;

            }

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Pedidos";
                case 1:
                    return "Cardapio";
            }
            return null;
        }
    }

    public void insere(View v){

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_insere, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setView(promptsView);

        final EditText Nome = (EditText) promptsView.findViewById(R.id.newName);
        final EditText Desc=  (EditText) promptsView.findViewById(R.id.newDesc);
        final EditText Valor =(EditText) promptsView.findViewById(R.id.newValue);
        final EditText Cate=  (EditText) promptsView.findViewById(R.id.newCategoria);

        alertDialogBuilder
                .setCancelable(false).setPositiveButton("OK",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {

                String nome,descricao,categoria;
                Double valor;
                nome = String.valueOf(Nome.getText());
                descricao = String.valueOf(Desc.getText());
                categoria = String.valueOf(Cate.getText());
                valor = Double.valueOf(String.valueOf(Valor.getText()));
                Comida c = new Comida(  nome,  descricao,  valor,  categoria);


                if ( nome.matches("") || descricao.matches("") || valor.equals(0) || categoria.matches("")){
                    dialog.cancel();
                    Toast.makeText(Home.this," Possui Campos Vazio \n Preencha todos" ,Toast.LENGTH_SHORT).show();
                }
                else {

                    Database.addLanche(c);
                    Toast.makeText(Home.this, "Inserido", Toast.LENGTH_SHORT).show();
                }
            }
        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                                Toast.makeText(Home.this,"Cancelado" ,Toast.LENGTH_SHORT).show();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();


    }

    public void Configs(View v){
        Intent myIntent = new Intent(Home.this, MeusDados.class);
        startActivity(myIntent);

    }


}
