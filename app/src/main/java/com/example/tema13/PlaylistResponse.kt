package com.example.tema13
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PlaylistResponse(
    val data: List<Playlist>,
    val pages: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Playlist.CREATOR)!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(data)
        parcel.writeInt(pages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaylistResponse> {
        override fun createFromParcel(parcel: Parcel): PlaylistResponse {
            return PlaylistResponse(parcel)
        }

        override fun newArray(size: Int): Array<PlaylistResponse?> {
            return arrayOfNulls(size)
        }
    }

    data class Playlist(
        @SerializedName("dummy_image_url")
        val imageUrl: String,
        val id: Int,
        @SerializedName("playlistTitulo")
        val playlistTitulo: String,
        @SerializedName("number_of_followers")
        val numberOfFollowers: Int,
        val songs: List<Song>
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readInt(),
            parcel.createTypedArrayList(Song.CREATOR)!!
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(imageUrl)
            parcel.writeInt(id)
            parcel.writeString(playlistTitulo)
            parcel.writeInt(numberOfFollowers)
            parcel.writeTypedList(songs)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Playlist> {
            override fun createFromParcel(parcel: Parcel): Playlist {
                return Playlist(parcel)
            }

            override fun newArray(size: Int): Array<Playlist?> {
                return arrayOfNulls(size)
            }
        }

        data class Song(
            val artist: String,
            val name: String,
            val url: String,
            var favorito: Boolean
        ) : Parcelable {
            constructor(parcel: Parcel) : this(
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readByte() != 0.toByte()
            )

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(artist)
                parcel.writeString(name)
                parcel.writeString(url)
                parcel.writeByte(if (favorito) 1 else 0)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<Song> {
                override fun createFromParcel(parcel: Parcel): Song {
                    return Song(parcel)
                }

                override fun newArray(size: Int): Array<Song?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}
