package com.raywenderlich.galacticon

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class RecyclerAdapter(private val photos: ArrayList<Photo>) : RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>() { // right-click on RecyclerAdapter and choose Implement Members

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.PhotoHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_item_row, false)
        return PhotoHolder(inflatedView)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: RecyclerAdapter.PhotoHolder, position: Int) {
        val itemPhoto = photos[position]
        holder.bindPhoto(itemPhoto)
    }

    class PhotoHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var photo: Photo? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = itemView.context

            // show new activity on the screen
            val showPhotoIntent = Intent(context, PhotoActivity::class.java)
            showPhotoIntent.putExtra(PHOTO_KEY, photo)
            context.startActivity(showPhotoIntent)
        }

        companion object {
            private val PHOTO_KEY = "PHOTO"
        }

        fun bindPhoto(photo: Photo) {
            this.photo = photo
            Picasso.with(view.context).load(photo.url).into(view.itemImage)
            view.itemDate.text = photo.humanDate
            view.itemDescription.text = photo.explanation
        }
    }
}