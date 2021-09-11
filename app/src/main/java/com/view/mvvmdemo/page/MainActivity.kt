package com.view.mvvmdemo.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.view.mvvmdemo.R
import com.view.mvvmdemo.bean.PostsVS
import com.view.mvvmdemo.core.utils.LoadingIndicatorDialog
import com.view.mvvmdemo.viewmodel.PostsViewModel
import com.view.mvvmdemo.viewmodel.VersionModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import android.preference.PreferenceManager

import android.content.SharedPreferences
import com.view.mvvmdemo.DemoApplication
import com.view.mvvmdemo.utils.EncryptUtil
import com.view.mvvmdemo.utils.SharedPreference
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private val mVersionModel: VersionModel by viewModel()
    private val mPostsViewModel: PostsViewModel by viewModel()
    private lateinit var dialog: LoadingIndicatorDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()
        observeAdduserViewModel()
        dialog = LoadingIndicatorDialog(this)
        dialog?.setCancelable(false)
        mVersionModel.getVersion()

        txtmessage.setOnClickListener {
            Log.e("hemly", "click")
            val names = randomString(10)
            val _time: String = getTime()
            val txt: String = EncryptUtil.txtau(_time, getIdentity(), getIdentity())
            val txt2: String = EncryptUtil.encryptAndBase64Encode2(txt)
            val jsonObject = JSONObject()
            jsonObject.put("Token", txt2)
            jsonObject.put("Avatar", "https://123.com")
            jsonObject.put("Email", names + "@gmail.com")
            jsonObject.put("Name", names)
            jsonObject.put("Time", _time)
            jsonObject.put("Uid", getIdentity())
            Log.e("hemly", "jsonObject= " + jsonObject)
            // Convert JSONObject to String
            val jsonObjectString = jsonObject.toString()
            val requestBody =
                jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
            mPostsViewModel.addUser(requestBody)
        }
    }

    private fun observeAdduserViewModel() {
        mPostsViewModel.viewState.observe(this, {
            when (it) {
                is PostsVS.addUser -> {
                    Log.e("hemly", "adduser= " + it.adduser.data.token)
                }
                is PostsVS.ShowLoader -> {
                    if (it.showLoader) {
                        dialog?.show()
                    } else {
                        dialog?.cancel()
                    }
                }
                is PostsVS.Error -> {
                    dialog?.cancel()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun observeViewModel() {
        mVersionModel.viewState.observe(this, {
            when (it) {
                is PostsVS.Version -> {
                    Log.e("hemly", "key= " + it.version.key)
                    Log.e("hemly", "token= " + it.version.token)
                    Log.e("hemly", "env= " + it.version.env)
                    DemoApplication.setappToken(it.version.token)
                    var versionkey: String = it.version.key
                    val sharedPreference: SharedPreference = SharedPreference(this)
                    sharedPreference?.save("key", versionkey)
                    sharedPreference?.save("enc", it.version.token)
                    sharedPreference?.save("env", it.version.env)
                    txtmessage.text = it.version.token
                }
                is PostsVS.ShowLoader -> {
                    if (it.showLoader) {
                        dialog?.show()
                    } else {
                        dialog?.cancel()
                    }
                }
                is PostsVS.Error -> {
                    dialog?.cancel()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun getTime(): String {
        val dff = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dff.setTimeZone(TimeZone.getTimeZone("UTC"))
        val _time: String = dff.format(Date())
        return _time
    }

    fun getIdentity(): String? {
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        var identity = preference.getString("identity", null)
        if (identity == null) {
            identity = UUID.randomUUID().toString()
            preference.edit().putString("identity", identity)
        }
        return identity
    }

    fun randomString(len: Int): String? {
        val str = "0123456789abcdefghijklmnopqrstuvwxyz"
        val sb = StringBuffer()
        for (i in 0 until len) {
            val idx = (Math.random() * str.length).toInt()
            sb.append(str[idx])
        }
        return sb.toString()
    }
}