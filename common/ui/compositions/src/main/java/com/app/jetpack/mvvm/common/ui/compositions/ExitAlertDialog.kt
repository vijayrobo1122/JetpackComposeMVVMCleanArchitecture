package com.app.jetpack.mvvm.common.ui.compositions


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.jetpack.mvvm.common.ui.resources.strings.StringResources

@Composable
fun ExitAlertDialog(
    cancelButtonClick: (isOpen: Boolean) -> Unit,
    okButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
        },
        // below line is use to display title of our dialog
        // box and we are setting text color to white.
        title = {
            Text(
                text = stringResource(StringResources.closeTheApp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        },
        text = {
            Text(text = stringResource(StringResources.doYouWantToExitTheApp), fontSize = 16.sp)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    okButtonClick()
                }) {
                Text(
                    stringResource(StringResources.yesLabel),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(color = Color.Black)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    cancelButtonClick(false)
                }) {
                Text(
                    stringResource(StringResources.noLabel),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(color = Color.Black)
                )
            }
        },
    )
}
