package org.jan.model;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Note(
        String text
) {
}
