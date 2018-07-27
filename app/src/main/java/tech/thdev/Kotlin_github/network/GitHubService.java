package tech.thdev.Kotlin_github.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tech.thdev.Kotlin_github.data.Repositories;

public interface GitHubService {

    /**
     * Github search repositories
     * <a href="https://developer.github.com/v3/search/#search-repositories">Search repositories</a>
     *
     * @param userKeyword : q string	Required. The search keywords, as well as any qualifiers.
     * @param sort : string	The sort field. One of stars, forks, or updated. Default: results are sorted by best match.
     * @param order : string	The sort order if sort parameter is provided. One of asc or desc. Default: desc
     * @param page : now page. start 1
     * @param perPage : per page 100
     */
    @GET("/search/repositories?")
    Single<Repositories> searchUser(
            @Query(value = "q", encoded = true) String userKeyword,
            @Query("sort") String sort,
            @Query("order") String order,
            @Query("page") int page,
            @Query("per_page") int perPage);
}
