package io.github.manankalra.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private int calculatePrice(boolean cream, boolean chocolate) {
        int price;
        if(cream == true && chocolate == true){
           price = quantity * 8;

        }
        else if(cream == true && chocolate == false){
            price = quantity * 6;

        }
        else if(cream == false && chocolate == true){
            price = quantity * 7;
        }
        else{
            price = quantity * 5;
        }
        return price;
    }

    /**
     * Called when the order button is clicked.
     */

    public void submitOrder(View view){
        if(quantity == 0){
            displayMessage("Add something to the cart!");
        }
        else{
            displayMessage(printOrderSummary());
            EditText name = (EditText) findViewById(R.id.name);
            String nameOfTheCustomer = name.getText().toString();
            String subject = "Coffee order for " + nameOfTheCustomer;
            String recipient[] = {"kalra.manan14@stu.upes.ac.in"};
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_EMAIL, recipient);
            intent.putExtra(Intent.EXTRA_TEXT, printOrderSummary());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    /**
     * Returns the order summary to the submitOrder method.
     */
    public String printOrderSummary(){
        EditText name = (EditText) findViewById(R.id.name);
        String nameOfTheCustomer = name.getText().toString();
        CheckBox whippedCheck = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox chocoCheck = (CheckBox) findViewById(R.id.checkBox2);
        String summary = "Name: " + nameOfTheCustomer + "\nQuantity: " + quantity + "\n" + calculatePrice(whippedCheck.isChecked(), chocoCheck.isChecked()) + " dollars for " + quantity + " cups of coffee. Pay up!\n" + getString(R.string.thank_you);
        if(whippedCheck.isChecked() == true && chocoCheck.isChecked() == true){
            summary += "\nWe added a whipped cream and a chocolate topping, by the way! ;)";

        }
        else if(whippedCheck.isChecked() == true && chocoCheck.isChecked() == false){
            summary += "\nWe added a whipped cream topping, by the way! ;)";

        }
        else if(whippedCheck.isChecked() == false && chocoCheck.isChecked() == true){
            summary += "\nWe added a chocolate topping, by the way! ;)";
        }
        //Log.v("MainActivity", nameOfTheCustomer);
        //Log.v("MainActivity", "Add whipped cream: " + whippedCheck.isChecked());
        //Log.v("MainActivity", "Add chocolate: " + chocoCheck.isChecked());
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
        if (quantity == 0) {
            Toast.makeText(this, "Just playin' around, huh?", Toast.LENGTH_SHORT).show();
        }
        else{
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