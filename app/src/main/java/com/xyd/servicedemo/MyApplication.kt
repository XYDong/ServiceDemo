package com.xyd.servicedemo

import android.app.Application
import com.blankj.utilcode.util.Utils

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}