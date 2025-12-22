package com.example.travenorcustomer.network

import android.util.Log
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import org.koin.dsl.module

@OptIn(SupabaseInternal::class)
val networkModule = module {

    single {
        createSupabaseClient(
            supabaseUrl = BuildConfig.BASE_URL,
            supabaseKey = BuildConfig.API_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Realtime)

            httpConfig {
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.d("KtorClient", message)
                        }
                    }
                }
            }
        }
    }

}