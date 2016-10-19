package com.remytabardel.henripotier.activities;

import android.os.Bundle;

import com.remytabardel.henripotier.R;

/**
 * @author Remy Tabardel
 */

public class OrderSummaryActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        setTitle(R.string.activity_order_summary_title);
    }
}
