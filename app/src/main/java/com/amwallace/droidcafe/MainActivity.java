package com.amwallace.droidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.amwallace.droidcafe.Model.DessertOrder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DessertOrder dessertOrder;
    private TextView donutQty, froyoQty, sandwichQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setup quantity textviews
        donutQty = (TextView) findViewById(R.id.donutQtyTxt);
        donutQty.setText("Qty: 0");
        froyoQty = (TextView) findViewById(R.id.froyoQtyTxt);
        froyoQty.setText("Qty: 0");
        sandwichQty = (TextView) findViewById(R.id.sandwhichQtyTxt);
        sandwichQty.setText("Qty: 0");

        //check if returning from order activity
        if(getIntent().hasExtra("dessertOrder")){
            //get desert order (cleared) from activity
            dessertOrder = (DessertOrder) getIntent().getSerializableExtra("dessertOrder");
        } else { //not returning from order activity
            //instantiate empty dessert order
            dessertOrder = new DessertOrder();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent orderIntent = new Intent(MainActivity.this,
                        OrderActivity.class);
                orderIntent.putExtra("dessertOrder", dessertOrder);
                startActivity(orderIntent);
                finish();
            }
        });
    }

    //The Ice cream sandwich image was clicked, order an ice cream sandwich
    public void orderSandwich(View view) {
        //get number of ice cream sandwiches in order
        int sandwichCount = dessertOrder.getNumSandiwches();
        //increase number by 1
        dessertOrder.setNumSandiwches(sandwichCount + 1);
        //update quantity text
        sandwichQty.setText("Qty: " + dessertOrder.getNumSandiwches());

        displayToast(getString(R.string.ice_cream_order_message));

    }


    //The froyo image was clicked, order froyo
    public void orderFroyo(View view){
        //get number of froyo in order
        int froyoCount = dessertOrder.getNumFroyo();
        //increase by 1
        dessertOrder.setNumFroyo(froyoCount + 1);
        //update quantity text
        froyoQty.setText("Qty: " + dessertOrder.getNumFroyo());

        displayToast(getString(R.string.froyo_order_message));
    }


    //The donut image was clicked, order a donut
    public void orderDonut(View view){
        //get number of donuts in order
        int donutCount = dessertOrder.getNumDonuts();
        //increase by 1
        dessertOrder.setNumDonuts(donutCount + 1);
        //update quantity text
        donutQty.setText("Qty: " + dessertOrder.getNumDonuts());
        displayToast(getString(R.string.donut_order_message));
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

        switch (id){
            case R.id.action_contact:
                displayToast("Selected Menu item Contact");
                break;
            case R.id.action_favorites:
                displayToast("Selected Menu item Favorites");
                break;
            case R.id.action_order:
                displayToast("Selected Menu item Order");
                //go to order page
                Intent orderIntent = new Intent(MainActivity.this,
                        OrderActivity.class);
                orderIntent.putExtra("dessertOrder", dessertOrder);
                startActivity(orderIntent);
                finish();
                break;
            case R.id.action_status:
                displayToast("Selected Menu item Status");
                break;
            default:
                //do nothing
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    //display toast message
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
