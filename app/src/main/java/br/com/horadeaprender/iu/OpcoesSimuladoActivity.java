package br.com.horadeaprender.iu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.horadeaprender.R;

public class OpcoesSimuladoActivity extends AppCompatActivity {

    private ViewHolder vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes_simulado);

        vh = new ViewHolder();
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
