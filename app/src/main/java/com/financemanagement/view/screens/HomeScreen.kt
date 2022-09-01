package com.financemanagement.view.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.financemanagement.AddNewEntryActivity
import com.financemanagement.R
import com.financemanagement.model.UserEntryItem
import com.financemanagement.view.components.UserEntryCard
import com.financemanagement.viewmodels.HomeViewModel

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val homeViewModel = HomeViewModel()
    val screenState by homeViewModel.state.collectAsState()

    Column {
        TopAppBar(title = { Text(text = context.getString(R.string.finance)) })
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = context.getString(R.string.total_income),
                    modifier = Modifier
                        .fillMaxWidth(0.70f)
                        .wrapContentWidth(Alignment.Start),
                    fontSize = 26.sp
                )
                Text(
                    text = screenState.totalIncome.toString(),
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    fontSize = 26.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = context.getString(R.string.total_expense),
                    modifier = Modifier
                        .fillMaxWidth(0.70f)
                        .wrapContentWidth(Alignment.Start),
                    fontSize = 26.sp
                )
                Text(
                    text = screenState.totalExpense.toString(),
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    fontSize = 26.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = context.getString(R.string.balance),
                    modifier = Modifier
                        .fillMaxWidth(0.70f)
                        .wrapContentWidth(Alignment.Start),
                    fontSize = 26.sp
                )
                Text(
                    text = (screenState.totalIncome - screenState.totalExpense).toString(),
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    fontSize = 26.sp
                )
            }
            Text(
                text = context.getString(R.string.expenses),
                fontSize = 20.sp,
                style = TextStyle(color = Color.Gray),
                modifier = Modifier.padding(10.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn {
                    itemsIndexed(
                        items = screenState.userEntries
                    ) { index, item ->
                        ShowCard(index, item, context)
                    }
                }
                FloatingActionButton(
                    onClick = {
                        context.startActivity(Intent(context, AddNewEntryActivity::class.java))
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp),
                    contentColor = Color.White,
                    backgroundColor = Color.Black,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = context.getString(R.string.add_new_expense)
                    )
                }

            }

        }
    }
}

@Composable
private fun ShowCard(index: Int, item: UserEntryItem, context: Context) {
    UserEntryCard(
        userEntry = item,
        onClick = {
            Toast.makeText(context, "Clicked Item ${index + 1}", Toast.LENGTH_SHORT).show()
        }
    )
}
