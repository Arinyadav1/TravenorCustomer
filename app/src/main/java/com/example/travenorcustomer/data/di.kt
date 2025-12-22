package com.example.travenorcustomer.data

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {

    singleOf(::AuthenticationRepositoryImpl){bind<AuthenticationRepository>()}
}