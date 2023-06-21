package sns.example.projectakhir;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityReviewInput2 extends AppCompatActivity implements View.OnClickListener {
    EditText reviewInput;
    TextView rateCount, showRating;
    Button postReview;
    RatingBar ratingBar;
    float rateValue;
    String temp;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    DatabaseReference reviews;

//    public ActivityReviewInput2 (String reviewId, String rateCount2, String reviewInput2) {
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_input);

        mAuth = FirebaseAuth.getInstance();

        rateCount = findViewById(R.id.tvRateCount);
        ratingBar = findViewById(R.id.ratingBar);
        reviewInput = findViewById(R.id.etReviewInput);
        postReview = findViewById(R.id.btnPostReview);
        showRating = findViewById(R.id.tvShowRating);

        databaseReference = FirebaseDatabase.getInstance("https://logistics-123-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        reviews = this.databaseReference.child("reviews");

        postReview.setOnClickListener(this);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();

                if(rateValue<1 && rateValue>0)
                    rateCount.setText("" + rateValue + "/5");
                else if(rateValue<=2 && rateValue<1)
                    rateCount.setText("" + rateValue + "/5");
                else if(rateValue<=3 && rateValue<2)
                    rateCount.setText("" + rateValue + "/5");
                else if(rateValue<=4 && rateValue<3)
                    rateCount.setText("" + rateValue + "/5");
                else if(rateValue<=5 && rateValue<4)
                    rateCount.setText("" + rateValue + "/5");
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (postReview.getId() == view.getId()) {
           // String reviewInput = ((EditText) this.findViewById(R.id.reviewInput)).getText().toString();
            String reviewInput1 = reviewInput.getText().toString();
            String rateCount1 = rateCount.getText().toString();
            Review newReview = new Review(rateCount1, reviewInput1);

            databaseReference.child("review").child(mAuth.getUid()).push().setValue(newReview).addOnSuccessListener(ActivityReviewInput2.this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(ActivityReviewInput2.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivityReviewInput2.this, ActivityReviewPage.class);
                    startActivity(intent);
                }
            }).addOnFailureListener(ActivityReviewInput2.this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ActivityReviewInput2.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
