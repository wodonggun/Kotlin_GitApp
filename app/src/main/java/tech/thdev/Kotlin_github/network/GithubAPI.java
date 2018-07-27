package tech.thdev.Kotlin_github.network;

import tech.thdev.Kotlin_github.contract.Contract;

public class GithubAPI {

    private GitHubService gitHubService;

    private GithubAPI() {
        gitHubService = RetrofitCreator.createRetrofit(Contract.API, GitHubService.class);
    }

    private static GithubAPI instance;

    public static GithubAPI getInstance() {
        if (instance == null) {
            synchronized (GithubAPI.class) {
                if (instance == null) {
                    instance = new GithubAPI();
                }
            }
        }
        return instance;
    }

    public GitHubService getGithubService() {
        return gitHubService;
    }
}
