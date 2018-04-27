package com.wiley.firewatch.api;

import com.wiley.firewatch.api.enums.RelationshipType;
import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.observers.responce.ResponseCodeObserver;
import com.wiley.firewatch.observers.responce.ResponseHeaderObserver;
import com.wiley.firewatch.utils.ContentType;
import net.lightbody.bmp.core.har.HarResponse;

import static com.wiley.firewatch.observers.MatchingType.EQUALS;
import static com.wiley.firewatch.observers.MatchingType.EQUALS_IGNORE_CASE;

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

    public FirewatchResponse contentType(ContentType contentType) {
        return observe(new ResponseHeaderObserver(EQUALS, "Content-Type", EQUALS_IGNORE_CASE, contentType.toString()));
    }

    @Override
    public String toString() {
        return "Response" + super.toString();
    }
}
