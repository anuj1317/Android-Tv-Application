package com.example.tv_app.UI

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.example.tv_app.Presenters.*
import com.example.tv_app.R
import com.example.tv_app.dataclass.CircleAvatar
import com.example.tv_app.dataclass.Movie
import com.example.tv_app.dataclass.WithoutDesc
/**
Created by Anuj Singh
 */
class CatalogFragment : BrowseSupportFragment(),OnItemViewSelectedListener,OnItemViewClickedListener {

    lateinit var myAdapter:ArrayObjectAdapter
    lateinit var backgroundManager: BackgroundManager
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onItemViewSelectedListener = this
        onItemViewClickedListener = this
        prepareBackground()
        initUI()
        loadRows()
    }

    private fun prepareBackground(){
        backgroundManager = BackgroundManager.getInstance(activity)
        backgroundManager.attach(requireActivity().window)
    }


    private fun initUI() {
        title = getString(R.string.app_name)

//        badgeDrawable = ContextCompat.getDrawable(requireContext(), R.mipmap.ic_launcher)

        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        setHeaderPresenterSelector(object : PresenterSelector() {
            override fun getPresenter(item: Any?): Presenter {
                return HeaderPresenter()
            }

        })

    }

    private fun loadRows(){

        val customListRowPresenter = object : ListRowPresenter() {
            override fun isUsingDefaultListSelectEffect() = false
        }.apply {
            shadowEnabled = false
        }

//        myAdapter = ArrayObjectAdapter(ListRowPresenter())
        myAdapter = ArrayObjectAdapter(customListRowPresenter)

        val headerItem = HeaderItem("Genres")
        val adapter0 = ArrayObjectAdapter(GriditemPresenter())
        adapter0.add("ACTION")
        adapter0.add("SPORTS")
        adapter0.add("PUZZLES")
        adapter0.add("HORROR")

        val listRow0 = ListRow(headerItem,adapter0)
        myAdapter.add(listRow0)


        val headerItem2 = HeaderItem("Characters")
        val adapter2 = ArrayObjectAdapter(RectanglePresenter())
        adapter2.add(WithoutDesc(imageUrl = R.drawable.char1))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.char2))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.char3))
        adapter2.add(WithoutDesc(imageUrl = R.drawable.char1))

        val listRow2 = ListRow(headerItem2,adapter2)
        myAdapter.add(listRow2)


        val headerItem3 = HeaderItem("Games")
        val adapter3 = ArrayObjectAdapter(CircularCardPresenter())
        adapter3.add(CircleAvatar(imageUrl = R.drawable.char4))
        adapter3.add(CircleAvatar(imageUrl = R.drawable.char1))
        adapter3.add(CircleAvatar(imageUrl = R.drawable.char2))
        adapter3.add(CircleAvatar(imageUrl = R.drawable.char3))

        val listRow3 = ListRow(headerItem3,adapter3)
        myAdapter.add(listRow3)


        val headerItem4 = HeaderItem("Favourites")
        val adapter4 = ArrayObjectAdapter(CardPresenter())
        adapter4.add(Movie(title = "title 1",desText = "des 1", imageUrl = R.drawable.onboard1))
        adapter4.add(Movie(title = "title 2",desText = "des 2", imageUrl = R.drawable.onboard2))
        adapter4.add(Movie(title = "title 3",desText = "des 3", imageUrl = R.drawable.onboard3))
        adapter4.add(Movie(title = "title 4",desText = "des 4", imageUrl = R.drawable.onboard1))

        val listRow4 = ListRow(headerItem4,adapter4)
        myAdapter.add(listRow4)


        val headerItem5 = HeaderItem("Top Rated")
        val adapter5 = ArrayObjectAdapter(PresenterWithoutDesc())
        adapter5.add(WithoutDesc(imageUrl = R.drawable.char1))
        adapter5.add(WithoutDesc(imageUrl = R.drawable.char2))
        adapter5.add(WithoutDesc(imageUrl = R.drawable.char3))
        adapter5.add(WithoutDesc(imageUrl = R.drawable.char4))

        val listRow5 = ListRow(headerItem5,adapter5)
        myAdapter.add(listRow5)




        adapter = myAdapter

    }

    override fun onItemSelected(
        itemViewHolder: Presenter.ViewHolder?,
        item: Any?,
        rowViewHolder: RowPresenter.ViewHolder?,
        row: Row?
    ) {

        if(item is Movie){
            backgroundManager.drawable = ContextCompat.getDrawable(requireContext(),
                R.drawable.onboard3
            )
        }
        else if(item is WithoutDesc){
            backgroundManager.drawable = ContextCompat.getDrawable(requireContext(),
                R.drawable.r2
            )
        }
        else if(item is CircleAvatar){
            backgroundManager.drawable = ContextCompat.getDrawable(requireContext(),
                R.drawable.onboard1
            )
        }
        else
        {   backgroundManager.drawable = ContextCompat.getDrawable(requireContext(),
            R.drawable.spider1
        )
        }

    }

    override fun onItemClicked(
        itemViewHolder: Presenter.ViewHolder?,
        item: Any?,
        rowViewHolder: RowPresenter.ViewHolder?,
        row: Row?
    )
    {

        if(item is WithoutDesc)
        {
        val intent = Intent(requireActivity(), DetailActivity2::class.java)
      startActivity(intent)
         }
        else
        {
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            startActivity(intent)

        }
    }

//    override fun onItemClicked(
//        itemViewHolder: Presenter.ViewHolder?,
//        item: Any?,
//        rowViewHolder: RowPresenter.ViewHolder?,
//        row: Row?
//    ) {
//
//        val intent = Intent(requireActivity(),DetailActivity::class.java)
//        startActivity(intent)
//    }


}