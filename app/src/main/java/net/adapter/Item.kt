package net.adapter

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.pacific.adapter.AdapterViewHolder
import com.pacific.adapter.SimpleRecyclerItem
import net.basicmodel.R
import net.entity.DataEntity
import net.utils.ScreenUtils

/**
 * Copyright (C) 2021,2021/11/30, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class Item(val activity: Activity,val context: Context,val item:DataEntity): SimpleRecyclerItem() {
    override fun bind(holder: AdapterViewHolder) {

        val root = holder.itemView.findViewById<CardView>(R.id.root)
        root.let {
            it.layoutParams =it.layoutParams.apply {
                height = ScreenUtils.getScreenSize(activity)[0] / 3
            }
        }
        holder.attachOnClickListener(R.id.root)
        val img = holder.itemView.findViewById<ImageView>(R.id.img)
        Glide.with(context).load(item.imageUrl).into(img)
        val name = holder.itemView.findViewById<TextView>(R.id.img_name)
        name.text = item.title
    }

    override fun getLayout(): Int {
       return R.layout.layout_item
    }
}