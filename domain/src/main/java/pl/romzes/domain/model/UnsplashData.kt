package pl.romzes.domain.model

data class UnsplashData(
    val total: Int,
    val total_pages: Int,
    val results: List<UnsplashImageResult>
)
