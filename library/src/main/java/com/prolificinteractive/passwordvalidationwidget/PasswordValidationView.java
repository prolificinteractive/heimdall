package com.prolificinteractive.passwordvalidationwidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PasswordValidationView extends LinearLayout {

  private GridView gridView;
  private TextInputLayout passwordTil;
  private EditText passwordText;
  private TextView titleText;

  public PasswordValidationView(Context context) {
    this(context, null);
  }

  public PasswordValidationView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PasswordValidationView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public PasswordValidationView(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  /**
   * Initialize view
   */
  private void init() {
    setOrientation(VERTICAL);

    //Inflate xml resource, pass "this" as the parent, we use <merge> tag in xml to avoid
    //redundant parent, otherwise a LinearLayout will be added to this LinearLayout ending up
    //with two view groups
    inflate(getContext(), R.layout.view_password_validation, this);

    gridView = (GridView) findViewById(R.id.grid_view);
    passwordTil = (TextInputLayout) findViewById(R.id.password_til);
    passwordText = (EditText) findViewById(R.id.password_text);
    titleText = (TextView) findViewById(R.id.validation_title_text);
  }
}
