package kz.alfaneos.multimoduleproject.notification

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

open class ITarget(val width: Int, val height: Int) : Target<Bitmap> {

    private var loadRequest: Request? = null

    override fun getSize(cb: SizeReadyCallback) {
        cb.onSizeReady(width, height)
    }

    override fun setRequest(request: Request?) {
       this.loadRequest = request
    }

    override fun getRequest(): Request? {
        return this.loadRequest
    }

    override fun onLoadStarted(placeholder: Drawable?)= Unit

    override fun onLoadFailed(errorDrawable: Drawable?) = Unit


    override fun onStop() = Unit
    override fun removeCallback(cb: SizeReadyCallback) = Unit

    override fun onLoadCleared(placeholder: Drawable?) = Unit

    override fun onStart()= Unit

    override fun onDestroy() = Unit

    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) = Unit
}