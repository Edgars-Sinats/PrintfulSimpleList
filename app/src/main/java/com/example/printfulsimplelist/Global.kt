package com.example.printfulsimplelist


/**TODO
 * 1. Get rid of Global scope! //Why? When started running in child, won`t stop if parent stop. Task will run independent.
 * 2. Build Retrofit instance for each APIRequest.

 * Replace JSON with Moshi
 * Check network connectivity.
 *
 */
//LV news =>    /v2/top-headlines?country=lv&apiKey=04a42ee08c094f9580d3cb6e0bd5a43c

const val LOG_TAG = "newsLog"

const val GLOBAL_BASE_URL = "https://newsapi.org"
const val WEB_SERVICE_URL = "https://newsapi.org/v2/top-headlines?country=lv&apiKey=04a42ee08c094f9580d3cb6e0bd5a43c"

