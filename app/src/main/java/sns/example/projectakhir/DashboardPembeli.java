package sns.example.projectakhir;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import sns.example.projectakhir.Adapter.DashboardPembeliAdapter;
import sns.example.projectakhir.Model.Barang;

public class DashboardPembeli extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_pembeli);
        List<Barang> barang = new ArrayList<>();
        barang.add(new Barang("Pancake", "12000"));

        DashboardPembeliAdapter dbAdapter = new DashboardPembeliAdapter(this, barang);

        // ambil RecycleView dari layout
        RecyclerView rvDashboardPembeli = this.findViewById(R.id.rvDashboardPembeli);

        // set layout manager
        rvDashboardPembeli.setLayoutManager(new LinearLayoutManager(this));

        // sambungkan Adapter ke RecyclerView
        rvDashboardPembeli.setAdapter(dbAdapter);

        // ambil objek EditText filter dari layout
//        EditText editTextFilter = this.findViewById(R.id.editTextFilter);
//        editTextFilter.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                cAdapter.getFilter().filter(editable.toString());
//            }
//        });

    }
}