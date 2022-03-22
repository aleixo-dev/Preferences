package br.com.nicolas.preferences.di

import android.content.Context
import android.content.SharedPreferences
import br.com.nicolas.preferences.data.Constants.COMMENTS_FILENAME
import br.com.nicolas.preferences.data.repository.PrefRepository
import br.com.nicolas.preferences.data.repository.PrefRepositoryImpl
import br.com.nicolas.preferences.data.repository.storage.LocalStore
import br.com.nicolas.preferences.data.repository.storage.LocalStoreDelegate
import br.com.nicolas.preferences.data.service.PreferencesService
import br.com.nicolas.preferences.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val instance = module {

    single { provideRetrofit() }
    single { provideService(get()) }

    single<LocalStoreDelegate> {
        LocalStore(
            provideSharedPreferences(
                context = get()
            )
        )
    }

    factory<PrefRepository> {
        PrefRepositoryImpl(
            service = get(), local = get()
        )
    }

    viewModel { MainViewModel(get()) }

}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideService(retrofit: Retrofit): PreferencesService =
    retrofit.create(PreferencesService::class.java)

private fun provideSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(COMMENTS_FILENAME, Context.MODE_PRIVATE)
}