package com.example.android.baking.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipe implements Parcelable{

    private String id;
    private String name;
    private ArrayList<Ingredients> ingredients = new ArrayList<>();
    private ArrayList<Steps> steps;
    private String servings;
    private String image;


    public Recipe(String id, String name, ArrayList<Ingredients> ingredients, ArrayList<Steps> steps, String servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Steps> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Steps> steps) {
        this.steps = steps;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", servings='" + servings + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(id);
        out.writeString(name);
        out.writeList(ingredients);
        out.writeList(steps);
        out.writeString(servings);
        out.writeString(image);

    }

    public Recipe(Parcel parcel){
        id = parcel.readString();
        name = parcel.readString();
        ingredients = parcel.readArrayList(Ingredients.class.getClassLoader());
        steps = parcel.readArrayList(Steps.class.getClassLoader());
        servings = parcel.readString();
        image = parcel.readString();


    }

    @Override
    public int describeContents() {
        return 0;
    }


    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {

        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[0];
        }
    };

}
