package tech.thdev.Kotlin_github.view.main.search.adapter.viewmodel

import tech.thdev.Kotlin_github.base.adapter.data.source.AdapterRepositoryInterface
import tech.thdev.Kotlin_github.base.adapter.viewmodel.BaseAdapterViewModel

class SearchAdapterViewModel(adapterRepository: AdapterRepositoryInterface) : BaseAdapterViewModel(adapterRepository) {

    interface OnClickListener {
        fun onClick()
    }

    lateinit var onClickItem: (adapterPosition: Int) -> Unit
}