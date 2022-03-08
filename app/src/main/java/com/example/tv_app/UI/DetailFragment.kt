package com.example.tv_app.UI

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.*
import com.example.tv_app.Presenters.DetailDescriptionPresenter
import com.example.tv_app.Presenters.PresenterWithoutDesc
import com.example.tv_app.R
import com.example.tv_app.dataclass.Movie
import com.example.tv_app.dataclass.WithoutDesc
/**
Created by Anuj Singh
 */
class DetailFragment : DetailsSupportFragment() {


    val movie: Movie = Movie(title = "Spider Man", desText =
            "When a new villain threatens New York City, Peter Parker and Spider-Man’s worlds collide." +
            " To save the city and those he loves, he must rise up and be greater.",
        R.drawable.onboard1
    )
    lateinit var myAdapter: ArrayObjectAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareBackground()
        initUI()
    }

    private fun prepareBackground() {
        val backgroundManager = BackgroundManager.getInstance(activity)
        backgroundManager.attach(requireActivity().window)
        backgroundManager.drawable = ContextCompat.getDrawable(requireContext(),
            R.drawable.spiderback
        )
    }

    private fun initUI() {

        val fullWidthDetailsOverviewRowPresenter = FullWidthDetailsOverviewRowPresenter(
            DetailDescriptionPresenter(),
            DetailsOverviewLogoPresenter()
        )

        val detailsOverviewRow = DetailsOverviewRow(movie)

        val sparseArrayObjectAdapter = SparseArrayObjectAdapter()
        sparseArrayObjectAdapter.set(0, Action(0, "action 1"))
        sparseArrayObjectAdapter.set(1, Action(1, "action 2"))

        detailsOverviewRow.actionsAdapter = sparseArrayObjectAdapter
        detailsOverviewRow.imageDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.spiderprofile)

        fullWidthDetailsOverviewRowPresenter.setOnActionClickedListener {
            val intent = Intent(requireContext(), PlayerActivity::class.java)
            startActivity(intent)
        }



        val headerItem2 = HeaderItem("Images")
        val adapter2 = ArrayObjectAdapter(PresenterWithoutDesc())
        adapter2.add(WithoutDesc(imageUrl = R.drawable.spiderprofile))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.spider1))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.spider2))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.spiderback))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.spider1))

        val listRow = ListRow(headerItem2,adapter2)




        val classPresenterSelector = ClassPresenterSelector()
        classPresenterSelector.addClassPresenter(DetailsOverviewRow::class.java,fullWidthDetailsOverviewRowPresenter)

        classPresenterSelector.addClassPresenter(ListRow::class.java,ListRowPresenter())

        myAdapter = ArrayObjectAdapter(classPresenterSelector)
        myAdapter.add(detailsOverviewRow)
        myAdapter.add(listRow)

        adapter = myAdapter

    }
}