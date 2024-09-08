package com.github.kosmateus.shinden.anime;

import com.github.kosmateus.shinden.BaseTest;
import com.github.kosmateus.shinden.anime.request.AnimeSearchRequest;
import com.github.kosmateus.shinden.anime.response.AnimeSearchResult;
import com.github.kosmateus.shinden.common.request.FixedPageable;
import com.github.kosmateus.shinden.common.request.Sort;
import com.github.kosmateus.shinden.common.response.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.kosmateus.shinden.anime.request.AnimeSearchRequest.SortType.TITLE;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("Anime API tests")
class AnimeApiTest extends BaseTest {

    @Nested
    @DisplayName("Search anime tests")
    class AnimeSearchTest {

        @Test
        @DisplayName("Should find all anime")
        void shouldFindAllAnime() {
            Page<AnimeSearchResult> animeSearchResults = animeApi.searchAnime(AnimeSearchRequest.EMPTY, FixedPageable.of(1, Sort.by(TITLE.desc())));
            assertThat(animeSearchResults).isNotNull();
            assertThat(animeSearchResults.getContent()).isNotEmpty();
        }

    }
}
