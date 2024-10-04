package com.example.myapplication.model

data class VerifyRequest (
    val code : String,
    val userId : String
)