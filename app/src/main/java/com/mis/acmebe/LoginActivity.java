package com.mis.acmebe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

        private FirebaseAuth mAuth;
        private Button signinButtonGoogle;
        private Button signinButtonMail;
        private Button loginButtonSignup;
        private ProgressBar progressBar;
        private TextInputLayout loginEmailParent;
        private TextInputLayout loginPassParent;
        private AutoCompleteTextView loginEmail;
        private AutoCompleteTextView loginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.loginProgres);
        loginEmail = findViewById(R.id.loginEmailEt);
        loginPass = findViewById(R.id.loginPassEt);
        loginEmailParent = findViewById(R.id.loginEmail);
        loginPassParent = findViewById(R.id.loginPass);
        signinButtonGoogle = findViewById(R.id.loginButtonGoogle);
        signinButtonMail = findViewById(R.id.loginButtonMail);
        loginButtonSignup = findViewById(R.id.loginButtonRegister);

        signinButtonMail.setOnClickListener(l -> attempLoginEmail());
    }

    private void attempLoginEmail() {
        loginEmailParent.setError(null);
        loginPassParent.setError(null);

        if (loginEmail.getText().length()==0){
            loginEmailParent.setErrorEnabled(true);
            loginEmailParent.setError(getString(R.string.login_mail_error_1));
        } else if (loginPass.getText().length()==0){
            loginPassParent.setErrorEnabled(true);
            loginPassParent.setError(getString(R.string.login_pass_error));
            
        }else{
            signInEmail();
        }
    }

    private void signInEmail() {
        if (mAuth== null){
            mAuth= FirebaseAuth.getInstance();
        }
        if (mAuth != null){
            mAuth.signInWithEmailAndPassword(loginEmail.getText().toString(),loginPass.getText().toString()).addOnCompleteListener(this,task -> {
               if (!task.isSuccessful() || task.getResult().getUser()== null ){
                 showErrorDialogMail();
               } else if (!task.getResult().getUser().isEmailVerified()) {
                  showErrorEmailVerified(task.getResult().getUser());
               }else{
                   FirebaseUser user = task.getResult().getUser();
                   checkUserDatabaseLogin(user);
               }
            });
        }else{
            showGooglePlayServicesError();
        }
    }

    private void showGooglePlayServicesError() {
        Snackbar.make(loginButtonSignup, R.string.login_play_services_error, Snackbar.LENGTH_SHORT).setAction(R.string.login_download_gps, view -> {
           try{
               startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.gps_download_url))));
           }catch (Exception ex){
               startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.market_download_url))));

           }
        });
    }

    private void checkUserDatabaseLogin(FirebaseUser user) {
        //TODO: complete
    }

    private void showErrorEmailVerified(FirebaseUser user) {
        hideLoginButton(false);
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage(R.string.login_verified_mail_error)
        .setPositiveButton(R.string.login_verified_mail_error_ok,((dialog,wich) -> {
            user.sendEmailVerification().addOnCompleteListener(task1 ->{
                if (task1.isSuccessful()){
                    Snackbar.make(loginEmail, R.string.login_mail_error_sent, Snackbar.LENGTH_SHORT).show();
                }else{
                    Snackbar.make(loginEmail, R.string.login_mail_error_no_sent, Snackbar.LENGTH_SHORT).show();
                }
            });
        })).setNegativeButton(R.string.login_verified_mail_error_cancel, (dialog, which) -> {

        }).show();
    }

    private void showErrorDialogMail() {
        hideLoginButton(false);
        Snackbar.make(signinButtonMail, getString(R.string.login_mail_acces_error), Snackbar.LENGTH_SHORT).show();
    }

    private void hideLoginButton(boolean hide) {
        TransitionSet transitionSet = new TransitionSet();
        Transition layoutFade = new AutoTransition();
        layoutFade.setDuration(1000);
        transitionSet.addTransition(layoutFade);
        if (hide){
            TransitionManager.beginDelayedTransition(findViewById(R.id.loginMainLayout), transitionSet);
            progressBar.setVisibility(View.VISIBLE);
            signinButtonMail.setVisibility(View.GONE);
            signinButtonGoogle.setVisibility(View.GONE);
            loginButtonSignup.setVisibility(View.GONE);
            loginEmailParent.setEnabled(false);
            loginPassParent.setEnabled(false);
        }else{
            TransitionManager.beginDelayedTransition(findViewById(R.id.loginMainLayout), transitionSet);
            progressBar.setVisibility(View.GONE);
            signinButtonMail.setVisibility(View.VISIBLE);
            signinButtonGoogle.setVisibility(View.VISIBLE);
            loginButtonSignup.setVisibility(View.VISIBLE);
            loginEmailParent.setEnabled(true);
            loginPassParent.setEnabled(true);
        }
    }
}