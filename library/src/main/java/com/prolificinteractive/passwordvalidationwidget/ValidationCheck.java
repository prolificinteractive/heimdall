package com.prolificinteractive.passwordvalidationwidget;

import java.util.regex.Pattern;

public class ValidationCheck {
  public Pattern regexPattern;
  public String title;
  public boolean isMatched;

  public ValidationCheck(Pattern regexPattern, String title) {
    this.regexPattern = regexPattern;
    this.title = title;
  }

  public void setMatched(boolean isMatched) {
    this.isMatched = isMatched;
  }
}
