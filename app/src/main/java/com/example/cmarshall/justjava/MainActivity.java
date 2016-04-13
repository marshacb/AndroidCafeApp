package com.example.cmarshall.justjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends Activity {

    int quantity = 0;

    int pricePerCup = 5;
    int whippedCreamCost = 1;
    int chocolateCost = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab != null)
            fab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });


        Button nextScreen = (Button) findViewById(R.id.next_button);
        if (nextScreen != null)
            nextScreen.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent showNextScreen = new Intent(MainActivity.this, ActivityTwo.class);

                    startActivity(showNextScreen);

                }

            });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_check_box_view);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box_view);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        //Log.v("MainActivity", "Has Whipped Cream: " + hasWhippedCream);
        EditText text = (EditText) findViewById(R.id.name_field);
        String custName = text.getText().toString();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, custName);
       displayOrderSummary(priceMessage);
    }

    public void displayQuantity(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void displayOrderSummary(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary);   //enter id for price text view
        orderSummaryTextView.setText("Order Summary: \n" + message);
    }

    public void increment(View view) {
        if(quantity == 100){
            return;
        }
        quantity += 1;
        displayQuantity(quantity);

    }

    public void decrement(View view) {
        if(quantity == 0) {
            return;
        }
            quantity -= 1;
            displayQuantity(quantity);
        }

        //add else clause for 0 to avoid negative values



    private int calculatePrice(boolean addWhippedCream, boolean addChocolate){
        int price = quantity * pricePerCup;
        if(addWhippedCream){
            price += (whippedCreamCost * quantity);
        }
        if(addChocolate){
            price += (chocolateCost * quantity);
        }
        return price;

    }

    /**
     *@param price of order
     * @param addWhippedCream is whether or not the user wants whipped cream
     * @return text sumamry
     */

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name)   {
        String priceMessage = "Name: " + name;
        priceMessage += "\naddWhippedCream? " + addWhippedCream;
        priceMessage += "\naddChocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;


    }
//displays Name:   Quantity:    Price:  and 	Thank You!



//change price text view to order summary text view
//change price header to say Order Summary

}
