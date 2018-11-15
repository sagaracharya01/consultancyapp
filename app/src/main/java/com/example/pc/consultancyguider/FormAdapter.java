package com.example.pc.consultancyguider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.FormViewHolder>{
    private Context mCtx;
    private List<FormModel> formlist;

    public FormAdapter(Context mCtx, List<FormModel> formlist) {
        this.mCtx = mCtx;
        this.formlist = formlist;
    }

    @NonNull
    @Override
    public FormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)  {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_form,null);
        FormAdapter.FormViewHolder holder=new FormAdapter.FormViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FormViewHolder holder, int position) {
        FormModel model=formlist.get(position);

        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getPhone());
        holder.gender.setText(model.getGender());
        holder.education.setText(model.getEducation());
        holder.consultancy.setText(model.getConsultancy());


    }

    @Override
    public int getItemCount() {
        return formlist.size();
    }


    class FormViewHolder extends RecyclerView.ViewHolder{
        TextView name,address,email,phone,gender,education,consultancy;

        public FormViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.edtname);
            address=itemView.findViewById(R.id.edtaddress);
            email=itemView.findViewById(R.id.edtemail);
            phone=itemView.findViewById(R.id.edtphone);
            gender=itemView.findViewById(R.id.edtgender);
            education=itemView.findViewById(R.id.edteducation);
            consultancy=itemView.findViewById(R.id.edtconsult);

        }
    }
}
