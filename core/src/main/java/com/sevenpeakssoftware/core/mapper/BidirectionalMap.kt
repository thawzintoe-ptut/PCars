package com.sevenpeakssoftware.core.mapper

interface BidirectionalMap<F, T> {
    fun map(item: F): T
    fun reverseMap(item: T): F
}
