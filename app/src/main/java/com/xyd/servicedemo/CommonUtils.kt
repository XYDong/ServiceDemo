package com.xyd.servicedemo

import android.content.Context
import android.content.Intent

fun activityTiaozhuan(ctx : Context, clz : Class<Any>){
    var intent = Intent()
    intent.setClass(ctx,clz)
    ctx.startActivity(intent)
}