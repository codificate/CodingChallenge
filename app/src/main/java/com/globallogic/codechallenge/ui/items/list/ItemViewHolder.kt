package com.globallogic.codechallenge.ui.items.list

import com.globallogic.codechallenge.R
import com.globallogic.codechallenge.data.db.entity.ItemEntry
import com.globallogic.codechallenge.internal.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_detail.*

class ItemViewHolder(
    val itemEntry: ItemEntry
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            setTitle()
            setDescription()
            setImage()
        }
    }

    override fun getLayout() = R.layout.item_detail

    private fun ViewHolder.setTitle(){
        textView_title.text = itemEntry.title
    }

    private fun ViewHolder.setDescription(){
        textView_description.text = itemEntry.description
    }

    private fun ViewHolder.setImage() {
        GlideApp
            .with(this.containerView)
            .load(itemEntry.image)
            .placeholder(R.drawable.ic_computer_black_24dp)
            .into(imageView_item)
    }
}