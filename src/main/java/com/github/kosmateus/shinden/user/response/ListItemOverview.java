package com.github.kosmateus.shinden.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Represents a brief overview of an item in a user's list, such as an anime or manga.
 * <p>
 * The {@code ListItemOverview} class provides essential details about a list item, including
 * its title, associated URL, image, last modified timestamp, and current status. This class is
 * typically used to display a summarized view of a user's list item.
 * </p>
 *
 * @version 1.0.0
 */
@Getter
@Builder
@ToString
public class ListItemOverview {

    /**
     * The unique identifier for the list item.
     */
    private final Long id;

    /**
     * The type of URL associated with the list item (e.g., "anime", "manga").
     */
    private final String urlType;

    /**
     * The title of the list item.
     */
    private final String title;

    /**
     * The URL of the list item's image.
     */
    private final String imageUrl;

    /**
     * The timestamp when the list item was last modified.
     */
    private final LocalDateTime lastModifiedAt;

    /**
     * The current status of the list item (e.g., "Watching", "Completed").
     */
    private final String status;
}
