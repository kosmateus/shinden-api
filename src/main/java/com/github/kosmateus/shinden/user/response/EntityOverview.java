package com.github.kosmateus.shinden.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents an overview of an entity, such as a character or person, associated with a media title.
 * <p>
 * The {@code EntityOverview} class encapsulates key details about an entity, including its name, image,
 * associated media, and related URLs. This class is typically used to provide a summarized view of an entity
 * within the context of a media title.
 * </p>
 *
 * @version 1.0.0
 */
@Getter
@Builder
@ToString
public class EntityOverview {

    /**
     * The unique identifier for the entity.
     */
    private final Long id;

    /**
     * The type of URL associated with the entity (e.g., "character", "person").
     */
    private final String urlType;

    /**
     * The URL of the entity's image.
     */
    private final String imageUrl;

    /**
     * The first name of the entity (if applicable).
     */
    private final String firstName;

    /**
     * The last name of the entity (if applicable).
     */
    private final String lastName;

    /**
     * The unique identifier for the media title associated with the entity.
     */
    private final Long mediaId;

    /**
     * The title of the media associated with the entity.
     */
    private final String mediaTitle;

    /**
     * The type of URL associated with the media (e.g., "anime", "manga").
     */
    private final String mediaUrlType;
}
