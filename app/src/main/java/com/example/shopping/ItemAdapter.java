package com.example.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> items;
    private ArrayList<Item> cart;  // Track the items already added to the cart
    private boolean isInCartPage;  // Flag to know if we're in CartPage

    public ItemAdapter(Context context, ArrayList<Item> items, ArrayList<Item> cart, boolean isInCartPage) {
        this.context = context;
        this.items = items;
        this.cart = cart;
        this.isInCartPage = isInCartPage;  // Set the flag
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        }

        // Get current item
        Item currentItem = items.get(position);

        // Set item name
        TextView itemName = convertView.findViewById(R.id.item_name);
        itemName.setText(currentItem.getName());

        // Set item price
        TextView itemPrice = convertView.findViewById(R.id.item_price);
        itemPrice.setText("$" + currentItem.getPrice());

        // Set item image
        ImageView itemImage = convertView.findViewById(R.id.item_image);
        itemImage.setImageResource(currentItem.getImageResource());

        // Add to cart button (only visible on the Main Page)
        Button addToCartButton = convertView.findViewById(R.id.add_to_cart_button);

        // If we are in the Cart Page, hide the "Add to Cart" button
        if (isInCartPage || cart.contains(currentItem)) {
            addToCartButton.setVisibility(View.GONE); // Hide button if item is in cart or we're in Cart Page
        } else {
            addToCartButton.setVisibility(View.VISIBLE); // Show button if item is not in cart
        }

        // Add to Cart button click behavior
        addToCartButton.setOnClickListener(v -> {
            if (context instanceof MainActivity) {
                // Add item to cart and update the button visibility
                ((MainActivity) context).addToCart(currentItem);
                addToCartButton.setVisibility(View.GONE);  // Hide button after adding to cart
                notifyDataSetChanged(); // Notify adapter to update the view (important)
            }
        });

        return convertView;
    }
}
