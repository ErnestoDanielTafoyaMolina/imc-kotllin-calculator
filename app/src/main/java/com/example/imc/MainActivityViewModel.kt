package com.example.imc
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    // Guardamos la información del usuario en variables mutables
    var userHeight: MutableLiveData<Float>? = MutableLiveData<Float>().apply { value = 0.0f }
    var userWeight: MutableLiveData<Float>? = MutableLiveData<Float>().apply { value = 0.0f }
    var userIMC: MutableLiveData<Float> = MutableLiveData<Float>().apply { value = 0.0f }
    var userCategory: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    var userName: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

    // Obtenemos la información del usuario
    fun getUserHeightValue(): Float? {
        return this.userHeight?.value
    }

    fun getUserWeightValue(): Float? {
        return this.userWeight?.value
    }

    fun getUserIMCValue(): Float? {
        return this.userIMC.value
    }

    fun getUserCategoryValue(): String? {
        return this.userCategory.value
    }

    fun getUsernameValue():String?{
        return this.userName.value
    }

    // Realizamos acciones con los datos del usuario
    fun setUserHeightValue(value: Float?) {
        this.userHeight?.value = value
    }

    fun setUserWeightValue(value: Float?) {
        this.userWeight?.value = value
    }

    fun setUserIMCValue(value: Float?) {
        this.userIMC.value = value
    }

    fun setUserCategoryValue(value: String?) {
        this.userCategory.value = value
    }
    fun setUsernameValue(value:String?){
        this.userName.value = value
    }
}