package com.target.targetcasestudy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.target.targetcasestudy.ui.DealListFragment

class MainActivity : AppCompatActivity() {


  fun getIntent(context: Context?): Intent? {
    return Intent(context, MainActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    supportFragmentManager.beginTransaction()
      .replace(R.id.container, DealListFragment())
      .commit()
  }

//  override fun onCreateOptionsMenu(menu: Menu): Boolean {
//    menuInflater.inflate(R.menu.menu_main, menu)
//    return true
//  }
//
//  override fun onOptionsItemSelected(item: MenuItem): Boolean {
//    return when (item.itemId) {
//      R.id.credit_card -> {
//        PaymentDialogFragment().show(supportFragmentManager, "CreditCardValidation")
//        true
//      }
//      else -> false
//    }
//  }

  override fun onBackPressed() {
    val fragment =
      this.supportFragmentManager.findFragmentById(R.id.container)
    (fragment as? OnBackPressed)?.onBackPressed()?.not().let {
      super.onBackPressed()
    }
  }

}
