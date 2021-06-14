package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    int coffeePrice = 5;
    int chocolatePrice = 2;
    int whippedCreamPrice = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkBox);
        EditText editTexPersonName = (EditText) findViewById(R.id.et_personName);

        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckbox.isChecked();
        String personName = editTexPersonName.getText().toString();

        int price = calculatePrice(quantity, hasChocolate, hasWhippedCream);
        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, personName));
    }

    private int calculatePrice(int quantity, boolean hasChocolate, boolean hasCream) {

        int baseCoffeePrice = coffeePrice;

        if (hasChocolate) {
            baseCoffeePrice += chocolatePrice;
        }
        if (hasCream) {
            baseCoffeePrice += whippedCreamPrice;
        }

        return quantity * baseCoffeePrice;
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String personName) {
        return "Name: " + personName + "\n" +
                "Add Whipped Cream? " + addWhippedCream + "\n" +
                "Add Chocolate? " + addChocolate + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: $" + price + "\n" +
                "Thank you!";
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view
        );
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        if (quantity < 100) {
            quantity += 1;
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity > 1) {
            quantity -= 1;
        }

        displayQuantity(quantity);
    }
}