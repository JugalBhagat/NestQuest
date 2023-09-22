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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class T_Registration extends AppCompatActivity {
    SqlOp db;
    EditText ed_fname,ed_lname,ed_phone,ed_email,ed_pass,ed_age;
    Button btn_reg;
    RadioGroup rb_group;
    RadioButton rb;
    RadioButton rb_male,rb_female;
    TextView hyp_link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tregistration);

        hyp_link=findViewById(R.id.lnkLogin);
        ed_fname=findViewById(R.id.txt_firstname);
        ed_lname=findViewById(R.id.txt_lastname);
        ed_email=findViewById(R.id.txt_email);
        ed_phone=findViewById(R.id.txt_phone);
        ed_pass=findViewById(R.id.txt_pass);
        ed_age=findViewById(R.id.txt_age);

        rb_male=findViewById(R.id.rb_male);
        rb_female=findViewById(R.id.rb_female);
        rb_group=findViewById(R.id.rb_group);
        btn_reg=findViewById(R.id.btn_register);

        String text="Already Registered? Login";
        SpannableString ss=new SpannableString(text);
        ClickableSpan cs=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {

                startActivity(new Intent(T_Registration.this,T_Login.class));
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
                    try {
                        int id = rb_group.getCheckedRadioButtonId();
                        rb = findViewById(id);
                        String gender = rb.getText().toString();

                        SqlOp db2 = new SqlOp(getApplicationContext());
                        boolean res_check = db2.tenent_Check_email(ed_email.getText().toString().toLowerCase());
                        Boolean res = false;
                        // Toast.makeText(T_Registration.this,"hello",Toast.LENGTH_SHORT).show();
                        if (String.valueOf(res_check) == "false") {
                            SqlOp db = new SqlOp(T_Registration.this);
                            res = db.reg_insert(ed_fname.getText().toString(), ed_lname.getText().toString(), ed_email.getText().toString().toLowerCase(), ed_pass.getText().toString(), ed_phone.getText().toString(), ed_age.getText().toString(), gender);
                        } else
                            Toast.makeText(T_Registration.this, "Email Already used", Toast.LENGTH_SHORT).show();
                        // Toast.makeText(T_Registration.this, String.valueOf(res), Toast.LENGTH_SHORT).show();

                        if (res) {
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
                            Toast.makeText(T_Registration.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            thread.start();
                            Intent i = new Intent(T_Registration.this, T_Login.class);
                            startActivity(i);
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(T_Registration.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            private void send_mail(String email) {
                try {
                    String msg = "Your account id Successfully created ";
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

                    } catch (MessagingException e) {
                        Toast.makeText(T_Registration.this, "not sent", Toast.LENGTH_SHORT).show();
                    }
                    //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                   // StrictMode.setThreadPolicy(policy);

                }catch (Exception e)
                {
                    Toast.makeText(T_Registration.this,e.toString(),Toast.LENGTH_SHORT).show();
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
        Matcher matcher = pattern.matcher(ed_fname.getText().toString());
        if(matcher.matches())
        {
            count+=1;
            ed_fname.setBackground(draw_normal);
        }
        else {
            ed_fname.setBackground(draw_warn);
            count=0;
        }

        matcher = pattern.matcher(ed_lname.getText().toString());
        if(matcher.matches())
        {
            count+=1;
            ed_lname.setBackground(draw_normal);
        }
        else {
            ed_lname.setBackground(draw_warn);
            count=0;
        }
        regexPattern = "^\\d{1,3}$";
        pattern = Pattern.compile(regexPattern);
        matcher = pattern.matcher(ed_age.getText().toString());
        if(matcher.matches())
        {
            count+=1;
            ed_age.setBackground(draw_normal);
        }
        else {
            ed_age.setBackground(draw_warn);
            count=0;
        }

        if(rb_male.isChecked() || rb_female.isChecked())
        {
            count+=1;
            //rb_group.setBackground(draw_normal);
        }
        else {
            rb_group.setBackground(draw_warn);
            count=0;
        }

        if(count>6)
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