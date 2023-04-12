package com.jv.findnotebysound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv.findnotebysound.ui.theme.FindNoteBySoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindNoteBySoundTheme {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    val record = remember { Record() }
                    PitchDetectorCompose(
                        pitchValue = record.pitchInHz.toString(),
                        note = record.note,
                        onStart = record::start
                    )
                }
            }
        }
    }
}

@Composable
fun PitchDetectorCompose(
    pitchValue: String,
    note: String,
    onStart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = pitchValue)
        Spacer(Modifier.height(16.dp))
        Text(text = note)
        Spacer(Modifier.height(16.dp))
        Button(onStart) { Text(text = "Start") }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FindNoteBySoundTheme {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            val record = remember { Record() }
            PitchDetectorCompose(
                pitchValue = record.pitchInHz.toString(),
                note = record.note,
                onStart = record::start
            )
        }
    }
}