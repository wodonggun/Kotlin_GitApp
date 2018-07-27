package tech.thdev.Kotlin_github.view.main.user.adapter

import android.view.ViewGroup
import tech.thdev.Kotlin_github.base.adapter.BaseRecyclerViewAdapter
import tech.thdev.Kotlin_github.base.adapter.data.source.AdapterRepository
import tech.thdev.Kotlin_github.base.adapter.holder.BaseViewHolder
import tech.thdev.Kotlin_github.view.main.user.adapter.holder.LoginViewHolder
import tech.thdev.Kotlin_github.view.main.user.adapter.viewmodel.UserAdapterViewModel

class UserRecyclerAdapter : BaseRecyclerViewAdapter<UserAdapterViewModel>(UserAdapterViewModel(AdapterRepository())) {

    override fun createViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder<Any, UserAdapterViewModel> =
            when (viewType) {
                UserAdapterViewModel.VIEW_TYPE_USER -> LoginViewHolder(parent)
                else -> LoginViewHolder(parent)
            }
}