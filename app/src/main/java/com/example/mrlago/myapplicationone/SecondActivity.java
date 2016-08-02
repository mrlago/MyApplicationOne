package com.example.mrlago.myapplicationone;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /*Intent dados = getIntent();

        TextView nome2 = (TextView) findViewById(R.id.nome2);
        TextView senha2 = (TextView) findViewById(R.id.senha2);
        nome2.setText(dados.getExtras().getString("nome"));
        senha2.setText(dados.getExtras().getString("senha"));
        System.out.println("Nome: " + dados.getExtras().getString("nome"));
        System.out.println("Senha: " + senha2.getText());*/

        BaseController crud = new BaseController(getBaseContext());
        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {DatabaseUtil.TELEFONE, DatabaseUtil.NOME};
        int[] idViews = new int[] {R.id.telefone, R.id.nome};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.activity_second,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUtil.ID));
                Intent intent = new Intent(SecondActivity.this, AlterActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }

    public void returnMainView(View view){
        Intent newView = new Intent(this, MainActivity.class);
        startActivity(newView);
    }
}
