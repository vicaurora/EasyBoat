package com.example.easyboat;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.example.easyboat.model.Annonce;
import com.example.easyboat.model.ExpandableListAdapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class MainActivity extends AppCompatActivity {




    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    public List<String> listDataHeader;
    public HashMap<String, List<String>> listDataChild;
    private String[] listeAnnonceString;
    public TextView resultat;
    private Button supprimer;
    private Button depose;
    private Button rafraichir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequeteGET();
        depose = findViewById(R.id.deposer);
rafraichir= findViewById(R.id.refresh);
        supprimer = findViewById(R.id.supprimer);


       resultat=findViewById(R.id.resultat);

        depose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent DeposerAnnonce = new Intent(MainActivity.this, DeposerAnnonce.class);
                startActivity(DeposerAnnonce);
            }
        });
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent SupprimerAnnonce = new Intent(MainActivity.this, SupprimerAnnonce.class);
                startActivity(SupprimerAnnonce);
            }
        });
        rafraichir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              RequeteGET();
            }
        });

        resultat.setText("Liste des annonces trouvées : ");


    }


    public void RequeteGET() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request myGetRequest = new Request.Builder()
                .url(getResources().getString(R.string.adresse_ip))
                .build();

        okHttpClient.newCall(myGetRequest).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {


            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //le retour est effectué dans un thread différent
                final String text = response.body().string();
                listeAnnonceString = text.split("---");
                final int statusCode = response.code();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        expListView = (ExpandableListView) findViewById(R.id.lvExp);

                        // preparing list data
                        listDataHeader = new ArrayList<String>();
                        listDataChild = new HashMap<String, List<String>>();
                        Annonce annonce;
                        int nbannonce =0;

                        try {

                            for (int i = 0; i < listeAnnonceString.length; i++) {
                                Gson listeAnnonceGson = new Gson();
                                annonce = listeAnnonceGson.fromJson(listeAnnonceString[i], Annonce.class);

                                    listDataHeader.add(annonce.getResume());
                                    List<String> details = new ArrayList<String>();
                                    details.add(annonce.getNom());
                                    details.add(annonce.getNbPlace());
                                    details.add(annonce.getMontage());
                                    details.add(annonce.getDate());
                                    details.add(annonce.getAnnee());
                                    details.add(annonce.getDescription());
                                    details.add(annonce.getClubnom());
                                    details.add(annonce.getClubtel());
                                    details.add(annonce.getClubmail());
                                    nbannonce++;

                                    listDataChild.put(listDataHeader.get(i), details);


                            }

                        }
                        catch (java.lang.NullPointerException e){
                            resultat.setText("Aucune annonce trouvée");
                        }
                        if (nbannonce==0) {

                            listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);
                            expListView.setAdapter(listAdapter);

                            resultat.setText("Aucune annonce trouvée");
                            expListView.deferNotifyDataSetChanged();

                        }
                        else {
                            resultat.setText("Liste des annonces trouvées :");

                            listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);

                            // setting list adapter
                            expListView.setAdapter(listAdapter);
                        }
                        Context context = getApplicationContext();
                        CharSequence message = "Liste des annonces mise à jour : " + Integer.toString(nbannonce) + " annonces trouvées";
                        int time = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, message,time);
                        toast.show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.preferences){
            Intent Preferences = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(Preferences);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    }



