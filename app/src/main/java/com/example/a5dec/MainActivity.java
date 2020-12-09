 package com.example.a5dec;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    protected int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


//    this is called when + button click

    public void increment(View view) {
        if(quantity==100){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

        quantity=quantity+1;

        displayQuantity(quantity);
    }


//    private Context context;
//
//    TextView priceTextView= new TextView(context);
//
//
//    ImageView coffeeImageView= new ImageView(context);
//    Button submitOrder=new Button(context);
//    CatView sleepyCatView  = new CatView("Cat");
//    MediaPlayer player= MediaPlayer.create(context, R.raw.song);
//    Toast toastmessage= Toast.makeText(context,"Hi",duration);

//    this class call when - button is clicked
    public void decrement(View view) {
        if(quantity==1){
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do

            return;
        }
        quantity=quantity-1;
        displayQuantity(quantity);
    }



    public void submitOrder(View view) {
        EditText text = (EditText)findViewById(R.id.name_text_field);
        String name= text.getText().toString();
        CheckBox choclateCheck=(CheckBox) findViewById(R.id.chocolate_checkbox);
        CheckBox whippedCreamCheck= (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        boolean hasWheepCream= whippedCreamCheck.isChecked();
        boolean haschoclate=choclateCheck.isChecked();
        int price=calculate_price(haschoclate,haschoclate);
        String priceMessage= createOrderSummary(name,price,hasWheepCream,haschoclate);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "5DEC order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage );

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//        displayMessage(priceMessage);

    }



    @SuppressLint("StringFormatMatches")
    private String createOrderSummary(String name,int price,boolean addWheepCream,boolean addchoclate){
        String priceMessage=getString(R.string.order_summary_name,name);
        priceMessage += " \n" +getString(R.string.order_summary_name,addWheepCream) ;
        priceMessage+= "\n" + getString(R.string.order_summary_name,addchoclate);
        priceMessage +="\n"+getString(R.string.order_summary_name,quantity);
        priceMessage +="\n" + getString(R.string.order_summary_name,NumberFormat.getCurrencyInstance().format(price));
        priceMessage +="\n" + getString(R.string.thank_you);
        return priceMessage;
    }


    private int calculate_price(boolean addWheepCream,boolean addchoclate ){
        int baseprice=5;
        if(addWheepCream){
            baseprice +=1;
        }
        if(addchoclate){
            baseprice +=2;
        }
        return quantity*baseprice;

    }



    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



//    /**
//     * This method displays the given text on the screen.
//     */
//
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

}