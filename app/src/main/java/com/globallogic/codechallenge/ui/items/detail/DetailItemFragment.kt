package com.globallogic.codechallenge.ui.items.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.globallogic.codechallenge.R
import com.globallogic.codechallenge.data.db.entity.ItemEntry
import com.globallogic.codechallenge.internal.glide.GlideApp
import com.google.gson.Gson
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.item_detail.*

class DetailItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let { DetailItemFragmentArgs.fromBundle(it) }
        val item = Gson().fromJson<ItemEntry>(safeArgs?.itemValues, ItemEntry::class.java)
        bindUI(item)
    }

    private fun bindUI(item: ItemEntry) {
        (activity as AppCompatActivity)?.supportActionBar?.title = (activity as AppCompatActivity).getString(R.string.tool_bar_detail_title)
        textView_content_title.text = item.title
        textView_content_description.text = item.description
        GlideApp
            .with(this)
            .load(item.image)
            .error(R.drawable.ic_computer_black_24dp)
            .into(image_item_detail_collapsed)
    }
}