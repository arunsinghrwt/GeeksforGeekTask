package com.arun.geeksforgeektask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arun.geeksforgeektask.Adapter.NewsFeedAdapter
import com.arun.geeksforgeektask.Network.FeedViewModel
import com.arun.geeksforgeektask.Utility.GlideApp
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {


    val mainModel by viewModel<FeedViewModel>()
    private lateinit var newsFeedAdapter: NewsFeedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        observer()


    }
    private fun observer() {
     mainModel.mediatorList.observe(this,{
         swipe_refresh.isRefreshing = false
         if (!it.status.isNullOrEmpty()){
             val glide = GlideApp.with(this)
             container_View.apply {
                 newsFeedAdapter = NewsFeedAdapter(it.items, applicationContext,glide)
                 adapter = newsFeedAdapter
             }
         }
     }) }



    private fun initView() {
        mainModel.getFeedList()
        swipe_refresh.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        newsFeedAdapter.removeList()
        mainModel.getFeedList()
    }
}