package tech.thdev.Kotlin_github.data;

import com.google.gson.annotations.SerializedName;

public class RepositoriesItem {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("owner")
    private UserItem owner;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("description")
    private String description;
    @SerializedName("stargazers_count")
    private int stargazersCount;
    @SerializedName("language")
    private String language;
    @SerializedName("forks_count")
    private int forksCount;
    @SerializedName("license")
    private License license;

    public RepositoriesItem(int id, String name, String fullName,
                            UserItem owner, String htmlUrl,
                            String description, int stargazersCount, int forksCount,
                            String language, License license) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.owner = owner;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.stargazersCount = stargazersCount;
        this.forksCount = forksCount;
        this.language = language;
        this.license = license;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public UserItem getOwner() {
        return owner;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public String getLanguage() {
        return language;
    }

    public int getForksCount() {
        return forksCount;
    }

    public License getLicense() {
        return license;
    }
}
