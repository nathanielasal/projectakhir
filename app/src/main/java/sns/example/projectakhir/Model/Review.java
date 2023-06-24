package sns.example.projectakhir.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.PropertyName;

public class Review  {

    private String reviewInput;
    private String rateCount;
    private String key;

    public Review() {

    }

    //    public Review(String key, String rateCount, String reviewInput){
//        this.key = key;
//        this.rateCount = rateCount;
//        this.reviewInput = reviewInput;
//    }
    public Review(String rateCount, String reviewInput) {
        this.rateCount = rateCount;
        this.reviewInput = reviewInput;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getReviewInput() {
        return reviewInput;
    }

    public String getRateCount() {
        return rateCount;
    }

    public void setReviewInput(String reviewInput) {
        this.reviewInput = reviewInput;
    }

    public void setRateCount(String rateCount) {
        this.rateCount = rateCount;
    }
    };



