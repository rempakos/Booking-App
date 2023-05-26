package com.example.event_booking_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
//import android.widget.SearchView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DividerItemDecoration

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerViewAPI {

    private var eventList: MutableList<Event> = mutableListOf()         // The list of events displayed in the RecyclerView

    /**
     * Called when the activity is starting. Sets up the UI and initializes the event list from the API.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eventsAPI = RetrofitHelper.getInstance().create(EventsAPI::class.java)        // Create an instance of the EventsAPI using Retrofit

        // Launching a new coroutine and making an API CALL
        GlobalScope.launch {
            val response = eventsAPI.getEvents()
            if (response.isSuccessful) {
                val events = response.body()
                if (events != null) {
                    eventList.addAll(events)
                    // Call a method to update the RecyclerView adapter with the eventList
                    val searchView = findViewById<SearchView>(R.id.searchView)//check me and change me please!
                    searchView.clearFocus()
                    //catch query after 'x' characters
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String): Boolean {
                            updateRecyclerViewAdapter(query)
                            return false
                        }

                        override fun onQueryTextChange(newText: String): Boolean {
                            updateRecyclerViewAdapter(newText)
                            return true
                        }
                    })
                    //updateRecyclerViewAdapter()
                }
            } else {
                // Handle the API call failure
                //add a view that indicates that there are no events
                Log.e("API Error", response.message())
            }
        }
    }

    /**
     * Updates the RecyclerView adapter based on the provided query.
     *
     * @param query The search query or criteria used to filter the data displayed in the RecyclerView.
     */
    override fun updateRecyclerViewAdapter(query: String) {
        runOnUiThread {
            val filteredList = if (query.isEmpty()) {
                mutableListOf<Event>() // Empty list
            } else {
                //eventList.toMutableList()

                eventList.filter { event ->
                    event.name.contains(query, ignoreCase = true)
                    event.location.contains(query, ignoreCase = true)

                }.toMutableList()
            }
            val recyclerViewEvents = findViewById<RecyclerView>(R.id.recyclerViewEvents)
            recyclerViewEvents.layoutManager = LinearLayoutManager(this)
            recyclerViewEvents.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

            val adapter = EventRecyclerViewAdapter(filteredList, this)
            recyclerViewEvents.adapter = adapter
        }
    }

    /**
     * Handles the click event on a specific row within the RecyclerView.
     *
     * @param position The position of the clicked row in the RecyclerView.
     */
    override fun onItemClick(position: Int) {

        val clickedEvent = eventList.getOrNull(position)
        clickedEvent?.let {
            //Toast.makeText(this, "position"+position, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, EventDetailsActivity::class.java)
            //intent.putExtra("event_id", clickedEvent.id)
            intent.putExtra("event", it)
            startActivity(intent)//fragment-navgraphs(MUST)
        }
    }


}

/*
*     fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
* */