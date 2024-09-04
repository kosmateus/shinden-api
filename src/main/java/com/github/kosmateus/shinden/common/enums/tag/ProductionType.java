package com.github.kosmateus.shinden.common.enums.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductionType implements Tag {
    GRAPHICS_2_5D(2658, "tags.production-type.graphics-2-5d"),
    ANIMATION_3D(2617, "tags.production-type.animation-3d"),
    CHINESE_ANIMATION(2343, "tags.production-type.chinese-animation"),
    KOREAN_ANIMATION(2634, "tags.production-type.korean-animation"),
    ANTHOLOGY(2747, "tags.production-type.anthology"),
    NO_DIALOGUES(2743, "tags.production-type.no-dialogues"),
    CHINESE_JAPANESE_COPRODUCTION(2604, "tags.production-type.chinese-japanese-coproduction"),
    BLACK_AND_WHITE(2819, "tags.production-type.black-and-white"),
    DOUJINSHI(1178, "tags.production-type.doujinshi"),
    REAL_SCENERY(2660, "tags.production-type.real-scenery"),
    EPISODIC(2646, "tags.production-type.episodic"),
    PICTURE_DRAMA(2683, "tags.production-type.picture-drama"),
    VERTICAL_ANIME(2637, "tags.production-type.vertical-anime"),
    ADVERTISEMENT(2753, "tags.production-type.advertisement"),
    FULL_COLOR(2418, "tags.production-type.full-color"),
    WEBNOVEL(2878, "tags.production-type.webnovel"),
    WEBTOON(2877, "tags.production-type.webtoon"),
    PRINTED_EDITION(2879, "tags.production-type.printed-edition"),
    PUBLISHED_IN_POLAND(2665, "tags.production-type.published-in-poland"),
    YONKOMA(1884, "tags.production-type.yonkoma"),
    YOUNG_ANIMATOR_TRAINING_PROJECT(2644, "tags.production-type.young-animator-training-project");
    private final Integer id;
    private final String translationKey;

    @Override
    public String getTagType() {
        return "productiontype";
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
