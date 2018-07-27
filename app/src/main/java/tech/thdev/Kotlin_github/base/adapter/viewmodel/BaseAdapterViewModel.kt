package tech.thdev.Kotlin_github.base.adapter.viewmodel

import tech.thdev.Kotlin_github.base.adapter.data.source.AdapterRepositoryInterface

abstract class BaseAdapterViewModel(val adapterRepository: AdapterRepositoryInterface) {

    lateinit var notifyDataSetChanged: () -> Unit

    lateinit var notifyItemChanged: (position: Int) -> Unit
}