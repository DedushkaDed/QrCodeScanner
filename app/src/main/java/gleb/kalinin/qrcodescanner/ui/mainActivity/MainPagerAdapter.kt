package gleb.kalinin.qrcodescanner.ui.mainActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import gleb.kalinin.qrcodescanner.ui.scanned_history.ScannedHistoryFragment
import gleb.kalinin.qrcodescanner.ui.scanner.QrScannerFragment

class MainPagerAdapter(var fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) : Fragment {
        return when (position) {
            0 -> QrScannerFragment.newInstance()
            1 -> ScannedHistoryFragment.newInstance()
            2 -> ScannedHistoryFragment.newInstance()
            else -> QrScannerFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}