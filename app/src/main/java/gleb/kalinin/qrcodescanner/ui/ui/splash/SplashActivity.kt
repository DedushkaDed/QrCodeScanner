package gleb.kalinin.qrcodescanner.ui.ui.splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import gleb.kalinin.qrcodescanner.ui.ui.mainActivity.MainActivity
import gleb.kalinin.qrcodescanner.R


class SplashActivity : AppCompatActivity() {

    companion object {

        private const val CAMERA_PERMISSION_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Delay splash-screen for 3 sec.
        Handler().postDelayed({
            checkForPermission()
        }, 3000)
    }



    private fun checkForPermission() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            goToMainActivity()
        } else {
            requestThePermission()
        }
    }

    private fun requestThePermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){

            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                goToMainActivity()
            } else if (isUserPermanentlyDenied()) {
                showGoToAppSettingsDialog()
            } else {
                requestThePermission()
                // Может быть сделать TOAST? -> с указанием ошибки. Или же функцию: showGoToAppSettingsDialog()
            }
        }
    }

    private fun showGoToAppSettingsDialog() {
        AlertDialog.Builder(this)
            .setTitle("Предоставьте доступ к камере")
            .setMessage("Нам необходим доступ к вашей камере, чтобы отсканировать QR код. \n" +
                    "\n" +
                    "Зайдите в настройки приложения и разрешите приложению доступ к вашей камере.")
            .setPositiveButton("Разрешить"){dialog, which ->
                goToAppSettings()
            }
            .setNegativeButton("Отменить") { dialog, which ->
                Toast.makeText(this, "Приложению необходим доступ к камере, для запуска текущего приложения.", Toast.LENGTH_LONG).show()
                finish()
            }.show()
    }

    private fun goToAppSettings() {
        val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null))
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun isUserPermanentlyDenied(): Boolean {
        // if sdk version -> greater than marshmallow (android version) -> return if. Otherwise -> return false
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA).not().not()
        } else {
            return false
        }
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onRestart() {
        super.onRestart()
        checkForPermission()
    }

}
