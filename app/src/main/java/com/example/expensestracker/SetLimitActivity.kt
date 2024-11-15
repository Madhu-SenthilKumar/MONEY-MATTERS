package com.example.expensestracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SetLimitActivity : ComponentActivity() {
    private lateinit var expenseDatabaseHelper: ExpenseDatabaseHelper

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        expenseDatabaseHelper = ExpenseDatabaseHelper(this)
        setContent {
            Scaffold(
                bottomBar = {
                    BottomAppBar(
                        backgroundColor = Color(0xFFB2EBF2),
                        modifier = Modifier.height(80.dp),
                        content = {
                            Spacer(modifier = Modifier.width(15.dp))

                            Button(
                                onClick = { startActivity(Intent(applicationContext, AddExpensesActivity::class.java)) },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00838F)),
                                modifier = Modifier.size(height = 55.dp, width = 130.dp)
                            ) {
                                Text(
                                    text = "Add Expenses",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }

                            Spacer(modifier = Modifier.width(15.dp))

                            Button(
                                onClick = { startActivity(Intent(applicationContext, SetLimitActivity::class.java)) },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00838F)),
                                modifier = Modifier.size(height = 55.dp, width = 130.dp)
                            ) {
                                Text(
                                    text = "Set Limit",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }

                            Spacer(modifier = Modifier.width(15.dp))

                            Button(
                                onClick = { startActivity(Intent(applicationContext, ViewRecordsActivity::class.java)) },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00838F)),
                                modifier = Modifier.size(height = 55.dp, width = 130.dp)
                            ) {
                                Text(
                                    text = "View Records",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    )
                }
            ) {
                val expenseList = expenseDatabaseHelper.getAllExpense()
                Log.d("ExpenseData", expenseList.toString())
                Limit(this, expenseDatabaseHelper, expenseList)
            }
        }
    }
}

@Composable
fun Limit(context: Context, expenseDatabaseHelper: ExpenseDatabaseHelper, expenseList: List<Expense>) {
    var amount by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "Monthly Amount Limit",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF00838F)
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Amount Input Field
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text(text = "Enter Limit Amount", color = Color.Gray) },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFF0F0F0),
                focusedBorderColor = Color(0xFF00838F),
                unfocusedBorderColor = Color(0xFFB0BEC5)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Error Message
        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = Color(0xFFD32F2F),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }

        // Set Limit Button
        Button(
            onClick = {
                if (amount.isNotEmpty()) {
                    val expense = Expense(id = null, amount = amount)
                    expenseDatabaseHelper.insertExpense(expense)
                    Toast.makeText(context, "Limit Set Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    error = "Amount field cannot be empty"
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00838F)),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(
                text = "Set Limit",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Display Remaining Amount
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            item {
                LazyColumn {
                    items(expenseList) { expense ->
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Remaining Amount: ${expense.amount}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color(0xFF333333)
                            )
                        }
                    }
                }
            }
        }
    }
}
