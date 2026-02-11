package com.example.kmpdamoproject

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform