package com.harr1424.listmaker

import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val welcomeShown = prefs.getBoolean("PREV_SHOWN", false)
        if (!welcomeShown) {
            val title = "Welcome!"
            val instructions = "Add a new item by clicking the add button at the bottom of the " +
                    "screen.\n\nRename or delete an item by long-clicking it."
            AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(instructions)
                .setPositiveButton(
                    "Got It"
                ) { dialog, _ -> dialog.dismiss() }
                .show()
            val editor: Editor = prefs.edit()
            editor.putBoolean("PREV_SHOWN", true)
            editor.apply()
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}