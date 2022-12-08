package vn.luongvo.kmm.survey.di.module

import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import vn.luongvo.kmm.survey.data.remote.apiclient.ApiClient
import vn.luongvo.kmm.survey.data.remote.datasource.AuthRemoteDataSource
import vn.luongvo.kmm.survey.data.remote.datasource.AuthRemoteDataSourceImpl

private const val UNAUTHORIZED_API_CLIENT = "UNAUTHORIZED_API_CLIENT"

val remoteModule = module {
    singleOf(::ApiClient)
    single(named(UNAUTHORIZED_API_CLIENT)) { ApiClient(get()) }
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(get(named(UNAUTHORIZED_API_CLIENT))) }
}
