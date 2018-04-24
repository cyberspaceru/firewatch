package com.wiley.firewatch.observers.request;

import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.observers.StringParamObserver;
import net.lightbody.bmp.core.har.HarRequest;

/**
 * Created by itatsiy on 4/23/2018.
 */
public class RequestUrlObserver extends StringParamObserver<HarRequest> {
    private final String url;

    public RequestUrlObserver(MatchingType type, String url) {
        super(type);
        this.url = url;
    }

    @Override
    public boolean observe(HarRequest har) {
        return match(har.getUrl(), url);
    }

    @Override
    public String toString() {
        return "URL(matching='" + type + "', url='" + url + "')";
    }
}
