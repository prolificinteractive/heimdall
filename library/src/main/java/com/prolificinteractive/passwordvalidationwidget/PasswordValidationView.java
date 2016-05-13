package com.prolificinteractive.passwordvalidationwidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.prolificinteractive.passwordvalidationwidget.PasswordCallbackTextWatcher.CallbackForPattern;
import com.prolificinteractive.passwordvalidationwidget.PasswordCallbackTextWatcher.ValidationCallback;
import java.util.ArrayList;
import java.util.List;

public class PasswordValidationView extends LinearLayout
    implements CallbackForPattern, ValidationCallback {

  private static final String TAG = PasswordValidationView.class.getSimpleName();

  private RecyclerView recyclerView;
  private TextInputLayout passwordTil;
  private EditText passwordText;
  private TextView titleText;

  private PasswordCallbackTextWatcher passwordCallbackTextWatcher;
  private ValidationChecksAdapter adapter;
  private final List<ValidationCheck> items = new ArrayList<>();

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

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    passwordTil = (TextInputLayout) findViewById(R.id.password_til);
    passwordText = (EditText) findViewById(R.id.password_text);
    titleText = (TextView) findViewById(R.id.validation_title_text);

    adapter = new ValidationChecksAdapter();

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

    // initialize our callback listener for password field
    passwordCallbackTextWatcher =
        new PasswordCallbackTextWatcher(passwordText, this);
  }

  public void setupValidation(ValidationCheck... fields) {
    for (ValidationCheck field : fields) {
      items.add(field);
      passwordCallbackTextWatcher.addPatternCallback(
          new PasswordCallbackTextWatcher.PatternCallback(field, this));
    }
    adapter.swapData(items);
    passwordText.addTextChangedListener(passwordCallbackTextWatcher);
  }

  @Override public void onMatch(ValidationCheck check) {
    Log.d(TAG, "Got Match! for check: " + check.toString());
    adapter.setMatch(check);
  }

  @Override public void noMatch(ValidationCheck check) {
    Log.d(TAG, "No Match! for check: " + check.toString());
    adapter.setNoMatch(check);
  }

  @Override public void onChecksCompleted() {
    adapter.notifyDataSetChanged();
  }
}
