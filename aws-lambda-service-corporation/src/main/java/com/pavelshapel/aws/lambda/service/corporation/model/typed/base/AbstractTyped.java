package com.pavelshapel.aws.lambda.service.corporation.model.typed.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pavelshapel.aws.lambda.service.corporation.model.Type;
import com.pavelshapel.core.spring.boot.starter.api.model.Named;
import com.pavelshapel.core.spring.boot.starter.api.model.Typed;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractTyped implements Typed<Type>, Named {
    @JsonIgnore
    Type type;
    String name;

    @JsonProperty
    public Type getType() {
        return type;
    }

    @JsonIgnore
    public void setType(Type type) {
        this.type = type;
    }

    protected abstract void postConstruct();
}
