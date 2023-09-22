package com.example.try_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class tenent_forgot_pass extends AppCompatActivity {
    String OTP;
    Button btn_send,btn_submit;
    TextView tv_email;
    EditText ed_email,ed_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenent_forgot_pass);
        ed_email=findViewById(R.id.ed_email);

        btn_send=findViewById(R.id.btn_send);
        tv_email=findViewById(R.id.tv_email);
/*
        SqlOp db=new SqlOp(getApplicationContext());
        db.temp();*/

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = validate();
                if (b == true) {
                    String email = ed_email.getText().toString();
                    int r = 0;
                    Boolean res = check_email_data(ed_email.getText().toString());
                    if (res)
                    {
                        btn_send.setText("Resend OTP");
                        Toast.makeText(getApplicationContext(), "OTP sent", Toast.LENGTH_SHORT).show();
                        send_mail(ed_email.getText().toString());
                        Intent i = new Intent(getApplicationContext(), T_Enter_OTP.class);
                        i.putExtra("pass_for_otp", OTP);
                        i.putExtra("pass_for_email", ed_email.getText().toString());
                        startActivity(i);

                    } else
                        Toast.makeText(getApplicationContext(), "Email not registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void send_mail(String email)
    {
        Random r = new Random();
        int otp = r.nextInt(999999-100000)+100000;
        OTP=String.valueOf(otp);
        try {
            String msg = "OTP for Reset password is :"+OTP;
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "587");
            Session s = Session.getInstance(p,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("jugalbhagat17.2@gmail.com", "kfsospfgbiyymcyj");
                        }
                    });
            try {
                Message m = new MimeMessage(s);
                m.setFrom(new InternetAddress("jugalbhagat17.2@gmail.com"));
                m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                m.setSubject("Reset Password");
                m.setText(msg);
                // Transport.send(m);
                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Transport.send(m);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }

                    }
                });
                t.start();
            } catch (MessagingException e) {
                Toast.makeText(getApplicationContext(), "not sent", Toast.LENGTH_SHORT).show();
                tv_email.setText(e.toString());
            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
            tv_email.setText(e.toString());
        }
    }
    boolean check_email_data(String email)
    {
        SqlOp obj=new SqlOp(getApplicationContext());
        Boolean c=obj.tenent_Check_email(email);
        return c;
    }
    public boolean validate()
    {
        Drawable draw_warn = ContextCompat.getDrawable(getApplicationContext(), R.drawable.warning_border);
        Drawable draw_normal = ContextCompat.getDrawable(getApplicationContext(), R.drawable.spinner_item);

        int count=0;
        if(Patterns.EMAIL_ADDRESS.matcher(ed_email.getText().toString()).matches())
        {
            count+=1;
            ed_email.setBackground(draw_normal);
        }
        else
        {
            ed_email.setBackground(draw_warn);
            count=0;
        }

        if(count>0)
        {
            //Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            //Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}