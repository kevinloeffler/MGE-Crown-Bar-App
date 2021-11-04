package ch.ost.rj.mge.mgecrownbar

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import kotlin.properties.Delegates

class CocktailActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    // UI bindings
    private var cocktailFavButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail)

        cocktailFavButton = findViewById(R.id.cocktail_fav_button)
        cocktailFavButton?.setOnClickListener{ favouriteCocktail(true) }
    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = this.getSharedPreferences("ch.ost.rj.mge.mgecrownbar", Context.MODE_PRIVATE)
        favouriteCocktail(false)
    }

    private fun favouriteCocktail(clicked: Boolean) {
        var newState = sharedPreferences.getBoolean("cocktail_fav_state", false)

        if (clicked) {
            newState = !newState
            writeToLocalStorage(newState)
            renderToast(newState)
        }

        renderFavourite(newState)
    }

    private fun renderFavourite(newState: Boolean) {
        if (newState) {
            cocktailFavButton?.setImageResource(R.drawable.heart_base)
        } else {
            cocktailFavButton?.setImageResource(R.drawable.heart_selected)
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
            .putBoolean("cocktail_fav_state", newState)
            .apply()
    }

}
