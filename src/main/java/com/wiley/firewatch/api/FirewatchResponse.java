package com.wiley.firewatch.api;

import com.wiley.firewatch.api.enums.RelationshipType;
import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.observers.responce.ResponseJsonContentObserver;
import com.wiley.firewatch.observers.responce.ResponseCodeObserver;
import com.wiley.firewatch.observers.responce.ResponseHeaderObserver;
import com.wiley.firewatch.observers.responce.ResponseTextContentObserver;
import com.wiley.firewatch.utils.ContentType;
import com.wiley.firewatch.utils.StringMatcher;
import net.lightbody.bmp.core.har.HarResponse;

import java.util.function.BiPredicate;

import static com.wiley.firewatch.observers.MatchingType.*;

/**
 * Created by itatsiy on 4/23/2018.
 */
public class FirewatchResponse extends Firewatch<HarResponse, FirewatchResponse> {
    FirewatchResponse(FirewatchBlueprint parent, RelationshipType relationship) {
        super(parent, relationship);
    }

    public FirewatchResponse status(int status) {
        return observe(new ResponseCodeObserver(status));
    }

    public FirewatchResponse headerEquals(String name, String value) {
        return header(name, EQUALS, value);
    }

    public FirewatchResponse header(String name, MatchingType type, String value) {
        return observe(new ResponseHeaderObserver(EQUALS, name, type, value));
    }

    public <K> FirewatchResponse jsonContentEquals(Class<K> objectClass, K instance) {
        return observe(new ResponseJsonContentObserver<>(objectClass, instance, Object::equals));
    }

    public <K> FirewatchResponse jsonContent(Class<K> objectClass, K instance, BiPredicate<K, K> predicate) {
        return observe(new ResponseJsonContentObserver<>(objectClass, instance, predicate));
    }

    public FirewatchResponse textContent(MatchingType matchingType, String expected) {
        return observe(new ResponseTextContentObserver(expected, (a, e) -> StringMatcher.match(a, matchingType, e)));
    }

    public FirewatchResponse textContent(String expected, BiPredicate<String, String> predicate) {
        return observe(new ResponseTextContentObserver(expected, predicate));
    }

    public FirewatchResponse contentType(ContentType contentType) {
        return observe(new ResponseHeaderObserver(EQUALS_IGNORE_CASE, "Content-Type", REGEXP_CASE_INSENSITIVE, contentType.toString()));
    }

    @Override
    public String toString() {
        return "Response" + super.toString();
    }
}
