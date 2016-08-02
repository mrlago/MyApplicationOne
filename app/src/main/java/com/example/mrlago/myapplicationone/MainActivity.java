package com.example.mrlago.myapplicationone;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void startNewView(View view){
        try {
            testConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent newView = new Intent(getApplicationContext(), SecondActivity.class);
        //newView.putExtra("nome",nome.getText().toString());
        //newView.putExtra("senha",senha.getText().toString());

        BaseController crud = new BaseController(getBaseContext());
        EditText nome = (EditText)findViewById(R.id.nome2);
        EditText telefone = (EditText)findViewById((R.id.telefone));
        String nomeString = nome.getText().toString();
        String telefoneString = telefone.getText().toString();
                String resultado;

        resultado = crud.insereDado(nomeString,telefoneString);
        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        startActivity(newView);
    }

    public void testConnection() throws IOException {
        NetworkUtil conn = new NetworkUtil();
        try {
            conn.sendGET();
            System.out.println("GET DONE");
            //conn.sendPOST();
           // System.out.println("POST DONE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
