package com.ciandt.internstellarapi.helper;

import java.io.Serializable;

/**
 * Created by rodrigosclosa on 23/09/16.
 */

public class RestHeaders implements Serializable {
    private String key;
    private String value;

    public RestHeaders(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
