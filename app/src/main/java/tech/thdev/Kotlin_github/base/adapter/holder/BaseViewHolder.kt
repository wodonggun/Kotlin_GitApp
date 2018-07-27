package tech.thdev.Kotlin_github.base.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.thdev.Kotlin_github.base.adapter.viewmodel.BaseAdapterViewModel

@Suppress("UNCHECKED_CAST")
abstract class BaseViewHolder<in ITEM : Any, VIEW_MODEL : BaseAdapterViewModel> @JvmOverloads constructor(
        itemView: View,
        protected val context: Context = itemView.context) : AndroidViewHolder(
        itemView) {

    constructor(
            layoutRes: Int,
            parent: ViewGroup) : this(
            LayoutInflater.from(parent.context).inflate(layoutRes, parent, false), parent.context)

    private lateinit var _viewModel: VIEW_MODEL

    var viewModel: VIEW_MODEL
        get() = _viewModel
        set(value) {
            _viewModel = value
            _viewModel.onInitViewModel()
        }

    fun checkItemAndBindViewHolder(item: Any?) {
        try {
            onBindViewHolder(item as? ITEM)
        } catch (e: Exception) {
            onBindViewHolder(null)
        }
    }

    /**
     * How to use.
     *
     * viewModel.onClick.... or the others use viewMode
     */
    abstract fun VIEW_MODEL.onInitViewModel()

    /**
     * Use viewHolder
     */
    abstract fun onBindViewHolder(item: ITEM?)
}