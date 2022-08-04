package com.van.itunessearch.apis.resp

import com.google.gson.annotations.SerializedName

data class MusicSearchResp(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<MusicInfo>
)

data class MusicInfo(
    @SerializedName("artistId")
    val artistId: Int,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("artistViewUrl")
    val artistViewUrl: String,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String,
    @SerializedName("artworkUrl30")
    val artworkUrl30: String,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String,
    @SerializedName("collectionArtistId")
    val collectionArtistId: Int,
    @SerializedName("collectionArtistName")
    val collectionArtistName: String,
    @SerializedName("collectionArtistViewUrl")
    val collectionArtistViewUrl: String,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String,
    @SerializedName("collectionId")
    val collectionId: Int,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("collectionPrice")
    val collectionPrice: Double,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("discCount")
    val discCount: Int,
    @SerializedName("discNumber")
    val discNumber: Int,
    @SerializedName("isStreamable")
    val isStreamable: Boolean,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String,
    @SerializedName("trackCount")
    val trackCount: Int,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String,
    @SerializedName("trackId")
    val trackId: Int,
    @SerializedName("trackName")
    val trackName: String,
    @SerializedName("trackNumber")
    val trackNumber: Int,
    @SerializedName("trackPrice")
    val trackPrice: Double,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String,
    @SerializedName("wrapperType")
    val wrapperType: String
)
/*
{
  "resultCount": 50,
  "results": [
    {
      "wrapperType": "track",
      "kind": "song",
      "artistId": 6596751,
      "collectionId": 315843479,
      "trackId": 315844118,
      "artistName": "Franz Ferdinand",
      "collectionName": "Franz Ferdinand",
      "trackName": "Michael",
      "collectionCensoredName": "Franz Ferdinand",
      "trackCensoredName": "Michael",
      "artistViewUrl": "https://music.apple.com/us/artist/franz-ferdinand/6596751?uo=4",
      "collectionViewUrl": "https://music.apple.com/us/album/michael/315843479?i=315844118&uo=4",
      "trackViewUrl": "https://music.apple.com/us/album/michael/315843479?i=315844118&uo=4",
      "previewUrl": "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview125/v4/73/b1/5a/73b15aae-5d14-f7ca-58f6-3419dac0f84e/mzaf_4852161536966911429.plus.aac.p.m4a",
      "artworkUrl30": "https://is5-ssl.mzstatic.com/image/thumb/Music115/v4/19/32/bb/1932bba8-d7a2-33ef-9fe5-2ccf9d7aca77/mzi.pzhapxnn.jpg/30x30bb.jpg",
      "artworkUrl60": "https://is5-ssl.mzstatic.com/image/thumb/Music115/v4/19/32/bb/1932bba8-d7a2-33ef-9fe5-2ccf9d7aca77/mzi.pzhapxnn.jpg/60x60bb.jpg",
      "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music115/v4/19/32/bb/1932bba8-d7a2-33ef-9fe5-2ccf9d7aca77/mzi.pzhapxnn.jpg/100x100bb.jpg",
      "collectionPrice": 9.99,
      "trackPrice": 0.99,
      "releaseDate": "2004-02-09T08:00:00Z",
      "collectionExplicitness": "notExplicit",
      "trackExplicitness": "notExplicit",
      "discCount": 1,
      "discNumber": 1,
      "trackCount": 11,
      "trackNumber": 9,
      "trackTimeMillis": 203307,
      "country": "USA",
      "currency": "USD",
      "primaryGenreName": "Pop",
      "isStreamable": true
    },{...}
  ]
}
 */