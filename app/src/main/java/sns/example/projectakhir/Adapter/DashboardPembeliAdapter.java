package sns.example.projectakhir.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import sns.example.projectakhir.Model.Barang;
import sns.example.projectakhir.R;

public class DashboardPembeliAdapter extends RecyclerView.Adapter {

    private final Context ctx;
    private List<Barang> barang; // menyimpan kontak yang asli

    // Memasukkan data set ke dalam adapter
    // digunakan untuk memasukkan context dan dataset
    // ke dalam adapter
    public DashboardPembeliAdapter(Context ctx, List<Barang> barang){
        this.ctx = ctx;
        this.barang = barang;
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence search) {
//                FilterResults results = new FilterResults();
//                // Jika tidak ada yang difilter, maka contactfilter sama dengan contact
//                if (search.length() == 0){
//                    contactsFilter = contacts;
//                    results.count = contactsFilter.size();
//                    results.values = contactsFilter;
//                } else {
//                    // jika filter ada isinya (by nama, telepon, email)
//                    contactsFilter = new ArrayList<>();
//                    for (Contact c : contacts){
//                        if (c.nama.toLowerCase().contains(search) || c.telepon.toLowerCase().contains(search) || c.email.toLowerCase().contains(search)){
//                            contactsFilter.add(c);
//                        }
//                        results.count = contactsFilter.size();
//                        results.values = contactsFilter;
//                    }
//                }
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                contactsFilter = (List<Contact>) filterResults.values;
//                notifyDataSetChanged(); // agar adapter meng-update tampilan
//            }
//        };
//    }

    // class View Holder custom untuk Contact
    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNama;
        public TextView tvHarga;


        MyViewHolder(View rowView){
            super(rowView);
            this.tvNama = rowView.findViewById(R.id.tvDashboardPembeli_NamaBrg);
            this.tvHarga = rowView.findViewById(R.id.tvHarga);

            // btnDelete.setOnClickListener(this);
        }
//
//        @Override
//        public void onClick(View v) {
//            if (v.getId() == btnDelete.getId()) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
//                        .setTitle("Delete Contacts")
//                        .setMessage("Are you sure you want to delete this item?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                int position = getAdapterPosition();
//                                if (position != RecyclerView.NO_POSITION) {
//                                    contacts.remove(position);
//                                    notifyItemRemoved(position);
//                                }
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        }
    }

    // Mengembalikan view holder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(this.ctx).inflate(R.layout.item_dashboard_pembeli, parent, false)
        );
    }

    // Saat view holder dipasangkan dengan contact
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Barang b = this.barang.get(position);
        MyViewHolder db = (MyViewHolder) holder;
        db.tvNama.setText(b.nama);
        db.tvHarga.setText(b.harga);
    }

    @Override
    public int getItemCount() {
        return this.barang.size();
    }
}
