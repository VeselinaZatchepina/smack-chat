package com.github.veselinazatchepina.coderswag.controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.veselinazatchepina.coderswag.R
import com.github.veselinazatchepina.coderswag.adapters.CategoryRecyclerAdapter
import com.github.veselinazatchepina.coderswag.services.DataService
import com.github.veselinazatchepina.coderswag.utils.EXTRA_CATEGORY
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter : CategoryRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CategoryRecyclerAdapter(this, DataService.categories) {
            val intent  = Intent(this, ProductsActivity::class.java)
            intent.putExtra(EXTRA_CATEGORY, it.title)
            startActivity(intent)
        }
        categoryListView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        categoryListView.layoutManager = layoutManager
        categoryListView.setHasFixedSize(true)
    }
}
