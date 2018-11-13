package com.trybnikova.github.moshi.di

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import org.koin.dsl.module.module
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.annotation.Nullable

val moshiModule = module {

    single {
        Moshi.Builder()
            .add(DefaultBooleanAdapter())
            .add(DefaultIntAdapter())
            .add(DefaultDoubleAdapter())
            .add(DefaultStringAdapter())
            .add(LocalDateAdapter())
            .build()
    }

}

/**
 * Null-safe (De)Serializer for an [Boolean]
 */
private class DefaultBooleanAdapter {

    /**
     * Deserialization
     */
    @FromJson
    fun fromJson(@Nullable jsonBoolean: Boolean?) = jsonBoolean ?: false

    /**
     * Serialization
     */
    @ToJson
    fun toJson(jsonBoolean: Boolean?) = jsonBoolean
}

/**
 * Null-safe (De)Serializer for an [Int]
 */
private class DefaultIntAdapter {

    /**
     * Deserialization
     */
    @FromJson
    fun fromJson(@Nullable jsonInt: Int?) = jsonInt ?: -1

    /**
     * Serialization
     */
    @ToJson
    fun toJson(jsonInt: Int?) = jsonInt
}

/**
 * Null-safe (De)Serializer for an [Double]
 */
private class DefaultDoubleAdapter {

    /**
     * Deserialization
     */
    @FromJson
    fun fromJson(@Nullable jsonDouble: Double?) = jsonDouble ?: -1.0

    /**
     * Serialization
     */
    @ToJson
    fun toJson(jsonDouble: Double?) = jsonDouble
}

/**
 * Null-safe (De)Serializer for an [String]
 */
private class DefaultStringAdapter {

    /**
     * Deserialization
     */
    @FromJson
    fun fromJson(@Nullable jsonString: String?) = jsonString ?: ""

    /**
     * Serialization
     */
    @ToJson
    fun toJson(jsonString: String?) = jsonString
}

/**
 * (De)Serializer for a [LocalDate]
 */
private class LocalDateAdapter {

    /**
     * Deserialization
     */
    @FromJson
    fun fromJson(date: String): LocalDate = LocalDate.parse(date,
        FORMATTER
    )

    /**
     * Serialization
     */
    @ToJson
    fun toJson(date: LocalDate): String = FORMATTER.format(date)

    companion object {
        private val FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE
    }
}
