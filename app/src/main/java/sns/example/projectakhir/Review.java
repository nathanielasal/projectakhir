package sns.example.projectakhir;

import android.os.Parcel;
import android.os.Parcelable;

public class Review {

    private String reviewInput;
    private String rateCount;
    private String key;



    public Review(){

    }
    public Review(String key, String rateCount, String reviewInput){
        this.key = key;
        this.rateCount = rateCount;
        this.reviewInput = reviewInput;
    }
    public Review(String rateCount, String reviewInput){
        this.rateCount = rateCount;
        this.reviewInput = reviewInput;
    }




    public String getKey(){ return key; }

    public void setKey(String key) {
        this.key = key;
    }

    public String getReviewInput(){
        return this.reviewInput;
    }
    public String getRateCount(){
        return this.rateCount;
    }

    public void setReviewInput(String reviewInput){
        this.reviewInput = reviewInput;
    }
    public void setRateCount(String rateCount){
        this.rateCount = rateCount;
    }


    public void readFromParcel(Parcel source) {
        this.key = source.readString();
        this.reviewInput = source.readString();
        this.rateCount = source.readString();
    }
    protected Review(Parcel in) {
        this.key = in.readString();
        this.reviewInput = in.readString();
        this.rateCount = in.readString();
    }

    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel source) {
            return new Review(source);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}

