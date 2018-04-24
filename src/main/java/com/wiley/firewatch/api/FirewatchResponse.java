package com.wiley.firewatch.api;

import com.wiley.firewatch.api.enums.RelationshipType;
import com.wiley.firewatch.observers.responce.ResponseCodeObserver;
import net.lightbody.bmp.core.har.HarResponse;

/**
 * Created by itatsiy on 4/23/2018.
 */
public class FirewatchResponse extends Firewatch<HarResponse, FirewatchResponse> {
    FirewatchResponse(FirewatchBlueprint parent, RelationshipType relationship) {
        super(parent, relationship);
    }

    public FirewatchResponse status(int expected) {
        return observe(new ResponseCodeObserver(expected));
    }

    @Override
    public String toString() {
        return "Response" + super.toString();
    }
}
