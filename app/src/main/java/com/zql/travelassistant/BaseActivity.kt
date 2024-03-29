package com.zql.travelassistant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.zql.travelassistant.util.LocalManageUtils
import dev.shreyaspatil.MaterialDialog.AbstractDialog
import dev.shreyaspatil.MaterialDialog.MaterialDialog
import io.multimoon.colorful.CAppCompatActivity

abstract class BaseActivity(isShowTitle:Boolean?): CAppCompatActivity() {

    val TAG = BaseActivity::class.java.name

    var isShowTitle = false

    val mContext = this

    var mDialog:AbstractDialog? = null

    // Let the child class to implement this method
    abstract fun init()


    fun showLoading() {
        mDialog = MaterialDialog.Builder(this).setAnimation(R.raw.lottie_loading_animation).setMessage(resources.getString(R.string.text_loading)).build()
        mDialog?.show()
    }

    fun closeLoading(){
        mDialog!!.dismiss()
    }

    init {
        if (isShowTitle != null) {
            this.isShowTitle = isShowTitle
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            init()
        } catch (e:java.lang.Exception){
            e.printStackTrace()
            Log.e(TAG, "RUN TIME ERROR")
        }

        // Set back button
        if(isShowTitle){
            supportActionBar?.setDisplayHomeAsUpEnabled(isShowTitle)
        }


    }

    /**
     * Click the back button will end this Activity
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocalManageUtils.setLocale(newBase!!))
    }
}


abstract class BaseActivityWithTitle : BaseActivity(true)

abstract class BaseActivityWithNoTitle : BaseActivity(false)