package ch.ost.rj.mge.mgecrownbar

import ch.ost.rj.mge.mgecrownbar.api.EventsJson
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import android.content.ActivityNotFoundException




const val EVENTS_BASE_URL = "https://mge-crown-app-server.herokuapp.com"

class MainActivity : AppCompatActivity() {

    private var eventsObject: EventsJson? = null

    private var eventTextView1: TextView? = null
    private var eventTextView2: TextView? = null
    private var eventTextView3: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchEvents() // call to Events API

        // Set touch targets
        val eventsContainer: View = findViewById(R.id.events_container)
        eventsContainer.setOnClickListener{ startActivityEvents() }

        val featuredCocktail: View = findViewById(R.id.featured_cocktail_container)
        featuredCocktail.setOnClickListener{ startActivityCocktail() }

        val featuredMocktail: View = findViewById(R.id.featured_mocktail_container)
        featuredMocktail.setOnClickListener{ startActivityMocktail() }

        val mapView: ImageView = findViewById(R.id.map_view)
        mapView.setOnClickListener{ startActivityGoogleMaps() }

        val instaView: View = findViewById(R.id.insta_view)
        instaView.setOnClickListener{ startActivityInsta() }

        val websiteView: View = findViewById(R.id.website_container)
        websiteView.setOnClickListener{ startActivityWebsite() }

        eventTextView1 = findViewById(R.id.events_event_1)
        eventTextView2 = findViewById(R.id.events_event_2)
        eventTextView3 = findViewById(R.id.events_event_3)
    }

    private fun fetchEvents() {
        val api = Retrofit.Builder()
            .baseUrl(EVENTS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getEvents().awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    eventsObject = data
                    withContext(Dispatchers.Main) {
                            eventTextView1?.text = getString(R.string.event_text_1, data[0].eventDate, data[0].eventName)
                            eventTextView2?.text = getString(R.string.event_text_2, data[1].eventDate, data[1].eventName)
                            eventTextView3?.text = getString(R.string.event_text_3, data[2].eventDate, data[2].eventName)
                    }
                }
            }
        }
    }

    private fun startActivityEvents() {
        val eventsActivity = Intent(this, EventsActivity::class.java)
        Log.d("log", "Events Object before sending: $eventsObject")
        eventsActivity.putExtra("events", eventsObject)
        startActivity(eventsActivity)
    }

    private fun startActivityCocktail() {
        val cocktailActivity = Intent(this, CocktailActivity::class.java)
        startActivity(cocktailActivity)
    }

    private fun startActivityMocktail() {
        val mocktailActivity = Intent(this, MocktailActivity::class.java)
        startActivity(mocktailActivity)
    }

    private fun startActivityGoogleMaps() {
        val gmmIntentUri = Uri.parse("geo:0,0?q=The+Crown+Bar,+Rapperswil+Jona")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun startActivityInsta() {
        val uri = Uri.parse("https://instagram.com/thecrownbarrappi")
        val instaIntent = Intent(Intent.ACTION_VIEW, uri)
        instaIntent.setPackage("com.instagram.android")

        try {
            startActivity(instaIntent)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://instagram.com/thecrownbarrappi")
                )
            )
        }
    }

    private fun startActivityWebsite() {
        val uri = Uri.parse("https://www.thecrownbar.ch")
        val websiteIntent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(websiteIntent)
    }
}
