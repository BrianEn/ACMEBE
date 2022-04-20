package com.mis.acmebe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    public static final String EMAIL_PARAM = "email_parameter";
    private AutoCompleteTextView loginEmailEt;
    private AutoCompleteTextView loginPassEt;
    private AutoCompleteTextView loginPassConfirmationEt;

    private TextInputLayout loginEmail;
    private TextInputLayout loginPass;
    private TextInputLayout loginPassConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        String emailParam = getIntent().getStringExtra(EMAIL_PARAM);

        loginEmailEt = findViewById(R.id.loginEmailEt);
        loginPassEt = findViewById(R.id.loginPassEt);
        loginPassConfirmationEt = findViewById(R.id.loginPassConfirmationEt);

        loginEmail = findViewById(R.id.loginEmail);
        loginPass = findViewById(R.id.loginPass);
        loginPassConfirmation = findViewById(R.id.loginPassConfirmation);

        loginEmailEt.setText(emailParam);

findViewById(R.id.signUpButton).setOnClickListener(l ->{
    if (loginEmailEt.getText().length()==0){
        loginEmail.setErrorEnabled(true);
        loginEmail.setError(getString(R.string.signup_error_user));
    }else if(loginPassEt.getText().length()==0){
        loginPass.setErrorEnabled(true);
        loginPass.setError(getString(R.string.signup_error_pass));
    }else if(loginPassConfirmationEt.getText().toString().equals(loginPassEt.getText().toString())){
        loginPassConfirmation.setErrorEnabled(true);
        loginPassConfirmation.setError(getString(R.string.signup_error_pass_not_match));
    }else {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(loginEmailEt.getText().toString(), loginPassEt.getText().toString()).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(this, R.string.signup_created, Toast.LENGTH_SHORT).show();
                SignUpActivity.this.finish();
            }else{
                Toast.makeText(this, R.string.signup_create_error, Toast.LENGTH_SHORT).show();

            }
        });
    }
});

    }
}