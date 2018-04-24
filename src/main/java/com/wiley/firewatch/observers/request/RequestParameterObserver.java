package com.wiley.firewatch.observers.request;

import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.observers.StringParamObserver;
import net.lightbody.bmp.core.har.HarNameValuePair;
import net.lightbody.bmp.core.har.HarPostDataParam;
import net.lightbody.bmp.core.har.HarRequest;

/**
 * Created by itatsiy on 4/23/2018.
 */
public class RequestParameterObserver extends StringParamObserver<HarRequest> {
    private final String name;
    private final String value;

    public RequestParameterObserver(String name, MatchingType type, String value) {
        super(type);
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean observe(HarRequest har) {
        String actual = null;
        if (har.getPostData() != null) {
            for (HarPostDataParam param : har.getPostData().getParams()) {
                if (param.getName().equals(name)) {
                    actual = param.getValue();
                    break;
                }
            }
        } else {
            for (HarNameValuePair pair : har.getQueryString()) {
                if (pair.getName().equals(name)) {
                    actual = pair.getValue();
                    break;
                }
            }
        }
        return match(actual, value);
    }

    @Override
    public String toString() {
        return "Parameter(name='" + name + "', matching='" + type + "', value='" + value + "')";
    }
}
