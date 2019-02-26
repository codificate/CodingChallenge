package com.globallogic.codechallenge.ui.items.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.globallogic.codechallenge.R
import com.globallogic.codechallenge.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.items_list_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ItemListFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private lateinit var viewModel: ItemListViewModel
    private val viewModelFactory: ItemListViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.items_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ItemListViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {

        (activity as AppCompatActivity)?.supportActionBar?.title = (activity as AppCompatActivity).getString(R.string.tool_bar_main_title)

        val itemsEntries = viewModel.fetchListItems.await()//viewModel.itemList.await()

        itemsEntries.observe( this@ItemListFragment, Observer {
            if (it == null) return@Observer

            group_loading.visibility = View.GONE
            initRecyclerView(it.map { entry -> ItemViewHolder(entry) })
        } )
    }

    private fun initRecyclerView(items: List<ItemViewHolder>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply  {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ItemListFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            Toast.makeText(activity, (item as ItemViewHolder)?.itemEntry.description , Toast.LENGTH_LONG).show()
        }
    }

}