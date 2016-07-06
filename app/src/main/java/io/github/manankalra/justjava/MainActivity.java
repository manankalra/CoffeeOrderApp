package io.github.manankalra.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int price;
        CheckBox whippedCreamCheck = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox chocolateCheck = (CheckBox) findViewById(R.id.checkBox2);
        if(whippedCreamCheck.isChecked() == true && chocolateCheck.isChecked() == true){
           price = quantity * 8;

        }
        else if(whippedCreamCheck.isChecked() == true && chocolateCheck.isChecked() == false){
            price = quantity * 6;

        }
        else if(whippedCreamCheck.isChecked() == false && chocolateCheck.isChecked() == true){
            price = quantity * 7;
        }
        else{
            price = quantity * 5;
        }
        return price;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){
        if(quantity == 0){
            displayMessage("Add something to the cart!");
        }
        else{
            displayMessage(printOrderSummary());
        }
    }

    /**
     * This method returns the order summary
     * @return
     */
    private String printOrderSummary(){
        EditText name = (EditText) findViewById(R.id.name);
        String nameOfTheCustomer = name.getText().toString();
        CheckBox whippedCheck = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox chocoCheck = (CheckBox) findViewById(R.id.checkBox2);
        String summary = "Name: " + nameOfTheCustomer + "\nQuantity: " + quantity + "\n" + calculatePrice() + " dollars for " + quantity + " cups of coffee. Pay up!\nThank You!";
        if(whippedCheck.isChecked() == true && chocoCheck.isChecked() == true){
            summary += "\nWe added a whipped cream and a chocolate topping, by the way! ;)";

        }
        else if(whippedCheck.isChecked() == true && chocoCheck.isChecked() == false){
            summary += "\nWe added a whipped cream topping, by the way! ;)";

        }
        else if(whippedCheck.isChecked() == false && chocoCheck.isChecked() == true){
            summary += "\nWe added a chocolate topping, by the way! ;)";
        }
        Log.v("MainActivity", nameOfTheCustomer);
        Log.v("MainActivity", "Add whipped cream: " + whippedCheck.isChecked());
        Log.v("MainActivity", "Add chocolate: " + chocoCheck.isChecked());
        return summary;
    }

    /**
     * Increment and decrement buttons.
     * @param view
     */
    public void increment(View view) {
        quantity++;
        display(quantity);
    }
    public void decrement(View view) {
        if (quantity > 0) {
            quantity--;
            display(quantity);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}