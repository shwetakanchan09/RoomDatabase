package com.shweta.roomdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PojoAdapter extends RecyclerView.Adapter<PojoAdapter.ViewHolder> {
    Context context;
    List<PojoModel> pojoModelList;
    AdapterListener adapterListener;

    public PojoAdapter(Context context, AdapterListener adapterListener) {
        this.context = context;
        this.adapterListener = adapterListener;
        pojoModelList = new ArrayList<>();
    }

    public void addUser(PojoModel pojoModel){
        pojoModelList.add(pojoModel);
        notifyDataSetChanged();
    }
    public void clearData(){
        pojoModelList.clear();
        notifyDataSetChanged();
    }
    public void removeUser(int position){
        pojoModelList.remove(position);
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public PojoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PojoAdapter.ViewHolder holder, int position) {
        PojoModel pojoModel = pojoModelList.get(position);
        holder.name.setText(pojoModel.getName());
        holder.email.setText(pojoModel.getEmail());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onUpdate(pojoModelList.get(position));
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onDelete(pojoModelList.get(position).getId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pojoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,email;
        ImageView edit,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            email = itemView.findViewById(R.id.txtEmail);
            edit = itemView.findViewById(R.id.imgEdit);
            delete = itemView.findViewById(R.id.imgDelete);
        }
    }
}