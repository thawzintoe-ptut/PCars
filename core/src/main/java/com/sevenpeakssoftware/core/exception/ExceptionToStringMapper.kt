package com.sevenpeakssoftware.core.exception

import com.sevenpeakssoftware.core.mapper.UnidirectionalMap

interface ExceptionToStringMapper : UnidirectionalMap<Throwable, String>
