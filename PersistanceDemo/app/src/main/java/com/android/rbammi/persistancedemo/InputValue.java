package com.android.rbammi.persistancedemo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by rbammi on 11/5/15.
 */

@Table(name = "input_values")
public class InputValue extends Model {
    @Column(name = "text")
    public String text;

    public InputValue() {
        super();
    }

    public InputValue(String text) {
        super();
        this.text = text;

    }

    public static InputValue queryMostRecent() {
        return new Select().from(InputValue.class)
                .orderBy("id DESC").limit("1").executeSingle();
    }
}
