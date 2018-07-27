package tech.thdev.Kotlin_github.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import tech.thdev.Kotlin_github.base.adapter.holder.AndroidViewHolder;
import tech.thdev.Kotlin_github.base.adapter.holder.BaseViewHolder;
import tech.thdev.Kotlin_github.base.adapter.viewmodel.BaseAdapterViewModel;

public abstract class BaseRecyclerViewAdapter<VIEW_MODEL extends BaseAdapterViewModel> extends RecyclerView.Adapter<AndroidViewHolder> {

    private VIEW_MODEL viewModel;

    public BaseRecyclerViewAdapter(@NonNull VIEW_MODEL viewModel) {
        super();
        this.viewModel = viewModel;

        init();
    }

    public VIEW_MODEL getViewModel() {
        return viewModel;
    }

    /**
     * ViewModel의 화면 갱신을 초기화
     */
    private void init() {
        viewModel.setNotifyItemChanged(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer integer) {
                notifyItemChanged(integer);
                return null;
            }
        });

        viewModel.setNotifyDataSetChanged(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                notifyDataSetChanged();
                return null;
            }
        });
    }

    @NonNull
    @Override
    public AndroidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AndroidViewHolder viewHolder = createViewHolder(viewType, parent);
        if (viewHolder instanceof BaseViewHolder) {
            ((BaseViewHolder) viewHolder).setViewModel(viewModel);
        }
        return viewHolder;
    }

    protected abstract AndroidViewHolder createViewHolder(int viewType, @NonNull ViewGroup parent);

    @Override
    public void onBindViewHolder(@NonNull AndroidViewHolder holder, int position) {
        if (holder instanceof BaseViewHolder) {
            ((BaseViewHolder) holder).checkItemAndBindViewHolder(viewModel.getAdapterRepository().getItem(position));
        }
    }

    @Override
    public int getItemCount() {
        return viewModel.getAdapterRepository().getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return viewModel.getAdapterRepository().getItemViewType(position);
    }
}
