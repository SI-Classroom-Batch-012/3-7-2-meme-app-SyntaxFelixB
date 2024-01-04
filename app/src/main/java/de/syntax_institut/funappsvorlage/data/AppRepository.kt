package de.syntax_institut.funappsvorlage.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntax_institut.funappsvorlage.data.model.Meme
import de.syntax_institut.funappsvorlage.data.remote.MemeApi
import java.lang.Exception

const val TAG = "AppRepositoryTAG"

/**
 * Diese Klasse holt die Informationen und stellt sie mithilfe von Live Data dem Rest
 * der App zur Verfügung
 */
class AppRepository(private val api: MemeApi) {

    private val _memes = MutableLiveData<List<Meme>>()
    val memes: LiveData<List<Meme>>
        get() = _memes

    private val titleMap = mutableMapOf<Int, String>()


    suspend fun getMemes() {
        try {
            val memes = api.retrofitService.getMemes().data.memes

            memes.forEach { meme ->
                titleMap[meme.id]?.let {
                    meme.savedTitle = it
                }
            }

            _memes.value = memes
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun saveTitle(id: Int, title: String) {
        titleMap[id] = title
    }
}
