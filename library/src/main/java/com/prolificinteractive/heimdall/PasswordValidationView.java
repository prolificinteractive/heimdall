package com.prolificinteractive.heimdall;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.prolificinteractive.heimdall.PasswordCallbackTextWatcher.CallbackForPattern;
import com.prolificinteractive.heimdall.PasswordCallbackTextWatcher.ValidationCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordValidationView extends LinearLayout
    implements CallbackForPattern, ValidationCallback {

  private final List<ValidationCheck> items = new ArrayList<>();
  // attribute fields
  private String headerTextString;
  private int headerTextAppearance;
  private Drawable itemDrawableMatch;
  private Drawable itemDrawableNoMatch;
  private int itemTextAppearance;
  private int passwordEditTextId;
  private Callback callback;
  private ValidationChecksAdapter adapter;

  public PasswordValidationView(Context context) {
    this(context, null);
  }

  public PasswordValidationView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PasswordValidationView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setupAttributes(attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public PasswordValidationView(
      Context context,
      AttributeSet attrs,
      int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    setupAttributes(attrs);
  }


  @Override public void onMatch(ValidationCheck check) {
    adapter.setMatch(check);
  }

  @Override public void noMatch(ValidationCheck check) {
    adapter.setNoMatch(check);
  }

  @Override public void onChecksCompleted(boolean allChecksMatch) {
    adapter.notifyDataSetChanged();

    if (callback != null) {
      callback.onChecksCompleted(allChecksMatch);
    }
  }

  public void setupValidation(Callback callback, ValidationCheck... fields) {
    if (!items.isEmpty()) {
      throw new IllegalStateException("Already initialized");
    }

    this.callback = callback;

    Collections.addAll(items, fields);

    init();
  }

  /**
   * Initialize view
   */
  private void init() {
    setOrientation(VERTICAL);

    final EditText passwordText = (EditText) getRootView().findViewById(passwordEditTextId);
    final TextView headerText = new TextView(getContext());
    final RecyclerView recyclerView = new RecyclerView(getContext());

    headerText.setText(headerTextString);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      headerText.setTextAppearance(headerTextAppearance);
    } else {
      headerText.setTextAppearance(getContext(), headerTextAppearance);
    }

    addView(headerText);
    addView(recyclerView);

    adapter = new ValidationChecksAdapter(
        itemTextAppearance,
        itemDrawableMatch,
        itemDrawableNoMatch
    );

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
      @Override public boolean canScrollVertically() {
        return false;
      }
    });

    // initialize our callback listener for password field
    PasswordCallbackTextWatcher passwordCallbackTextWatcher =
        new PasswordCallbackTextWatcher(passwordText, this);

    for (ValidationCheck field : items) {
      passwordCallbackTextWatcher.addPatternCallback(
          new PasswordCallbackTextWatcher.PatternCallback(field, this));
    }
    adapter.swapData(items);
    passwordText.addTextChangedListener(passwordCallbackTextWatcher);
  }

  private void setupAttributes(AttributeSet attrs) {
    final TypedArray a = getContext().getTheme()
        .obtainStyledAttributes(attrs, R.styleable.PasswordValidationView, 0, 0);
    try {
      passwordEditTextId = a.getResourceId(R.styleable.PasswordValidationView_pvv_editTextId, -1);
      headerTextString = a.getString(R.styleable.PasswordValidationView_pvv_headerText);
      itemDrawableMatch =
          a.getDrawable(R.styleable.PasswordValidationView_pvv_itemDrawableMatch);
      itemDrawableNoMatch =
          a.getDrawable(R.styleable.PasswordValidationView_pvv_itemDrawableNoMatch);
      itemTextAppearance = a.getResourceId(
          R.styleable.PasswordValidationView_pvv_itemTextAppearance,
          R.style.TextAppearance_PasswordValidationView_Header
      );
      headerTextAppearance = a.getResourceId(
          R.styleable.PasswordValidationView_pvv_headerTextAppearance,
          R.style.TextAppearance_PasswordValidationView_Header
      );
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      a.recycle();
    }
  }

  public interface Callback {
    void onChecksCompleted(boolean allChecksMatch);
  }
}