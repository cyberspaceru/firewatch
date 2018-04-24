package com.wiley.firewatch.api;

import com.wiley.firewatch.api.enities.FirewatchAPIContext;
import com.wiley.firewatch.api.enities.ObserverMetadata;
import com.wiley.firewatch.api.enums.RelationshipType;
import com.wiley.firewatch.observers.IObserver;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Common API in order to create Firewatch Assert.
 * @param <T> Observable object.
 * @param <S> Current class to build fluent API.
 */
@Slf4j
@Accessors(fluent = true)
public abstract class FirewatchBlueprint<T, S extends FirewatchBlueprint> {
    private FirewatchAPIContext context = new FirewatchAPIContext();
    @Getter
    private List<ObserverMetadata<T>> observers = new ArrayList<>();
    @Getter
    private RelationshipType relationship;
    @Getter
    private FirewatchBlueprint parent;
    @Getter
    private FirewatchBlueprint child;

    FirewatchBlueprint(FirewatchBlueprint parent, RelationshipType relationship) {
        this.relationship = relationship;
        if (parent != null) {
            this.parent = parent;
            this.parent.child = this;
        }
    }

    public static FirewatchResponse response() {
        return new FirewatchResponse(null, RelationshipType.AND);
    }

    public static FirewatchRequest request() {
        return new FirewatchRequest(null, RelationshipType.AND);
    }

    public S not() {
        context.inverted(true);
        return self();
    }

    public FirewatchRequest andRequest() {
        return request(this, RelationshipType.AND);
    }

    public FirewatchResponse andResponse() {
        return response(this, RelationshipType.AND);
    }

    protected static FirewatchRequest request(FirewatchBlueprint parent, RelationshipType relationship) {
        return new FirewatchRequest(parent, relationship);
    }

    protected static FirewatchResponse response(FirewatchBlueprint parent, RelationshipType relationship) {
        return new FirewatchResponse(parent, relationship);
    }

    S observe(IObserver<T> observer) {
        observers.add(new ObserverMetadata<>(observer, context.inverted()));
        return self();
    }

    @SuppressWarnings("unchecked")
    S self() {
        return (S) this;
    }

    @Override
    public String toString() {
        return "[" + observers.stream().map(ObserverMetadata::toString).collect(Collectors.joining(" -> ")) + "]";
    }
}
