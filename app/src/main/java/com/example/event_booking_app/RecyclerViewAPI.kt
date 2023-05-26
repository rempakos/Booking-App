package com.example.event_booking_app

/**
 * The RecyclerViewAPI interface defines functions for updating the RecyclerView adapter.
 */
interface RecyclerViewAPI {
    fun updateRecyclerViewAdapter(query: String)
    fun onItemClick(position: Int)
}