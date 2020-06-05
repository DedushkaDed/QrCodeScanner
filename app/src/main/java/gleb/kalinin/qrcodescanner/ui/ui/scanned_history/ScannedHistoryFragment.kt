package gleb.kalinin.qrcodescanner.ui.ui.scanned_history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import gleb.kalinin.qrcodescanner.R

class ScannedHistoryFragment : Fragment() {

    companion object {
        fun newInstance(): ScannedHistoryFragment{
            return ScannedHistoryFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scanned_history, container, false)
    }

}
