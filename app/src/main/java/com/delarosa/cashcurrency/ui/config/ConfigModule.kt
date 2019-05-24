package com.delarosa.cashcurrency.ui.config

import androidx.lifecycle.ViewModel
import com.delarosa.cashcurrency.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ConfigModule {

    @Binds
    @IntoMap
    @ViewModelKey(ConfigViewModel::class)
    internal abstract fun bindConfigViewModel(viewModel: ConfigViewModel): ViewModel
}