package com.prolificinteractive.heimdall.sample;

import java.util.regex.Pattern;

public class PasswordRegexes {
  private static final String EIGHT_CHARS = ".{8,}";
  private static final String ONE_SPECIAL = "^.*(?=.*[!@#$%^&*()-+=?<>]).*$";
  private static final String ONE_NUMBER = ".*[0-9].*";
  private static final String ONE_LOWER = ".*[a-z].*";
  private static final String ONE_UPPER = ".*[A-Z].*";

  public static final Pattern AT_LEAST_8_CHARACTERS = Pattern.compile(EIGHT_CHARS);
  public static final Pattern AT_LEAST_ONE_SPECIAL = Pattern.compile(ONE_SPECIAL);
  public static final Pattern AT_LEAST_ONE_NUMBER = Pattern.compile(ONE_NUMBER);
  public static final Pattern AT_LEAST_ONE_LOWER_CASE = Pattern.compile(ONE_LOWER);
  public static final Pattern AT_LEAST_ONE_UPPER_CASE = Pattern.compile(ONE_UPPER);
}
