package sns.example.projectakhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sns.example.projectakhir.Model.Barang;

public class TambahBarang extends AppCompatActivity implements View.OnClickListener{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference barangs;
    EditText tvNama, tvHarga;
    Button btnSave;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_barang);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance("https://logistics-123-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        barangs = this.databaseReference.child("barangs");
        tvNama = findViewById(R.id.tambah_tvNama);
        tvHarga = findViewById(R.id.tambah_tvHarga);
        btnSave = findViewById(R.id.tambah_btnSave);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (btnSave.getId() == view.getId()) {
//            Toast.makeText(this, "pip", Toast.LENGTH_SHORT).show();
            String nama = tvNama.getText().toString();
            String harga = tvHarga.getText().toString();
            Barang b = new Barang(nama, harga);
            databaseReference.child("barangs").child(mAuth.getUid()).push().setValue(b).addOnSuccessListener(TambahBarang.this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Intent intent = new Intent(TambahBarang.this, DashboardPenjual.class);
//                    intent.putExtra("barangs", b);
                    startActivity(intent);
                    Toast.makeText(TambahBarang.this, "Data added", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(TambahBarang.this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(TambahBarang.this, "Failed to Add data", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
