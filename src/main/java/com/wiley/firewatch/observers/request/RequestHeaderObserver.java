package com.wiley.firewatch.observers.request;

import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.observers.StringParamObserver;
import net.lightbody.bmp.core.har.HarNameValuePair;
import net.lightbody.bmp.core.har.HarRequest;

/**
 * Created by itatsiy on 4/24/2018.
 */
public class RequestHeaderObserver extends StringParamObserver<HarRequest> {
    private final String name;
    private final String value;

    public RequestHeaderObserver(String name, MatchingType type, String value) {
        super(type);
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean observe(HarRequest har) {
        String actual = null;
        for (HarNameValuePair pair : har.getHeaders()) {
            if (pair.getName().equals(name)) {
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
