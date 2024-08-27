package com.github.kosmateus.shinden.user;

import com.github.kosmateus.shinden.BaseTest;
import com.github.kosmateus.shinden.common.response.UpdateResult;
import com.github.kosmateus.shinden.http.request.LocalFileResource;
import com.github.kosmateus.shinden.i18n.Translatable;
import com.github.kosmateus.shinden.user.common.AnimeListSettings;
import com.github.kosmateus.shinden.user.common.MangaListSettings;
import com.github.kosmateus.shinden.user.common.PageSettings;
import com.github.kosmateus.shinden.user.common.ReadTimeSettings;
import com.github.kosmateus.shinden.user.common.enums.AnimeStatus;
import com.github.kosmateus.shinden.user.common.enums.ChapterLanguage;
import com.github.kosmateus.shinden.user.common.enums.ChapterStatus;
import com.github.kosmateus.shinden.user.common.enums.PageMainMenu;
import com.github.kosmateus.shinden.user.common.enums.PageTheme;
import com.github.kosmateus.shinden.user.common.enums.ShowOption;
import com.github.kosmateus.shinden.user.common.enums.SkipFillers;
import com.github.kosmateus.shinden.user.common.enums.SliderPosition;
import com.github.kosmateus.shinden.user.common.enums.StatusAutoChange;
import com.github.kosmateus.shinden.user.common.enums.SubtitlesLanguage;
import com.github.kosmateus.shinden.user.common.enums.UserGender;
import com.github.kosmateus.shinden.user.request.AddToListSettingsRequest;
import com.github.kosmateus.shinden.user.request.AvatarFileUpdateRequest;
import com.github.kosmateus.shinden.user.request.AvatarUrlUpdateRequest;
import com.github.kosmateus.shinden.user.request.BaseSettingsRequest;
import com.github.kosmateus.shinden.user.request.FavouriteTagsRequest;
import com.github.kosmateus.shinden.user.request.FavouriteTagsRequest.ListType;
import com.github.kosmateus.shinden.user.request.FavouriteTagsRequest.RateType;
import com.github.kosmateus.shinden.user.request.FavouriteTagsRequest.TagType;
import com.github.kosmateus.shinden.user.request.ImportMalListRequest;
import com.github.kosmateus.shinden.user.request.ImportMalListRequest.ImportType;
import com.github.kosmateus.shinden.user.request.ListsSettingsRequest;
import com.github.kosmateus.shinden.user.request.UpdatePasswordRequest;
import com.github.kosmateus.shinden.user.request.UserInformationRequest;
import com.github.kosmateus.shinden.user.response.Achievement;
import com.github.kosmateus.shinden.user.response.Achievements;
import com.github.kosmateus.shinden.user.response.Comment;
import com.github.kosmateus.shinden.user.response.EntityOverview;
import com.github.kosmateus.shinden.user.response.FavouriteMediaItem;
import com.github.kosmateus.shinden.user.response.FavouriteTag;
import com.github.kosmateus.shinden.user.response.MediaStatistics;
import com.github.kosmateus.shinden.user.response.Recommendation;
import com.github.kosmateus.shinden.user.response.Review;
import com.github.kosmateus.shinden.user.response.UserInformation;
import com.github.kosmateus.shinden.user.response.UserOverview;
import com.github.kosmateus.shinden.user.response.UserSettings;
import com.github.kosmateus.shinden.utils.ResourceLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.github.kosmateus.shinden.common.response.Result.SUCCESS;
import static com.github.kosmateus.shinden.i18n.TranslationUtil.getTranslation;
import static com.github.kosmateus.shinden.user.UserApiTest.UserAsserts.IMAGE_EXTENSIONS;
import static com.github.kosmateus.shinden.user.UserApiTest.UserAsserts.LANGUAGE;
import static com.github.kosmateus.shinden.user.UserApiTest.UserAsserts.PAST_DATE_TIME;
import static com.github.kosmateus.shinden.user.UserApiTest.UserAsserts.RANK_PREASANT;
import static com.github.kosmateus.shinden.user.UserApiTest.UserAsserts.RANK_USER;
import static com.github.kosmateus.shinden.user.UserApiTest.UserAsserts.assertAchievements;
import static com.github.kosmateus.shinden.user.UserApiTest.UserAsserts.assertComments;
import static com.github.kosmateus.shinden.user.UserApiTest.UserAsserts.assertFavouriteEntities;
import static com.github.kosmateus.shinden.user.UserApiTest.UserAsserts.assertFavouriteMediaItems;
import static com.github.kosmateus.shinden.user.UserApiTest.UserAsserts.assertStatistics;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("User API tests")
class UserApiTest extends BaseTest {

