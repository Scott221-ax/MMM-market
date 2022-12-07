package model;

public class Item {
    private String name;
    private float price;
    private int num;
    private String unit;//计量单位，比如kg,ton.....
    private String Imgsrc;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgsrc() {
        return Imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        Imgsrc = imgsrc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
