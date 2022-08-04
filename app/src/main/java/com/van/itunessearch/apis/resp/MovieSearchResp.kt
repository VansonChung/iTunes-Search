package com.van.itunessearch.apis.resp

import com.google.gson.annotations.SerializedName

data class MovieSearchResp(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<MovieInfo>
)

data class MovieInfo(
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
    @SerializedName("collectionArtistViewUrl")
    val collectionArtistViewUrl: String,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String,
    @SerializedName("collectionHdPrice")
    val collectionHdPrice: Double,
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
    @SerializedName("hasITunesExtras")
    val hasITunesExtras: Boolean,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("longDescription")
    val longDescription: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String,
    @SerializedName("trackCount")
    val trackCount: Int,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String,
    @SerializedName("trackHdPrice")
    val trackHdPrice: Double,
    @SerializedName("trackHdRentalPrice")
    val trackHdRentalPrice: Double,
    @SerializedName("trackId")
    val trackId: Int,
    @SerializedName("trackName")
    val trackName: String,
    @SerializedName("trackNumber")
    val trackNumber: Int,
    @SerializedName("trackPrice")
    val trackPrice: Double,
    @SerializedName("trackRentalPrice")
    val trackRentalPrice: Double,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String,
    @SerializedName("wrapperType")
    val wrapperType: String
)
/*
{
  "resultCount": 39,
  "results": [
    {
      "wrapperType": "track",
      "kind": "feature-movie",
      "trackId": 345696894,
      "artistName": "Nora Ephron",
      "trackName": "Michael (1996)",
      "trackCensoredName": "Michael (1996)",
      "trackViewUrl": "https://itunes.apple.com/us/movie/michael-1996/id345696894?uo=4",
      "previewUrl": "https://video-ssl.itunes.apple.com/itunes-assets/Video128/v4/4d/2b/b4/4d2bb473-e063-69c5-c1bb-6415c131b5f4/mzvf_8250535074002304982.640x476.h264lc.U.p.m4v",
      "artworkUrl30": "https://is4-ssl.mzstatic.com/image/thumb/Video/bb/59/4a/mzi.yabsockp.jpg/30x30bb.jpg",
      "artworkUrl60": "https://is4-ssl.mzstatic.com/image/thumb/Video/bb/59/4a/mzi.yabsockp.jpg/60x60bb.jpg",
      "artworkUrl100": "https://is4-ssl.mzstatic.com/image/thumb/Video/bb/59/4a/mzi.yabsockp.jpg/100x100bb.jpg",
      "collectionPrice": 12.99,
      "trackPrice": 12.99,
      "trackRentalPrice": 3.99,
      "collectionHdPrice": 12.99,
      "trackHdPrice": 12.99,
      "trackHdRentalPrice": 3.99,
      "releaseDate": "1996-12-25T08:00:00Z",
      "collectionExplicitness": "notExplicit",
      "trackExplicitness": "notExplicit",
      "trackTimeMillis": 6304800,
      "country": "USA",
      "currency": "USD",
      "primaryGenreName": "Comedy",
      "contentAdvisoryRating": "PG",
      "longDescription": "Academy Award-nominee and Golden Globe-winner John Travolta (\"Be Cool,\" \"Ladder 49\"), a hard-living, promiscuous angel living on Earth who drinks, smokes and smells like cookies, is investigated by two tabloid journalists who eventually learn from him the true meaning of love. From Academy Award and Golden Globe-nominee Nora Ephron (\"Bewitched,\" \"Sleepless in Seattle\"), with an all-star supporting cast, including Golden Globe-nominee Andie MacDowell (\"Beauty Shop,\" \"Four Weddings and a Funeral\"), Academy Award-winner and Golden Globe-nominee William Hurt (\"Syriana,\" \"The Village\"\"), Emmy and Golden Globe-winner Jean Stapleton (\"You've Got Mail,\" TV's \"All in the Family\"), Academy Award nominee-Teri Garr (\"Dumb & Dumber,\" \"Mr. Mom\"), Emmy-nominee Robert Pastorelli (\"Be Cool,\" \"Bait\"), and Academy Award-nominee and Golden Globe-winner Bob Hoskins (\"Unleashed,\" \"Maid in Manhattan\")."
    },{...}
  ]
}
 */