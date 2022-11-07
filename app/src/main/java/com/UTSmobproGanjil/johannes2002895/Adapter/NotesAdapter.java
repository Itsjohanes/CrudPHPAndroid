package com.UTSmobproGanjil.johannes2002895.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.UTSmobproGanjil.johannes2002895.Activity.EditActivity;
import com.UTSmobproGanjil.johannes2002895.Model.Notes;
import com.UTSmobproGanjil.johannes2002895.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder>{
    List<Notes> mNotesList;

    public NotesAdapter(List<Notes> notesList) {
        mNotesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.mTextViewName.setText(mNotesList.get(position).getNama());
        holder.mTextViewDescription.setText(mNotesList.get(position).getDeskripsi());
        holder.mTextViewKategori.setText(mNotesList.get(position).getKategori());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kirim ke EditActivity
                    Intent mIntent = new Intent(view.getContext(), EditActivity.class);
                    mIntent.putExtra("id", mNotesList.get(position).getId());
                    mIntent.putExtra("nama", mNotesList.get(position).getNama());
                    mIntent.putExtra("deskripsi", mNotesList.get(position).getDeskripsi());
                    mIntent.putExtra("kategori", mNotesList.get(position).getKategori());
                    mIntent.putExtra("ukuran", mNotesList.get(position).getUkuran());

                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return mNotesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewName, mTextViewDescription, mTextViewKategori;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_nama);
            mTextViewDescription = (TextView) itemView.findViewById(R.id.tv_item_detail);
            mTextViewKategori = (TextView) itemView.findViewById(R.id.tv_kategori);
        }
    }
}
