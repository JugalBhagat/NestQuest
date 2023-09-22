package com.example.try_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class O_Register extends AppCompatActivity {

    Button btn_reg;
    TextView hyp_link;
    EditText ed_fnm,ed_lnm,ed_email,ed_phone,ed_pass;
    SqlOp db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oregister);

        hyp_link=findViewById(R.id.lnkLogin);
        ed_email=findViewById(R.id.txt_email);
        ed_fnm=findViewById(R.id.txt_firstname);
        ed_lnm=findViewById(R.id.txt_lastname);
        ed_phone=findViewById(R.id.txt_phone);
        ed_pass=findViewById(R.id.txt_pass);
        btn_reg=findViewById(R.id.btn_register);
        String text="Already Registered? Login";
        SpannableString ss=new SpannableString(text);
        ClickableSpan cs=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                startActivity(new Intent(O_Register.this,O_Login.class));
                //Toast.makeText(T_Login.this,"hello ccc",Toast.LENGTH_SHORT).show();
            }
        };
        ss.setSpan(cs,0,25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hyp_link.setText(ss);
        hyp_link.setMovementMethod(LinkMovementMethod.getInstance());

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b=validate();
                if(b==true)
                {
                    SqlOp db=new SqlOp(O_Register.this);
                    boolean res=false;
                    boolean res_check=db.Owner_Check_email(ed_email.getText().toString().toLowerCase());
                    //Toast.makeText(O_Register.this, String.valueOf(res_check), Toast.LENGTH_SHORT).show();
                    if(String.valueOf(res_check)=="false")
                        res=db.own_insert(ed_fnm.getText().toString(),ed_lnm.getText().toString(),ed_email.getText().toString().toLowerCase(),ed_pass.getText().toString(),ed_phone.getText().toString());
                    else
                        Toast.makeText(O_Register.this,"This Email is already registeried",Toast.LENGTH_SHORT).show();

                    if(res) {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    send_mail(ed_email.getText().toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        Toast.makeText(O_Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        thread.start();
                        Intent i = new Intent(O_Register.this, O_Login.class);
                        startActivity(i);
                    }
                }
            }
            public void send_mail(String email)
            {
                try {
                    String msg = "Your account id Successfully created for Owner";
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
                        m.setSubject("Registration");
                        m.setText(msg);
                        Transport.send(m);
                        Toast.makeText(O_Register.this, "Email sent", Toast.LENGTH_SHORT).show();
                    } catch (MessagingException e) {
                        Toast.makeText(O_Register.this, "not sent", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(O_Register.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
        });

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

        if(ed_pass.getText().toString().length() >= 8)
        {
            count+=1;
            ed_pass.setBackground(draw_normal);
        }
        else {
            ed_pass.setBackground(draw_warn);
            count=0;
        }

        if(ed_phone.getText().toString().length() == 10)
        {
            count+=1;
            ed_phone.setBackground(draw_normal);
        }
        else {
            ed_phone.setBackground(draw_warn);
            count=0;
        }

        String regexPattern = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(ed_fnm.getText().toString());
        if(matcher.matches())
        {
            count+=1;
            ed_fnm.setBackground(draw_normal);
        }
        else {
            ed_fnm.setBackground(draw_warn);
            count=0;
        }

        matcher = pattern.matcher(ed_lnm.getText().toString());
        if(matcher.matches())
        {
            count+=1;
            ed_lnm.setBackground(draw_normal);
        }
        else {
            ed_lnm.setBackground(draw_warn);
            count=0;
        }


        if(count>4)
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