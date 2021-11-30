package net.utils

import android.text.TextUtils
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import net.entity.DataEntity

/**
 * Copyright (C) 2021,2021/11/30, a Tencent company. All rights reserved.
 *
 * User : v_xhangxie
 *
 * Desc :
 */
class DataHandleUtils {
    companion object {
        private var i: DataHandleUtils? = null
            get() {
                field ?: run {
                    field = DataHandleUtils()
                }
                return field
            }

        @Synchronized
        fun get(): DataHandleUtils {
            return i!!
        }
    }

    fun strToObject(str: String): ArrayList<DataEntity> {
        val result = ArrayList<DataEntity>()
        val map: Map<String, DataEntity> =
            JSON.parseObject(str, object : TypeReference<Map<String, DataEntity>>() {})
        val m: Set<Map.Entry<String, DataEntity>> = map.entries
        val it: Iterator<Map.Entry<String, DataEntity>> = m.iterator()
        while (it.hasNext()) {
            val en: Map.Entry<String, DataEntity> = it.next()
            val entity: DataEntity = en.value
            entity.key = en.key
            if (TextUtils.isEmpty(entity.panoid)) {
                continue
            } else {
                if (entity.panoid == "LiAWseC5n46JieDt9Dkevw") {
                    continue
                }
            }
            if (entity.fife == null) {
                continue
            }
            if (entity.fife) {
                entity.imageUrl =
                    "https://lh4.googleusercontent.com/" + entity.panoid + "/w400-h300-fo90-ya0-pi0/"
                continue
            } else {
                entity.imageUrl =
                    "https://geo0.ggpht.com/cbk?output=thumbnail&thumb=2&panoid=" + entity.panoid
            }
            result.add(entity)
        }
        return result
    }

    fun strToObject2(str: String, big: DataEntity): ArrayList<DataEntity> {
        val result = ArrayList<DataEntity>()
        val map: Map<String, DataEntity> =
            JSON.parseObject(str, object : TypeReference<Map<String, DataEntity>>() {})
        val m: Set<Map.Entry<String, DataEntity>> = map.entries
        val it: Iterator<Map.Entry<String, DataEntity>> = m.iterator()
        while (it.hasNext()) {
            val en: Map.Entry<String, DataEntity> = it.next()
            val entity: DataEntity = en.value
            entity.pannoId = entity.panoid
            if (big.fife) {
                entity.imageUrl = "https://lh4.googleusercontent.com/" + entity.pannoId + "/w400-h300-fo90-ya0-pi0/"
            } else {
                entity.imageUrl =
                    "https://geo0.ggpht.com/cbk?output=thumbnail&thumb=2&panoid=" + entity.panoid
            }
            result.add(entity)
        }
        return result
    }
}