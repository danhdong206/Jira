package com.jira.example.network;

import com.opiyn.mobile.model.network.CategoryResponse;
import com.opiyn.mobile.model.network.HighlightResponse;
import com.opiyn.mobile.model.network.MessageNetwork;
import com.opiyn.mobile.network.request.CommentRequest;
import com.opiyn.mobile.network.request.MessageRequest;
import com.opiyn.mobile.network.request.OpiynRequest;
import com.opiyn.mobile.network.request.PageRequest;
import com.opiyn.mobile.network.request.PushRequest;
import com.opiyn.mobile.network.request.ReportRequest;
import com.opiyn.mobile.network.request.SettingRequest;
import com.opiyn.mobile.network.response.ChatResponse;
import com.opiyn.mobile.network.response.CommentNetworkResponse;
import com.opiyn.mobile.network.response.CommentResponse;
import com.opiyn.mobile.network.response.FollowedReviewersResponse;
import com.opiyn.mobile.network.response.MessagesResponse;
import com.opiyn.mobile.network.response.NotificationResponse;
import com.opiyn.mobile.network.response.OpiynResponse;
import com.opiyn.mobile.network.response.OpiynsResponse;
import com.opiyn.mobile.network.response.OpiynsResponseNew;
import com.opiyn.mobile.network.response.PageResponse;
import com.opiyn.mobile.network.response.PagesResponse;
import com.opiyn.mobile.network.response.PagesResponseNew;
import com.opiyn.mobile.network.response.PhotoRepsonse;
import com.opiyn.mobile.network.response.ProfileFeedResponse;
import com.opiyn.mobile.network.response.SettingsResponse;
import com.opiyn.mobile.network.response.StatusResponse;
import com.opiyn.mobile.network.response.StoriesReponse;
import com.opiyn.mobile.network.response.UserResponse;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by admin on 10/18/16.
 */

public interface RestService {
    @GET("/page-categories")
    Observable<CategoryResponse> getCategory();

    @GET("/feeds/highlights")
    Observable<HighlightResponse> getHighlight(@Query("page_category_id") String categoryId,
                                               @Query("page_number") int pageNumber);

    @GET("/feeds/highlights")
    Observable<HighlightResponse> getHighlight(@Query("page_number") int pageNumber);

    @GET("/feeds/highlights")
    Observable<HighlightResponse> getHighlight(@Query("page_category_id") String categoryId,
                                               @Query("latitude") Double latitude,
                                               @Query("longitude") Double longitude,
                                               @Query("page_number") int pageNumber);

    @GET("/feeds/highlights")
    Observable<HighlightResponse> getHighlight(@Query("latitude") Double latitude,
                                               @Query("longitude") Double longitude,
                                               @Query("page_number") int pageNumber);

    @FormUrlEncoded
    @POST("/sessions/sign_in")
    Observable<UserResponse> login(@Field("accessToken") String accessToken,
                                   @Field("userID") String userId);

    @Deprecated
    @GET("/pages/search/pages")
    Observable<PagesResponse> getPages(@Query("query") String keyword,
                                       @Query("page_number") int pageNumber);

    @Deprecated
    @GET("/pages/search/pages")
    Observable<PagesResponse> getPages(@Query("query") String keyword,
                                       @Query("latitude") Double latitude,
                                       @Query("longitude") Double longitude,
                                       @Query("page_number") int pageNumber);

    @Deprecated
    @GET("/pages/search/pages")
    Observable<PagesResponse> getPages(@Query("query") String keyword,
                                       @Query("latitude") Double latitude,
                                       @Query("longitude") Double longitude,
                                       @Query("distance") int distance,
                                       @Query("page_number") int pageNumber);

    @GET("/search/pages")
    Observable<PagesResponseNew> getPagesNew(@Query("filter") String keyword,
                                             @Query("page_number") int pageNumber,
                                             @Query("page_type") int pageType);

    @GET("/search/pages")
    Observable<PagesResponseNew> getPagesNew(@Query("filter") String keyword,
                                             @Query("coordinates") String location,
                                             @Query("page_number") int pageNumber,
                                             @Query("page_type") int pageType);

    @GET("/search/pages")
    Observable<PagesResponseNew> getPagesNew(@Query("filter") String keyword,
                                             @Query("coordinates") String location,
                                             @Query("distance") int distance,
                                             @Query("page_number") int pageNumber,
                                             @Query("page_type") int pageType);

    @GET("/search/places")
    Observable<PagesResponseNew> getPlaces(@Query("filter") String keyword,
                                           @Query("page_number") int pageNumber,
                                           @Query("page_type") int pageType);

    @GET("/search/places")
    Observable<PagesResponseNew> getPlaces(@Query("filter") String keyword,
                                           @Query("coordinates") String location,
                                           @Query("page_number") int pageNumber,
                                           @Query("page_type") int pageType);

