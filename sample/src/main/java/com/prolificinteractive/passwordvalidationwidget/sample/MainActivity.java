package com.prolificinteractive.passwordvalidationwidget.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.prolificinteractive.passwordvalidationwidget.PasswordRegexes;
import com.prolificinteractive.passwordvalidationwidget.PasswordValidationView;
import com.prolificinteractive.passwordvalidationwidget.ValidationCheck;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    PasswordValidationView passwordValidationView =
        (PasswordValidationView) findViewById(R.id.password_validation_view);
    passwordValidationView.setupValidation(
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
}
