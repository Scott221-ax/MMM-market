package main;

import model.Item;

import java.net.MalformedURLException;

public interface MyListener {
    void onClickListener(Item item) throws MalformedURLException;

}
