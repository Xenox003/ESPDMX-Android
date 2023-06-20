package de.jxdev.espdmx.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConnectionStatusDialog (setShowDialog: (Boolean) -> Unit, context : Context) {


    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = {
            setShowDialog(false)
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(0.7f),
            shape = RoundedCornerShape(5.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                ) {
                    Text(
                        text = "System Controls",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .height(25.dp)
                            .clickable { setShowDialog(false) }
                            .background(MaterialTheme.colorScheme.error)
                            .aspectRatio(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "X", color = MaterialTheme.colorScheme.onError)
                    }
                }

                Row {
                    WebsocketLogWindow(
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                    )
                }
            }
        }
    }
}

@Composable
fun WebsocketLogWindow (modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    var coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(bottom = 5.dp),
            text = "Socket Messages"
        )
        Box (
            modifier = Modifier
                .background(Color.Black)
                .padding(1.dp)
        ) {
            LazyColumn(
                reverseLayout = true,
                state = listState,
                modifier = Modifier
                    .fillMaxSize()

            ) {
                items(40) {
                    WebsocketLogEntry(text = "test")
                }
            }
            if (listState.firstVisibleItemIndex > 10) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .shadow(10.dp)
                        .offset((-5).dp, (-5).dp)
                        .padding(5.dp)
                        .width(50.dp)
                        .height(50.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clickable {
                            coroutineScope.launch {
                                listState.scrollToItem(0)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "DOWN")
                }
            }
        }
    }
}

@Composable
fun WebsocketLogEntry (text : String) {
    Text(text = text, fontFamily = FontFamily.Monospace, fontSize = 14.sp)
}