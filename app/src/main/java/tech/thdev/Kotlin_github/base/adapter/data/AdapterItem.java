package tech.thdev.Kotlin_github.base.adapter.data;

public class AdapterItem {

    private int viewType;
    private Object item;

    public AdapterItem(int viewType, Object item) {
        this.viewType = viewType;
        this.item = item;
    }

    public int getViewType() {
        return viewType;
    }

    public Object getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "viewType " + viewType + ", item " + item;
    }
}
