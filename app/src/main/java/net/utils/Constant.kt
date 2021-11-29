package net.utils

import android.Manifest

/**
 * Copyright (C) 2021,2021/9/29, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
object Constant {
    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val title = arrayOf("map", "nearby", "streetView", "interActive")
    const val BASE_URL:String = "https://www.google.com/"
    const val URI_BIG:String="streetview/feed/gallery/data.json"
    const val airport = "airport"
    const val atm = "atm"
    const val bakery = "bakery"
    const val bank = "bank"
    const val bus = "bus"
    const val cafe = "cafe"
    const val church = "church"
    const val cloth = "cloth"
    const val dentist = "dentist"
    const val doctor = "doctor"
    const val down = "down"
    const val fire_station = "fire_station"
    const val gas_station = "gas_station"
    const val hospital = "hospital"
    const val hotel = "hotel"
    const val interactive = "interactive"
    const val jewelry = "jewelry"
    const val mall = "mall"
    const val mosque = "mosque"
    const val park = "park"
    const val pet = "pet"
    const val pharmacy = "pharmacy"
    const val police = "police"
    const val post_office = "post_office"
    const val salon = "salon"
    const val school = "school"
    const val shoe = "shoe"
    const val stadium = "stadium"
    const val university = "university"
    const val zoo = "zoo"
}