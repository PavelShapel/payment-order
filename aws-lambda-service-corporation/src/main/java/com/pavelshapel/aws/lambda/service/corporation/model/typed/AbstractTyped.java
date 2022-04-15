package com.pavelshapel.aws.lambda.service.corporation.model.typed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Named;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import lombok.*;

@EqualsAndHashCode
@ToString
public abstract class AbstractTyped implements Typed<Type>, Named {
    @JsonIgnore
    private final Type type;
    @Getter
    @Setter
    private String name;

    protected AbstractTyped() {
        this.type = Type.valueOf(getClass().getSimpleName().toUpperCase());
    }

    @JsonProperty
    public Type getType() {
        return type;
    }

    @JsonIgnore
    public void setType(Type type) {
        //unused
    }
}
