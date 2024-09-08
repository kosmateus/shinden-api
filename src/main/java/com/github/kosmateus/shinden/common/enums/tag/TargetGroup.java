package com.github.kosmateus.shinden.common.enums.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration representing different target groups for tagging purposes in the application.
 *
 * <p>Each target group is associated with a unique identifier and a translation key for localization
 * purposes, along with predefined query parameters used for API searches.</p>
 *
 * @version 1.0.0
 */
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
    private final String tagType = "targetgroup";
    private final String queryParameter = "tag";
    private final String animeSearchQueryParameter = "genres";

    @Override
    public String getQueryValue() {
        return String.valueOf(id);
    }
}
