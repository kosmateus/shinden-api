package com.github.kosmateus.shinden.common.enums.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TargetGroup implements Tag {
    KIDS(218, "tags.target-group.kids"),
    JOSEI(39, "tags.target-group.josei"),
    SEINEN(48, "tags.target-group.seinen"),
    SHOUJO(128, "tags.target-group.shoujo"),
    SHOUNEN(23, "tags.target-group.shounen");

    private final Integer id;
    private final String translationKey;

    @Override
    public String getTagType() {
        return "targetgroup";
    }

    @Override
    public String getQueryParameter() {
        return "tag";
    }

    @Override
    public String getQueryValue() {
        return String.valueOf(id);
    }
}
