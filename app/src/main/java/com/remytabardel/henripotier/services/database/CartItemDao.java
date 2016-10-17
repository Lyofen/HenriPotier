package com.remytabardel.henripotier.services.database;

import com.remytabardel.henripotier.models.CartItem;

import java.util.List;

/**
 * @author Remy Tabardel
 */

public interface CartItemDao {
    List<CartItem> selectAll();

    boolean insert(CartItem cartItem);

    boolean update(CartItem cartItem);

    boolean delete(CartItem cartItem);
}
