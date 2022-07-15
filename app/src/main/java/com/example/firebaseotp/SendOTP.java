package com.example.firebaseotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTP extends AppCompatActivity {
    EditText editTextVerification1 , editTextVerification2 , editTextVerification3 , editTextVerification4 , editTextVerification5 , editTextVerification6;
    TextView textViewGetPhone , textViewResendOTP;
    Button buttonVerify;
    String getBackEndOTP ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken!=null && accessToken.isExpired()==false){
            startActivity(new Intent(SendOTP.this,Home.class));
            finish();
        }

        editTextVerification1 = findViewById(R.id.verificationCode1);
        editTextVerification2 = findViewById(R.id.verificationCode2);
        editTextVerification3 = findViewById(R.id.verificationCode3);
        editTextVerification4 = findViewById(R.id.verificationCode4);
        editTextVerification5 = findViewById(R.id.verificationCode5);
        editTextVerification6 = findViewById(R.id.verificationCode6);

        textViewGetPhone = findViewById(R.id.getPhoneNumber);
        textViewGetPhone.setText(String.format(
                "+92-%s" , getIntent().getStringExtra("mobile")
        ));

        getBackEndOTP = getIntent().getStringExtra("backEndOTP");

        buttonVerify = findViewById(R.id.verifyButton);
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verficationCode1 = editTextVerification1.getText().toString().trim();
                String verficationCode2 = editTextVerification2.getText().toString().trim();
                String verficationCode3 = editTextVerification3.getText().toString().trim();
                String verficationCode4 = editTextVerification4.getText().toString().trim();
                String verficationCode5 = editTextVerification5.getText().toString().trim();
                String verficationCode6 = editTextVerification6.getText().toString().trim();
                if (!verficationCode1.isEmpty() && !verficationCode2.isEmpty() && !verficationCode3.isEmpty() && !verficationCode4.isEmpty() && !verficationCode5.isEmpty() && !verficationCode6.isEmpty()){

                    String enterCodeOTP = editTextVerification1.getText().toString()+
                            editTextVerification2.getText().toString()+
                            editTextVerification3.getText().toString()+
                            editTextVerification4.getText().toString()+
                            editTextVerification5.getText().toString()+
                            editTextVerification6.getText().toString();
                    if (getBackEndOTP != null){
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getBackEndOTP , enterCodeOTP
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            Intent intent = new Intent(getApplicationContext() , Home.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(SendOTP.this, "Your OTP is wrong", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }else{
                        Toast.makeText(SendOTP.this, "Please check your internet connection", Toast.LENGTH_LONG).show();
                    }

//            Toast.makeText(this, "OTP verified", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SendOTP.this, "Enter all numbers", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textViewResendOTP = findViewById(R.id.resendOTP);
        textViewResendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+92" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        SendOTP.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(SendOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackEndOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getBackEndOTP = newbackEndOTP ;
                                Toast.makeText(SendOTP.this, "OTP send successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });

        OTPCursor();
    }
    private void OTPCursor() {

        editTextVerification1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    editTextVerification2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextVerification2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    editTextVerification3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextVerification3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    editTextVerification4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextVerification4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    editTextVerification5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextVerification5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    editTextVerification6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}