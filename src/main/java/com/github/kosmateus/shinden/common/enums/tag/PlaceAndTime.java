package com.github.kosmateus.shinden.common.enums.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enumeration representing different places and times for tagging purposes in the application.
 *
 * <p>Each place and time tag is associated with a unique identifier and a translation key for localization
 * purposes, along with predefined query parameters used for API searches.</p>
 *
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum PlaceAndTime implements Tag {
    ALTERNATIVE_EARTH(2328, "tags.place-and-time.alternative-earth"),
    NORTH_AMERICA(1789, "tags.place-and-time.north-america"),
    OFFICE(2844, "tags.place-and-time.office"),
    APARTMENT_LIFE(2336, "tags.place-and-time.apartment-life"),
    CHINA(1949, "tags.place-and-time.china"),
    DUNGEON(2663, "tags.place-and-time.dungeon"),
    DYSTOPIA(2348, "tags.place-and-time.dystopia"),
    EUROPE(1745, "tags.place-and-time.europe"),
    FEUDAL_JAPAN(1730, "tags.place-and-time.feudal-japan"),
    LIKE_GAME(2322, "tags.place-and-time.like-game"),
    MEDIEVAL(2362, "tags.place-and-time.medieval"),
    JAPAN(1740, "tags.place-and-time.japan"),
    CAFE(2341, "tags.place-and-time.cafe"),
    SPACE(10, "tags.place-and-time.space"),
    CITY(1785, "tags.place-and-time.city"),
    OCEAN(2363, "tags.place-and-time.ocean"),
    OMEGAVERSE(2875, "tags.place-and-time.omegaverse"),
    TRAVEL(1788, "tags.place-and-time.travel"),
    POST_APOCALYPTIC(470, "tags.place-and-time.post-apocalyptic"),
    FUTURE(2326, "tags.place-and-time.future"),
    DESERT(2988, "tags.place-and-time.desert"),
    ALTERNATIVE_WORLD(2327, "tags.place-and-time.alternative-world"),
    ALL_BOYS_SCHOOL(2333, "tags.place-and-time.all-boys-school"),
    ALL_GIRLS_SCHOOL(2332, "tags.place-and-time.all-girls-school"),
    VIRTUAL_REALITY(1729, "tags.place-and-time.virtual-reality"),
    GREAT_BRITAIN(2858, "tags.place-and-time.great-britain"),
    COUNTRYSIDE(1784, "tags.place-and-time.countryside"),
    CONTEMPORARY(1739, "tags.place-and-time.contemporary"),
    ISLAND(2357, "tags.place-and-time.island");

    private final Integer id;
    private final String translationKey;
    private final String tagType = "place";
    private final String queryParameter = "tag";
    private final String animeSearchQueryParameter = "genres";

    @Override
    public String getQueryValue() {
        return String.valueOf(id);
    }
}
