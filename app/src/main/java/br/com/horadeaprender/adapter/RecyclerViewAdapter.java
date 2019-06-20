package br.com.horadeaprender.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
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

    private OnReceive onReceive;

    public RecyclerViewAdapter(Context mContext, ArrayList<Simulado> opcoesSimulado,  OnReceive onReceive) {
        this.opcoesSimulado = opcoesSimulado;
        this.mContext = mContext;
        this.onReceive = onReceive;
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

        viewHolder.simulado = opcoesSimulado.get(i);

        viewHolder.cardView.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReceive.onReceiveItemList(viewHolder.simulado);
            }
        });
    }

    @Override
    public int getItemCount() {
        return opcoesSimulado.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNome;
        TextView textViewDescricao;
        ConstraintLayout parentLayout;
        CardView cardView;

        Simulado simulado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewDescricao =  itemView.findViewById(R.id.textViewDescricao);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            cardView = itemView.findViewById(R.id.cardViewListSimulados);

            simulado = new Simulado();
        }
    }
    public interface OnReceive {

        void onReceiveItemList(Simulado simulado);

    }
}

