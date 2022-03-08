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
class DetailFragment2 : DetailsSupportFragment() {


    val movie: Movie = Movie(title = "Ratchet & Clank", desText =
    "Ratchet & Clank blasts onto PlayStation 4 for the first time, with a new game based on elements from the original Ratchet & Clank(PS2). " +
            "Ratchet & Clank (PS4) re-imagines the characters’ origin stories and modernizes the original" +
            " gameplay. Featuring all-new boss fights, several new planets, new flight sequences, and" +
            " much more – with completely new visuals constructed to take advantage of the power of the PS4.",
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
        backgroundManager.drawable = ContextCompat.getDrawable(requireContext(), R.drawable.rback)
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
            ContextCompat.getDrawable(requireContext(), R.drawable.rprofile)

        fullWidthDetailsOverviewRowPresenter.setOnActionClickedListener {
            val intent = Intent(requireContext(), PlayerActivity::class.java)
            startActivity(intent)
        }

        val headerItem2 = HeaderItem("Images")
        val adapter2 = ArrayObjectAdapter(PresenterWithoutDesc())
        adapter2.add(WithoutDesc(imageUrl = R.drawable.rprofile))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.r1))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.r2))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.rback))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.r1))

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