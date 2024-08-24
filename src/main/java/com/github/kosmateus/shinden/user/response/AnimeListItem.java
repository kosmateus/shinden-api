package com.github.kosmateus.shinden.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Represents an item in a user's anime list.
 * <p>
 * The {@code AnimeListItem} class encapsulates detailed information about an anime
 * that is part of a user's anime list. This includes fields such as the anime's title,
 * image URL, user and total scores, episode counts, type, status, and a brief description.
 * </p>
 *
 * @version 1.0.0
 */
@Getter
@Builder
@ToString
public class AnimeListItem {

    /**
     * The unique identifier for the anime.
     */
    private final Long id;

    /**
     * The title of the anime.
     */
    private final String title;

    /**
     * The URL of the anime's image.
     */
    private final String imageUrl;

    /**
     * The score given by the user to the anime.
     */
    private final Integer userScore;

    /**
     * The total score of the anime.
     */
    private final Integer totalScore;

    /**
     * The score for the plot of the anime.
     */
    private final Integer plotScore;

    /**
     * The score for the music in the anime.
     */
    private final Integer musicScore;

    /**
     * The score for the characters in the anime.
     */
    private final Integer charactersScore;

    /**
     * The score for the graphics of the anime.
     */
    private final Integer graphicsScore;

    /**
     * The total number of episodes in the anime.
     */
    private final Integer totalEpisodes;

    /**
     * The number of episodes watched by the user.
     */
    private final Integer watchedEpisodes;

    /**
     * The type of the anime (e.g., TV, OVA, Movie).
     */
    private final String type;

    /**
     * The URL type for the anime, indicating the resource type in the URL.
     */
    private final String urlType;

    /**
     * The date when the user started watching the anime.
     */
    private final LocalDate startDate;

    /**
     * The date when the user finished watching the anime.
     */
    private final LocalDate endDate;

    /**
     * The current status of the anime in the user's list (e.g., Watching, Completed).
     */
    private final String status;

    /**
     * A brief description of the anime.
     */
    private final String description;
}
