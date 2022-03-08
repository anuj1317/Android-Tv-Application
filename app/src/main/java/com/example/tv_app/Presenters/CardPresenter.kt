package com.example.tv_app.Presenters

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.example.tv_app.dataclass.Movie

/**
Created by Anuj Singh
 */

class CardPresenter : Presenter() {

    var mContext:Context? = null
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        mContext = parent?.context
        val imageCardView = ImageCardView(mContext)
        imageCardView.cardType = ImageCardView.CARD_TYPE_INFO_UNDER
        imageCardView.isFocusable = true
        imageCardView.isFocusableInTouchMode = true

        return ViewHolder(imageCardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val movie = item as Movie
        ( viewHolder?.view as ImageCardView).titleText = movie.title
        ( viewHolder?.view as ImageCardView).contentText = movie.desText
        ( viewHolder?.view as ImageCardView).mainImage = ContextCompat.getDrawable(mContext!!,movie.imageUrl)
        ( viewHolder?.view as ImageCardView).setMainImageDimensions(300,200)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}