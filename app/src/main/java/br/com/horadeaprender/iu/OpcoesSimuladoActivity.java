package br.com.horadeaprender.iu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import br.com.horadeaprender.R;
import br.com.horadeaprender.adapter.RecyclerViewAdapter;
import br.com.horadeaprender.model.Simulado;

public class OpcoesSimuladoActivity extends AppCompatActivity implements RecyclerViewAdapter.OnReceive{

    private static String TAG = "OpcoesSimuladoActivity";

    private ViewHolder vh;
    private ArrayList<Simulado> opcoesSimulados;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes_simulado);

        vh = new ViewHolder();
        opcoesSimulados = new ArrayList<>();

        getOpcoesSimulado();
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

    private void getOpcoesSimulado(){

        db.collection("simulados")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                    Simulado s = document.toObject(Simulado.class);
                                    opcoesSimulados.add(s);
                                }
                            carregarDadosRecyclerView();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void carregarDadosRecyclerView(){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, opcoesSimulados, this);
        vh.recyclerView.setAdapter(adapter);
        vh.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void abrirTelaSimulado(){

        Intent intent = new Intent(this, SimuladoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onReceiveItemList(Simulado simulado) {
        abrirTelaSimulado();
    }

    public class ViewHolder {

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSimulados);

        public ViewHolder() {
            supportNaviagteUp();
        }
        private void supportNaviagteUp() {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}
