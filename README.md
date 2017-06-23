Heimdall
========

A collection of useful views/widgets that guard your app.

Currently contains:
- PasswordValidationView - A simple view which provides password criteria matching via Regex for your password fields. 

<img src="/images/demo1.gif" alt="Demo Screen Capture" width="300px" />

Usage
-----

1. Add `compile 'com.prolificinteractive:heimdall:0.0.1-SNAPSHOT'` to your dependencies.
2. Place the `PasswordValidationView` below your password `EditText` field in your layout.
3. Make sure to set add `app:pvv_editTextId="@id/your_password_field_id"` to the PasswordValidationView attributes.
4. Call `PasswordValidationView.setupValidation(Callback callback, ValidationCheck... fields)` and pass in the required callback and your validation checks (Regex).

Example:

```xml
  <android.support.design.widget.TextInputLayout
        android:id="@+id/password_til"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="8dp"
        >
      <EditText
          android:id="@+id/password_text"
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:hint="@string/hint_password"
          android:inputType="textPassword"
          />
    </android.support.design.widget.TextInputLayout>
    <com.prolificinteractive.heimdall.PasswordValidationView
        android:id="@+id/password_validation_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="8dp"
        android:visibility="gone"
        app:pvv_editTextId="@id/password_text"
        app:pvv_headerText="@string/password_must_include"
        app:pvv_headerTextAppearance="@style/CustomHeaderTextAppearance"
        app:pvv_itemDrawableMatch="@drawable/ic_check_black_24dp"
        app:pvv_itemDrawableNoMatch="@drawable/ic_close_black_24dp"
        app:pvv_itemTextAppearance="@style/CustomItemTextAppearance"
        app:pvv_headerTextVisiblity="visible"
        app:pvv_noOfColumns="2"
        >
    </com.prolificinteractive.heimdall.PasswordValidationView>
```

Customization
-------------

Currently supported XML attributes:

- `pvv_editTextId` Your password field's view id.
- `pvv_headerText` Set the text for the header text (above the validation checks grid).
- `pvv_headerTextAppearance` Set the header's appearance.
- `pvv_itemDrawableMatch` Set the icon when the entered text matches the validation check.
- `pvv_itemDrawableNoMatch` Set the icon when the entered text does NOT match the validation check (Default state).
- `pvv_itemTextAppearance` Set items text appearance (You can set your selected/unselected state here).
- `pvv_headerTextVisiblity` Set the visiblity of the header (visible, invisible, gone)
- `pvv_noOfColumns` Set the number of columns used to display the items (min 1, max 5)

Make sure to check sample for a more concrete example.

Contributing
============

Would you like to contribute? Fork us and send a pull request! Be sure to checkout our issues first.

## License

The Heimdall library is Copyright (c) 2016 Prolific Interactive. It may be redistributed under the terms specified in the [LICENSE] file.

[LICENSE]: /LICENSE

## Maintainers

![prolific](https://s3.amazonaws.com/prolificsitestaging/logos/Prolific_Logo_Full_Color.png)

Heimdall is maintained and funded by Prolific Interactive.

Heimdall (name and character) is a copyright of Marvel.