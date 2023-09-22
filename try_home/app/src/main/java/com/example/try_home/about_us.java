package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class about_us extends AppCompatActivity {

    TextView tv,tv1,tv2,tv3,tv4,tv5,tv6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        tv=findViewById(R.id.txt_des);
        tv1=findViewById(R.id.txt_des);
        tv2=findViewById(R.id.txt_des2);
        tv3=findViewById(R.id.txt_des3);
        tv4=findViewById(R.id.txt_des4);
        tv5=findViewById(R.id.txt_des5);
        tv6=findViewById(R.id.txt_des6);
        String d1="Nest Quest was started with the aim to reduce information asymmetry in the Real Estate industry and provide customers with world-class service using cutting-edge technology. When we saw what was missing in the real estate industry, we created products to bridge the gap. But, when it came to home security, we saw many areas that needed work. That is why we created – Nest QuestHood.";
        String d2="Our founders Akhil Gupta, Amit Kumar Agarwal and Saurabh Garg understood the need to have a tech-enabled visitor and community management system, that aims to make life convenient, hassle-free, and secure for the residents of a gated community.";
        String d3="The constant movement of service staff (maids, drivers, courier services, delivery personnel, etc.) and guests can make security management an inconvenience. While most apartment buildings and residential complexes do have security measures in place, they are either outdated or rely heavily on manual record-keeping – which is time-consuming and sometimes ineffective.";
        String d4="Moreover, in larger societies, there is always doubt on the number of staff required to fulfil day-to-day tasks such as visitor management, accounting and so on. This can pose an issue for residents and other committee members to deal with issues like unauthorised visitor entries/vehicle parking and maintaining service staff attendance. Sometimes, residents are also required to respond to calls from the main gate to authorize entry - which might be troublesome if they are busy at that moment.";
        String d5="This is where Nest QuestHood comes to your rescue. We offer a plethora of services and features which enables our resident community to manage multiple activities from the comfort of their homes – from finding domestic help to monitoring visitor entry and pre-authorising guest visits, it can all be done by phone. We believe that it also further strengthens the security and safety of your society since Nest QuestHood keeps both visual and digital records of all entries and exits – accessible at any time from anywhere – and automates staff entry through a biometric process.";
        String d6="Get in touch with us to experience a better, smarter and affordable safety system for your gated society.";

        tv1.setText(d1);
        tv2.setText(d2);
        tv3.setText(d3);
        tv4.setText(d4);
        tv5.setText(d5);
        tv6.setText(d6);

    }
}