/*
 * Copyright (c) 2017 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.galacticon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.* // this library removes findViewById()
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), ImageRequester.ImageRequesterResponse {

  private var photosList: ArrayList<Photo> = ArrayList()
  private lateinit var imageRequester: ImageRequester
  private lateinit var linearLayoutManager: LinearLayoutManager
  private lateinit var recyclerAdapter: RecyclerAdapter

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    linearLayoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = linearLayoutManager // we can access recyclerView directly because
                                                    // of kotlinx.android.synthetic library
    recyclerAdapter = RecyclerAdapter(photosList)
    recyclerView.adapter = recyclerAdapter
    imageRequester = ImageRequester(this)
  }

  override fun onStart() {
    super.onStart()
    if(photosList.size == 0) {
      requestPhoto()
    }
  }

  private fun requestPhoto() {
    try {
      imageRequester.getPhoto()
    } catch (e: IOException) {
      e.printStackTrace()
    }

  }

  override fun receivedNewPhoto(newPhoto: Photo) {
    runOnUiThread {
      photosList.add(newPhoto)
      recyclerAdapter.notifyItemInserted(photosList.size - 1)
    }
  }
}
