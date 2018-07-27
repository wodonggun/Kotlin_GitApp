package tech.thdev.Kotlin_github.view.main.search.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import tech.thdev.Kotlin_github.base.adapter.BaseRecyclerViewAdapter;
import tech.thdev.Kotlin_github.base.adapter.data.source.AdapterRepository;
import tech.thdev.Kotlin_github.base.adapter.holder.AndroidViewHolder;
import tech.thdev.Kotlin_github.view.main.search.adapter.holder.SearchViewHolder;
import tech.thdev.Kotlin_github.view.main.search.adapter.viewmodel.SearchAdapterViewModel;

public class SearchRecyclerAdapter extends BaseRecyclerViewAdapter<SearchAdapterViewModel> {

    public SearchRecyclerAdapter() {
        super(new SearchAdapterViewModel(new AdapterRepository()));
    }

    @Override
    protected AndroidViewHolder createViewHolder(int viewType, @NonNull ViewGroup parent) {
        return new SearchViewHolder(parent);
    }
}
