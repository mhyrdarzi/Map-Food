package com.example.mhyrfoodapp.utils

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.mhyrfoodapp.R

//Extension function for spinner setting up
fun Spinner.setupListWithAdapter(list: MutableList<out Any>, callback: (String) -> Unit) {
    val adapter = ArrayAdapter(context, R.layout.spinner_item, list)
    adapter.setDropDownViewResource(R.layout.spinner_item_list)
    this.adapter = adapter
    this.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            callback(list[position].toString())
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}

    }
}

//Extension function for recyclerView setting up
fun RecyclerView.setupRecyclerView(
    layoutManager: RecyclerView.LayoutManager,
    adapter: RecyclerView.Adapter<*>
) {
    this.layoutManager = layoutManager
    this.isNestedScrollingEnabled = true
    this.setHasFixedSize(false)
    this.adapter = adapter
}

//Extensions function for visible/inVisible view
fun View.isVisible(isShown: Boolean, hideOrShowView: View) {
    if (isShown) {
        hideOrShowView.visibility = View.GONE
        this.visibility = View.VISIBLE
    } else {
        hideOrShowView.visibility = View.VISIBLE
        this.visibility = View.GONE
    }
}
