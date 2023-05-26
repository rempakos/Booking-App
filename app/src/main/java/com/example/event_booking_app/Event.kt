package com.example.event_booking_app

import java.io.Serializable

/**
 * The Event data class represents an event object with its properties.
 *
 * @param _id The unique identifier of the event.
 * @param name The name of the event.
 * @param date The date of the event.
 * @param description The description of the event.
 * @param image The URL of the event image.
 * @param eventPrice The price of the event.
 * @param eventCategory The category of the event.
 * @param totalTickets The total number of available tickets for the event.
 * @param totalSoldTickets The total number of tickets sold for the event.
 * @param detailedDescription The detailed description of the event.
 * @param location The location of the event.
 * @param createdAt The timestamp of when the event was created.
 * @param updatedAt The timestamp of when the event was last updated.
 * @param __v The version number of the event.
 */
data class Event(
    val _id: String,
    val name: String,
    val date: String,
    val description: String,
    val image: String,
    val eventPrice: Int,
    val eventCategory: String,
    val totalTickets: Int,
    val totalSoldTickets: Int,
    val detailedDescription: String,
    val location: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
): Serializable
//By implementing the Serializable interface, the Event class objects can be serialized and passed as extras through intents.
