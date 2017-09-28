package org.ditto.lib.apirest;


import org.ditto.lib.apirest.data.GitUser;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * REST API access points
 */
public interface RxGithubService {
    //https://api.github.com/users/conanchen
    @GET("users/{login}")
    Observable<GitUser> getUser(@Path("login") String login);

//
//
//    @GET("search/repositories")
//    Call<RepoSearchResponse> searchRepos(@Query("q") String query, @Query("page") int page);
}