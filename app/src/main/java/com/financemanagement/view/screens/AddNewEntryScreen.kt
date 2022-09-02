package com.financemanagement.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.financemanagement.R

@Composable
fun AddNewEntryScreen() {
    val context = LocalContext.current
    val radioOptions = listOf("INCOME", "EXPENSE")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }

    Column {
        TopAppBar(title = { Text(text = context.getString(R.string.title_activity_add_new_entry)) })
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var titleTextState by remember { mutableStateOf(TextFieldValue()) }
            var amountTextState by remember { mutableStateOf(TextFieldValue()) }

            TextField(
                value = titleTextState,
                onValueChange = { titleTextState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textStyle = TextStyle(background = Color.White),
                label = { Text(text = stringResource(R.string.title)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
            TextField(
                value = amountTextState,
                onValueChange = { amountTextState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textStyle = TextStyle(background = Color.White),
                label = { Text(text = stringResource(R.string.amount)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = context.getString(R.string.entry_type),
                    fontSize = 20.sp,
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(0.35f),
                    textAlign = TextAlign.Start
                )

                radioOptions.forEach { text ->
                    Row(modifier =
                    Modifier
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            shape = when (text) {
                                // left outer button
                                "INCOME" -> RoundedCornerShape(
                                    topStart = 8.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 8.dp,
                                    bottomEnd = 0.dp
                                )

                                else -> RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 8.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 8.dp
                                )
                            },
                            onClick = {
                                onOptionSelected(text)
                            },
                            border = BorderStroke(
                                1.dp, if (selectedOption == text) {
                                    MaterialTheme.colors.primary
                                } else {
                                    Color.DarkGray.copy(alpha = 0.75f)
                                }
                            ),
                            colors = if (selectedOption == text) {
                                ButtonDefaults.outlinedButtonColors(
                                    backgroundColor = MaterialTheme.colors.primary.copy(
                                        alpha = 0.1f
                                    ), contentColor = MaterialTheme.colors.primary
                                )
                            } else {
                                ButtonDefaults.outlinedButtonColors(
                                    backgroundColor = MaterialTheme.colors.surface,
                                    contentColor = MaterialTheme.colors.primary
                                )
                            }
                        ) {
                            Text(
                                text = text
                            )
                        }

                    }
                }
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(15.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = stringResource(R.string.done)
                )
            }
        }
    }
}