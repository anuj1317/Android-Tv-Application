package com.example.tv_app.Presenters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowHeaderPresenter
import com.example.tv_app.R
/**
Created by Anuj Singh
 */
class HeaderPresenter : RowHeaderPresenter() {

    override fun onCreateViewHolder(parent: ViewGroup?): Presenter.ViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.layout_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder?, item: Any?) {
        val headerItem = (item as ListRow).headerItem
        (viewHolder as HeaderViewHolder).headerText.text = headerItem.name

    }

    inner class HeaderViewHolder(view: View) : ViewHolder(view) {
        val headerText = view.findViewById<TextView>(R.id.header_text_view)
    }
}