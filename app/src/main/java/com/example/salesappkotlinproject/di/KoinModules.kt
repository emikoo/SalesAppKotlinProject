package com.example.salesappkotlinproject.di

import androidx.room.Room
import com.example.notesapp.data.network.client.*
import com.example.salesappkotlinproject.data.local.AppDatabase
import com.example.salesappkotlinproject.data.local.DATABASE_NAME
import com.example.salesappkotlinproject.data.network.api.AuthApi
import com.example.salesappkotlinproject.helper.PrefsHelper
import com.example.salesappkotlinproject.repository.ProductRepositoryImpl
import com.example.salesappkotlinproject.repository.UserRepository
import com.example.salesappkotlinproject.repository.UserRepositoryImpl
import com.example.salesappkotlinproject.ui.authorization.login.LoginFragment
import com.example.salesappkotlinproject.ui.authorization.registration.AuthViewModel
import com.example.salesappkotlinproject.ui.authorization.registration.CheckPersonRegistrationFragment
import com.example.salesappkotlinproject.ui.authorization.registration.RegistrationFragment
import com.example.salesappkotlinproject.ui.owner.bottom_nav.product_list.ProductListFragment
import com.example.salesappkotlinproject.ui.owner.bottom_nav.product_list.ProductViewModel
import com.example.salesappkotlinproject.ui.owner.bottom_nav.sales_history.SalesHistoryFragment
import com.example.salesappkotlinproject.ui.owner.bottom_nav.sales_history.SoldProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    fragment { ProductListFragment() }
    fragment { SalesHistoryFragment() }
    fragment { LoginFragment() }
    fragment { CheckPersonRegistrationFragment() }
    fragment { RegistrationFragment() }
}

val viewModelModule = module {
    viewModel { ProductViewModel(get()) }
    viewModel { SoldProductViewModel(get()) }
    viewModel { AuthViewModel(get()) }
}

val repositoryModule = module {
    factory { ProductRepositoryImpl(get()) }
    factory<UserRepository> { UserRepositoryImpl(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .allowMainThreadQueries()
//            .addMigrations(MIGRATION_1_2)
            .build()
    }
    single { get<AppDatabase>().productDao() }
}

val networkRepository = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get(), get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideUserApi(get()) }
    single { TokenAuthenticator(get(), get()) }
    single { HeadersInterceptor() }
    single { PrefsHelper.init(androidContext()) }

}