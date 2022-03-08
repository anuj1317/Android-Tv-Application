package com.example.tv_app.Presenters

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.example.tv_app.dataclass.Movie
/**
Created by Anuj Singh
 */
class DetailDescriptionPresenter:AbstractDetailsDescriptionPresenter() {

    override fun onBindDescription(vh: ViewHolder?, item: Any?) {
       val movie = item as Movie
        vh?.title?.text = movie.title
//        vh?.subtitle?.text = movie.title
        vh?.body?.text = movie.desText
    }
}