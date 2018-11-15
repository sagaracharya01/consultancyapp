package com.example.pc.consultancyguider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ViewerAdapter extends RecyclerView.Adapter<ViewerAdapter.ViewerViewHolder>{
    private Context mCtx;
    private List<ViewerModel> viewerlist;

    RequestOptions option;

    public ViewerAdapter(Context mCtx, List<ViewerModel> viewerlist) {
        this.mCtx = mCtx;
        this.viewerlist = viewerlist;

        option=new RequestOptions().centerCrop().placeholder(R.drawable.backimage).error(R.drawable.backimage);
    }

    @NonNull
    @Override
    public ViewerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_view,parent,false);
        ViewerViewHolder holder=new ViewerViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewerViewHolder holder, int position) {
        ViewerModel model=viewerlist.get(position);

        holder.name.setText(model.getName());
        holder.subject.setText(model.getSubject());
        holder.desc.setText(model.getDesc());










     Glide.with(mCtx).load(model.getImg_url()).apply(option).into(holder.imgview);



    }

    @Override
    public int getItemCount() {
        return viewerlist.size();
    }

    class ViewerViewHolder extends RecyclerView.ViewHolder{
        TextView name,subject,desc;
       ImageView imgview;

        public ViewerViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.txttitle);
            subject=itemView.findViewById(R.id.txtsubject);
            desc=itemView.findViewById(R.id.txtdesc);
            imgview=itemView.findViewById(R.id.imageviewer);

        }
    }



}