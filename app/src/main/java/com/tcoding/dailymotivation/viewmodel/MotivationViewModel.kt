package com.tcoding.dailymotivation.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcoding.dailymotivation.model.Motivation
import com.tcoding.dailymotivation.model.Slip
import com.tcoding.dailymotivation.network.RetroInstance
import com.tcoding.dailymotivation.network.RetroService
import kotlinx.coroutines.*
import retrofit2.*

class MotivationViewModel: ViewModel() {

    lateinit var motivationLiveData: MutableLiveData<Motivation>

    init {
        motivationLiveData = MutableLiveData()
    }

    fun getLiveData() : MutableLiveData<Motivation> {
        return motivationLiveData
    }

    fun callAPI() {


        val api = RetroInstance.getRetroInstance().create(RetroService::class.java)
        GlobalScope.launch(Dispatchers.IO) {
           // delay(5000)
            val response = api.getAdvice().awaitResponse()
            if(response.isSuccessful) {

                val data = response.body()

                withContext(Dispatchers.Main) {
                    motivationLiveData.postValue(data!!)
                }
            }
        }

    }

}