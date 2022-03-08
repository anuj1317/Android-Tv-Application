package com.example.tv_app.Presenters

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.example.tv_app.dataclass.WithoutDesc
/**
Created by Anuj Singh
 */
class PresenterWithoutDesc : Presenter() {

    var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        mContext = parent?.context
        val imageCardView = ImageCardView(mContext)
        imageCardView.cardType = ImageCardView.CARD_TYPE_FLAG_IMAGE_ONLY
        imageCardView.isFocusable = true
        imageCardView.isFocusableInTouchMode = true


        return ViewHolder(imageCardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val cardDesign = item as WithoutDesc

        ( viewHolder?.view as ImageCardView).mainImage = ContextCompat.getDrawable(mContext!!,cardDesign.imageUrl)
        ( viewHolder?.view as ImageCardView).setMainImageDimensions(300,200)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }




}