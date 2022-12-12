package model;

import java.io.File;
import java.net.MalformedURLException;

public class Item {
    private String name;
    private float price;
    private int num;//当前数据库中数量
    private int chosenNum = 0;//被选中数量
    private int remainNum = 0;//剩余数量，后期更新数据库；被购买后就要更新num
    private String unit;//计量单位，比如kg,ton.....
    private String Imgsrc;
    private String type;//商品种类
    private boolean ifSelected = false;

    public Item() {
    }

    public boolean isIfSelected() {
        return ifSelected;
    }

    public void setIfSelected(boolean bool) {
        ifSelected = bool;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getChosenNum() {
        return chosenNum;
    }

    public void setChosenNum(int chosenNum) {
        this.chosenNum = chosenNum;
        if (num - chosenNum >= 0) {
            setRemainNum(num - chosenNum);
        } else {
            setRemainNum(0);
        }
    }

    public int getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
        num = remainNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgsrc() throws MalformedURLException {
        File file = new File(Imgsrc);
        String filePath = file.toURI().toURL().toString();
        return filePath;
    }

    public String getImgPath() {
        return Imgsrc;//这是在数据库里存放的绝对路径
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
