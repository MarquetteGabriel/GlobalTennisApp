/*
 *
 * @brief Copyright (c) 2024 Gabriel Marquette
 *
 * Copyright (c) 2024 Gabriel Marquette. All rights reserved.
 *
 */

package fr.gmarquette.globaltennisapp.model.tournament

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URL

class BitmapUrl {

    private val url = "https://www.atptour.com"
    suspend fun getBitmapFromUrl(overviewUrl: String, context: Context): Bitmap
    {
        val urlOverview = url + overviewUrl
        val html = URL(urlOverview).readText()
        val doc: Document = Jsoup.parse(html)

        val imgElement = doc.select(".media_container img").first()
        val imageUrl = url + imgElement?.attr("src")

        return getBitmap(imageUrl, context)
    }


    private suspend fun getBitmap(imageUrl: String, context: Context): Bitmap {
        val loading = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .build()
        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }
}