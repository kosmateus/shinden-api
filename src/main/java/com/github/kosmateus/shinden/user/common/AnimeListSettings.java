package com.github.kosmateus.shinden.user.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.kosmateus.shinden.user.common.enums.AnimeStatus;
import com.github.kosmateus.shinden.user.common.enums.SkipFillers;
import com.github.kosmateus.shinden.user.common.enums.StatusAutoChange;
import com.github.kosmateus.shinden.user.common.enums.SubtitlesLanguage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Class representing the settings for a user's anime list.
 * <p>
 * The {@code AnimeListSettings} class encapsulates various settings related to the user's anime list,
 * including subtitle languages, watch status preferences, and options for skipping fillers and automatic
 * status changes. The class uses the {@link Builder} pattern to simplify the creation and configuration of its instances.
 * </p>
 *
 * @version 1.0.0
 */
@Getter
@Builder
@ToString
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class AnimeListSettings {

    /**
     * The list of preferred subtitle languages.
     * <p>
     * This field holds a list of {@link SubtitlesLanguage} enums, representing the user's preferred languages for subtitles
     * when watching anime.
     * </p>
     */
    private final List<SubtitlesLanguage> subtitlesLanguages;

    /**
     * The list of anime watch statuses.
     * <p>
     * This field holds a list of {@link AnimeStatus} enums, representing the user's preferred statuses for managing anime in their list.
     * </p>
     */
    private final List<AnimeStatus> animeWatchStatus;

    /**
     * The option to skip filler episodes.
     * <p>
     * This field holds a {@link SkipFillers} enum, indicating whether the user prefers to skip filler episodes in their anime list.
     * </p>
     */
    private final SkipFillers skipFillers;

    /**
     * The option for automatic status changes.
     * <p>
     * This field holds a {@link StatusAutoChange} enum, indicating whether the user's anime statuses should update automatically based on their progress.
     * </p>
     */
    private final StatusAutoChange statusAutoChange;
}
