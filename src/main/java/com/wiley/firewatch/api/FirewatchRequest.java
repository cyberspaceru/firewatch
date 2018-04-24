package com.wiley.firewatch.api;

import com.wiley.firewatch.api.enums.RelationshipType;
import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.observers.request.RequestHeaderObserver;
import com.wiley.firewatch.observers.request.RequestMethodObserver;
import com.wiley.firewatch.observers.request.RequestParameterObserver;
import com.wiley.firewatch.observers.request.RequestUrlObserver;
import io.netty.handler.codec.http.HttpMethod;
import net.lightbody.bmp.core.har.HarRequest;

/**
 * Created by itatsiy on 4/23/2018.
 */
public class FirewatchRequest extends Firewatch<HarRequest, FirewatchRequest> {
    FirewatchRequest(FirewatchBlueprint parent, RelationshipType relationship) {
        super(parent, relationship);
    }

    public FirewatchResponse thenResponse() {
        return response(this, RelationshipType.THEN);
    }

    public FirewatchRequest url(MatchingType type, String url) {
        return observe(new RequestUrlObserver(type, url));
    }

    public FirewatchRequest method(HttpMethod method) {
        return observe(new RequestMethodObserver(method));
    }

    public FirewatchRequest headerEquals(String name, String value) {
        return header(name, MatchingType.EQUALS, value);
    }

    public FirewatchRequest header(String name, MatchingType type, String value) {
        return observe(new RequestHeaderObserver(name, type, value));
    }

    public FirewatchRequest parameterEquals(String name, String value) {
        return parameter(name, MatchingType.EQUALS, value);
    }

    public FirewatchRequest parameter(String name, MatchingType type, String value) {
        return observe(new RequestParameterObserver(name, type, value));
    }

    @Override
    public String toString() {
        return "Request" + super.toString();
    }
}
