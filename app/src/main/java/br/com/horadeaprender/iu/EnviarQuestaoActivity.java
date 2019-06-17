package br.com.horadeaprender.iu;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.horadeaprender.R;
import br.com.horadeaprender.model.Questao;

public class EnviarQuestaoActivity extends AppCompatActivity {

    private ViewHolder vh;
    private Questao questao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_questao);

        vh = new ViewHolder();
        questao = new Questao();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public class ViewHolder {

        EditText edtPergunta = findViewById(R.id.edt_pergunta);
        EditText edtAlternativa = findViewById(R.id.edt_alternativa);
        EditText edtCorreta = findViewById(R.id.edt_correta);
        EditText edtComentario = findViewById(R.id.edt_comentario);

        Button buttonAdicionarAlternativa = findViewById(R.id.buttonAdicionarAlternativa);
        Button getButtonAdicionarComentario = findViewById(R.id.buttonAdicionarComentario);
        Button buttonVerPreview = findViewById(R.id.buttonVerPreview);
        Button buttonEnviar = findViewById(R.id.buttonEnviar);

        TextView textViewPreview = findViewById(R.id.textView_preview);



        public ViewHolder() {

            supportNaviagteUp();

            buttonAdicionarAlternativa.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    questao.getAlternativas().add(edtAlternativa.getText().toString());
                    edtAlternativa.setText("");
                    Snackbar.make(v, "Alternativa adicionada!",
                            Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });

            getButtonAdicionarComentario.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    questao.getComentarios().add(edtComentario.getText().toString());
                    edtComentario.setText("");
                    Snackbar.make(v, "comentário adicionado!",
                            Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });

            buttonVerPreview.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    questao.setEnuciado(edtPergunta.getText().toString());
                    questao.setAlternativaCorreta(edtCorreta.getText().toString());
                    textViewPreview.setText(questao.toString());
                }
            });

            buttonEnviar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                }
            });


        }
        private void supportNaviagteUp() {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
