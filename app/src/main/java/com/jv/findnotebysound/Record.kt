package com.jv.findnotebysound

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import be.hogent.tarsos.dsp.MicrophoneAudioDispatcher
import be.hogent.tarsos.dsp.pitch.PitchDetectionHandler
import be.hogent.tarsos.dsp.pitch.PitchProcessor

class Record {
    lateinit var audioThread: Thread
    var pitchInHz by mutableStateOf(0.0f)
    var note by mutableStateOf("")

    fun start() {
        try {
            val dispatcher = MicrophoneAudioDispatcher(22050, 1024, 0)
            val pdh = PitchDetectionHandler { pitchDetectionResult, audioEvent ->
                processPitch(pitchDetectionResult.pitch)
            }
            val audioProcessor = PitchProcessor(
                PitchProcessor.PitchEstimationAlgorithm.FFT_YIN,
                22050F,
                1024,
                pdh
            )
            dispatcher.addAudioProcessor(audioProcessor)
            audioThread = Thread(dispatcher, "Audio Thread")
            audioThread.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun processPitch(pitchInHz: Float) {
        this.pitchInHz = pitchInHz
        if (pitchInHz >= 110 && pitchInHz < 123.47) {
            //A
            note = "A"
        } else if (pitchInHz >= 123.47 && pitchInHz < 130.81) {
            //B
            note = "B"
        } else if (pitchInHz >= 130.81 && pitchInHz < 146.83) {
            //C
            note = "C"
        } else if (pitchInHz >= 146.83 && pitchInHz < 164.81) {
            //D
            note = "D"
        } else if (pitchInHz >= 164.81 && pitchInHz <= 174.61) {
            //E
            note = "E"
        } else if (pitchInHz >= 174.61 && pitchInHz < 185) {
            //F
            note = "F"
        } else if (pitchInHz >= 185 && pitchInHz < 196) {
            //G
            note = "G"
        }
    }
}