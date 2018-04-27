package com.wiley.firewatch.observers.responce;

import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.observers.StringParamObserver;
import net.lightbody.bmp.core.har.HarNameValuePair;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;

/**
 * Created by itatsiy on 4/24/2018.
 */
public class ResponseHeaderObserver extends StringParamObserver<HarResponse> {
    private final String name;
    private final String value;

    public ResponseHeaderObserver(String name, MatchingType type, String value) {
        super(type);
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean observe(HarResponse har) {
        String actual = null;
        for (HarNameValuePair pair : har.getHeaders()) {
            if (pair.getName().equalsIgnoreCase(name)) {
                actual = pair.getValue();
                break;
            }
        }
        return match(actual, value);
    }

    @Override
    public String toString() {
        return "Header(name='" + name + "', matching='" + type + "', value='" + value + "')";
    }
}
