package com.wiley.firewatch.observers.request;

import com.wiley.firewatch.observers.IObserver;
import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.utils.StringMatcher;
import net.lightbody.bmp.core.har.HarNameValuePair;
import net.lightbody.bmp.core.har.HarPostDataParam;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;

/**
 * Created by itatsiy on 4/23/2018.
 */
public class RequestParameterObserver implements IObserver<HarRequest> {
    private final MatchingType nameMatchingType;
    private final String name;
    private final MatchingType valueMatchingType;
    private final String value;

    public RequestParameterObserver(MatchingType nameMatchingType, String name, MatchingType valueMatchingType, String value) {
        this.nameMatchingType = nameMatchingType;
        this.name = name;
        this.valueMatchingType = valueMatchingType;
        this.value = value;
    }

    @Override
    public boolean observe(HarRequest har) {
        String actual = null;
        if (har.getPostData() != null && har.getPostData().getParams() != null) {
            for (HarPostDataParam param : har.getPostData().getParams()) {
                if (StringMatcher.match(param.getName(), nameMatchingType, name)) {
                    actual = param.getValue();
                    break;
                }
            }
        } else if (har.getQueryString() != null) {
            for (HarNameValuePair pair : har.getQueryString()) {
                if (StringMatcher.match(pair.getName(), nameMatchingType, name)) {
                    actual = pair.getValue();
                    break;
                }
            }
        }
        return StringMatcher.match(actual, valueMatchingType, value);
    }

    @Override
    public String toString() {
        return "Parameter(nMatching='" + nameMatchingType + ", name='" + name + "', vMatching='" + valueMatchingType + "', value='" + value + "')";
    }
}
