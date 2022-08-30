package org.bigproject.dtskas;


import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<SurfaceControl.Transaction> items;
    private OnItemTransactionListener listener;
    TextView descriptionText;
    TextView amountText;

    public interface OnItemTransactionListener {
        void onTransactionClicked(int index, SurfaceControl.Transaction item);
    }

    public TransactionAdapter(List<SurfaceControl.Transaction> items, OnItemTransactionListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        descriptionText = itemView.findViewById(R.id.text_description);
        amountText = itemView.findViewById(R.id.text_amount);
    }

    public void bind(final int index, final SurfaceControl.Transaction item) {
        descriptionText.setText(item.getDescription());
        amountText.setText(String.valueOf(item.getAmount()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTransactionClicked(index, item);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SurfaceControl.Transaction item = items.get(position);
        holder.bind(position, item);
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }



}


