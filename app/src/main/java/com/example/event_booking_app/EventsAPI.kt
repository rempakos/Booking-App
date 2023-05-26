package com.example.event_booking_app

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface EventsAPI {
    // https://healthy-slippers-clam.cyclic.app/events
    @GET("/events/")
    suspend fun getEvents(): Response<List<Event>>

    /*
    TODO
    @GET("/events/{id}")
    suspend fun getEventById(@Path("id") eventId: String): Response<Event>


    @GET("/events?location")
    open fun getEventsByLocation(
        @Query("location") location: String?
    ): Observable<Event?>?
    */

    // http://localhost:3000/events/:id
    @FormUrlEncoded
    @PUT("/events/{id}")
    suspend fun bookEvent(@Path("id") eventId: String, @Field("totalSoldTickets") totalSoldTickets: Int): Response<Event>
}