    @Nested
    @DisplayName("User Overview tests")
    class UserOverviewTest {

        @Test
        @DisplayName("Should successfully get user overview")
        void shouldSuccessfullyGetOverview() {
            UserOverview overview = userApi.getOverview(API_CONFIG.getUnauthenticatedUserId());
            assertThat(overview).isNotNull();
            assertThat(overview.getId()).isNotNull();
            assertThat(overview.getUsername()).isEqualToIgnoringCase(API_CONFIG.getUnauthenticatedUsername());
            assertThat(IMAGE_EXTENSIONS).anySatisfy(extension -> assertThat(overview.getAvatarUrl()).contains(extension));
            assertThat(overview.getAchievements()).isNotNull();
            assertThat(overview.getLastOnline()).isAfter(PAST_DATE_TIME);
            assertThat(overview.getRank()).matches(rank -> rank.equalsIgnoreCase(RANK_USER) || rank.equalsIgnoreCase(RANK_PREASANT));
            assertThat(overview.getJoinDate()).isAfter(PAST_DATE_TIME);
            assertThat(overview.getScore()).isNotNull();
            assertThat(overview.getLanguage()).isEqualTo(LANGUAGE);
            assertStatistics(overview.getAnimeStatistics());
            assertStatistics(overview.getMangaStatistics());
            assertFavouriteMediaItems(overview.getFavouriteAnime());
            assertFavouriteMediaItems(overview.getFavouriteManga());
            assertFavouriteEntities(overview.getFavouritePeople());
            assertFavouriteEntities(overview.getFavouriteCharacters());
            assertComments(overview.getComments());
        }
    }

    @Nested
    @DisplayName("User Achievements tests")
    class UserAchievementsTest {

        @Test
        @DisplayName("Should successfully get user achievements")
        void shouldSuccessfullyGetAchievements() {
            Achievements achievements = userApi.getAchievements(API_CONFIG.getUnauthenticatedUserId());
            assertThat(achievements).isNotNull();
            assertThat(achievements.getLastCheck()).isAfter(PAST_DATE_TIME);
            assertThat(achievements.getAchievements()).isNotEmpty();
            assertAchievements(achievements.getAchievements());
        }
    }

    @Nested
    @DisplayName("Favourite Tags tests")
    class FavouriteTagsTest {

        @PauseBetween(500)
        @ParameterizedTest
        @DisplayName("Should successfully get favourite tags")
        @ArgumentsSource(FavouriteTagsRequestArgumentsProvider.class)
        void shouldSuccessfullyGetFavouriteTags(FavouriteTagsRequest request) {
            List<FavouriteTag> favouriteTags = userApi.getFavouriteTags(request);

            if (favouriteTags.isEmpty()) {
                log.warn("No favourite tags found for request: {}", request);
            } else {
                assertThat(favouriteTags).allMatch(tag -> tag.getId() != null);
                assertThat(favouriteTags).allMatch(tag -> StringUtils.isNotBlank(tag.getName()));
                assertThat(favouriteTags).allMatch(tag -> tag.getLowestRating() != null);
                assertThat(favouriteTags).allMatch(tag -> tag.getHighestRating() != null);
                assertThat(favouriteTags).allMatch(tag -> tag.getAverageRating() != null);
                assertThat(favouriteTags).allMatch(tag -> tag.getWeightedRating() != null);
                assertThat(favouriteTags).allMatch(tag -> tag.getSpentTime() != null);
            }

        }
    }

    @Nested
    @DisplayName("User Reviews tests")
    class UserReviewsTest {

        @Test
        @DisplayName("Should successfully get user reviews")
        void shouldSuccessfullyGetReviews() {
            List<Review> reviews = userApi.getReviews(1013L);
            assertThat(reviews).isNotNull();
            assertThat(reviews).isNotEmpty();
            reviews.forEach(review -> {
                assertThat(review.getId()).isNotNull();
                assertThat(review.getMediaId()).isNotNull();
                assertThat(review.getMediaTitle()).isNotBlank();
                assertThat(review.getMediaUrlType()).isNotBlank();
                assertThat(IMAGE_EXTENSIONS).anySatisfy(
                        extension -> assertThat(review.getMediaImageUrl()).contains(extension));
                assertThat(review.getTotalVotes()).isNotNull();
                assertThat(review.getPositiveVotes()).isNotNull();
                assertThat(review.getNegativeVotes()).isNotNull();
                assertThat(review.getContent()).isNotBlank();
            });
        }
    }

