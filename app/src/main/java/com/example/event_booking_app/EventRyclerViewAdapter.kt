package com.example.event_booking_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class EventRecyclerViewAdapter(private val eventList: MutableList<Event>, private val recyclerViewAPI: RecyclerViewAPI) :
    RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>() {

    /**
     * The EventViewHolder class holds the view references for an event row in the RecyclerView.
     *
     * @param itemView The view for an event row.
     */
    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView = itemView.findViewById(R.id.event_name)
        val eventDate: TextView = itemView.findViewById(R.id.event_date)
        val eventDescription: TextView = itemView.findViewById(R.id.event_description)
        val eventImage: ImageView = itemView.findViewById(R.id.event_image)
    }

    /**
     * Called when the RecyclerView needs a new ViewHolder instance.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new EventViewHolder instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_view_event_row,
            parent,
            false
        )
        return EventViewHolder(itemView)
    }

    /**
     * Called by the RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder that should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentItem = eventList[position]

        holder.eventName.text = currentItem.name
        holder.eventDate.text = currentItem.date
        holder.eventDescription.text = currentItem.description
        Picasso.get().load(currentItem.image).into(holder.eventImage)

        holder.itemView.setOnClickListener {
            recyclerViewAPI.onItemClick(position)
        }
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in the adapter.
     */
    override fun getItemCount(): Int = eventList.size
}
