package ch.ost.rj.mge.mgecrownbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.ost.rj.mge.mgecrownbar.api.Event

class EventsAdapter(
    private var events: List<Event>
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>()
{

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.events_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.itemView.apply {
            holder.itemView.findViewById<TextView>(R.id.event_item_date).text = events[position].eventName
            holder.itemView.findViewById<TextView>(R.id.event_item_title).text = events[position].eventDate
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }


}