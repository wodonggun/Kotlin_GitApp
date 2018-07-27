package tech.thdev.Kotlin_github.view.main.search.adapter.holder.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import tech.thdev.Kotlin_github.data.RepositoriesItem;
import tech.thdev.Kotlin_github.view.main.search.adapter.viewmodel.SearchAdapterViewModel;

public class SearchHolderViewModel extends ViewModel {

    private RepositoriesItem item;
    private SearchAdapterViewModel.OnClickListener onClickListener;

    public SearchHolderViewModel(RepositoriesItem item, SearchAdapterViewModel.OnClickListener onClickListener) {
        this.item = item;
        this.onClickListener = onClickListener;
    }

    public ObservableField<String> getImageUrl() {
        return new ObservableField<>(item.getOwner().getAvatarUrl());
    }

    public ObservableField<String> getFullName() {
        return new ObservableField<>(item.getFullName());
    }

    public ObservableField<String> getDescription() {
        return new ObservableField<>(item.getDescription());
    }

    public boolean isShowDescription() {
        return item.getDescription() != null && !item.getDescription().isEmpty();
    }

    public ObservableField<String> getLanguage() {
        if (item.getLanguage() != null) {
            return new ObservableField<>(item.getLanguage());
        } else {
            return new ObservableField<>("");
        }
    }

    public boolean isShowLanguage() {
        return item.getLanguage() != null && !item.getLanguage().isEmpty();
    }

    public ObservableField<String> getStargazersCount() {
        return new ObservableField<>(item.getStargazersCount() + "");
    }

    public boolean isStargazersCount() {
        return item.getStargazersCount() > 0;
    }

    public ObservableField<String> getForksCount() {
        return new ObservableField<>(item.getForksCount() + "");
    }

    public boolean isForksCount() {
        return item.getForksCount() > 0;
    }

    public ObservableField<String> getLicense() {
        if (item.getLicense() != null) {
            return new ObservableField<>(item.getLicense().getName());
        } else {
            return new ObservableField<>("");
        }
    }

    public boolean isLicenseVisibility() {
        return item.getLicense() != null;
    }

    public void onClick() {
        onClickListener.onClick();
    }
}
