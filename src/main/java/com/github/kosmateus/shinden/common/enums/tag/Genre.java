package com.github.kosmateus.shinden.common.enums.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

/**
 * Enum representing different genres for tagging anime or manga.
 * <p>
 * Each genre is associated with a unique identifier and a translation key.
 * The enum provides methods to retrieve a genre based on its ID or a string representation of the ID.
 * </p>
 *
 * @version 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum Genre implements Tag {
    ACTION(5, "tags.genre.action"),
    CYBERPUNK(106, "tags.genre.cyberpunk"),
    DRAMA(8, "tags.genre.drama"),
    ECCHI(78, "tags.genre.ecchi"),
    EXPERIMENTAL(1741, "tags.genre.experimental"),
    FANTASY(22, "tags.genre.fantasy"),
    HAREM(130, "tags.genre.harem"),
    HENTAI(234, "tags.genre.hentai"),
    HISTORICAL(92, "tags.genre.historical"),
    HORROR(51, "tags.genre.horror"),
    COMEDY(7, "tags.genre.comedy"),
    POLICE(20, "tags.genre.police"),
    MAGIC(18, "tags.genre.magic"),
    MECHA(98, "tags.genre.mecha"),
    REVERSE_HAREM(263, "tags.genre.reverse-harem"),
    MUSIC(136, "tags.genre.music"),
    SUPERNATURAL(19, "tags.genre.supernatural"),
    DEMENTIA(97, "tags.genre.dementia"),
    SLICE_OF_LIFE(42, "tags.genre.slice-of-life"),
    PARODY(165, "tags.genre.parody"),
    ADVENTURE(6, "tags.genre.adventure"),
    PSYCHOLOGICAL(52, "tags.genre.psychological"),
    ROMANCE(2672, "tags.genre.romance"),
    ROMANCE_OLD(38, "tags.genre.romance-old"),
    SCIENCE_FICTION(549, "tags.genre.science-fiction"),
    SHOUJO_AI(167, "tags.genre.shoujo-ai"),
    SHOUNEN_AI(207, "tags.genre.shounen-ai"),
    SPACE_OPERA(384, "tags.genre.space-opera"),
    SPORTS(31, "tags.genre.sports"),
    STEAMPUNK(1734, "tags.genre.steampunk"),
    SCHOOL(65, "tags.genre.school"),
    MARTIAL_ARTS(57, "tags.genre.martial-arts"),
    MYSTERY(12, "tags.genre.mystery"),
    THRILLER(53, "tags.genre.thriller"),
    MILITARY(93, "tags.genre.military"),
    YAOI(364, "tags.genre.yaoi"),
    YURI(380, "tags.genre.yuri");

    private final Integer id;
    private final String translationKey;
    private final String tagType = "genre";
    private final String queryParameter = "tag";
    private final String animeSearchQueryParameter = "genres";

    /**
     * Retrieves a {@code Genre} enum based on its integer ID.
     *
     * @param value the integer ID of the genre
     * @return the {@code Genre} corresponding to the given ID
     * @throws IllegalArgumentException if no genre with the given ID exists
     */
    public static Genre fromValue(Integer value) {
        return Stream.of(Genre.values())
                .filter(genre -> genre.id.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No genre with id " + value));
    }

    /**
     * Retrieves a {@code Genre} enum based on its string representation of the ID.
     *
     * @param value the string representation of the genre's ID
     * @return the {@code Genre} corresponding to the given string ID
     */
    public static Genre fromValue(String value) {
        return fromValue(Integer.parseInt(value));
    }

    @Override
    public String getQueryValue() {
        return String.valueOf(id);
    }
}
