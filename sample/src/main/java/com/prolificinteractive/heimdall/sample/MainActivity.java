package com.prolificinteractive.heimdall.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.prolificinteractive.heimdall.PasswordValidationView;
import com.prolificinteractive.heimdall.ValidationCheck;

public class MainActivity extends AppCompatActivity implements PasswordValidationView.Callback {

  private EditText passwordText;
  private PasswordValidationView passwordValidationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    passwordText = (EditText) findViewById(R.id.password_text);
    passwordValidationView =
        (PasswordValidationView) findViewById(R.id.password_validation_view);

    passwordText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override public void onFocusChange(View v, boolean hasFocus) {
        passwordValidationView.setVisibility(hasFocus ? View.VISIBLE : View.GONE);
      }
    });

    passwordValidationView.setupValidation(this,
        new ValidationCheck(
            PasswordRegexes.AT_LEAST_8_CHARACTERS,
            "Minimum 8 characters."),
        new ValidationCheck(
            PasswordRegexes.AT_LEAST_ONE_NUMBER,
            "At least one number."),
        new ValidationCheck(
            PasswordRegexes.AT_LEAST_ONE_SPECIAL,
            "At least one special character."),
        new ValidationCheck(
            PasswordRegexes.AT_LEAST_ONE_LOWER_CASE,
            "At least one lower case letter."),
        new ValidationCheck(
            PasswordRegexes.AT_LEAST_ONE_UPPER_CASE,
            "At least one upper case letter.")
    );
  }

  @Override public void onChecksCompleted(boolean allChecksMatch) {
    if (allChecksMatch) {
      Toast.makeText(this, "ALL PASS!", Toast.LENGTH_SHORT).show();
    }
  }
}