package tech.thdev.Kotlin_github.base.adapter.data.source;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import tech.thdev.Kotlin_github.base.adapter.data.AdapterItem;


public class AdapterRepository implements AdapterRepositoryInterface {

    private List<AdapterItem> itemList = new ArrayList<>();

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return itemList.get(position).getItem();
    }

    @Override
    public void removeAt(int position) {
        itemList.remove(position);
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getViewType();
    }

    @Override
    public void addItem(int viewType, @Nullable Object item) {
        itemList.add(new AdapterItem(viewType, item));
    }

    @Override
    public void addItems(int viewType, @Nullable List<?> itemList) {
        if (itemList != null && itemList.size() > 0) {
            for (Object item : itemList) {
                addItem(viewType, item);
            }
        }
    }

    @Override
    public void clear() {
        itemList.clear();
    }
}
