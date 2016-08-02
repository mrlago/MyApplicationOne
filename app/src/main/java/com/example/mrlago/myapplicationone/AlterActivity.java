package com.example.mrlago.myapplicationone;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlterActivity extends AppCompatActivity {

    EditText nome;
    EditText telefone;
    Button alterar;
    Button deletar;
    Cursor cursor;
    BaseController crud;
    String codigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BaseController(getBaseContext());

        nome = (EditText)findViewById(R.id.nomeAltera);
        telefone = (EditText)findViewById(R.id.telefoneAltera);

        alterar = (Button)findViewById(R.id.buttonAlterar);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUtil.NOME)));
        telefone.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUtil.TELEFONE)));

        deletar = (Button)findViewById(R.id.buttonDeletar);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void alteraDados(View view){
        crud.alteraRegistro(Integer.parseInt(codigo), nome.getText().toString(),telefone.getText().toString());
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
        finish();
    }


}
