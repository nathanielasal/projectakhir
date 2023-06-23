package sns.example.projectakhir.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import sns.example.projectakhir.DialogForm;
import sns.example.projectakhir.Model.Barang;
import sns.example.projectakhir.Model.Review;
import sns.example.projectakhir.R;

public class DashboardPenjualAdapter extends RecyclerView.Adapter<DashboardPenjualAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Barang> dataList;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    public DashboardPenjualAdapter(Context context, ArrayList<Barang> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DashboardPenjualAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_dashboard, parent, false);
        return new DashboardPenjualAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Barang data = dataList.get(position);
        holder.tvNama.setText(data.getNama());
        holder.tvHarga.setText(data.getHarga());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String noteId = data.getId();
                        if (noteId != null) {
                            databaseReference.child("barangs").child(firebaseAuth.getUid()).child(data.getId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setMessage("Apakah Anda yakin ingin menghapus keranjang " + data.getNama() + "?");
                builder.show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                DialogForm dialogForm = new DialogForm(
                        data.getId(),
                        data.getNama(),
                        data.getHarga()
                );
                dialogForm.show(fragmentManager, "form");
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNama;
        private TextView tvHarga;
        private ImageView ivGambar;
        private Button btnEdit, btnDelete;
        // ConstraintLayout clReview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvDashboard_NamaBrg);
            tvHarga = itemView.findViewById(R.id.tvDashboard_HargaBrg);
            ivGambar = itemView.findViewById(R.id.ivGambarBrg);
            btnEdit = itemView.findViewById(R.id.dashboard_btnEdit);
            btnDelete = itemView.findViewById(R.id.dashboard_btnDelete);
        }
    }
}
