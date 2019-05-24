package com.delarosa.cashcurrency.ui.main

import androidx.lifecycle.ViewModel
import com.delarosa.cashcurrency.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
internal abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindDashboardViewModel(viewModel: MainViewModel): ViewModel
}