package com.example.sensmove;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sensmove.entities.Value;

import java.util.List;

public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.ValueViewHolder> {
    private final List<Value> values;
    public class ValueViewHolder extends RecyclerView.ViewHolder {
        public TextView Value;
        public ValueViewHolder(View itemView) {
            super(itemView);
            Value = itemView.findViewById(R.id.value);
        }
    }

    public ValueAdapter(List<Value> stations) {
        this.values = stations;
    }

    @NonNull
    @Override
    public ValueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.value_item, parent, false);
        return new ValueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ValueViewHolder holder, int position) {
        Value item = values.get(position);
        holder.Value.setText(Integer.toString(item.getValue()));
    }

    @Override
    public int getItemCount() {
        if(values == null)
            return 0;
        return values.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Value item);
    }
}
