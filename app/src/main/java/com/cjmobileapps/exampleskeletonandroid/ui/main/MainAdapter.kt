package com.cjmobileapps.exampleskeletonandroid.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cjmobileapps.exampleskeletonandroid.R
import com.cjmobileapps.exampleskeletonandroid.data.model.Post
import com.cjmobileapps.exampleskeletonandroid.databinding.ItemPostBinding
import com.squareup.picasso.Picasso

class MainAdapter(
    var posts: List<Post> = emptyList(),
    val onPostClicked: (post: Post) -> Unit = {}
) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainHolder {
        val itemBinding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    inner class MainHolder(private val itemBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(post: Post) {
            itemBinding.postItemHeadline.text = post.headline

            Picasso.get()
                .load(post.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(itemBinding.postItemImage)

            itemBinding.root.setOnClickListener {
                onPostClicked.invoke(post)
            }
        }
    }
}
