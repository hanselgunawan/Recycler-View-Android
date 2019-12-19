package com.raywenderlich.galacticon

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// ViewGroup doesn't have inflate function on it
// that's why we use this Extensions function
// ViewGroup.inflate equals to adding inflate class to ViewGroup class

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
