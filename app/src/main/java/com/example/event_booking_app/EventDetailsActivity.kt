package com.example.event_booking_app

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso

class EventDetailsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    /**
     * Called when the activity is starting. Sets up the UI and populates it with event details.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        val clickedEvent = intent.getParcelableExtra("event", Event::class.java)
        //val clickedEvent = extras. //check me

        if (clickedEvent != null) {
            populateUI(clickedEvent)
            setupButtonClickListener()
        } else {
            // Handle the case when the clickedEvent is null or invalid
        }
    }
    /**
     * Populates the UI with the details of the given event.
     *
     * @param event The event to display the details of.
     */
    private fun populateUI(event: Event){
        val detailedDescription = event.detailedDescription
        val image = event.image
        val date = event.date
        val name = event.name
        val price = event.eventPrice

        //replace with one generic "set"
        setDetailedDescription(detailedDescription)
        setName(name)
        setDate(date)
        setPrice(price.toDouble())
        loadImage(image)

        //setListeners

    }

    private fun setDetailedDescription(description: String) {
        val textViewDetailedDescription = findViewById<TextView>(R.id.textViewDetailedDescription)
        textViewDetailedDescription.text = description
    }

    private fun setName(name: String) {
        val textViewName = findViewById<TextView>(R.id.textViewName)
        textViewName.text = name
    }

    private fun setDate(date: String) {
        val textViewDate = findViewById<TextView>(R.id.textViewDate)
        textViewDate.text = date
    }

    private fun setPrice(price: Double) {
        val textViewPrice = findViewById<TextView>(R.id.textViewPrice)
        textViewPrice.text = price.toString()
    }

    private fun loadImage(imageUrl: String) {
        val imageViewEvent = findViewById<ImageView>(R.id.imageViewEvent)
        Picasso.get().load(imageUrl).into(imageViewEvent)
    }

    private fun setupButtonClickListener() {
        //TODO
        val buttonBookTicket = findViewById<Button>(R.id.buttonBookTicket)
        buttonBookTicket.setOnClickListener {

            showToast("Ticket booked successfully!")
        }
    }

    private fun showToast(message: String) {
        //TODO
        Toast.makeText(this, "Purchase complete!", Toast.LENGTH_LONG).show()
    }
}
