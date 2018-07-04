package taza.noe.restaurant;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class Category {

    private String name;
    private String categoryId;
    private String description;
    private double price;
    private String ID;
    private String image;
    private boolean cheacked = false;

    public Category() {
        super();
    }

    public Category(String categoryId, String name, String description, String ID, double price, String imagen) {
        super();
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.ID = ID;
        this.price = price;
        this.image = imagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(String categoryId){
        this.categoryId = categoryId;
    }

    public boolean isCheacked() {
        return cheacked;
    }

    public void setCheacked(boolean cheacked) {
        this.cheacked = cheacked;
    }
}
