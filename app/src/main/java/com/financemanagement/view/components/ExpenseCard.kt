package com.financemanagement.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.financemanagement.model.EntryType
import com.financemanagement.model.UserEntryItem

@Composable
fun UserEntryCard(
    userEntry: UserEntryItem,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(
                top = 5.dp,
                bottom = 5.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 2.dp
    ) {
        Column {
            userEntry.title.let { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.80f)
                            .wrapContentWidth(Alignment.Start),
                        fontSize = 20.sp,
                        style = TextStyle(
                            color = if (userEntry.entryType == EntryType.EXPENSE)
                                Color.Red
                            else Color.Green
                        )
                    )
                    Text(
                        text = userEntry.amount.toString(),
                        modifier = Modifier
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        fontSize = 20.sp,
                        style = TextStyle(
                            color = if (userEntry.entryType == EntryType.EXPENSE)
                                Color.Red
                            else Color.Green
                        )
                    )
                }
            }
        }
    }
}