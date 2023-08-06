package com.sevenpeakssoftware.core.ui

abstract class ScreenState<T>(
    data: ScreenStateData<T>,
    callBack: ScreenStateCallBack<T>
)

interface ScreenStateData<T>

interface ScreenStateCallBack<T>
