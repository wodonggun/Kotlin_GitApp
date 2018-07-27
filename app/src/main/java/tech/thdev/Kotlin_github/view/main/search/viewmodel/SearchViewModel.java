package tech.thdev.Kotlin_github.view.main.search.viewmodel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import tech.thdev.Kotlin_github.base.viewmodel.BaseLifecycleViewModel;
import tech.thdev.Kotlin_github.data.RepositoriesItem;
import tech.thdev.Kotlin_github.data.source.repositories.SearchRepository;
import tech.thdev.Kotlin_github.view.main.search.adapter.viewmodel.SearchAdapterViewModel;

public class SearchViewModel extends BaseLifecycleViewModel {

    private final String defaultOrder = "stars";
    private final String defaultSort = "desc";
    private final int defaultPerPage = 100;

    private final SearchRepository searchRepository;
    private final SearchAdapterViewModel searchAdapterViewModel;

    private final BehaviorSubject<String> searchSubject = BehaviorSubject.create();
    public final BehaviorSubject<Boolean> showEmptyViewSubject = BehaviorSubject.create();
    public final BehaviorSubject<String> showDetailPageViewSubject = BehaviorSubject.create();

    public SearchViewModel(SearchRepository searchRepository, SearchAdapterViewModel searchAdapterViewModel) {
        super();

        this.searchRepository = searchRepository;
        this.searchAdapterViewModel = searchAdapterViewModel;

        initVieModel();

        searchAdapterViewModel.setOnClickItem(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer integer) {
                Object item = searchAdapterViewModel.getAdapterRepository().getItem(integer);
                if (item instanceof RepositoriesItem) {
                    showDetailPageViewSubject.onNext(((RepositoriesItem) item).getHtmlUrl());
                }
                return null;
            }
        });
    }

    private void initVieModel() {
        getDisposables().add(searchSubject
                .observeOn(Schedulers.io())
                .filter(new AppendOnlyLinkedArrayList.NonThrowingPredicate<String>() {
                    @Override
                    public boolean test(String query) {
                        return !query.isEmpty();
                    }
                })
                .switchMapSingle(new Function<String, SingleSource<List<RepositoriesItem>>>() {
                    @Override
                    public SingleSource<List<RepositoriesItem>> apply(String s) throws Exception {
                        return searchRepository.searchRepositories(s, defaultSort, defaultOrder, defaultPerPage)
                                .subscribeOn(Schedulers.io())
                                .observeOn(Schedulers.io());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new AppendOnlyLinkedArrayList.NonThrowingPredicate<List<RepositoriesItem>>() {
                    @Override
                    public boolean test(List<RepositoriesItem> repositoriesItems) {
                        if (repositoriesItems.size() <= 0) {
                            searchAdapterViewModel.getAdapterRepository().clear();
                            searchAdapterViewModel.getNotifyDataSetChanged().invoke();
                            showEmptyViewSubject.onNext(true);
                        }
                        return repositoriesItems.size() > 0;
                    }
                })
                .subscribe(new Consumer<List<RepositoriesItem>>() {
                    @Override
                    public void accept(List<RepositoriesItem> repositoriesItems) throws Exception {
                        searchAdapterViewModel.getAdapterRepository().clear();
                        searchAdapterViewModel.getAdapterRepository().addItems(0, repositoriesItems);
                        searchAdapterViewModel.getNotifyDataSetChanged().invoke();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }));
    }

    public void search(@NotNull String query) {
        searchSubject.onNext(query);
    }
}
