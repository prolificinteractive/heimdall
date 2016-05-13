package com.prolificinteractive.passwordvalidationwidget;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.regex.Pattern;

public class ValidationCheck implements Parcelable {
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

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ValidationCheck that = (ValidationCheck) o;

    if (!regexPattern.equals(that.regexPattern)) {
      return false;
    }
    return title.equals(that.title);
  }

  @Override public int hashCode() {
    int result = regexPattern.hashCode();
    result = 31 * result + title.hashCode();
    return result;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeSerializable(this.regexPattern);
    dest.writeString(this.title);
    dest.writeByte(isMatched ? (byte) 1 : (byte) 0);
  }

  @Override public String toString() {
    return "ValidationCheck{" +
        "regexPattern=" + regexPattern +
        ", title='" + title + '\'' +
        ", isMatched=" + isMatched +
        '}';
  }

  protected ValidationCheck(Parcel in) {
    this.regexPattern = (Pattern) in.readSerializable();
    this.title = in.readString();
    this.isMatched = in.readByte() != 0;
  }

  public static final Parcelable.Creator<ValidationCheck> CREATOR =
      new Parcelable.Creator<ValidationCheck>() {
        public ValidationCheck createFromParcel(Parcel source) {
          return new ValidationCheck(source);
        }

        public ValidationCheck[] newArray(int size) {
          return new ValidationCheck[size];
        }
      };
}
