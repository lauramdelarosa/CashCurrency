package com.delarosa.cashcurrency.di

import com.delarosa.cashcurrency.ui.config.ConfigActivity
import com.delarosa.cashcurrency.ui.config.ConfigModule
import com.delarosa.cashcurrency.ui.launcher.LauncherModule
import com.delarosa.cashcurrency.ui.launcher.LauncherActivity
import com.delarosa.cashcurrency.ui.main.MainActivity
import com.delarosa.cashcurrency.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LauncherModule::class])
    internal abstract fun launcherActivity(): LauncherActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [ConfigModule::class])
    internal abstract fun configActivity(): ConfigActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun mainActivity(): MainActivity
}