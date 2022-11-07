package com.UTSmobproGanjil.johannes2002895.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.UTSmobproGanjil.johannes2002895.Adapter.NotesAdapter;
import com.UTSmobproGanjil.johannes2002895.Model.GetNotes;
import com.UTSmobproGanjil.johannes2002895.Model.Notes;
import com.UTSmobproGanjil.johannes2002895.R;
import com.UTSmobproGanjil.johannes2002895.Rest.ApiClient;
import com.UTSmobproGanjil.johannes2002895.Rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String actionBarTitle;
        actionBarTitle = "Notes";
        getSupportActionBar().setTitle(actionBarTitle);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_notes);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;
        showData();

        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });
    }

    public void showData() {
        //memanggil function get notes
        Call<GetNotes> HerosCall = mApiInterface.getNotes("get_coffeenotes");
        HerosCall.enqueue(new Callback<GetNotes>() {
            @Override
            public void onResponse(Call<GetNotes> call, Response<GetNotes>
                    response) {
                List<Notes> notesList = response.body().getListDataNotes();
                Log.d("Retrofit Get", "Jumlah data Notes: " +
                        String.valueOf(notesList.size()));
                mAdapter = new NotesAdapter(notesList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetNotes> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}