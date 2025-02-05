package com.code.craft.ecommerce.application.service;

import com.code.craft.ecommerce.domain.ItemCart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartService {
    private List<ItemCart> itemCarts;
    private HashMap<Integer, ItemCart> itemCardHashMap;

    public CartService() {
        this.itemCardHashMap = new HashMap<>();
        this.itemCarts = new ArrayList<>();
    }

    public void addItemCard(Integer quantity, Integer idProduct, String nameProduct, BigDecimal price) {
        ItemCart itemCart = new ItemCart(idProduct, nameProduct, quantity, price);
        itemCardHashMap.put(itemCart.getIdProduct(), itemCart);
        fillList();
    }

    public BigDecimal getTotalPriceCart() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemCart itemCart : itemCarts) {
            total = total.add(itemCart.getTotalPrice());
        }
        return total;
    }

    public void removeItemCart(Integer idProduct) {
        itemCardHashMap.remove(idProduct);
        fillList();
    }

    public void removeAllItemsCart() {
        itemCardHashMap.clear();
        itemCarts.clear();
    }

    private void fillList() {
        itemCarts.clear();
        itemCardHashMap
                .forEach((integer, itemCart) -> itemCarts.add(itemCart));
    }

    public List<ItemCart> getItemCarts(){
        return itemCarts;
    }
}
