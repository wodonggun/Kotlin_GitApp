package tech.thdev.Kotlin_github.view.main.user.adapter.viewmodel;

import org.jetbrains.annotations.NotNull;

import tech.thdev.Kotlin_github.base.adapter.data.source.AdapterRepositoryInterface;
import tech.thdev.Kotlin_github.base.adapter.viewmodel.BaseAdapterViewModel;

public class UserAdapterViewModel extends BaseAdapterViewModel {

    public static final int VIEW_TYPE_USER = 0;

    public UserAdapterViewModel(@NotNull AdapterRepositoryInterface adapterRepository) {
        super(adapterRepository);
    }
}
