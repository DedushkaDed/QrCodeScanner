package gleb.kalinin.qrcodescanner.ui.ui.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import gleb.kalinin.qrcodescanner.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewPagerAdapter()
        setBottomNavigation()
        setViewPagerListener()
    }



    private fun setBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener {

           viewPager.currentItem = when(it.itemId){
                R.id.scanMenuId -> 0
                R.id.recentScannedMenuId -> 1
                R.id.favouritesMenuId -> 2
               else -> 0
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setViewPagerAdapter() {
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
    }

    private fun setViewPagerListener() {
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                bottomNavigationView.selectedItemId = when(position){
                    0 ->  R.id.scanMenuId
                    1 ->  R.id.recentScannedMenuId
                    2 ->  R.id.favouritesMenuId
                    else -> R.id.scanMenuId
                }
            }
        })
    }
}
