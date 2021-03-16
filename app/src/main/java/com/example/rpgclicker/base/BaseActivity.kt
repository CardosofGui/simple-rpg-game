package com.example.rpgclicker.base

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.rpgclicker.R

open class BaseActivity () : AppCompatActivity(){

    fun setupToolbar(toolbar: Toolbar, title : String, onClickBackCheck : Boolean = false){

        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(onClickBackCheck)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> {
                this.onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}