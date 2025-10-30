package com.mysecondapp.newsapp.data

import com.mysecondapp.newsapp.data.ApiBuilder.buildapi
import com.mysecondapp.newsapp.data.model.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class newsrepo {
    val retroinstance = buildapi.retrofitobject()

    suspend fun getheadline(country : String = "us") : Flow<apistate>{
        return flow {
            emit(apistate(loading = true))
            try {
                val response = retroinstance.getheadlines(country = country)
                emit(apistate(data_to_loaded = response))

            }catch(e : HttpException){
                emit(apistate(error = e.localizedMessage))
            }catch (e : Exception){
                emit(apistate(error = e.localizedMessage))
            }


        }
    }
    fun geteverthing(q : String = "us") : Flow<apistate>{
        return flow {
            emit(apistate(loading = true))
            try {
                val response = retroinstance.getEverything(q = q)
                emit(apistate(data_to_loaded = response))

            }catch(e : HttpException){
                emit(apistate(error = e.localizedMessage))
            }catch (e : Exception){
                emit(apistate(error = e.localizedMessage))
            }


        }
    }
}

data class apistate(
    val loading: Boolean? = false,
    val data_to_loaded: ApiResponse? = null,
    val error: String? = null
)