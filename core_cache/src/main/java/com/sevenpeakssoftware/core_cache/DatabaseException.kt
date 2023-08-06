package com.sevenpeakssoftware.core_cache

import java.io.IOException

data class DatabaseException constructor(
    val errorMessage: String
) : IOException()
