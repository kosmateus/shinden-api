package com.github.kosmateus.shinden.user.common;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
public class BaseSettings {
    private final PageSettings pageSettings;
    private final ReadTimeSettings readTimeSettings;
}
