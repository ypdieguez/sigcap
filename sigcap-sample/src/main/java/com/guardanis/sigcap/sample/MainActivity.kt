package com.guardanis.sigcap.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.widget.Toast
import com.guardanis.sigcap.NoSignatureException
import com.guardanis.imageloader.ImageRequest
import com.guardanis.sigcap.SignatureDialogBuilder
import java.io.File

class MainActivity: AppCompatActivity(), SignatureDialogBuilder.SignatureEventListener {

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        setContentView(R.layout.activity_main)
    }

    fun startClicked(view: View?) {
        SignatureDialogBuilder()
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