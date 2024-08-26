package com.github.kosmateus.shinden.user.common;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
public class ListsSettings {
    private final AnimeListSettings animeListSettings;
    private final MangaListSettings mangaListSettings;
}
