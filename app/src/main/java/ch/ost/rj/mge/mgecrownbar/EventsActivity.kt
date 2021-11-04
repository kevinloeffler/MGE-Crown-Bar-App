package ch.ost.rj.mge.mgecrownbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.ost.rj.mge.mgecrownbar.api.Event


class EventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        val eventsList: List<Event>? = intent.extras?.get("events") as ArrayList<Event>?

        if (eventsList != null) {
            val adapter = EventsAdapter(eventsList)
            val recyclerView: RecyclerView = findViewById(R.id.events_recycler_view)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
}
