package com.werockstar.kotlin2days

class UserAdapter(val func: () -> Unit) {

    fun onClick() {
        func()
    }
}