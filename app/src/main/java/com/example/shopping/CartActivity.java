package com.example.shopping;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ArrayList<Item> cart;
    private TextView totalPriceTextView;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Get the cart items passed from MainActivity
        cart = (ArrayList<Item>) getIntent().getSerializableExtra("cartItems");

        // Initialize views
        ListView listView = findViewById(R.id.cart_list);
        totalPriceTextView = findViewById(R.id.total_price);

        // Setup the adapter and display cart items
        adapter = new ItemAdapter(this, cart, new ArrayList<>(), true);  // Cart page: pass 'true'
        listView.setAdapter(adapter);

        // Display the total price
        updateTotalPrice();

        // Book Items Button
        Button bookButton = findViewById(R.id.book_button);
        bookButton.setOnClickListener(v -> {
            // Show confirmation message that items are booked
            Toast.makeText(CartActivity.this, "Items successfully booked!", Toast.LENGTH_SHORT).show();

            // Optionally, clear the cart after booking
            cart.clear();
            updateTotalPrice();  // Update the total price after clearing the cart

            // Update the ListView after booking
            adapter.notifyDataSetChanged();
        });
    }

    // Update the total price displayed in the CartActivity
    private void updateTotalPrice() {
        double totalPrice = 0;
        for (Item item : cart) {
            totalPrice += item.getPrice();
        }
        totalPriceTextView.setText("Total: $" + String.format("%.2f", totalPrice));
    }
}
