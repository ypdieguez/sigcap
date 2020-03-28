package com.guardanis.sigcap.sample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.guardanis.sigcap.NoSignatureException
import com.guardanis.sigcap.SignatureDialogBuilder
import com.guardanis.sigcap.SignatureRequest
import com.guardanis.sigcap.SignatureResponse

class MainActivity: AppCompatActivity(), SignatureDialogBuilder.SignatureEventListener {

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        setContentView(R.layout.activity_main)
    }

    fun startClicked(view: View?) {
        SignatureDialogBuilder()
                .setRequest(
                        SignatureRequest()
                                .setResultBackgroundColor(Color.TRANSPARENT)
                                .setResultIncludeBaseline(true)
                                .setResultIncludeBaselineXMark(true))
                .show(this, this)
    }

    override fun onSignatureEntered(response: SignatureResponse) {
        findViewById<AppCompatImageView>(R.id.main__image)
                .setImageBitmap(response.result)

        val savedFile = response.saveToFileCache(this)
                .get()
    }

    override fun onSignatureInputCanceled() {
        Toast.makeText(this, "Signature input canceled", Toast.LENGTH_SHORT)
                .show()
    }

    override fun onSignatureInputError(e: Throwable) {
        if (e is NoSignatureException) {
            // They clicked confirm without entering anything
            // doSomethingOnNoSignatureEntered()
        }

        Toast.makeText(this, "Signature error", Toast.LENGTH_SHORT)
                .show()
    }
}