
package com.example.tv_app.Presenters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.*
import com.example.tv_app.dataclass.CircleAvatar
import com.example.tv_app.R
/**
Created by Anuj Singh
 */
 class CircularCardPresenter: RowPresenter() {
    var mContext: Context? = null


    inner class HeaderViewHolder(view: View) : ViewHolder(view) {
        var image = view.findViewById<ImageView>(R.id.img)
    }

    override fun createRowViewHolder(parent: ViewGroup?): ViewHolder {
        mContext = parent?.context
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.card_layout, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindRowViewHolder(vh: ViewHolder?, item: Any?) {
        val cardDesign = item as CircleAvatar

        (vh as HeaderViewHolder).image.setImageDrawable(ContextCompat.getDrawable(mContext!!,cardDesign.imageUrl))
    }

}






