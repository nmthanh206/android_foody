package hcmute.spkt.foody_g7;

public class FoodItem {
    private int img;
    private String name;
    private Double price;
    private String category;

    public FoodItem(int img, String name, Double price, String category) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
