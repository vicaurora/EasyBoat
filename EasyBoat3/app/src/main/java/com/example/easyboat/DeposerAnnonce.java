package com.example.easyboat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class DeposerAnnonce extends AppCompatActivity {
    public TextView resultat;
    private EditText resume;
    private EditText nom;
    private EditText nbplace;
    private EditText montage;
    private EditText date;
    private EditText annee;
    private EditText description;
    private EditText clubnom;
    private EditText clubmail;
    private EditText clubtel;
    private EditText id;
    private Button deposeFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposer_annonce);

        resume = findViewById(R.id.resume_content);
        nom = findViewById(R.id.nom_content);
        nbplace = findViewById(R.id.nbPlace_content);
        montage = findViewById(R.id.montage_content);
        date = findViewById(R.id.date_content);
        annee = findViewById(R.id.annee_content);
        description = findViewById(R.id.description_content);
        clubmail = findViewById(R.id.clubmail_content);
        clubnom = findViewById(R.id.clubnom_content);
        clubtel = findViewById(R.id.clubtel_content);
        id = findViewById(R.id.password_content);
        deposeFinal = findViewById(R.id.depose);
        deposeFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> infos = new ArrayList<String>();

                infos.add(resume.getText().toString());
                infos.add(nom.getText().toString());
                infos.add(nbplace.getText().toString());
                infos.add(montage.getText().toString());
                infos.add(date.getText().toString());
                infos.add(annee.getText().toString());
                infos.add(description.getText().toString());
                infos.add(clubnom.getText().toString());
                infos.add(clubmail.getText().toString());
                infos.add(clubtel.getText().toString());
                infos.add(id.getText().toString());
                RequetePOST(infos);

            }
        });

    }


    public void RequetePOST(List<String> details) {

        OkHttpClient okHttpClient = new OkHttpClient();


        //Création de la requête POST


        RequestBody formBody = new FormBody.Builder()
                .add("resume", details.get(0))
                .add("nom", details.get(1))
                .add("nbplace", details.get(2))
                .add("montage", details.get(3))
                .add("date", details.get(4))
                .add("annee", details.get(5))
                .add("description", details.get(6))
                .add("clubnom", details.get(7))
                .add("clubmail", details.get(8))
                .add("clubtel", details.get(9))
                .add("id", details.get(10))
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
                        CharSequence message = "Votre annonce a bien été déposée !";
                        int time = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, message, time);
                        toast.show();

                    }
                });
            }
        });

    }
}