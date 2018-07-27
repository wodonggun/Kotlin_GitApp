package tech.thdev.Kotlin_github.data.source.repositories;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import tech.thdev.Kotlin_github.data.Repositories;
import tech.thdev.Kotlin_github.data.RepositoriesItem;
import tech.thdev.Kotlin_github.network.GitHubService;

public class SearchRepository {

    private final GitHubService gitHubService;
    private static SearchRepository instance;

    private SearchRepository(@NotNull GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    public static SearchRepository getInstance(@NotNull GitHubService gitHubService) {
        if (instance == null) {
            synchronized (SearchRepository.class) {
                if (instance == null) {
                    instance = new SearchRepository(gitHubService);
                }
            }
        }
        return instance;
    }

    private int nowPage = 1;

    public Single<List<RepositoriesItem>> searchRepositories(String searchKeyword, String sort, String order, int perPage) {
        return gitHubService.searchUser(searchKeyword, sort, order, nowPage, perPage)
                .map(new Function<Repositories, List<RepositoriesItem>>() {
                    @Override
                    public List<RepositoriesItem> apply(Repositories repositories) throws Exception {
                        return repositories.getItems();
                    }
                });
    }
}
