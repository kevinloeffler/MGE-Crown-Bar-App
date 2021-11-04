package ch.ost.rj.mge.mgecrownbar

import ch.ost.rj.mge.mgecrownbar.api.EventsJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {
    @GET("/events")
    fun getEvents(): Call<EventsJson>
}