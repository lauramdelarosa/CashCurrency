package com.delarosa.cashcurrency.ui.launcher

import androidx.lifecycle.ViewModel
import com.delarosa.cashcurrency.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class LauncherModule {

    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    internal abstract fun bindLauncherViewModel(viewModel: LauncherViewModel): ViewModel
}