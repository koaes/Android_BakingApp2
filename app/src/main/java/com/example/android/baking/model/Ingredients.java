package com.example.android.baking.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable{

    private String quantity;
    private String measure;
    private String ingredient;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "quantity='" + quantity + '\'' +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(quantity);
        out.writeString(measure);
        out.writeString(ingredient);
    }

    public Ingredients(Parcel parcel) {
        quantity = parcel.readString();
        measure = parcel.readString();
        ingredient = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public static final Parcelable.Creator<Ingredients> CREATOR = new Parcelable.Creator<Ingredients>() {

        @Override
        public Ingredients createFromParcel(Parcel parcel) {
            return new Ingredients(parcel);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[0];
        }
    };

}
