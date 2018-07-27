package tech.thdev.Kotlin_github.base.adapter.util

inline fun <reified T: Any> Any?.cast(): T? = this as? T