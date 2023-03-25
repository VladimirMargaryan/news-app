package com.mobiledev.news_app.service.enums

enum class Category(val queryValue: String, val displayName: String) {

    GENERAL("general", "General"),
    TECHNOLOGY("technology", "Technology"),
    BUSINESS("business", "Business"),
    SCIENCE("science", "Science"),
    SPORTS("sports", "Sports"),
    ENTERTAINMENT("entertainment", "Entertainment"),
    HEALTH("health", "Health");
}