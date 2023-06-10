package de.jxdev.espdmx.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ConnectionStatusDialog (setShowDialog: (Boolean) -> Unit) {
    Dialog (
        onDismissRequest = {
            setShowDialog(false)
        }
    ) {
        Surface (
            shape = RoundedCornerShape(5.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Box (
                contentAlignment = Alignment.Center
            ) {
                Text(text = "test")
            }
        }
    }
}