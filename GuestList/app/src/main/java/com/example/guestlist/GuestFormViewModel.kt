package com.example.guestlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guestlist.service.model.GuestModel
import com.example.guestlist.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application.applicationContext
    private val repository: GuestRepository = GuestRepository.getInstance(mContext)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel



    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest
    fun save(name: String, presence: Boolean) {
        repository.create(GuestModel(name = name, presence = presence))
    }

    fun save(guest: GuestModel)
    {
        if(guest.id == 0){
             insert(guest)
        }
        else{
                update(guest)
        }

    }
    fun insert(guestModel: GuestModel) : Boolean
    {
       return repository.create(guestModel)
    }

    fun get(id: Int) {
        guestModel.value =  repository.get(id)
    }

    fun update(guest: GuestModel) {
        repository.update(guest)
        guestModel.value = repository.get(guest.id)
    }
}