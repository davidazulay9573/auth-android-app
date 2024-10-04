package com.example.myapplication.api

import com.example.myapplication.model.SignInRequest
import com.example.myapplication.model.SignInResponse
import com.example.myapplication.model.SignupResponse
import com.example.myapplication.model.SignupRequest
import com.example.myapplication.model.VerifyRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/signup")
    suspend fun signUp(@Body signupRequest: SignupRequest): SignupResponse
    @POST("auth/verify")
    suspend fun verify(@Body verifyRequest: VerifyRequest) : Any
    @POST("auth/signin")
    suspend fun signIn(@Body signInRequest: SignInRequest): SignInResponse
}
