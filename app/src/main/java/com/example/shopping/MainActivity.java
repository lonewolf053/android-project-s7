package com.example.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Item> items;
    private ArrayList<Item> cart;
    private ItemAdapter adapter; // Reference to the adapter for refreshing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize lists
        items = new ArrayList<>();
        cart = new ArrayList<>();

        // Add sample items to list
        items.add(new Item("Apple", 1.2, R.drawable.item1_image)); // Replace with your image names
        items.add(new Item("Banana", 0.8, R.drawable.item1_image)); // Replace with your image names
        items.add(new Item("Orange", 1.5, R.drawable.item1_image)); // Replace with your image names

        // Setup ListView and Adapter
        ListView listView = findViewById(R.id.list_view);
        adapter = new ItemAdapter(this, items, cart, false);  // Main page: pass 'false'
        listView.setAdapter(adapter);

        // Go to Cart button
        Button cartButton = findViewById(R.id.cart_button);
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            intent.putExtra("cartItems", cart);  // Pass cart data
            startActivity(intent);
        });
    }

    // Add item to cart
    public void addToCart(Item item) {
        if (!cart.contains(item)) {
            cart.add(item);
            // Optionally, show a message
            Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
        }
    }
}
