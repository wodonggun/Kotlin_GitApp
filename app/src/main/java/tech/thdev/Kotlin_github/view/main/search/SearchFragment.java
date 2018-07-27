package tech.thdev.Kotlin_github.view.main.search;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import kotlin.jvm.functions.Function0;
import tech.thdev.Kotlin_github.R;
import tech.thdev.Kotlin_github.data.source.repositories.SearchRepository;
import tech.thdev.Kotlin_github.network.GithubAPI;
import tech.thdev.Kotlin_github.util.LifecycleExtensionsUtilKt;
import tech.thdev.Kotlin_github.view.main.MainActivity;
import tech.thdev.Kotlin_github.view.main.search.adapter.SearchRecyclerAdapter;
import tech.thdev.Kotlin_github.view.main.search.viewmodel.SearchViewModel;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private SearchRecyclerAdapter searchRecyclerAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchRecyclerAdapter = new SearchRecyclerAdapter();

        searchViewModel = LifecycleExtensionsUtilKt.inject(SearchViewModel.class, this, "", new Function0<SearchViewModel>() {
            @Override
            public SearchViewModel invoke() {
                return new SearchViewModel(SearchRepository.getInstance(GithubAPI.getInstance().getGithubService()),
                        searchRecyclerAdapter.getViewModel());
            }
        });

        initView();
        initViewModel();
    }

    private void initView() {
        recyclerView.setAdapter(searchRecyclerAdapter);
        setHasOptionsMenu(true);
    }

    private void initViewModel() {
        searchViewModel.getDisposables().add(searchViewModel.showEmptyViewSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Toast.makeText(getContext(), R.string.message_empty_view, Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));

        searchViewModel.getDisposables().add(searchViewModel.showDetailPageViewSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String url) throws Exception {
                        showChromeTabs(url);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((MainActivity) getContext()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String searchQuery = query;
                if (query == null) {
                    searchQuery = "";
                }
                searchViewModel.search(searchQuery);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchViewModel.search("");
                return false;
            }
        });
    }

    private void showChromeTabs(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
        builder.setStartAnimations(requireContext(), 0, 0);
        builder.setExitAnimations(requireContext(), 0, 0);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(requireContext(), Uri.parse(url));
    }
}
