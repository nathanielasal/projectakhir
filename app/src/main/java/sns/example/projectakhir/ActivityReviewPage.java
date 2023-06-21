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
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;

public class ActivityReviewPage extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference reviews;
    ArrayList<Review> dataList;
    RecyclerView rvReview;
    private ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_page);
        Button btnAddReview = findViewById(R.id.review_AddReview);
        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance("https://logistics-123-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        reviews = this.databaseReference.child("reviews");

        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityReviewPage.this, ActivityReviewInput2.class);
                startActivity(intent);
            }
        });


        rvReview = findViewById(R.id.rvReview);
        rvReview.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(this, dataList);
        rvReview.setAdapter(reviewAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        getAllData();
    }

    private void getAllData(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Review data = dataSnapshot.getValue(Review.class);
                    data.setKey(dataSnapshot.getKey());
                    dataList.add(data);
                }
                reviewAdapter.notifyDataSetChanged();


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityReviewPage.this, "Gagal mendapatkan data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
