package pl.romzes.domain.model

import com.google.gson.annotations.SerializedName

data class ImageUrlList(
   val raw: String,
   val full: String,
   val regular: String,
   val thumb: String,
   @SerializedName("small_s3")
   val smalls3: String
)
