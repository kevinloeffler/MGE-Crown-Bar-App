package ch.ost.rj.mge.mgecrownbar

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast

class MocktailActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    // UI bindings
    private var mocktailFavButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mocktail)

        mocktailFavButton = findViewById(R.id.mocktail_fav_button)
        mocktailFavButton?.setOnClickListener{ favouriteMocktail(true) }
    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = this.getSharedPreferences("ch.ost.rj.mge.mgecrownbar", Context.MODE_PRIVATE)
        favouriteMocktail(false)
    }

    private fun favouriteMocktail(clicked: Boolean) {
        var newState = sharedPreferences.getBoolean("mocktail_fav_state", false)

        if (clicked) {
            newState = !newState
            writeToLocalStorage(newState)
            renderToast(newState)
        }

        renderFavourite(newState)
    }

    private fun renderFavourite(newState: Boolean) {
        if (newState) {
            mocktailFavButton?.setImageResource(R.drawable.heart_base)
        } else {
            mocktailFavButton?.setImageResource(R.drawable.heart_selected)
        }
    }

    private fun renderToast(newState: Boolean) {
        val toast = if (newState) {
            Toast.makeText(applicationContext, "Removed from favourites", Toast.LENGTH_SHORT)
        } else {
            Toast.makeText(applicationContext, "Added to favourites", Toast.LENGTH_SHORT)
        }
        toast.show()
    }

    private fun writeToLocalStorage(newState: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean("mocktail_fav_state", newState)
            .apply()
    }


}
