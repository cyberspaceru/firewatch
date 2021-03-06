package com.wiley.firewatch.observers.request;

import com.wiley.firewatch.observers.IObserver;
import com.wiley.firewatch.observers.common.JsonObserver;
import com.wiley.firewatch.observers.common.TextObserver;
import net.lightbody.bmp.core.har.HarRequest;

import java.util.function.BiPredicate;

/**
 * Created by itatsiy on 4/28/2018.
 */
public class RequestTextPostDataObserver extends TextObserver implements IObserver<HarRequest> {
    public RequestTextPostDataObserver(String expected, BiPredicate<String, String> predicate) {
        super(expected, predicate);
    }

    @Override
    public boolean observe(HarRequest har) {
        return observeText(har.getPostData() == null ? null : har.getPostData().getText());
    }
}
