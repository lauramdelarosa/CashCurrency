package com.delarosa.cashcurrency.di

import android.content.Context
import com.delarosa.cashcurrency.MainApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApplication): Context = application.applicationContext

    /*  @Singleton
      @Provides
      fun providesPreferenceStorage(context: Context): PreferenceStorage =
          SharedPreferenceStorage(context)*/
}