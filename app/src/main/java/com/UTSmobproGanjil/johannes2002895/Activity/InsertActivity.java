package com.UTSmobproGanjil.johannes2002895.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.UTSmobproGanjil.johannes2002895.Model.PostPutDelNotes;
import com.UTSmobproGanjil.johannes2002895.R;
import com.UTSmobproGanjil.johannes2002895.Rest.ApiClient;
import com.UTSmobproGanjil.johannes2002895.Rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {

    private EditText edtNama, edtDeskripsi;
    private Spinner spnrKategori;
    private RadioGroup rgUkuran;
    private RadioButton rbReguler, rbLarge;
    Button btSubmit;

    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        // Identifikasi Komponen Action Bar
        String actionBarTitle;
        actionBarTitle = "Tambah";
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Identifikasi Komponen Form
        edtNama = findViewById(R.id.edt_nama);
        edtDeskripsi = findViewById(R.id.edt_deskripsi);
        spnrKategori = findViewById(R.id.spnr_kategori);
        rgUkuran = findViewById(R.id.rg_ukuran);
        rbLarge  = findViewById(R.id.rb_large);
        rbReguler = findViewById(R.id.rb_reguler);
        btSubmit = (Button) findViewById(R.id.btn_submit);

        // Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Fungsi Tombol Simpan
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    // Simpan Data
    private void saveData(){
        String nama = edtNama.getText().toString();
        String deskripsi = edtDeskripsi.getText().toString();
        if (TextUtils.isEmpty(nama)) {
            edtNama.setError("Field can not be blank");
            return;
        }
        if (TextUtils.isEmpty(deskripsi)) {
            edtDeskripsi.setError("Field can not be blank");
            return;
        }


        String ukuran = "";
        Integer selectedId = rgUkuran.getCheckedRadioButtonId();
        if (selectedId == R.id.rb_large) {
            ukuran = "Large";
        } else if (selectedId == R.id.rb_reguler) {
            ukuran = "Reguler";

        }

        Call<PostPutDelNotes> postHerosCall = mApiInterface.postNotes("insert_coffeenotes", RequestBody.create(MediaType.parse("text/plain"), edtNama.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), edtDeskripsi.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), spnrKategori.getSelectedItem().toString()), RequestBody.create(MediaType.parse("text/plain"), ukuran));
        postHerosCall.enqueue(new Callback<PostPutDelNotes>() {
            @Override
            public void onResponse(Call<PostPutDelNotes> call, Response<PostPutDelNotes> response) {
                MainActivity.ma.showData();
                finish();
            }

            @Override
            public void onFailure(Call<PostPutDelNotes> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Log.d("RETRO", "ON FAILURE : " + t.getCause());
                Toast.makeText(getApplicationContext(), "Error, entry data", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Menu Kembali Ke Home
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}