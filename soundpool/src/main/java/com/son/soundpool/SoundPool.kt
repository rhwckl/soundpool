package com.son.soundpool

import android.content.Context
import android.media.AudioAttributes

/**
 * Created by songc on 2017/02/21.
 * @param maxStreams 同時に再生できる効果音の数。デフォルトでは５つ。
 */
class SoundPool(maxStreams: Int = 5) {
    val audioAttributes = AudioAttributes
            .Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

    val soundPool = android.media.SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(maxStreams)
            .build()

    val aliases = mutableMapOf<String, Int>()

    fun load(context: Context, resourceId: Int, stringId: String) {
        aliases[stringId] = soundPool.load(context, resourceId, 1)
    }

    fun unload(stringId: String) {
        aliases[stringId]?.let { soundPool.unload(it) }
    }

    fun play(stringId: String) {
        aliases[stringId]?.let {
            soundPool.play(it, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }
}
