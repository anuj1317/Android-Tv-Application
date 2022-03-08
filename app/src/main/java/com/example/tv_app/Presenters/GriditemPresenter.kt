package com.example.tv_app.Presenters

import android.R
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.Presenter


/**
Created by Anuj Singh
 */

class GriditemPresenter : Presenter() {


    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = TextView(parent?.context)
        view.layoutParams = ViewGroup.LayoutParams(300, 200)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.setBackgroundColor(parent?.context?.getResources()!!.getColor(R.color.holo_purple))
        view.setTextColor(Color.WHITE)
        view.gravity = Gravity.CENTER

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {

        (viewHolder?.view as TextView).text = item as String
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}