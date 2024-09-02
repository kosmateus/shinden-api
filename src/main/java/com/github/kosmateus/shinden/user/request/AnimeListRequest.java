package com.github.kosmateus.shinden.user.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.kosmateus.shinden.common.request.Sort;
import com.github.kosmateus.shinden.user.common.enums.UserTitleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

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
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class AnimeListRequest {

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
     * Represents the sorting order for the anime list request.
     * <p>
     * The {@code SortOrder} class provides predefined sorting options for ordering
     * the anime list by various properties such as title, rate, progress, or type.
     * </p>
     */
    public static class SortOrder extends Sort.Order {

        /**
         * Private constructor to create a new sort order.
         *
         * @param property  the property by which the list should be sorted
         * @param direction the direction of sorting (ascending or descending)
         */
        private SortOrder(String property, Sort.Direction direction) {
            super(property, direction);
        }

        /**
         * Creates a sort order for sorting by anime title.
         *
         * @param direction the direction of sorting (ascending or descending)
         * @return a new {@code SortOrder} for sorting by title
         */
        public static SortOrder byTitle(Sort.Direction direction) {
            return new SortOrder("title", direction);
        }

        /**
         * Creates a sort order for sorting by the user's rating of the anime.
         *
         * @param direction the direction of sorting (ascending or descending)
         * @return a new {@code SortOrder} for sorting by rate
         */
        public static SortOrder byRate(Sort.Direction direction) {
            return new SortOrder("ratio", direction);
        }

        /**
         * Creates a sort order for sorting by the user's progress in the anime.
         *
         * @param direction the direction of sorting (ascending or descending)
         * @return a new {@code SortOrder} for sorting by progress
         */
        public static SortOrder byProgress(Sort.Direction direction) {
            return new SortOrder("animeProgress", direction);
        }

        /**
         * Creates a sort order for sorting by the type of anime (e.g., TV, OVA, Movie).
         *
         * @param direction the direction of sorting (ascending or descending)
         * @return a new {@code SortOrder} for sorting by type
         */
        public static SortOrder byType(Sort.Direction direction) {
            return new SortOrder("type", direction);
        }
    }
}
