package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.util.Random;

public class premium_plans extends AppCompatActivity implements PaymentResultListener, PaymentResultWithDataListener {

    private AlertDialog.Builder alertDialogBuilder;
    Button btn_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium_plans);
        btn_pay=findViewById(R.id.btn_purchase);


        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    start_payment_mathod();
                }catch (Exception e){

                }
            }
        });
    }

    public void start_payment_mathod() {
        Random r = new Random();
        int i1 = r.nextInt(20000 - 1) + 1;

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_NlEGza3lcRPv6F");//rzp_test_NlEGza3lcRPv6F

        //secrete key :X8yrRhE6D5uJzhb5WjXZ9wJP

        checkout.setImage(R.drawable.logo);

        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Jugal Bhagat");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
            //  options.put("order_id", i1);//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", 550*100);//pass amount in currency subunits
            options.put("prefill.email", "jugalbhagat17.3@gmail.com");
            options.put("prefill.contact","9924036196");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }
    @Override
    public void onPaymentSuccess(String s) {
        //tv.setText("payment success"+s);
        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
        succ();
    }

    @Override
    public void onPaymentError(int i, String s) {
        //tv.setText("payment failed try again"+s);
        Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show();
        fail();
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        //  super.unregisterReceiver(receiver);
        System.out.println("i got you------------------------------------------------------");
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try{
            alertDialogBuilder.setMessage("Payment Successful :\nPayment ID: "+s+"\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try{
            alertDialogBuilder.setMessage("Payment Failed:\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void succ()
    {
        Intent i=new Intent(getApplicationContext(),payment_redirect.class);
        i.putExtra("response","success");
        startActivity(i);
    }
    void fail()
    {
        Intent i=new Intent(getApplicationContext(),payment_redirect.class);
        i.putExtra("response","failed");
        startActivity(i);
    }
}