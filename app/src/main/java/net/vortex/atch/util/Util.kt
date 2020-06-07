package net.vortex.atch.util

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')
}

fun getTimeStamp(): String {
    return System.currentTimeMillis().toString()
}

fun hashGenerator(api_priv_key: String, api_pub_key: String, time_stamp: String = getTimeStamp()): Pair<String, String> {
    val input = time_stamp + api_priv_key + api_pub_key
    Log.e("TIMESTAMP", time_stamp)
    Log.e("HASHCODE", input.md5())
    return Pair(time_stamp, input.md5())
}