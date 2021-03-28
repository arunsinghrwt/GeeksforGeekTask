package com.arun.geeksforgeektask

import androidx.multidex.MultiDexApplication
import com.arun.geeksforgeektask.Network.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

import org.koin.core.context.startKoin


/**
// Created by Arun Singh Rawat  on 28-03-2021.



 **/

class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        //koin intalize
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            koin.loadModules(listOf(retrofitModule))
            koin.createRootScope()
        }

    }
}