package com.example.praneeth.shoping_cart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart_Adapter extends RecyclerView.Adapter<CartViewHolder> {
    Context context;
    List<Phone> phones;
    public Cart_Adapter(Context context, List<Phone> list){
        this.context=context;
        phones=list;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.phone,parent,false );
        CartViewHolder cvh=new CartViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.model.setText(phones.get(position).getModel());
        final String phone=phones.get(position).getModel();
        holder.manufacturer.setText(phones.get(position).getManufacturer());
        holder.price.setText("Rs "+phones.get(position).getPrice());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               search(phone);
            }
        });
        Glide.with(context).load(Uri.parse(phones.get(position).getImage())).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return phones.size();
    }
    public void search(final String phone){
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);

        View view= LayoutInflater.from(context).inflate(R.layout.dialog, null);
        dialog.setView(view);

        final EditText username=view.findViewById(R.id.user);
        final EditText quantity=view.findViewById(R.id.quantity);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Api api=ApiClient.retrofitCall().create(Api.class);
                Call<Buy> call=api.getbuy(phone, username.getText().toString(), quantity.getText().toString());
                call.enqueue(new Callback<Buy>() {
                    @Override
                    public void onResponse(Call<Buy> call, Response<Buy> response) {
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Buy> call, Throwable t) {
                        Toast.makeText(context,t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setTitle("Details:");
        AlertDialog alert=dialog.create();

        alert.show();
    }

}
