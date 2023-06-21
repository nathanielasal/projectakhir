package sns.example.projectakhir;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private Context context;
    private List<Review> dataList;

    public ReviewAdapter(Context context, List<Review> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review data = dataList.get(position);

        holder.textViewRateCount.setText(data.getRateCount());
        holder.textViewReviewInput.setText(data.getReviewInput());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewRateCount;
        private TextView textViewReviewInput;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRateCount = itemView.findViewById(R.id.item_tvRateCount);
            textViewReviewInput = itemView.findViewById(R.id.item_tvReviewInput);
        }
    }
}
