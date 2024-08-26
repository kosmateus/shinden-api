package com.github.kosmateus.shinden.user;

import com.github.kosmateus.shinden.http.request.FileResource;
import com.github.kosmateus.shinden.http.request.HttpRequest;
import com.github.kosmateus.shinden.http.response.ResponseHandler;
import com.github.kosmateus.shinden.http.rest.HttpClient;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static com.github.kosmateus.shinden.constants.ShindenConstants.SHINDEN_URL;

/**
 * Client for handling user-related HTTP requests.
 * <p>
 * The {@code UserHttpClient} class is responsible for making HTTP requests related to user operations,
 * such as updating user avatars and passwords, using the {@link HttpClient} for executing requests.
 * </p>
 *
 * @version 1.0.0
 */
@RequiredArgsConstructor(onConstructor_ = @__(@Inject))
class UserHttpClient {
    private final HttpClient httpClient;

    /**
     * Updates the avatar of a user by uploading a file.
     * <p>
     * This method sends an HTTP POST request to update the user's avatar with a provided image file.
     * It includes form data and the file to be uploaded.
     * </p>
     *
     * @param userId       the ID of the user whose avatar is being updated
     * @param formData     a map containing form fields required for the avatar update
     * @param fileResource the {@link FileResource} representing the file to be uploaded
     * @return a {@link ResponseHandler} containing the response as a string
     */
    ResponseHandler<String> updateUserAvatar(Long userId, Map<String, String> formData, FileResource fileResource) {
        return httpClient.post(HttpRequest.builder()
                .formFields(formData)
                .target(SHINDEN_URL)
                .path("/user/" + userId + "/edit_avatar")
                .fileResources(ImmutableMap.of("avatar-local", fileResource))
                .formFields(formData)
                .build(), String.class);
    }

    /**
     * Updates the avatar of a user without uploading a file.
     * <p>
     * This method sends an HTTP POST request to update the user's avatar using only form data.
     * </p>
     *
     * @param userId   the ID of the user whose avatar is being updated
     * @param formData a map containing form fields required for the avatar update
     * @return a {@link ResponseHandler} containing the response as a string
     */
    ResponseHandler<String> updateUserAvatar(Long userId, Map<String, String> formData) {
        return httpClient.post(HttpRequest.builder()
                .target(SHINDEN_URL)
                .formFields(formData)
                .path("/user/" + userId + "/edit_avatar")
                .build(), String.class);
    }

    /**
     * Updates the password of a user.
     * <p>
     * This method sends an HTTP POST request to update the user's password using the provided form data.
     * </p>
     *
     * @param userId   the ID of the user whose password is being updated
     * @param formData a map containing form fields required for the password update
     * @return a {@link ResponseHandler} containing the response as a string
     */
    ResponseHandler<String> updatePassword(Long userId, Map<String, String> formData) {
        return httpClient.post(HttpRequest.builder()
                .target(SHINDEN_URL)
                .formFields(formData)
                .path("/user/" + userId + "/edit_passwd")
                .build(), String.class);
    }
}
