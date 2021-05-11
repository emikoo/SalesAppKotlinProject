package com.example.salesappkotlinproject.helper

object Constants {

    // Endpoints
    const val BASE_URL = "http://logbook-env.eba-xv3wp97k.us-west-2.elasticbeanstalk.com/"
    const val REGISTER_URL = "auth/users/" //POST
    const val CREATE_TOKEN_URL = "auth/jwt/create/" //POST
    const val REFRESH_TOKEN_URL = "auth/jwt/refresh/" //POST
    const val VERIFY_TOKEN_URL = "auth/jwt/verify/" //POST
    const val GET_PRODUCTS_LIST_URL = "api/products/" //GET
    const val PRODUCT_URL = "api/products/{id}" //GET, POST, PUT, PATCH, DELETE
    const val FEEDBACK_URL = "api/feedback/" //POST
}