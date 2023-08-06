package com.sevenpeakssoftware.core.mapper

interface UnidirectionalMap<F, T> {
    fun map(item: F): T
}
