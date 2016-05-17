/*
 * Copyright (C) 2016 Prolific Interactive
 * Copyright (C) 2015 Flipagram, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.prolificinteractive.heimdall;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link android.text.TextWatcher} that calls the matching
 * {@link com.prolificinteractive.heimdall.PasswordCallbackTextWatcher.CallbackForPattern}
 * when the text at the selectionStart of the {@link android.widget.EditText} matches one
 * of the {@link com.prolificinteractive.heimdall.PasswordCallbackTextWatcher.PatternCallback}s
 */
public class PasswordCallbackTextWatcher implements TextWatcher {

  private final EditText editText;
  private int cursorPosition;

  private ValidationCallback validationCallback;
  private List<PatternCallback> patternCallbacks = new ArrayList<>();

  /**
   * Create a new {@link com.prolificinteractive.heimdall.PasswordCallbackTextWatcher}
   * and associate it with the given {@link android.widget.EditText}. You must
   * {@link android.widget.EditText#addTextChangedListener} the returned object to the
   * {@link android.widget.EditText}.
   */
  public PasswordCallbackTextWatcher(EditText editText, ValidationCallback callback) {
    this.editText = editText;
    this.validationCallback = callback;
  }

  /**
   * Add the {@link com.prolificinteractive.heimdall.PasswordCallbackTextWatcher}
   * to
   * the internal list.
   *
   * @return this for chaining
   */
  public PasswordCallbackTextWatcher addPatternCallback(PatternCallback patternCallback) {
    patternCallbacks.add(patternCallback);
    return this;
  }

  @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    cursorPosition = editText.getSelectionStart();
  }

  @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
    cursorPosition = Math.max(cursorPosition, editText.getSelectionStart());
  }

  @Override public void afterTextChanged(Editable s) {
    boolean allMatch = true;
    for (PatternCallback patternCallback : patternCallbacks) {
      if (s.toString().matches(patternCallback.check.regexPattern.pattern())) {
        patternCallback.callback.onMatch(patternCallback.check);
      } else {
        patternCallback.callback.noMatch(patternCallback.check);
        allMatch = false;
      }
    }
    validationCallback.onChecksCompleted(allMatch);
  }

  /**
   * When the text at the cursor matches, <code>onMatch</code> is called. If there are no matches
   * then noMatch is called
   */
  public interface CallbackForPattern {
    void onMatch(ValidationCheck check);

    void noMatch(ValidationCheck check);
  }

  /**
   * Associate a {@link java.util.regex.Pattern} with a
   * {@link com.prolificinteractive.heimdall.PasswordCallbackTextWatcher.CallbackForPattern}.
   */
  public static class PatternCallback {
    public ValidationCheck check;
    public CallbackForPattern callback;

    public PatternCallback(ValidationCheck check, CallbackForPattern callback) {
      this.check = check;
      this.callback = callback;
    }
  }

  public interface ValidationCallback {
    void onChecksCompleted(boolean allMatch);
  }
}