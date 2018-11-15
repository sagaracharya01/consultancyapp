package com.example.pc.consultancyguider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankViewHolder>{
    private Context mCtx;
    private List<RankModel> ranklist;

    public RankAdapter(Context mCtx, List<RankModel> ranklist) {
        this.mCtx = mCtx;
        this.ranklist = ranklist;
    }

    @NonNull
    @Override
    public RankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_rank,null);
        RankAdapter.RankViewHolder holder=new RankAdapter.RankViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RankViewHolder holder, int position) {
        RankModel model=ranklist.get(position);

        holder.cname.setText(model.getCname());
        holder.point.setText(model.getPoint());


    }

    @Override
    public int getItemCount() {
        return ranklist.size();
    }


    class RankViewHolder extends RecyclerView.ViewHolder{
        TextView cname,point;

        public RankViewHolder(View itemView) {
            super(itemView);
            cname=itemView.findViewById(R.id.txtcname);
            point=itemView.findViewById(R.id.rateid);


        }
    }
}
