package com.UTSmobproGanjil.johannes2002895.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.UTSmobproGanjil.johannes2002895.Config;
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

public class EditActivity extends AppCompatActivity {

    private EditText edtNama, edtDeskripsi;
    private Spinner spnrKategori;
    private RadioGroup rgUkuran;
    private RadioButton rbReguler, rbLarge;
    String ID;
    Button btUpdate;

    ApiInterface mApiInterface;

    private final int ALERT_DIALOG_CLOSE = Config.ALERT_DIALOG_CLOSE;
    private final int ALERT_DIALOG_DELETE = Config.ALERT_DIALOG_DELETE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Identifikasi Komponen Action Bar
        String actionBarTitle;
        actionBarTitle = "Ubah";
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Identifikasi Komponen Form
        edtNama = findViewById(R.id.edt_nama);
        edtDeskripsi = findViewById(R.id.edt_deskripsi);
        spnrKategori = findViewById(R.id.spnr_kategori);
        rgUkuran = findViewById(R.id.rg_ukuran);
        rbLarge  = findViewById(R.id.rb_large);
        rbReguler = findViewById(R.id.rb_reguler);


        btUpdate = (Button) findViewById(R.id.btn_submit);

        // Identifikasi intent ke Komponen Form
        Intent mIntent = getIntent();
        //MEMASUKAN iDNYA
        ID = mIntent.getStringExtra("id");
        edtNama.setText(mIntent.getStringExtra("nama"));
        edtDeskripsi.setText(mIntent.getStringExtra("deskripsi"));
        String kategori = mIntent.getStringExtra("kategori");
        //switch untuk kategori
        switch (kategori) {
            case "Kopi Arabika":
                spnrKategori.setSelection(0);
                break;
            case "Kopi Robusta":
                spnrKategori.setSelection(1);
                break;
            case "Kopi Liberika":
                spnrKategori.setSelection(2);
                break;
            case "Kopi Ekselsa":
                spnrKategori.setSelection(3);
                break;
            default:
                spnrKategori.setSelection(0);
        }
        String ukuran = mIntent.getStringExtra("ukuran");
        //switch untuk ukuran
        if (ukuran.equals("Large")) {
            rbLarge.setChecked(true);
        }
        if (ukuran.equals("Reguler")) {
            rbReguler.setChecked(true);
        }
        // Definisi API
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Fungsi Tombol Update
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    // Update Data
    private void updateData() {
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
        Call<PostPutDelNotes> putHerosCall = mApiInterface.postUpdateNotes("update_coffeenotes", ID, RequestBody.create(MediaType.parse("text/plain"), edtNama.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), edtDeskripsi.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), spnrKategori.getSelectedItem().toString()), RequestBody.create(MediaType.parse("text/plain"), ukuran));
        putHerosCall.enqueue(new Callback<PostPutDelNotes>() {
            @Override
            public void onResponse(Call<PostPutDelNotes> call, Response<PostPutDelNotes> response) {
                MainActivity.ma.showData();
                finish();
            }

            @Override
            public void onFailure(Call<PostPutDelNotes> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Log.d("RETRO", "ON FAILURE : " + t.getCause());
                Toast.makeText(getApplicationContext(), "Error, update data", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
            case android.R.id.home:
                showAlertDialog(ALERT_DIALOG_CLOSE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);
    }

    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;

        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?";
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?";
            dialogTitle = "Hapus Notes";
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (isDialogClose) {
                            finish();
                        } else {
                            // Kode Hapus
                            if (ID.trim().isEmpty()==false){
                                Call<PostPutDelNotes> deleteHeros = mApiInterface.deleteNotes("delete_coffeenotes", ID);
                                deleteHeros.enqueue(new Callback<PostPutDelNotes>() {
                                    @Override
                                    public void onResponse(Call<PostPutDelNotes> call, Response<PostPutDelNotes> response) {
                                        MainActivity.ma.showData();
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<PostPutDelNotes> call, Throwable t) {
                                        Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                                        Log.d("RETRO", "ON FAILURE : " + t.getCause());
                                        Toast.makeText(getApplicationContext(), "Error, delete data", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }else{
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}