package com.amwallace.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amwallace.droidcafe.Model.DessertOrder;

public class OrderActivity extends AppCompatActivity {
    private EditText nameEdt, addressEdt, phoneEdt, noteEdt;
    private Button cancelBtn, submitBtn;
    private DessertOrder dessertOrder;
    private TextView orderTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //set up UI (edit texts, order text view, buttons, etc.)
        nameEdt = (EditText) findViewById(R.id.orderNameEdt);
        addressEdt = (EditText) findViewById(R.id.orderAddressEdt);
        phoneEdt = (EditText) findViewById(R.id.orderPhoneEdt);
        noteEdt = (EditText) findViewById(R.id.orderNoteEdt);

        cancelBtn = (Button) findViewById(R.id.cancelOrderBtn);
        submitBtn = (Button) findViewById(R.id.submitOrderBtn);

        orderTxt = (TextView) findViewById(R.id.orderTxt);

        //get order details from intent
        dessertOrder = (DessertOrder) getIntent().getSerializableExtra("dessertOrder");
        //build string for order
        StringBuilder orderString = new StringBuilder("You ordered: ");
        //append strings with quantities for each item
        orderString.append(dessertOrder.getNumDonuts());
        orderString.append("x Donuts, ");
        orderString.append(dessertOrder.getNumFroyo());
        orderString.append("x Froyo, ");
        orderString.append(dessertOrder.getNumSandiwches());
        orderString.append("x Ice Cream Sandwiches");
        //set order text
        orderTxt.setText(orderString.toString());

        //onclick listeners for buttons
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear order, return to menu
                dessertOrder.setNumFroyo(0);
                dessertOrder.setNumSandiwches(0);
                dessertOrder.setNumDonuts(0);
                //create menu Intent, send dessertOrder
                Intent menuIntent = new Intent(OrderActivity.this,MainActivity.class);
                menuIntent.putExtra("dessertOrder",dessertOrder);
                startActivity(menuIntent);
                finish();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //submit order
                Toast.makeText(getApplicationContext(),
                        "Order Submitted",Toast.LENGTH_SHORT).show();
            }
        });

    }

    //method for radio button clicked listener
    public void onRadioButtonClicked(View view){
        //is button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        //determine which button was clicked
        switch (view.getId()){

            case R.id.sameDay:  //same day service
                if(checked){
                    //message about same day
                    Log.d("DELIVERY OPTION SELECTED: ", "SAME DAY SERVICE");
                }
                break;
            case R.id.nextDay:
                if(checked){
                    //message about next day ground
                    Log.d("DELIVERY OPTION SELECTED: ", "NEXT DAY GROUND");
                }
                break;
            case R.id.pickup:
                if (checked){
                    Log.d("DELIVERY OPTION SELECTED: ", "PICKUP");
                }
                break;
            default:
                //nothing
                Log.d("DELIVERY OPTION SELECTED: ", "ERROR IN SWITCH STATEMENT");
                break;
        }
    }


}
