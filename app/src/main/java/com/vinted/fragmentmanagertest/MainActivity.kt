package com.vinted.fragmentmanagertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FirstFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()

        next_button.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TWO)
            if (fragment == null) {
                Toast.makeText(this, "Fragment not null", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, TwoFragment(), FRAGMENT_TWO)
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            } else {
                Toast.makeText(this, "Fragment two exists", Toast.LENGTH_SHORT).show()
            }
        }

        back_to_first_button.setOnClickListener {
            val popped = supportFragmentManager.popBackStackImmediate(0,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            Toast.makeText(this, "Fragment popped : $popped", Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, FirstFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
        back_to_first_button_with_null.setOnClickListener {
            val popped = supportFragmentManager.popBackStackImmediate(null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            Toast.makeText(this, "Fragment popped : $popped", Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, FirstFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }

    companion object {
        private const val FRAGMENT_TWO = "two"
    }
}
