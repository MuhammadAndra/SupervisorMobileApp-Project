package com.example.supervisormobileapp_project

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.supervisormobileapp_project.navigation.AppNavigation
import com.example.supervisormobileapp_project.ui.theme.SupervisorMobileAppProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), NfcAdapter.ReaderCallback {
    private val nfcVm: NfcReaderViewModel by viewModels()
    private var nfcAdapter: NfcAdapter? = null
    private var isReaderEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        enableEdgeToEdge()
        setContent {
            SupervisorMobileAppProjectTheme {
                AppNavigation(
                    nfcVm = nfcVm,
//                    onEnableNfc = { enableNfcReader() },
//                    onDisableNfc = { disableNfcReader() }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter?.enableReaderMode(
            this,
            this,
            NfcAdapter.FLAG_READER_NFC_A or
                    NfcAdapter.FLAG_READER_NFC_B or
                    NfcAdapter.FLAG_READER_NFC_F or
                    NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
            null
        )
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableReaderMode(this)
    }

    override fun onTagDiscovered(tag: Tag?) {
        tag?.id?.let { idBytes ->
            runOnUiThread {
                nfcVm.onUidDetected(idBytes)
            }
        }
    }

//    fun enableNfcReader() {
//        if (nfcAdapter == null || !nfcAdapter!!.isEnabled) {
//            Toast.makeText(
//                this,
//                "NFC tidak aktif di perangkat",
//                Toast.LENGTH_SHORT
//            ).show()
//            return
//        }
//        if (!isReaderEnabled) {
//            val flags = NfcAdapter.FLAG_READER_NFC_A or
//                    NfcAdapter.FLAG_READER_NFC_B or
//                    NfcAdapter.FLAG_READER_NFC_F or
//                    NfcAdapter.FLAG_READER_NFC_V or
//                    NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK
//            nfcAdapter?.enableReaderMode(this, this, flags, null)
//            isReaderEnabled = true
//            Toast.makeText(this, "NFC reader diaktifkan", Toast.LENGTH_SHORT)
//                .show()
//        }
//    }
//
//    fun disableNfcReader() {
//        if (isReaderEnabled) {
//            nfcAdapter?.disableReaderMode(this)
//            isReaderEnabled = false
//            Toast.makeText(this, "NFC reader dimatikan", Toast.LENGTH_SHORT)
//                .show()
//        }
//    }
//
//    override fun onTagDiscovered(tag: Tag?) {
//        tag?.id?.let { idBytes ->
//            runOnUiThread {
//                nfcVm.onUidDetected(idBytes)
//                disableNfcReader() // otomatis matikan setelah dapat tag
//            }
//        }
//    }
}
