package com.github.kosmateus.shinden.user;

import com.github.kosmateus.shinden.exception.ForbiddenException;
import com.github.kosmateus.shinden.exception.JsoupParserException;
import com.github.kosmateus.shinden.exception.NotFoundException;
import com.github.kosmateus.shinden.user.request.AddToListSettingsRequest;
import com.github.kosmateus.shinden.user.request.BaseSettingsRequest;
import com.github.kosmateus.shinden.user.request.FavouriteTagsRequest;
import com.github.kosmateus.shinden.user.request.ListsSettingsRequest;
import com.github.kosmateus.shinden.user.request.UserInformationRequest;
import com.github.kosmateus.shinden.user.response.Achievements;
import com.github.kosmateus.shinden.user.response.FavouriteTag;
import com.github.kosmateus.shinden.user.response.Recommendation;
import com.github.kosmateus.shinden.user.response.Review;
import com.github.kosmateus.shinden.user.response.UserInformation;
import com.github.kosmateus.shinden.user.response.UserOverview;
import com.github.kosmateus.shinden.user.response.UserSettings;

import java.util.List;

/**
 * Interface defining user-related API operations.
 * <p>
 * The {@code UserApi} interface provides methods to interact with user-related data on the platform.
 * It includes operations for retrieving and updating user information, fetching user achievements,
 * favourite tags, reviews, and recommendations.
 * </p>
 *
 * @version 1.0.0
 */
public interface UserApi {

    /**
     * Retrieves the overview of a user by their ID.
     * <p>
     * This method fetches a general overview of the user, including their profile information and other relevant details.
     * </p>
     *
     * @param userId the ID of the user
     * @return the {@link UserOverview} containing the user's overview information
     * @throws NotFoundException    if the user page is not found
     * @throws JsoupParserException if there is an error parsing the web page
     */
    UserOverview getOverview(Long userId);

    /**
     * Retrieves the achievements of a user by their ID.
     * <p>
     * This method fetches the achievements earned by the user, such as badges, awards, and other recognitions.
     * </p>
     *
     * @param userId the ID of the user
     * @return the {@link Achievements} containing the user's achievements
     * @throws NotFoundException    if the achievements page is not found
     * @throws JsoupParserException if there is an error parsing the web page
     */
    Achievements getAchievements(Long userId);

    /**
     * Retrieves the favourite tags of a user based on the request.
     * <p>
     * This method returns a list of tags that the user has marked as favourites, based on the details provided in the request.
     * </p>
     *
     * @param request the {@link FavouriteTagsRequest} containing user and tag details
     * @return a list of {@link FavouriteTag} representing the user's favourite tags
     * @throws NotFoundException    if the favourite tags page is not found
     * @throws JsoupParserException if there is an error parsing the web page
     */
    List<FavouriteTag> getFavouriteTags(FavouriteTagsRequest request);

    /**
     * Retrieves the reviews of a user by their ID.
     * <p>
     * This method returns a list of reviews written by the user, providing insights into their opinions and experiences.
     * </p>
     *
     * @param userId the ID of the user
     * @return a list of {@link Review} representing the user's reviews
     * @throws NotFoundException    if the reviews page is not found
     * @throws JsoupParserException if there is an error parsing the web page
     */
    List<Review> getReviews(Long userId);

    /**
     * Retrieves the recommendations of a user by their ID.
     * <p>
     * This method returns a list of recommendations provided by the user, offering suggestions based on their preferences.
     * </p>
     *
     * @param userId the ID of the user
     * @return a list of {@link Recommendation} representing the user's recommendations
     * @throws NotFoundException    if the recommendations page is not found
     * @throws JsoupParserException if there is an error parsing the web page
     */
    List<Recommendation> getRecommendations(Long userId);

    /**
     * Retrieves the information of a user by their ID.
     * <p>
     * This method fetches detailed information about the user, such as personal details, settings, and preferences.
     * </p>
     *
     * @param userId the ID of the user
     * @return the {@link UserInformation} containing the user's detailed information
     * @throws NotFoundException    if the information page is not found
     * @throws ForbiddenException   if access to the information page is forbidden
     * @throws JsoupParserException if there is an error parsing the web page
     */
    UserInformation getInformation(Long userId);

    /**
     * Updates the information of a user.
     * <p>
     * This method updates the user's information based on the data provided in the request, such as profile settings and preferences.
     * </p>
     *
     * @param request the {@link UserInformationRequest} containing updated user information
     * @throws NotFoundException    if the information page is not found
     * @throws JsoupParserException if there is an error parsing the web page
     */
    void updateInformation(UserInformationRequest request);

    /**
     * Retrieves the settings of a user by their ID.
     * <p>
     * This method fetches the user's settings, such as preferences and configurations.
     * </p>
     *
     * @param userId the ID of the user
     * @return the {@link UserSettings} containing the user's settings
     * @throws NotFoundException    if the settings page is not found
     * @throws JsoupParserException if there is an error parsing the web page
     */
    UserSettings getSettings(Long userId);

    /**
     * Updates the base settings of a user.
     * <p>
     * This method updates the user's base settings, such as account-level configurations, based on the data provided in the request.
     * </p>
     *
     * @param request the {@link BaseSettingsRequest} containing the updated base settings
     * @throws NotFoundException    if the settings page is not found
     * @throws JsoupParserException if there is an error parsing the web page
     */
    void updateBaseSettings(BaseSettingsRequest request);

    /**
     * Updates the list settings of a user.
     * <p>
     * This method updates the user's list settings, which may include settings for anime and manga lists, based on the data provided in the request.
     * </p>
     *
     * @param request the {@link ListsSettingsRequest} containing the updated list settings
     * @throws NotFoundException    if the settings page is not found
     * @throws JsoupParserException if there is an error parsing the web page
     */
    void updateListsSettings(ListsSettingsRequest request);

    /**
     * Updates the "Add to List" settings of a user.
     * <p>
     * This method updates the user's "Add to List" settings, such as how items are added to their lists, based on the data provided in the request.
     * </p>
     *
     * @param request the {@link AddToListSettingsRequest} containing the updated "Add to List" settings
     * @throws NotFoundException    if the settings page is not found
     * @throws JsoupParserException if there is an error parsing the web page
     */
    void updateAddToListSettings(AddToListSettingsRequest request);
}
