package com.example.aniga.room

import androidx.room.TypeConverter
import com.example.aniga.data.model.animeData.*
import com.google.gson.Gson

class AnimeDataTypeConvertor {

    @TypeConverter
    fun animeListToString(animeList: AnimeList): String =Gson().toJson(animeList)

    @TypeConverter
    fun stringToAnimeList(string: String): AnimeList =Gson().fromJson(string,AnimeList::class.java)

    @TypeConverter
    fun attributeToString(attributes: Attributes): String =Gson().toJson(attributes)

    @TypeConverter
    fun stringToAttributes(string: String): Attributes =Gson().fromJson(string,Attributes::class.java)

    @TypeConverter
    fun castingsToString(castings: Castings): String =Gson().toJson(castings)

    @TypeConverter
    fun stringToCasting(string: String): Castings =Gson().fromJson(string,Castings::class.java)

    @TypeConverter
    fun charactersToString(characters: Characters): String =Gson().toJson(characters)

    @TypeConverter
    fun stringToCharacters(string: String): Characters =Gson().fromJson(string,Characters::class.java)

    @TypeConverter
    fun dataToString(data: Data):String=Gson().toJson(data)

    @TypeConverter
    fun stringToData(string: String): Data =Gson().fromJson(string,Data::class.java)

    @TypeConverter
    fun genresToString(genres: Genres)=Gson().toJson(genres)

    @TypeConverter
    fun stringToGenres(string: String)=Gson().fromJson(string,Genres::class.java)

    @TypeConverter
    fun linksToString(links: Links)=Gson().toJson(links)

    @TypeConverter
    fun stringToLinks(string: String)=Gson().fromJson(string,Links::class.java)

    @TypeConverter
    fun linksXToString(linksX: LinksX)=Gson().toJson(linksX)

    @TypeConverter
    fun stringToLinksX(string: String)=Gson().fromJson(string,LinksX::class.java)

    @TypeConverter
    fun linksXXXXToString(linksXXXX: LinksXXXX): String =Gson().toJson(linksXXXX)

    @TypeConverter
    fun stringToLinksXXXX(string: String): LinksXXXX =Gson().fromJson(string,LinksXXXX::class.java)

    @TypeConverter
    fun linksXXXXXXToString(linksXXXXXX: LinksXXXXXX): String =Gson().toJson(linksXXXXXX)

    @TypeConverter
    fun stringToLinksXXXXXX(string: String): LinksXXXXXX =Gson().fromJson(string,LinksXXXXXX::class.java)

    @TypeConverter
    fun linksXXXXXXXXToString(linksXXXXXXXX: LinksXXXXXXXX): String =Gson().toJson(linksXXXXXXXX)

    @TypeConverter
    fun stringToLinksXXXXXXXX(string: String): LinksXXXXXXXX =Gson().fromJson(string,LinksXXXXXXXX::class.java)

    @TypeConverter
    fun posterImageToString(posterImage: PosterImage): String =Gson().toJson(posterImage)

    @TypeConverter
    fun stringToPosterImage(string: String): PosterImage =Gson().fromJson(string,PosterImage::class.java)

    @TypeConverter
    fun relationshipsToString(relationships: Relationships): String =Gson().toJson(relationships)

    @TypeConverter
    fun stringToRelationship(string: String)=Gson().fromJson(string,Relationships::class.java)

    @TypeConverter
    fun titlesToString(titles: Titles)=Gson().toJson(titles)

    @TypeConverter
    fun stringToTitles(string: String)=Gson().fromJson(string,Titles::class.java)


}