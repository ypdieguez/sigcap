package com.guardanis.sigcap.sample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.guardanis.imageloader.ImageRequest
import com.guardanis.sigcap.NoSignatureException
import com.guardanis.sigcap.SignatureDialogBuilder
import com.guardanis.sigcap.SignatureRenderer
import com.guardanis.sigcap.SignatureRequest
import java.io.File

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
                                .setIncludeBaseline(true)
                                .setIncludeBaselineXMark(true))
                .show(this, this)
    }

    override fun onSignatureEntered(savedFile: File) {
        ImageRequest(this, findViewById<AppCompatImageView>(R.id.main__image))
                .setTargetFile(savedFile)
                .setFadeTransition()
                .execute() // Just showing the image
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