package com.example.shenron.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.duration;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText nameEditText = (EditText) findViewById(R.id.name_text_field);
        String nameOfUser = "";
        nameOfUser += nameEditText.getText();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, nameOfUser);
        displayMessage(priceMessage);
    }

    /*
    * Calcukate price of coffees*/

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate)
    {
        int basePrice = 5;
        if(hasWhippedCream)
        {
            basePrice +=1;
        }
        if(hasChocolate)
        {
            basePrice +=2;
        }
        return quantity*basePrice;
    }
    /*
    * Creates order summary */

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addchocolate, String nameOfUser)
    {
        String priceMessage = "Name: " + nameOfUser;
        priceMessage += "\nAdd Whipped Cream?" + addWhippedCream;
        priceMessage += "\nAdd Chocolate?" + addchocolate;
        priceMessage += "\nQuantity:" + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;

    }



    /*this method is called when + button is clicked*/
    public void increment(View view)
    {
        if(quantity < 100) {
            quantity++;
            display(quantity);
        }
        else
        {
            Toast toast = Toast.makeText(this, "You cannot have more than 100 cups of coffee", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    /*this method is called when - button is clicked*/
    public void decrement(View view)
    {
        if(quantity > 1) {
            quantity--;
            display(quantity);
        }
        else
        {
            Toast toast = Toast.makeText(this, "You cannot have less than 1 cup of coffee", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceMessage = (TextView) findViewById(R.id.price_text_view);
        priceMessage.setText(message);

    }
}