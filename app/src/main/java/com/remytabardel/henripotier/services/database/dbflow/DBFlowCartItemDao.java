package com.remytabardel.henripotier.services.database.dbflow;

import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.remytabardel.henripotier.models.CartItem;
import com.remytabardel.henripotier.models.CartItem_Table;
import com.remytabardel.henripotier.services.database.CartItemDao;

import java.util.List;

/**
 * @author Remy Tabardel
 *         Link between CartItemDao interface and DBFlow
 */

public class DBFlowCartItemDao implements CartItemDao {
    @Override
    public List<CartItem> selectAll() {
        return SQLite.select()
                .from(CartItem.class)
                .orderBy(OrderBy.fromProperty(CartItem_Table.addedTime).ascending())
                .queryList();
    }

    @Override
    public boolean insert(CartItem cartItem) {
        // separated code with update beacause if we need to change with orm or use sql later..
        cartItem.save();

        return true;
    }

    @Override
    public boolean update(CartItem cartItem) {
        // separated code with update beacause if we need to change with orm or use sql later..
        cartItem.save();

        return true;
    }

    @Override
    public boolean delete(CartItem cartItem) {
        cartItem.delete();

        return true;
    }
}
