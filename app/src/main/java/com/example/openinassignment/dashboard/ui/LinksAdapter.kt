package com.example.openinassignment.dashboard.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.openinassignment.R
import com.example.openinassignment.core.utils.loadImageUrl
import com.example.openinassignment.dashboard.domain.model.LinkModel
import com.example.openinassignment.databinding.ItemLinkBinding

class LinksAdapter: ListAdapter<LinkModel, LinksAdapter.LinksViewHolder>(
    object: DiffUtil.ItemCallback<LinkModel>() {
        override fun areContentsTheSame(oldItem: LinkModel, newItem: LinkModel): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        override fun areItemsTheSame(oldItem: LinkModel, newItem: LinkModel): Boolean {
            return oldItem == newItem
        }
    }
) {

    inner class LinksViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksViewHolder {
        return LinksViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_link, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LinksViewHolder, position: Int) {
        val data = getItem(position)
        (ItemLinkBinding.bind(holder.itemView)).apply {
            appImage.loadImageUrl(data.appImage)
            linkTitle.text = data.linkTitle
            linksClick.text = data.totalClicks.toString()
            createdAt.text = data.createdAt
            webLink.text = data.webLink
        }
    }

}