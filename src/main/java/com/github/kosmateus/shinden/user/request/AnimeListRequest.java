package com.github.kosmateus.shinden.user.request;

import com.github.kosmateus.shinden.common.enums.MPAA;
import com.github.kosmateus.shinden.common.enums.TitleStatus;
import com.github.kosmateus.shinden.common.enums.TitleType;
import com.github.kosmateus.shinden.common.enums.tag.Tag;
import com.github.kosmateus.shinden.common.request.Sort.Order;
import com.github.kosmateus.shinden.http.request.QueryParam;
import com.github.kosmateus.shinden.http.request.SortParam;
import com.github.kosmateus.shinden.user.common.enums.UserTitleStatus;
import com.github.kosmateus.shinden.utils.QueryParamsBuilder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import javax.annotation.Nullable;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.kosmateus.shinden.common.request.Sort.Direction.ASC;
import static com.github.kosmateus.shinden.common.request.Sort.Direction.DESC;
import static com.github.kosmateus.shinden.utils.RequestUtils.convertLocalDateToSeconds;

/**
 * Represents a request for fetching a user's anime list.
 * <p>
 * The {@code AnimeListRequest} class encapsulates the necessary parameters to request
 * an anime list for a specific user. This includes the user ID and an optional filter
 * for the status of the anime in the user's list.
 * </p>
 *
 * @version 1.0.0
 */
@Getter
@Builder
@ToString
@Jacksonized
@AllArgsConstructor
public class AnimeListRequest {

    private static final Map<Class<? extends QueryParam>, String> QUERY_PARAM_SEPARATOR_MAP = ImmutableMap.of(
            Tag.class, ",",
            TitleType.class, "_",
            TitleStatus.class, "_",
            MPAA.class, "_"
    );

    /**
     * The unique identifier of the user whose anime list is being requested.
     * Must not be null.
     */
    @NotNull
    private final Long userId;

    /**
     * The status of the anime in the user's list.
     * This field is optional and can be null to indicate no specific status filter.
     */
    @Nullable
    private final UserTitleStatus status;

    /**
     * The list of tags to filter the anime titles.
     */
    @Nullable
    private final Set<Tag> tags;

    /**
     * The list of anime types to filter the user's anime list.
     */
    @Nullable
    private final Set<TitleType> animeTypes;

    /**
     * The list of anime statuses to filter the user's anime list.
     */
    @Nullable
    private final Set<TitleStatus> animeStatuses;

    /**
     * The list of age ratings to filter the user's anime list.
     */
    @Nullable
    private final Set<MPAA> ageRatings;

    /**
     * The minimum year of the premiere date to filter the anime titles.
     * Must be greater than or equal to 1950.
     */
    @Min(1950)
    @Nullable
    private final Integer premiereYearMin;

    /**
     * The maximum year of the premiere date to filter the anime titles.
     * Must be greater than or equal to 1950.
     */
    @Min(1950)
    @Nullable
    private final Integer premiereYearMax;

    /**
     * Flag indicating whether to exclude or include the specified tags in the filter.
     */
    private final boolean excludeTags;

