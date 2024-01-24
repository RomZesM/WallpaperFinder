**Wallpaper Finder**

A mobile application that allows you to search for images on Unsplash.com . Save your favourite images for future use as wallpaper.

---

**Features**:

- Finds and downloads images from Unsplash.com according to the user's search query
- Save favourite image in local db for future using

---
**Additional libraries:**

- Kotlin coroutines
- Glide
- Retrofit
- ROOM
---
**App development plan**:

1. We get JSON with image information based on a custom query. (`Retrofit`)
2. Fragment N1 displays previews of the found images with a brief description.  There is a button to save the image to "Favourites" near each preview. (`RecyclerView`, `ROOM`)
3. If you click on the image preview, fragment N2 displays a large image with more detailed information. The fragment has a button to save the selected image to favourites.(`Constrant Layout`, `ROOM`). The favourites are saved to the local database.
4. The AppBar has a button to call fragment N3, which displays the images saved earlier in the database.(`RecyclerView`, `ROOM`)