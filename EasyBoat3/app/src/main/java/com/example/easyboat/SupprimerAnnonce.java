package com.example.easyboat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SupprimerAnnonce extends AppCompatActivity {

    private EditText titre;
    private EditText password;
    private Button supprime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_annonce);

        titre = findViewById(R.id.resume_supp_content);
        password = findViewById(R.id.password_supp_content);
        supprime = findViewById(R.id.supprime);

        supprime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> infos = new ArrayList<String>();

                infos.add(titre.getText().toString());
                infos.add(password.getText().toString());
                RequeteDELETE(infos);

            }        });

    }

    public void RequeteDELETE(List<String> details) {
        OkHttpClient okHttpClient = new OkHttpClient();



        //Création de la requête POST


        RequestBody formBody = new FormBody.Builder()
                .add("resume", details.get(0))
                .add("id", details.get(1))
                .build();
        Request request = new Request.Builder()
                .url(getResources().getString(R.string.adresse_ip))
                .post(formBody)
                .build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //le retour est effectué dans un thread différent
                final String text = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Context context = getApplicationContext();
                        CharSequence message = "Votre annonce : \"" +titre.getText().toString() + "\" a bien été supprimée !";
                        int time = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, message,time);
                        toast.show();

                    }
                });
            }
        });
}}