    /**
     * Converts the request parameters to a map of query parameters.
     * <p>
     * This method builds a map of query parameters using the provided filters, such as tags,
     * anime types, statuses, and age ratings. It also includes the premiere year filters,
     * if specified.
     * </p>
     *
     * @return a map of query parameters representing this request.
     */
    public Map<String, String> toQueryParams() {
        Map<String, String> queryParams = QueryParamsBuilder.buildWithSeparators(
                        QUERY_PARAM_SEPARATOR_MAP,
                        Stream.of(tags, animeTypes, animeStatuses, ageRatings)
                                .filter(list -> list != null && !list.isEmpty())
                                .flatMap(Set::stream)
                                .collect(Collectors.toSet())
                ).entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> adjustKey(entry.getKey(), tags, excludeTags),
                        Map.Entry::getValue
                ));

        addPremiereParams(queryParams, premiereYearMin, "premiereMin");
        addPremiereParams(queryParams, premiereYearMax, "premiereMax");

        return queryParams;
    }

    /**
     * Adjusts the key based on the presence of tags and the exclude flag.
     *
     * @param key         the original key
     * @param tags        the list of tags to filter
     * @param excludeTags flag indicating whether to exclude or include the tags
     * @return the adjusted key based on the tags and exclude flag.
     */
    private String adjustKey(String key, Set<Tag> tags, boolean excludeTags) {
        if (tags != null && !tags.isEmpty() && Lists.newArrayList(tags).get(0).getQueryParameter().equals(key)) {
            return excludeTags ? key + "E" : key + "I";
        }
        return key;
    }

    /**
     * Adds premiere year parameters to the query parameter map.
     *
     * @param queryParams  the map of query parameters
     * @param premiereYear the year to add to the query parameters
     * @param paramName    the name of the query parameter (e.g., "premiereMin" or "premiereMax")
     */
    private void addPremiereParams(Map<String, String> queryParams, Integer premiereYear, String paramName) {
        if (premiereYear != null) {
            queryParams.put(paramName, String.valueOf(convertLocalDateToSeconds(LocalDate.of(premiereYear, 1, 1))));
        }
    }

    /**
     * Validates that the minimum premiere year is less than or equal to the maximum premiere year.
     *
     * @return true if the minimum premiere year is less than or equal to the maximum, false otherwise.
     */
    @AssertTrue(message = "Premiere min year must be less than or equal to max year")
    private boolean isPremiereYearValid() {
        return premiereYearMin == null || premiereYearMax == null || premiereYearMin <= premiereYearMax;
    }

    /**
     * Represents the sorting order for the anime list request.
     * <p>
     * The {@code SortOrder} class provides predefined sorting options for ordering
     * the anime list by various properties such as title, rate, progress, or type.
     * </p>
     */
    @Getter
    @RequiredArgsConstructor
    public enum SortType implements SortParam<SortType> {
        TITLE("title"),
        RATE("ratio"),
        STORY_RATE("ratioStory"),
        GRAPHICS_RATE("ratioGraphic"),
        MUSIC_RATE("ratioMusic"),
        CHARACTERS_RATE("ratioCharacters"),
        PREMIERE_DATE("premiereDate"),
        PROGRESS("animeProgress"),
        TYPE("type");

        private final String value;

        @Override
        public String getSortValue() {
            return value;
        }

        @Override
        public String getSortParameter() {
            return "sort";
        }

        @Override
        public Order<SortType> desc() {
            return new Order<>(this, DESC);
        }

        @Override
        public Order<SortType> asc() {
            return new Order<>(this, ASC);
        }
    }

    /**
     * Builder for the {@link AnimeListRequest}.
     * <p>
     * Provides methods to add various query parameters to the request being constructed.
     * </p>
     */
    public class AnimeListRequestBuilder {

        /**
         * Adds a tag to the request filter.
         *
         * @param tag the tag to add
         * @return the builder itself
         */
        public AnimeListRequestBuilder addTag(Tag tag) {
            if (tags == null) {
                tags = Sets.newHashSet(tag);
            } else {
                tags.add(tag);
            }
            return this;
        }

        /**
         * Adds an anime type to the request filter.
         *
         * @param type the anime type to add
         * @return the builder itself
         */
        public AnimeListRequestBuilder addAnimeType(TitleType type) {
            if (animeTypes == null) {
                animeTypes = Sets.newHashSet(type);
            } else {
                animeTypes.add(type);
            }
            return this;
        }

        /**
         * Adds an anime status to the request filter.
         *
         * @param status the anime status to add
         * @return the builder itself
         */
        public AnimeListRequestBuilder addAnimeStatus(TitleStatus status) {
            if (animeStatuses == null) {
                animeStatuses = Sets.newHashSet(status);
            } else {
                animeStatuses.add(status);
            }
            return this;
        }

        /**
         * Adds an age rating to the request filter.
         *
         * @param rating the age rating to add
         * @return the builder itself
         */
        public AnimeListRequestBuilder addAgeRating(MPAA rating) {
            if (ageRatings == null) {
                ageRatings = Sets.newHashSet(rating);
            } else {
                ageRatings.add(rating);
            }
            return this;
        }
    }
}