    @Nested
    @DisplayName("User Recommendations tests")
    class UserRecommendationsTest {

        @Test
        @DisplayName("Should successfully get user recommendations")
        void shouldSuccessfullyGetRecommendations() {
            List<Recommendation> recommendations = userApi.getRecommendations(368L);
            assertThat(recommendations).isNotNull();
            assertThat(recommendations).isNotEmpty();
            recommendations.forEach(recommendation -> {
                assertThat(recommendation.getId()).isNotNull();
                assertThat(recommendation.getMediaId()).isNotNull();
                assertThat(recommendation.getMediaTitle()).isNotBlank();
                assertThat(recommendation.getMediaUrlType()).isNotBlank();
                assertThat(IMAGE_EXTENSIONS).anySatisfy(
                        extension -> assertThat(recommendation.getMediaImageUrl()).contains(extension));
                assertThat(recommendation.getForMediaId()).isNotNull();
                assertThat(recommendation.getForMediaTitle()).isNotBlank();
                assertThat(recommendation.getForMediaUrlType()).isNotBlank();
                assertThat(recommendation.getDate()).isAfter(PAST_DATE_TIME);
                assertThat(recommendation.getRating()).isNotNull();
                assertThat(recommendation.getDescription()).isNotBlank();
            });

        }
    }

    @Nested
    @DisplayName("User Information tests")
    class UserInformationTest {

        @Test
        @DisplayName("Should successfully get user information")
        void shouldSuccessfullyGetUserInformation() {
            login();
            UserInformation information = userApi.getInformation(getLoggedInUserId());
            assertThat(information).isNotNull();
            assertThat(information.getSignature()).isNotBlank();
            assertThat(information.getAboutMe()).isNotBlank();
            assertThat(information.getGender()).isNotNull();
            assertThat(information.getBirthYear()).isNotNull();
            assertThat(information.getBirthMonth()).isNotNull();
            assertThat(information.getBirthDay()).isNotNull();
            assertThat(information.getEmail()).isNotBlank();
        }

