package com.mysecondapp.newsapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mysecondapp.newsapp.data.ApiBuilder.buildapi
import com.mysecondapp.newsapp.data.model.ApiResponse
import com.mysecondapp.newsapp.data.newsrepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class newsviewmodel : ViewModel() {
    val retroinstance = buildapi.retrofitobject()
    private val flowstate = MutableStateFlow(appstate())
    val currentstate = flowstate.asStateFlow()
    val newsrepo : newsrepo = newsrepo()


    init {
        showeverythingnews()
    }

    fun shownews(){
        viewModelScope.launch {
            newsrepo.getheadline().collect {
                if(it.loading == true){
                    flowstate.value = appstate(is_loading = true)
                }
                else if(it.data_to_loaded != null){
                    flowstate.value = appstate(data_loaded = it.data_to_loaded, is_loading = false)
                }
                else if(it.error.isNullOrBlank().not()){
                    flowstate.value = appstate(is_error = it.error)
                }
            }
        }
    }
    fun showeverythingnews(ser :String = "tech-crunch"){
        viewModelScope.launch {
            newsrepo.geteverthing(ser).collect {
                if(it.loading == true){
                    flowstate.value = appstate(is_loading = true)
                }
                else if(it.data_to_loaded != null){
                    flowstate.value = appstate(data_loaded = it.data_to_loaded, is_loading = false)
                }
                else if(it.error.isNullOrBlank().not()){
                    flowstate.value = appstate(is_error = it.error)
                }
            }
        }
    }

}
data class appstate(
    val is_loading : Boolean? = false,
    val data_loaded : ApiResponse? = null,
    val is_error : String? = ""
)