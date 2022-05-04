package com.example.roommvvmcrudwtthhilt.viewModel

import android.util.Patterns
import androidx.lifecycle.*
import com.example.roommvvmcrudwtthhilt.database.Subscriber
import com.example.roommvvmcrudwtthhilt.repository.SubscriberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriberViewModel @Inject constructor(private val subscribeRepository: SubscriberRepository) :
    ViewModel() {

    private var getAll: Flow<List<Subscriber>> = subscribeRepository.getAll()
    var booleanItemSelected = false
    lateinit var subscriberToUpdateOrDelete: Subscriber

    var inputName = MutableLiveData<String>()
    var inputEmail = MutableLiveData<String>()
    private val statusMessage = MutableLiveData<String>()
    val message: LiveData<String>
        get() = statusMessage

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val deleteOrClearAllButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        deleteOrClearAllButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (inputName.value == null) {
            statusMessage.value = "please enter name"
        } else if (inputEmail.value == null) {
            statusMessage.value = "please enter email"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = "enter correct email"
        } else {
            if (booleanItemSelected) {
                val name = inputName.value!!
                val email = inputEmail.value!!
                subscriberToUpdateOrDelete.name = name
                subscriberToUpdateOrDelete.email = email
                updateSubscriber(subscriberToUpdateOrDelete)

            } else {
                val name = inputName.value!!
                val email = inputEmail.value!!

                val subscriber = Subscriber(0, name, email)
                insertSubscriber(subscriber)
                statusMessage.value = "Successful"
                inputEmail.value = ""
                inputName.value = ""
            }
        }
    }

    fun deleteOrClearAll() {
        if (booleanItemSelected) {
            deleteSubscriber(subscriberToUpdateOrDelete)
        } else {
            clearAllSubscribers()
        }
    }

    private fun insertSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        val rowId = subscribeRepository.insert(subscriber)
        if (rowId > -1) {
            statusMessage.value = "Inserted"
        } else {
            statusMessage.value = "Error Occurred inserting"
        }
    }

    private fun updateSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        val rowId = subscribeRepository.update(subscriber)
        if (rowId > 0) {
            booleanItemSelected = false
            inputName.value = ""
            inputEmail.value = ""
            saveOrUpdateButtonText.value = "Save"
            deleteOrClearAllButtonText.value = "Clear All"
            statusMessage.value = "Updated"
        } else {
            statusMessage.value = "Error Occurred Updating"
        }
    }

    private fun deleteSubscriber(subscriberToUpdateOrDelete: Subscriber) = viewModelScope.launch {
        val rowId = subscribeRepository.delete(subscriberToUpdateOrDelete)
        if (rowId != -1) {
            booleanItemSelected = false
            statusMessage.value = "Deleted"
            inputName.value = ""
            inputEmail.value = ""
            saveOrUpdateButtonText.value = "Save"
            deleteOrClearAllButtonText.value = "Clear All"
        }
        statusMessage.value = "Error Occurred Deleting"
    }

    private fun clearAllSubscribers() = viewModelScope.launch {
        val rowId = subscribeRepository.deleteAll()
        if (rowId > -1) {
            booleanItemSelected = false

            statusMessage.value = "Delete All"
        } else {
            statusMessage.value = "Error Occurred Deleting All"
        }
    }

    fun initUpdate(subscriber: Subscriber) {
        booleanItemSelected = true
        saveOrUpdateButtonText.value = "Update"
        deleteOrClearAllButtonText.value = "Delete"
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        subscriberToUpdateOrDelete = subscriber
    }

    fun getListFromFlow() = liveData {
        getAll.collect {
            emit(it)
        }
    }

    //  OR
//    fun getListFromFlow(): LiveData<List<Subscriber>> {
//        return liveData {
//            getAll().collect {
//                emit(it)
//            }
//        }
}