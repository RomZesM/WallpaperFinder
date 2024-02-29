package pl.romzes.wallpaperfinder.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.databinding.ImagePreviewLayoutBinding
import pl.romzes.domain.model.ImagePreview
import pl.romzes.wallpaperfinder.utils.MyRecyclerViewOnClickListener


//todo make separate class?
class ImagePreviewRVAdapter(private val fragment: Fragment) : RecyclerView.Adapter<ImagePreviewRVAdapter.ImagePreviewViewHolder>() {

    val TAG = "rmz"
    lateinit var imagePrewList : List<ImagePreview>
    //onclick listener, set through preview Fragment
    private var myRecyclerViewOnClickListener : MyRecyclerViewOnClickListener? = null


    //that class contain reference to the objects - item, that wold be drown in RV
    //item in our case would be ->
    class ImagePreviewViewHolder(item : View, fragment: Fragment) : RecyclerView.ViewHolder(item){
        //we use binding - object with reference to layout components
        // we can use  val textView = item.findViewById<TextView>(R.id.image_description_id) instead
        //but bindingView is more usefully
        val binding  = ImagePreviewLayoutBinding.bind(item)

        //we would bind our item for display on RV with data from model
        fun bind(imagePreview: ImagePreview){
            //insert image by id //todo use glide here
            //binding.imagePreviewId.setImageResource(imagePreview.imageId)
            binding.imageDescriptionId.text = imagePreview.description

            //check if this image is in fav
            if(imagePreview.isFav){
                binding.favouriteIconId.setImageResource(R.drawable.icon_heart_black)
                //use tag to understand what item is it (we can use image id instead tag
                binding.favouriteIconId.tag = "black"
            }
            else  if(!imagePreview.isFav){
               binding.favouriteIconId.setImageResource(R.drawable.icon_heart_empty)
               binding.favouriteIconId.tag = "empty"

            }

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

        //place onclick from myInterface
        holder.binding.imagePreviewId.setOnClickListener {
            if (myRecyclerViewOnClickListener != null) {
                myRecyclerViewOnClickListener!!.onClick(position)
            }
        }

        holder.binding.favouriteIconId.setOnClickListener{
            if (myRecyclerViewOnClickListener != null) {
                myRecyclerViewOnClickListener!!.favOnClick(imagePrewList[position], position)
            }
        }

        Glide.with(fragment)
            .load(imagePrewList[position].imageUrl)
            .override(dpToInt(400, fragment), dpToInt(200, fragment))
            .into(holder.binding.imagePreviewId)

    }

    fun setImagePreviewIntoRecyclerView(list : List<ImagePreview>){
        this.imagePrewList = list
        notifyDataSetChanged() //re-display RV elements after it was changed
    }

    //add listener from outside as dependency
    fun setMyOnclickListener(listener : MyRecyclerViewOnClickListener){
        this.myRecyclerViewOnClickListener = listener;
    }

    private fun dpToInt(dp : Int, fragment : Fragment) : Int{
        val scale: Float = fragment.resources.getDisplayMetrics().density
        val pixels = (dp * scale + 0.5f).toInt()
        return pixels
    }

}