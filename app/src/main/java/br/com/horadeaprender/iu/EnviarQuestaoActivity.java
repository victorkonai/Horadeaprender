package br.com.horadeaprender.iu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.horadeaprender.R;
import br.com.horadeaprender.model.Questao;

public class EnviarQuestaoActivity extends AppCompatActivity {

    public String TAG = "EnviarQuestaoActivity";

    private ViewHolder vh;
    private Questao questao;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_questao);

        vh = new ViewHolder();
        questao = new Questao();

        db = FirebaseFirestore.getInstance();

        carregarDados();


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

    private void carregarDados(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opcoes_simulado_aray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        vh.spinnerOpSimulado.setAdapter(adapter);
    }


    public class ViewHolder {

        Spinner spinnerOpSimulado = findViewById(R.id.spinner_tipo_simulado);

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
                    questao.setTipoSimulado(spinnerOpSimulado.getSelectedItem().toString());
                    questao.setEnuciado(edtPergunta.getText().toString());
                    questao.setAlternativaCorreta(edtCorreta.getText().toString());
                    textViewPreview.setText(questao.toString());
                }
            });

            buttonEnviar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    questao.setTipoSimulado(spinnerOpSimulado.getSelectedItem().toString());
                    questao.setEnuciado(edtPergunta.getText().toString());
                    questao.setAlternativaCorreta(edtCorreta.getText().toString());

                    db.collection("simulados").document(questao.getTipoSimulado()).collection("perguntas")
                            .add(questao)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());

                                    finish();
                                    telaSucesso();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);

                                    Toast.makeText(getApplicationContext(), "Erro ao enviar questão", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }
                            });
                }
            });
        }



        private void supportNaviagteUp() {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }
    private void telaSucesso(){
        Intent intent = new Intent(this, SucessoEnviaQuestaoActivity.class);
        startActivity(intent);
    }
}
