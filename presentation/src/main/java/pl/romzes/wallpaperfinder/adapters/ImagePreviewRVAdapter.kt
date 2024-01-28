package pl.romzes.wallpaperfinder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.databinding.ImagePreviewLayoutBinding
import pl.romzes.wallpaperfinder.model.ImagePreview


class ImagePreviewRVAdapter : RecyclerView.Adapter<ImagePreviewRVAdapter.ImagePreviewViewHolder>() {

    lateinit var imagePrewList : List<ImagePreview>

    //that class contain reference to the objects - item, that wold be drown in RV
    //item in our case would be ->
    class ImagePreviewViewHolder(item : View) : RecyclerView.ViewHolder(item){
        //we use binding - object with reference to layout components
        // we can use  val textView = item.findViewById<TextView>(R.id.image_description_id) instead
        //but bindingView is more usefully
        val binding  = ImagePreviewLayoutBinding.bind(item)

        //we would bind our item for display on RV with data from model
        fun bind(imagePreview: ImagePreview){
            //insert image by id //todo use glide here
            binding.imagePreviewId.setImageResource(imagePreview.imageId)
            binding.imageDescriptionId.text = imagePreview.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePreviewViewHolder {
        //inflate our layout of one item (put it into memory), and send it  to holder, where it filled with data
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_preview_layout, parent, false)
        return ImagePreviewViewHolder(view)
    }

    override fun getItemCount(): Int {
       return imagePrewList.size
    }

    override fun onBindViewHolder(holder: ImagePreviewViewHolder, position: Int) {
       //start after holder ("place" for item in RV was created, and we have a position of it "place"
        //send data into item to fill ot with internal binding function
        holder.bind(imagePrewList[position])

    }

    fun setImagePreviewIntoRecyclerView(list : List<ImagePreview>){
        this.imagePrewList = list
        notifyDataSetChanged() //re-display RV elements after it was changed
    }


}