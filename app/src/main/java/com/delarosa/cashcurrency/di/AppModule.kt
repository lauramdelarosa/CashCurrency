package com.delarosa.cashcurrency.di

import android.content.Context
import com.delarosa.cashcurrency.MainApplication
import com.delarosa.cashcurrency.ui.config.ConfigActivity
import com.delarosa.cashcurrency.ui.config.ConfigModule
import com.delarosa.cashcurrency.ui.config.ConfigViewModel
import com.delarosa.cashcurrency.util.PreferencesAdapter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApplication): Context = application.applicationContext

     @Singleton
      @Provides
      fun providesPreference(context: Context): PreferencesAdapter =
         PreferencesAdapter(context)
    @Module
    object MyModule {
        @JvmStatic
        @Provides
        fun myViewModel(preferencesAdapter: PreferencesAdapter) = ConfigViewModel(preferencesAdapter)
    }

}