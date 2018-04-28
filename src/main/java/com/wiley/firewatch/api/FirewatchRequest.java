package com.wiley.firewatch.api;

import com.wiley.firewatch.api.enums.RelationshipType;
import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.observers.request.*;
import com.wiley.firewatch.observers.responce.ResponceJsonObserver;
import com.wiley.firewatch.observers.responce.ResponseHeaderObserver;
import com.wiley.firewatch.utils.ContentType;
import io.netty.handler.codec.http.HttpMethod;
import net.lightbody.bmp.core.har.HarRequest;

import java.util.function.BiPredicate;

import static com.wiley.firewatch.observers.MatchingType.EQUALS_IGNORE_CASE;
import static com.wiley.firewatch.observers.MatchingType.REGEXP_CASE_INSENSITIVE;

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
        return observe(new RequestHeaderObserver(MatchingType.EQUALS, name, type, value));
    }

    public <K> FirewatchRequest jsonEquals(Class<K> objectClass, K instance) {
        return observe(new RequestJsonObserver<>(objectClass, instance, Object::equals));
    }

    public <K> FirewatchRequest json(Class<K> objectClass, K instance, BiPredicate<K, K> predicate) {
        return observe(new RequestJsonObserver<>(objectClass, instance, predicate));
    }

    public FirewatchRequest contentType(ContentType contentType) {
        return observe(new RequestHeaderObserver(EQUALS_IGNORE_CASE, "Content-Type", REGEXP_CASE_INSENSITIVE, contentType.toString()));
    }

    public FirewatchRequest parameterEquals(String name, String value) {
        return parameter(name, MatchingType.EQUALS, value);
    }

    public FirewatchRequest parameter(String name, MatchingType type, String value) {
        return observe(new RequestParameterObserver(MatchingType.EQUALS, name, type, value));
    }

    @Override
    public String toString() {
        return "Request" + super.toString();
    }
}
