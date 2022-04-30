package com.example.aniga.ui.animeDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.aniga.R
import com.example.aniga.databinding.AnimeDetailFragmentBinding


class AnimeDetailFragment:Fragment(R.layout.anime_detail_fragment) {

    private val args by navArgs<AnimeDetailFragmentArgs>()

    private lateinit var binding: AnimeDetailFragmentBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding=AnimeDetailFragmentBinding.bind(view)
        val data=args.animeData
        val attributes=data!!.attributes!!
        binding.apply {
            tvTitle.text= attributes.titles?.en
            ivPosterImage.load(attributes.posterImage?.original)
            tvDescription.text=attributes.description
            tvPopularityRank.text= attributes.popularityRank.toString()

            val youtubeId=attributes.youtubeVideoId
            val youtubeVideo= "<html><body>Video From YouTube<br><iframe width=\"100%\" height=\"100%\" " +
                    "src=\"https://www.youtube.com/embed/" +
                    "$youtubeId\" frameborder=\"0\" allowfullscreen></iframe></body></html>"
            tvRating.text=attributes.averageRating
            wbYoutubeVideo.webViewClient = object : WebViewClient() {
                @Deprecated("Deprecated in Java", ReplaceWith("false"))
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    return false
                }
            }
            wbYoutubeVideo.settings.javaScriptEnabled = true
            wbYoutubeVideo.loadData(youtubeVideo,"text/html","utf-8")
        }

    }

}