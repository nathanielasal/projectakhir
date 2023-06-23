package sns.example.projectakhir;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sns.example.projectakhir.Adapter.DashboardPenjualAdapter;
import sns.example.projectakhir.Model.Barang;

public class DashboardPenjual extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DashboardPenjualAdapter adapter;
    Button btnAddBarang;
    ArrayList<Barang> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_penjual);

        RecyclerView rvBarang = findViewById(R.id.rvDashboardPenjual);
        btnAddBarang = findViewById(R.id.btnAddBarang);
        btnAddBarang.setOnClickListener(this);

        dataList = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://logistics-123-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();

        rvBarang.setHasFixedSize(true);
        adapter = new DashboardPenjualAdapter(DashboardPenjual.this, dataList);
        rvBarang.setAdapter(adapter);
        rvBarang.setLayoutManager(new LinearLayoutManager(this));

        databaseReference.child("barangs").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Barang b = dataSnapshot.getValue(Barang.class);
                    b.setId(dataSnapshot.getKey());
                    dataList.add(b);
                }

                adapter = new DashboardPenjualAdapter(DashboardPenjual.this, dataList);
                rvBarang.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Toast.makeText(DashboardPenjual.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(btnAddBarang.getId()==view.getId()){
        Intent intent = new Intent(DashboardPenjual.this, TambahBarang.class);
        startActivity(intent);
        }
    }
}
