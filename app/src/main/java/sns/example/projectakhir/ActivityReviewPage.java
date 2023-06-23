package sns.example.projectakhir;

import android.content.Intent;
import android.os.Bundle;
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

import sns.example.projectakhir.Adapter.ReviewAdapter;
import sns.example.projectakhir.Model.Review;
import sns.example.projectakhir.Model.User;

public class ActivityReviewPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference review;
    private ArrayList<Review> dataList;
    private ArrayList<User> userList;
    private RecyclerView rvReview;
    private ReviewAdapter reviewAdapter;
    Button btnAddReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_page);

        rvReview = findViewById(R.id.rvReview);
        btnAddReview = findViewById(R.id.review_AddReview);
//        btnAddReview.setOnClickListener((View.OnClickListener) this);

        dataList = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://logistics-123-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference();

        rvReview.setHasFixedSize(true);
        reviewAdapter = new ReviewAdapter(ActivityReviewPage.this, dataList);
        rvReview.setAdapter(reviewAdapter);
        rvReview.setLayoutManager(new LinearLayoutManager(this));

        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnAddReview.getId()==view.getId()) {
                    Intent intent = new Intent(ActivityReviewPage.this, ActivityReviewInput2.class);
                    startActivity(intent);
                }
            }
        });

        getAllData();
    }
    private void getAllData() {

        databaseReference.child("review").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Review r = dataSnapshot.getValue(Review.class);
                    r.setKey(dataSnapshot.getKey());
                    dataList.add(r);
                }

                reviewAdapter = new ReviewAdapter(ActivityReviewPage.this, dataList);
                rvReview.setAdapter(reviewAdapter);

                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityReviewPage.this, "Gagal mendapatkan data", Toast.LENGTH_SHORT).show();
            }
        });

//
    }
}
