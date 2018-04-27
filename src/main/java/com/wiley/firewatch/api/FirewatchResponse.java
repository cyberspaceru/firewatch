package com.wiley.firewatch.api;

import com.wiley.firewatch.api.enums.RelationshipType;
import com.wiley.firewatch.observers.MatchingType;
import com.wiley.firewatch.observers.request.RequestHeaderObserver;
import com.wiley.firewatch.observers.responce.ResponseCodeObserver;
import com.wiley.firewatch.observers.responce.ResponseHeaderObserver;
import com.wiley.firewatch.utils.contenttype.ContentType;
import net.lightbody.bmp.core.har.HarResponse;

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
        return header(name, MatchingType.EQUALS, value);
    }

    public FirewatchResponse header(String name, MatchingType type, String value) {
        return observe(new ResponseHeaderObserver(name, type, value));
    }

    public FirewatchResponse contentType(ContentType contentType) {
        return header("Content-Type", MatchingType.EQUALS_IGNORE_CASE, contentType.toString());
    }

    @Override
    public String toString() {
        return "Response" + super.toString();
    }
}