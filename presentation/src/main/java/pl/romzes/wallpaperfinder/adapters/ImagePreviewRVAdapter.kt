package pl.romzes.wallpaperfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.databinding.ImagePreviewLayoutBinding
import pl.romzes.data.models.ImagePreview
import pl.romzes.wallpaperfinder.fragments.resultFragment.ResultFragment


class ImagePreviewRVAdapter(private val fragment: Fragment) : RecyclerView.Adapter<ImagePreviewRVAdapter.ImagePreviewViewHolder>() {

    lateinit var imagePrewList : List<ImagePreview>

    //that class contain reference to the objects - item, that wold be drown in RV
    //item in our case would be ->
    class ImagePreviewViewHolder(item : View, fragment: Fragment) : RecyclerView.ViewHolder(item){
        //we use binding - object with reference to layout components
        // we can use  val textView = item.findViewById<TextView>(R.id.image_description_id) instead
        //but bindingView is more usefully
        val binding  = ImagePreviewLayoutBinding.bind(item)
       //place for image for glide
        val imagePlace = binding.imagePreviewId

        //we would bind our item for display on RV with data from model
        fun bind(imagePreview: ImagePreview){
            //insert image by id //todo use glide here
            //binding.imagePreviewId.setImageResource(imagePreview.imageId)
            binding.imageDescriptionId.text = imagePreview.description
//
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePreviewViewHolder {
        //inflate our layout of one item (put it into memory), and send it  to holder, where it filled with data
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_preview_layout, parent, false)
        return ImagePreviewViewHolder(view, fragment)
    }

    override fun getItemCount(): Int {
       return imagePrewList.size
    }

    override fun onBindViewHolder(holder: ImagePreviewViewHolder, position: Int) {
       //start after holder ("place" for item in RV was created, and we have a position of it "place"
        //send data into item to fill ot with internal binding function
        holder.bind(imagePrewList[position])

        //Glidetesting todo - use real url for images
        Glide.with(fragment)
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Flag_of_Austria.svg/2560px-Flag_of_Austria.svg.png")
            .into(holder.imagePlace)

    }

    fun setImagePreviewIntoRecyclerView(list : List<ImagePreview>){
        this.imagePrewList = list
        notifyDataSetChanged() //re-display RV elements after it was changed
    }


}