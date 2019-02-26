package com.globallogic.codechallenge

import android.app.Application
import com.globallogic.codechallenge.data.db.CodeChallengeDatabase
import com.globallogic.codechallenge.data.network.*
import com.globallogic.codechallenge.data.repository.CodingChallengeRepository
import com.globallogic.codechallenge.data.repository.CodingChallengeRepositoryImpl
import com.globallogic.codechallenge.ui.items.list.ItemListViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CodeChallengeApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy{
        import(androidXModule(this@CodeChallengeApplication))
        bind() from singleton { CodeChallengeDatabase(instance()) }
        bind() from singleton { instance<CodeChallengeDatabase>().itemsDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton {CodeChallengeApiService(instance())}
        bind<ItemNetworkDataSource>() with singleton { ItemNetworkDataSourceImpl(instance()) }
        bind<CodingChallengeRepository>() with singleton { CodingChallengeRepositoryImpl(instance(), instance()) }
        bind() from provider { ItemListViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}