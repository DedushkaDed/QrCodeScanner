package gleb.kalinin.qrcodescanner.ui.scanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.zxing.Result

import gleb.kalinin.qrcodescanner.R
import kotlinx.android.synthetic.main.fragment_qr_scanner.view.*
import me.dm7.barcodescanner.zxing.ZXingScannerView


class QrScannerFragment : Fragment(), ZXingScannerView.ResultHandler {

    companion object {
        fun newInstance() : QrScannerFragment {
            return QrScannerFragment()
        }
    }

    private lateinit var mView: View

    private lateinit var scannerView: ZXingScannerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView =  inflater.inflate(R.layout.fragment_qr_scanner, container, false)
        initializeQrScanner()
        onClicks()
        return mView.rootView
    }

    private fun onClicks() {
        mView.flashToggle.setOnClickListener {
            if(it.isSelected){
                offFlashLight()
            } else {
                onFlashLight()
            }
        }
    }

    private fun offFlashLight() {
        mView.flashToggle.isSelected = false
        scannerView.flash = false
    }

    private fun onFlashLight() {
        mView.flashToggle.isSelected = true
        scannerView.flash = true
    }

    private fun initializeQrScanner() {
        scannerView = ZXingScannerView(context!!)
        scannerView.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorTranslucent))
        scannerView.setBorderColor(ContextCompat.getColor(context!!, R.color.colorPrimaryDark))
        scannerView.setLaserColor(ContextCompat.getColor(context!!, R.color.colorPrimaryDark))
        scannerView.setBorderStrokeWidth(10)
        scannerView.setAutoFocus(true)
        scannerView.setSquareViewFinder(true)
        scannerView.setResultHandler(this)
        // Передаем в UI
        mView.containerScanner.addView(scannerView)
        startQrCamera()

    }

    private fun startQrCamera(){
        scannerView.startCamera()
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler (this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        scannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(context!!, rawResult?.text, Toast.LENGTH_SHORT).show()
        // После того, как мы прочитали Qr-code, мы обновляем нашу камеру. Для того, чтобы камера не зависала - после чтения Qr-кода.
        scannerView.resumeCameraPreview(this)
    }

}
