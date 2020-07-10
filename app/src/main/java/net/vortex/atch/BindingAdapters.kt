package net.vortex.atch

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import net.vortex.atch.data.Result
import net.vortex.atch.database.characters.Character
import net.vortex.atch.ui.characters.ApiStatus
import net.vortex.atch.ui.characters.PhotoGridAdapter
import net.vortex.atch.util.EspressoIdlingResource

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?){
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        EspressoIdlingResource.countingIdlingResource.increment()

        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)

        EspressoIdlingResource.countingIdlingResource.decrement()

    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Result>?) {
    EspressoIdlingResource.countingIdlingResource.increment()
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
    EspressoIdlingResource.countingIdlingResource.decrement()
}

@BindingAdapter("imageUrl")
fun bindFavoritesImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        EspressoIdlingResource.countingIdlingResource.increment()
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
        EspressoIdlingResource.countingIdlingResource.decrement()
    }
}

@BindingAdapter("listData")
fun bindFavoritesRecyclerView(recyclerView: RecyclerView,
                     data: List<Character>?) {
    EspressoIdlingResource.countingIdlingResource.increment()
    val adapter = recyclerView.adapter as PhotoGridAdapter
//    adapter.submitList(data)
    EspressoIdlingResource.countingIdlingResource.decrement()
}