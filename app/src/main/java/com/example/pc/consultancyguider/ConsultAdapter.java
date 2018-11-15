package com.example.pc.consultancyguider;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ConsultAdapter extends RecyclerView.Adapter<ConsultAdapter.ConsultViewHolder>{
    private Context mCtx;
    private List<ConsultModel> consultlist;

    public ConsultAdapter(Context mCtx, List<ConsultModel> consultlist) {
        this.mCtx = mCtx;
        this.consultlist = consultlist;
    }

    @NonNull
    @Override
    public ConsultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_consult,null);
        ConsultViewHolder holder=new ConsultViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultViewHolder holder, int position) {
        ConsultModel model=consultlist.get(position);

        holder.name.setText(model.getName());
        holder.subject.setText(model.getSubject());
        holder.desc.setText(model.getDesc());


    }

    @Override
    public int getItemCount() {
        return consultlist.size();
    }

    class ConsultViewHolder extends RecyclerView.ViewHolder{
        TextView name,subject,desc;

        public ConsultViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.txttitle);
            subject=itemView.findViewById(R.id.txtsubject);
            desc=itemView.findViewById(R.id.txtdesc);

        }
    }



}
