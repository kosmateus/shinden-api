package com.github.kosmateus.shinden.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a favorite media item, such as an anime or manga, marked by a user.
 * <p>
 * The {@code FavouriteMediaItem} class encapsulates key details about a media item that
 * a user has marked as a favorite. This includes the item's title, image, type, and the year
 * it was released.
 * </p>
 *
 * @version 1.0.0
 */
@Getter
@Builder
@ToString
public class FavouriteMediaItem {

    /**
     * The unique identifier for the media item.
     */
    private final Long id;

    /**
     * The type of URL associated with the media item (e.g., "anime", "manga").
     */
    private final String urlType;

    /**
     * The title of the media item.
     */
    private final String title;

    /**
     * The URL of the media item's image.
     */
    private final String imageUrl;

    /**
     * The type of the media item (e.g., TV series, Movie, OVA).
     */
    private final String type;

    /**
     * The year the media item was released.
     */
    private final Integer year;
}
