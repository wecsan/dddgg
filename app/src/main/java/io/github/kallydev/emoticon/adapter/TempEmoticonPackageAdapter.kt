/*
 * An open source emoticon application
 * Copyright (C) 2019 KallyDev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.kallydev.emoticon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.kallydev.emoticon.R
import io.github.kallydev.emoticon.bean.EmoticonPackageBean
import kotlinx.android.synthetic.main.item_emoticon_package.view.*

class TempEmoticonPackageAdapter(private val context: Context) :
    RecyclerView.Adapter<TempEmoticonPackageAdapter.EmoticonViewHolder>() {

    private val emoticonPackageBeanArrayList = ArrayList<EmoticonPackageBean>()
    private var onItemClickListener: OnItemClickListener? = null

    private val colors = arrayOf(
        R.color.colorRed200, R.color.colorPink200, R.color.colorPurple200,
        R.color.colorDeepPurple200, R.color.colorIndigo200, R.color.colorBlue200,
        R.color.colorLightBlue200, R.color.colorCyan200, R.color.colorTeal200,
        R.color.colorGreen200, R.color.colorLightGreen200, R.color.colorLime200,
        R.color.colorYellow200, R.color.colorAmber200, R.color.colorOrange200,
        R.color.colorDeepOrange200, R.color.colorBrown200
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmoticonViewHolder {
        return EmoticonViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_emoticon_package,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return emoticonPackageBeanArrayList.size
    }

    override fun onBindViewHolder(holder: EmoticonViewHolder, position: Int) {
        Glide.with(context)
            .load(context.resources.getDrawable(R.drawable.ic_launcher_background))
            .into(holder.itemView.item_emoticon_package_appCompatImageView)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClickListener(it, position)
        }
        holder.itemView.setOnLongClickListener {
            onItemClickListener?.onItemLongClickListener(it, position)
            true
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(itemView: View, position: Int)
        fun onItemLongClickListener(itemView: View, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun loadEmoticonPackage(emoticonPackageBeanArray: Array<EmoticonPackageBean>, isNetwork: Boolean) {
        if (!isNetwork) {
            emoticonPackageBeanArrayList.clear()
        }
        emoticonPackageBeanArrayList.addAll(emoticonPackageBeanArray)
        notifyDataSetChanged()
    }

    inner class EmoticonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