        @Test
        @DisplayName("Should successfully update information")
        void shouldSuccessfullyUpdateUserInformation() {
            login();

            UpdateResult updateResult = userApi.updateInformation(UserInformationRequest.builder()
                    .userId(getLoggedInUserId())
                    .signature("Test signature")
                    .aboutMe("Test about me")
                    .gender(UserGender.MALE)
                    .birthDate(LocalDate.of(1990, 1, 1))
                    .build());

            assertThat(updateResult).isNotNull();
            assertThat(updateResult.getResult()).isEqualTo(SUCCESS);

            UserInformation information = userApi.getInformation(getLoggedInUserId());
            assertThat(information).isNotNull();
            assertThat(information.getSignature()).isEqualTo("Test signature");
            assertThat(information.getAboutMe()).isEqualTo("Test about me");
            assertThat(information.getGender()).isEqualTo(UserGender.MALE);
            assertThat(information.getBirthYear()).isEqualTo(1990);
            assertThat(information.getBirthMonth()).isEqualTo(1);
            assertThat(information.getBirthDay()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("User Settings tests")
    class UserSettingsTest {

        @BeforeEach
        void resetSettings() {
            login();
            userApi.updateBaseSettings(BaseSettingsRequest.builder()
                    .userId(getLoggedInUserId())
                    .pageSettings(PageSettings.builder()
                            .pageTheme(PageTheme.DEFAULT)
                            .pageMainMenu(PageMainMenu.ALL)
                            .build())
                    .readTimeSettings(ReadTimeSettings.builder()
                            .mangaChapterReadTime(5)
                            .visualNovelChapterReadTime(5)
                            .build())
                    .build());

            userApi.updateAddToListSettings(AddToListSettingsRequest.builder()
                    .userId(getLoggedInUserId())
                    .showAddToList(ShowOption.NO)
                    .sliderPosition(SliderPosition.NO_LIMIT)
                    .build());

            userApi.updateListsSettings(ListsSettingsRequest.builder()
                    .userId(getLoggedInUserId())
                    .animeListSettings(AnimeListSettings.builder()
                            .animeWatchStatus(Collections.singletonList(AnimeStatus.IN_PROGRESS))
                            .subtitlesLanguages(Collections.singletonList(SubtitlesLanguage.NONE))
                            .skipFillers(SkipFillers.NO)
                            .statusAutoChange(StatusAutoChange.NO)
                            .build())
                    .mangaListSettings(MangaListSettings.builder()
                            .mangaReadStatus(Collections.singletonList(ChapterStatus.IN_PROGRESS))
                            .chapterLanguages(Collections.singletonList(ChapterLanguage.NONE))
                            .statusAutoChange(StatusAutoChange.NO)
                            .build())
                    .build());

        }

        @Test
        @DisplayName("Should successfully update base settings")
        void shouldSuccessfullyUpdateBaseSettings() {
            login();
            UpdateResult result = userApi.updateBaseSettings(BaseSettingsRequest.builder()
                    .userId(getLoggedInUserId())
                    .pageSettings(PageSettings.builder()
                            .pageTheme(PageTheme.SIMPLE_BLUE)
                            .pageMainMenu(PageMainMenu.MOBILE)
                            .build())
                    .readTimeSettings(ReadTimeSettings.builder()
                            .mangaChapterReadTime(10)
                            .visualNovelChapterReadTime(10)
                            .build())
                    .build());

            assertThat(result).isNotNull();
            assertThat(result.getResult()).isEqualTo(SUCCESS);

            UserSettings settings = userApi.getSettings(getLoggedInUserId());
            assertThat(settings).isNotNull();
            assertThat(settings.getPageSettings()).isNotNull();
            assertThat(settings.getPageSettings().getPageTheme()).isEqualTo(PageTheme.SIMPLE_BLUE);
            assertThat(settings.getPageSettings().getPageMainMenu()).isEqualTo(PageMainMenu.MOBILE);
            assertThat(settings.getReadTimeSettings()).isNotNull();
            assertThat(settings.getReadTimeSettings().getMangaChapterReadTime()).isEqualTo(10);
            assertThat(settings.getReadTimeSettings().getVisualNovelChapterReadTime()).isEqualTo(10);
        }

        @Test
        @DisplayName("Should successfully update list settings")
        void shouldSuccessfullyUpdateListSettings() {
            login();
            UpdateResult updateResult = userApi.updateListsSettings(ListsSettingsRequest.builder()
                    .userId(getLoggedInUserId())
                    .animeListSettings(AnimeListSettings.builder()
                            .subtitlesLanguages(Arrays.asList(SubtitlesLanguage.values()))
                            .animeWatchStatus(Arrays.asList(AnimeStatus.values()))
                            .skipFillers(SkipFillers.YES)
                            .statusAutoChange(StatusAutoChange.YES)
                            .build())
                    .mangaListSettings(MangaListSettings.builder()
                            .chapterLanguages(Arrays.asList(ChapterLanguage.values()))
                            .mangaReadStatus(Arrays.asList(ChapterStatus.values()))
                            .statusAutoChange(StatusAutoChange.YES)
                            .build())
                    .build());

            assertThat(updateResult).isNotNull();
            assertThat(updateResult.getResult()).isEqualTo(SUCCESS);

            UserSettings settings = userApi.getSettings(getLoggedInUserId());
            assertThat(settings).isNotNull();
            assertThat(settings.getAnimeListSettings()).isNotNull();
            assertThat(settings.getAnimeListSettings().getSubtitlesLanguages()).containsExactly(SubtitlesLanguage.values());
            assertThat(settings.getAnimeListSettings().getAnimeWatchStatus()).containsExactly(AnimeStatus.values());
            assertThat(settings.getAnimeListSettings().getSkipFillers()).isEqualTo(SkipFillers.YES);
            assertThat(settings.getAnimeListSettings().getStatusAutoChange()).isEqualTo(StatusAutoChange.YES);
            assertThat(settings.getMangaListSettings()).isNotNull();
            assertThat(settings.getMangaListSettings().getChapterLanguages()).containsExactly(ChapterLanguage.values());
            assertThat(settings.getMangaListSettings().getMangaReadStatus()).containsExactly(ChapterStatus.values());
            assertThat(settings.getMangaListSettings().getStatusAutoChange()).isEqualTo(StatusAutoChange.YES);
        }

        @Test
        @DisplayName("Should successfully update add to list settings")
        void shouldSuccessfullyUpdateAddToListSettings() {
            login();
            UpdateResult updateResult = userApi.updateAddToListSettings(AddToListSettingsRequest.builder()
                    .userId(getLoggedInUserId())
                    .sliderPosition(SliderPosition.SIX_ITEMS)
                    .showAddToList(ShowOption.YES)
                    .build());

            assertThat(updateResult).isNotNull();
            assertThat(updateResult.getResult()).isEqualTo(SUCCESS);

            UserSettings settings = userApi.getSettings(getLoggedInUserId());
            assertThat(settings).isNotNull();
            assertThat(settings.getAddToListSettings()).isNotNull();
            assertThat(settings.getAddToListSettings().getSliderPosition()).isEqualTo(SliderPosition.SIX_ITEMS);
            assertThat(settings.getAddToListSettings().getShowAddToList()).isEqualTo(ShowOption.YES);
        }

        @Test
        @DisplayName("Should successfully get user settings")
        void shouldSuccessfullyGetUserSettings() {
            login();
            UserSettings settings = userApi.getSettings(getLoggedInUserId());
            assertThat(settings).isNotNull();
            assertThat(settings.getPageSettings()).isNotNull();
            assertThat(settings.getPageSettings().getPageTheme()).isEqualTo(PageTheme.DEFAULT);
            assertThat(settings.getPageSettings().getPageMainMenu()).isEqualTo(PageMainMenu.ALL);
            assertThat(settings.getReadTimeSettings()).isNotNull();
            assertThat(settings.getReadTimeSettings().getMangaChapterReadTime()).isEqualTo(5);
            assertThat(settings.getReadTimeSettings().getVisualNovelChapterReadTime()).isEqualTo(5);
            assertThat(settings.getAnimeListSettings()).isNotNull();
            assertThat(settings.getAnimeListSettings().getSubtitlesLanguages()).containsExactly(SubtitlesLanguage.NONE);
            assertThat(settings.getAnimeListSettings().getAnimeWatchStatus()).containsExactly(AnimeStatus.IN_PROGRESS);
            assertThat(settings.getAnimeListSettings().getSkipFillers()).isEqualTo(SkipFillers.NO);
            assertThat(settings.getAnimeListSettings().getStatusAutoChange()).isEqualTo(StatusAutoChange.NO);
            assertThat(settings.getMangaListSettings()).isNotNull();
            assertThat(settings.getMangaListSettings().getChapterLanguages()).containsExactly(ChapterLanguage.NONE);
            assertThat(settings.getMangaListSettings().getMangaReadStatus()).containsExactly(ChapterStatus.IN_PROGRESS);
            assertThat(settings.getMangaListSettings().getStatusAutoChange()).isEqualTo(StatusAutoChange.NO);
            assertThat(settings.getAddToListSettings()).isNotNull();
            assertThat(settings.getAddToListSettings().getSliderPosition()).isEqualTo(SliderPosition.NO_LIMIT);
            assertThat(settings.getAddToListSettings().getShowAddToList()).isEqualTo(ShowOption.NO);
        }
    }

    @Nested
    @DisplayName("User account tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class UserAccountTest {

        @Test
        @Order(1)
        @DisplayName("Should successfully update avatar with file")
        void shouldSuccessfullyUpdateAvatarWithFile() throws IOException {
            login();
            UpdateResult result = userApi.updateAvatar(new AvatarFileUpdateRequest(getLoggedInUserId(), LocalFileResource.of(ResourceLoader.loadFile("user/avatar.png"))));

            assertThat(result).isNotNull();
            assertThat(result.getResult()).isEqualTo(SUCCESS);
        }

        @Test
        @Order(2)
        @DisplayName("Should successfully update avatar with URL")
        void shouldSuccessfullyUpdateAvatarWithUrl() {
            login();
            UpdateResult result = userApi.updateAvatar(new AvatarUrlUpdateRequest(getLoggedInUserId(), "https://www.polmedis.pl/wp-content/uploads/2021/02/avatar-200x200-1.jpg"));
            assertThat(result).isNotNull();
            assertThat(result.getResult()).isEqualTo(SUCCESS);
        }

        @Test
        @Order(3)
        @DisplayName("Should successfully delete avatar")
        void shouldSuccessfullyDeleteAvatar() {
            login();
            UpdateResult result = userApi.deleteAvatar(getLoggedInUserId());
            assertThat(result).isNotNull();
            assertThat(result.getResult()).isEqualTo(SUCCESS);
        }

        @Test
        @DisplayName("Should successfully update password")
        void shouldSuccessfullyUpdatePassword() {
            login();
            UpdateResult result = userApi.updatePassword(UpdatePasswordRequest.builder()
                    .userId(getLoggedInUserId())
                    .currentPassword(API_CONFIG.getPassword())
                    .newPassword(API_CONFIG.getPassword())
                    .build()
            );
            assertThat(result).isNotNull();
            assertThat(result.getResult()).isEqualTo(SUCCESS);
        }
    }

    @Nested
    @DisplayName("MAL List Import tests")
    class MalListImportTest {

        @Test
        @DisplayName("Should successfully import MAL list")
        void shouldSuccessfullyImportMalList() throws IOException {
            login();

            File malListFile = ResourceLoader.loadFile("user/anime-list.gz");

            ImportMalListRequest request = ImportMalListRequest.builder()
                    .userId(getLoggedInUserId())
                    .importType(ImportType.FILL)
                    .malListFile(LocalFileResource.of(malListFile))
                    .build();

            UpdateResult result = userApi.importMalList(request);

            assertThat(result).isNotNull();
            assertThat(result.getResult()).isEqualTo(SUCCESS);
        }

        @Test
        @DisplayName("Should successfully import MAL list with REPLACE type")
        void shouldSuccessfullyImportMalListWithReplaceType() throws IOException {
            login();

            File malListFile = ResourceLoader.loadFile("user/anime-list.gz");

            ImportMalListRequest request = ImportMalListRequest.builder()
                    .userId(getLoggedInUserId())
                    .importType(ImportType.REPLACE)
                    .malListFile(LocalFileResource.of(malListFile))
                    .build();

            UpdateResult result = userApi.importMalList(request);

            assertThat(result).isNotNull();
            assertThat(result.getResult()).isEqualTo(SUCCESS);
        }
    }

    @Nested
    @DisplayName("Translation tests")
    class TranslationTests {

        @Test
        @DisplayName("Should properly translate all Translatable enums")
        void shouldProperlyTranslateAllTranslatableEnums() {
            assertTranslations(ImportType.values());
            assertTranslations(ChapterLanguage.values());
            assertTranslations(AnimeStatus.values());
            assertTranslations(SubtitlesLanguage.values());
            assertTranslations(SkipFillers.values());
            assertTranslations(ChapterStatus.values());
            assertTranslations(ListType.values());
            assertTranslations(RateType.values());
            assertTranslations(TagType.values());
            assertTranslations(StatusAutoChange.values());
        }

        private <T extends Enum<T> & Translatable> void assertTranslations(T[] values) {
            for (T value : values) {
                String translation = getTranslation(value.getTranslationKey());
                assertThat(translation)
                        .withFailMessage("Translation missing for %s.%s with key %s",
                                value.getDeclaringClass().getSimpleName(), value.name(), value.getTranslationKey())
                        .isNotBlank();
                assertThat(translation)
                        .withFailMessage("Translation for %s.%s is the same as the key: %s",
                                value.getDeclaringClass().getSimpleName(), value.name(), value.getTranslationKey())
                        .isNotEqualTo(value.getTranslationKey());
            }
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static final class UserAsserts {

        static final List<String> IMAGE_EXTENSIONS = Arrays.asList(".jpg", ".png", ".jpeg", ".gif");
        static final LocalDate PAST_DATE = LocalDate.of(2006, 1, 1);
        static final String RANK_USER = "Użytkownik";
        static final String RANK_PREASANT = "Wieśniak";
        static final LocalDateTime PAST_DATE_TIME = LocalDateTime.of(2006, 1, 1, 0, 0);
        static final String LANGUAGE = "pl";

        static void assertComments(List<Comment> comments) {
            if (comments.isEmpty()) {
                log.warn("No comments found");
            } else {
                comments.forEach(comment -> {
                    assertThat(comment.getId()).isNotNull();
                    assertThat(comment.getContent()).isNotEmpty();
                    assertThat(comment.getUsername()).isNotEmpty();
                    assertThat(comment.getUserId()).isNotNull();
                    assertThat(comment.getUserRole()).isNotEmpty();
                    assertThat(IMAGE_EXTENSIONS).anySatisfy(
                            extension -> assertThat(comment.getUserAvatar()).contains(extension));
                    assertThat(comment.getCreatedAt()).isAfter(LocalDateTime.of(2006, 1, 1, 0, 0));
                });
            }
        }

        static void assertFavouriteEntities(List<EntityOverview> favouritePeople) {
            if (favouritePeople.isEmpty()) {
                log.warn("No favourite people found");
            } else {
                favouritePeople.forEach(favouritePerson -> {
                    assertThat(favouritePerson.getId()).isNotNull();
                    assertThat(favouritePerson.getFirstName()).isNotBlank();
                    assertThat(favouritePerson.getMediaId()).isNotNull();
                    assertThat(favouritePerson.getMediaTitle()).isNotBlank();
                    assertThat(favouritePerson.getMediaUrlType()).isNotBlank();
                    assertThat(IMAGE_EXTENSIONS).anySatisfy(
                            extension -> assertThat(favouritePerson.getImageUrl()).contains(extension));
                });
            }
        }

        static void assertFavouriteMediaItems(List<FavouriteMediaItem> favouriteMediaItems) {
            if (!favouriteMediaItems.isEmpty()) {
                favouriteMediaItems.forEach(favouriteMediaItem -> {
                    assertThat(favouriteMediaItem.getId()).isNotNull();
                    assertThat(favouriteMediaItem.getTitle()).isNotBlank();
                    assertThat(favouriteMediaItem.getType()).isNotBlank();
                    assertThat(favouriteMediaItem.getYear()).isNotNull();
                    assertThat(IMAGE_EXTENSIONS).anySatisfy(
                            extension -> assertThat(favouriteMediaItem.getImageUrl()).contains(extension));
                });
            } else {
                log.warn("No favourite media items found");
            }

        }

        static void assertStatistics(MediaStatistics overview) {
            assertThat(overview.getTotalTime()).isNotNull();
            assertThat(overview.getMeanScore()).isNotNull();
            assertThat(overview.getInProgress()).isNotNull();
            assertThat(overview.getCompleted()).isNotNull();
            assertThat(overview.getSkip()).isNotNull();
            assertThat(overview.getHold()).isNotNull();
            assertThat(overview.getDropped()).isNotNull();
            assertThat(overview.getPlanned()).isNotNull();
            assertThat(overview.getTotalTitles()).isNotNull();
            assertThat(overview.getTotalSegments()).isNotNull();
            assertThat(overview.getTotalRevisits()).isNotNull();
        }

        static void assertAchievements(List<Achievement> achievements) {
            assertThat(achievements).isNotNull();
            assertThat(achievements).isNotEmpty();

            boolean hasPreviousTrue = false;
            boolean hasPreviousFalse = false;

            for (Achievement achievement : achievements) {
                assertThat(achievement.getTitle()).isNotBlank();
                assertThat(achievement.getDescription()).isNotBlank();
                assertThat(IMAGE_EXTENSIONS).anySatisfy(
                        extension -> assertThat(achievement.getImageUrl()).contains(extension));
                assertThat(achievement.getDate()).isAfter(PAST_DATE);
                assertThat(achievement.getType()).isNotBlank();

                if (Boolean.TRUE.equals(achievement.getPrevious())) {
                    hasPreviousTrue = true;
                    assertThat(achievement.getProgress()).isEqualTo(100);
                } else if (Boolean.FALSE.equals(achievement.getPrevious())) {
                    hasPreviousFalse = true;
                    assertThat(achievement.getProgress()).isNotNull();
                }
            }

            assertThat(hasPreviousTrue).isTrue();
            assertThat(hasPreviousFalse).isTrue();
        }
    }


    private static final class FavouriteTagsRequestArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(ListType.values()).flatMap(listType -> Stream.of(RateType.values()).flatMap(
                    rateType -> Stream.of(TagType.values()).map(tagType -> Arguments.of(
                            (FavouriteTagsRequest.userId(API_CONFIG.getUnauthenticatedUserId())).listType(listType).rateType(rateType).tagType(tagType).build()))));
        }
    }
}