    @GET("/search/places")
    Observable<PagesResponseNew> getPlaces(@Query("filter") String keyword,
                                           @Query("coordinates") String location,
                                           @Query("distance") int distance,
                                           @Query("page_number") int pageNumber,
                                           @Query("page_type") int pageType);

    @GET("/pages/trending-world")
    Observable<PagesResponse> getTrendingWorldPage(@Query("latitude") Double latitude,
                                                   @Query("longitude") Double longitude,
                                                   @Query("page_number") int pageNumber);

    @GET("/pages/trending-friends")
    Observable<PagesResponse> getTrendingFriendsPage(@Query("latitude") Double latitude,
                                                     @Query("longitude") Double longitude,
                                                     @Query("page_number") int pageNumber);

    @PUT("/pages/{page_id}/bookmark")
    Observable<PageResponse> bookmark(@Path("page_id") String pageId, @Body String empty);

    @PUT("/pages/{page_id}/unbookmark")
    Observable<PageResponse> unBookmark(@Path("page_id") String pageId, @Body String empty);

    @GET("/pages/{page_id}/world")
    Observable<PageResponse> getPage(@Path("page_id") String pageId);

    @GET("/pages/{page_id}/opiyns/world")
    Observable<OpiynsResponse> getOpiynsWorld(@Path("page_id") String pageId,
                                              @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/opiyns/following")
    Observable<OpiynsResponse> getOpiynsFollow(@Path("page_id") String pageId,
                                               @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/opiyns/user")
    Observable<OpiynsResponse> getOpiynsYou(@Path("page_id") String pageId,
                                            @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/opiyns/profile")
    Observable<OpiynsResponse> getOpiynsProfile(@Path("page_id") String pageId,
                                                @Query("page_number") int pageNumber);

    @Deprecated
    @GET("/opiyns/search")
    Observable<OpiynsResponse> getOpiyns(@Query("page_id") String pageId,
                                         @Query("query") String keyword,
                                         @Query("page_number") int pageNumber);

    @GET("/search/opiyns")
    Observable<OpiynsResponseNew> searchOpiyns(@Query("page_id") String pageId,
                                               @Query("filter") String keyword,
                                               @Query("page_number") int pageNumber);

    @POST("/pages/{page_id}/opiyn")
    Observable<OpiynResponse> opiyn(@Path("page_id") String id, @Body OpiynRequest opiynRequest);

    @POST("/pages/{page_id}/disopiyn")
    Observable<OpiynResponse> disOpiyn(@Path("page_id") String id, @Body OpiynRequest opiynRequest);

    @POST("/pages/{page_id}/unopiyn")
    Observable<OpiynResponse> unOpiyn(@Path("page_id") String id, @Body OpiynRequest opiynRequest);

    @PUT("/pages/{page_id}/report")
    Observable<PageResponse> report(@Path("page_id") String pageId, @Body ReportRequest reportRequest);

    @GET("/pages/{page_id}/activities/world")
    Observable<ChatResponse> getChatWorld(@Path("page_id") String pageId,
                                          @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/activities/friends")
    Observable<ChatResponse> getChatFriend(@Path("page_id") String pageId,
                                           @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/activities/profile")
    Observable<ChatResponse> getChatProfile(@Path("page_id") String pageId,
                                            @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/feed/{filter}")
    Observable<ChatResponse> getPageFeed(@Path("page_id") String pageId,
                                         @Path("filter") String filter,
                                         @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/images/{filter}")
    Observable<PhotoRepsonse> getPhotos(@Path("page_id") String pageId,
                                        @Path("filter") String filter,
                                        @Query("page_number") int pageNumber);

    @POST("/pages")
    Observable<PageResponse> createPage(@Body PageRequest pageRequest);

    @GET("/pages/search/people")
    Observable<PagesResponse> getPeople(@Query("query") String keyword,
                                        @Query("page_number") int pageNumber);

    @GET("/search/people")
    Observable<PagesResponseNew> getPeople(@Query("filter") String keyword,
                                           @Query("page_number") int pageNumber,
                                           @Query("page_type") int pageType);

    @PUT("/pages/{page_id}/follow")
    Observable<PageResponse> follow(@Path("page_id") String pageId, @Body String empty);

    @PUT("/pages/{page_id}/unfollow")
    Observable<PageResponse> unFollow(@Path("page_id") String pageId, @Body String empty);

    @GET("/pages/{page_id}/feed")
    Observable<ProfileFeedResponse> getProfileFeed(@Path("page_id") String pageId,
                                                   @Query("page_number") int pageNumber);

    @GET("/feeds/bookmarked-pages")
    Observable<ProfileFeedResponse> getBookmarkFeed(@Query("page_number") int pageNumber);

    @GET("/feeds/friends-and-following")
    Observable<ProfileFeedResponse> getFollowingFeed(@Query("page_number") int pageNumber);

    @GET("/feeds/notifications")
    Observable<ProfileFeedResponse> getNotificationFeed(@Query("page_number") int pageNumber);

    @GET("/feeds/notifications/unread")
    Observable<NotificationResponse> getUnreadNotification(@Query("page_number") int pageNumber);

    @PUT("/feeds/notifications/{id}/read")
    Observable<StatusResponse> readNotification(@Path("id") String id, @Body String empty);

    @GET("/pages/{page_id}/opiyns/{opiyn_id}/comments")
    Observable<CommentResponse> getCommentOpiyn(@Path("page_id") String pageId,
                                                @Path("opiyn_id") String opiynId,
                                                @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/comments")
    Observable<CommentResponse> getCommentOpiyn(@Path("page_id") String pageId,
                                                @Query("page_number") int pageNumber);

    @POST("/pages/{page_id}/opiyns/{opiyn_id}/comments")
    Observable<CommentNetworkResponse> postCommentOpiyn(@Path("page_id") String pageId,
                                                        @Path("opiyn_id") String opiynId,
                                                        @Body CommentRequest commentRequest);

    @POST("/pages/{page_id}/comments")
    Observable<CommentNetworkResponse> postCommentOpiyn(@Path("page_id") String pageId,
                                                        @Body CommentRequest commentRequest);

    @POST("/messages")
    Observable<MessageNetwork> postMessage(@Body MessageRequest messageRequest);

    @PUT("/opiyns/{opiyn_id}/report")
    Observable<PageResponse> reportOpiyn(@Path("opiyn_id") String opiynId, @Body ReportRequest reportRequest);

    @GET("/pages/{page_id}/bookmarkers")
    Observable<PagesResponse> getBookmarker(@Path("page_id") String pageId,
                                            @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/followers")
    Observable<PagesResponse> getFollowers(@Path("page_id") String pageId,
                                           @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/following")
    Observable<PagesResponse> getFollowing(@Path("page_id") String pageId,
                                           @Query("page_number") int pageNumber);

    @PUT("/pages/opiyns/{opiyn_id}/hide")
    Observable<PageResponse> hideOpiyn(@Path("opiyn_id") String opiynId, @Body String empty);

    @PUT("/pages/opiyns/{opiyn_id}/show")
    Observable<PageResponse> showOpiyn(@Path("opiyn_id") String opiynId, @Body String empty);

    @PUT("/images/{image_id}/report")
    Observable<PageResponse> reportImage(@Path("image_id") String opiynId, @Body ReportRequest reportRequest);

    @GET("/pages/bookmarked")
    Observable<PagesResponse> getMyBookmark(@Query("page_number") int pageNumber);

    @DELETE("/comments/{comment_id}")
    Observable<CommentResponse> deleteComment(@Path("comment_id") String commentId);

    @PUT("/comments/{comment_id}/report")
    Observable<CommentResponse> reportComment(@Path("comment_id") String commentId, @Body ReportRequest reportRequest);

    @PUT("/settings")
    Observable<Object> setSettings(@Body SettingRequest settingRequest);

    @GET("/settings")
    Observable<SettingsResponse> getSettings();

    @DELETE("/images/{image_id}")
    Observable<Object> deleteMyPhoto(@Path("image_id") String imageId);

    @DELETE("/pages/{page_id}/images/{image_id}")
    Observable<Object> deletePagePhoto(@Path("page_id") String pageId, @Path("image_id") String imageId);

    @POST("/mobile-devices")
    Observable<Object> registerPush(@Body PushRequest pushRequest);

    @DELETE("/mobile-devices/{device_token}")
    Observable<Object> deletePushToken(@Path("device_token") String deviceToken);

    @GET("/pages/{page_id}/comments/search")
    Observable<PagesResponse> searchMentionPage(@Path("page_id") String pageId,
                                                @Query("query") String keyword,
                                                @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/followed-reviewers")
    Observable<FollowedReviewersResponse> getFollowedReviewers(@Path("page_id") String pageId,
                                                               @Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/opiyns/{opiyn_id}/followed-reviewers")
    Observable<FollowedReviewersResponse> getFollowedReviewers(@Path("page_id") String pageId,
                                                               @Path("opiyn_id") String opiynId,
                                                               @Query("page_number") int pageNumber);

    @GET("/messages")
    Observable<MessagesResponse> getMyMessages(@Query("page_number") int pageNumber);

    @GET("/pages/{page_id}/stories")
    Observable<StoriesReponse> getStory(@Path("page_id") String pageId,
                                        @Query("page_number") int pageNumber);

    @PUT("/comments/{comment_id}/hide")
    Observable<Object> hideComment(@Path("comment_id") String commentId, @Body String empty);
}
