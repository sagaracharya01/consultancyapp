package com.example.pc.consultancyguider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class KingsAdapter extends RecyclerView.Adapter<KingsAdapter.KingsViewHolder>{
    private Context mCtx;
    private List<KingsModel>kingslist;

    public KingsAdapter(Context mCtx, List<KingsModel> kingslist) {
        this.mCtx = mCtx;
        this.kingslist = kingslist;
    }

    @NonNull
    @Override
    public KingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_comment,null);
        KingsAdapter.KingsViewHolder holder=new KingsAdapter.KingsViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull KingsViewHolder holder, int position) {
       KingsModel model=kingslist.get(position);
        holder.subject.setText(model.getSubject());
        holder.desc.setText(model.getDesc());

    }

    @Override
    public int getItemCount() {
        return kingslist.size();
    }


    class KingsViewHolder extends RecyclerView.ViewHolder{
        TextView subject,desc;

        public KingsViewHolder(View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.txtsubject);
            desc=itemView.findViewById(R.id.txtdesc);

        }
    }
}
