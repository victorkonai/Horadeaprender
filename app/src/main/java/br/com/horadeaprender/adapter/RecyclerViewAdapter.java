package br.com.horadeaprender.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.horadeaprender.R;
import br.com.horadeaprender.model.Simulado;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Simulado> opcoesSimulado;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<Simulado> opcoesSimulado) {
        this.opcoesSimulado = opcoesSimulado;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        Log.d(TAG, "onBindViewHolder: called");

        viewHolder.textViewNome.setText(opcoesSimulado.get(i).getNome());
        viewHolder.textViewDescricao.setText(opcoesSimulado.get(i).getDescricao());
    }

    @Override
    public int getItemCount() {
        return opcoesSimulado.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNome;
        TextView textViewDescricao;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewDescricao =  itemView.findViewById(R.id.textViewDescricao);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }

}
