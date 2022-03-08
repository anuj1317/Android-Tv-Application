package com.example.tv_app.Presenters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowHeaderPresenter
import com.example.tv_app.R
import com.example.tv_app.dataclass.WithoutDesc

/**
Created by Anuj Singh
 */

class RectanglePresenter: Presenter() {

    var mContext: Context?=null
    override fun onCreateViewHolder(parent: ViewGroup?): Presenter.ViewHolder {
        mContext=parent?.context
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.rect_layout,parent,false)
        return RectViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder?, item: Any?) {
        val carddesign = item as WithoutDesc
        (viewHolder as RectanglePresenter.RectViewHolder).rectImage?.setImageDrawable(
            ContextCompat.getDrawable(
                mContext!!,
                carddesign.imageUrl
            ))

    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder?) {
        TODO("Not yet implemented")
    }
    inner class RectViewHolder(view: View?) : RowHeaderPresenter.ViewHolder(view) {
        val rectImage = view?.findViewById<ImageView>(R.id.rec_img)

    }

}