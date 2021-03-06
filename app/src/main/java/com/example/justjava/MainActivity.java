package com.example.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, personName);
        displayMessage(message);

        String[] addresses = new String[]{"cgletras@gmail.com"};
        composeEmail(addresses, "Coffee order", message);
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
        String addWhippedCreamMsg = "";
        String addChocolateMsg = "";

        if(addChocolate){
            addChocolateMsg = getString(R.string.topping_msg_true);
        } else {
            addChocolateMsg = getString(R.string.topping_msg_false);
        }
        if(addWhippedCream){
            addWhippedCreamMsg = getString(R.string.topping_msg_true);
        } else {
            addWhippedCreamMsg = getString(R.string.topping_msg_false);
        }

        return getString(R.string.name) + personName + "\n" +
                getString(R.string.whipped_cream) + addWhippedCreamMsg + "\n" +
                getString(R.string.top_chocolate) + addChocolateMsg + "\n" +
                getString(R.string.quantity) + quantity + "\n" +
                getString(R.string.price) + price + "\n" +
                getString(R.string.thank_you);
    }

    public void composeEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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