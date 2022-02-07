package com.allometry.allometryreader.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allometry.allometryreader.model.MUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {

    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit)= viewModelScope.launch{

        try {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val displayName = task.result.user?.email?.split("@")?.get(0)
                createUser(displayName)
                home.invoke()
            }else {
                Log.d("TAG", "signinWithEmailAndPassword: ${task.result.toString()}")
            }
            _loading.value = false

        }
        }catch (ex: Exception){
            Log.e("FB Error ", "createUserWithEmailAndPassword: ${ex.message}")
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(userId = userId.toString(), displayName = displayName.toString(), avatarUrl = "", quote = "life is great", profession = "Farmer", id = null)
        FirebaseFirestore.getInstance().collection("users").add(user.toMap())
    }

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit )  = viewModelScope.launch{

        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d("Successful login", "signinWithEmailAndPassword: ${task.result.toString()}")
                    home.invoke()

                }else {
                    Log.d("TAG", "signinWithEmailAndPassword: ${task.result.toString()}")
                }
                _loading.value = false

            }
        }catch (ex: Exception){
            Log.e("FB Error ", "createUserWithEmailAndPassword: ${ex.message}")
        }
    }



}