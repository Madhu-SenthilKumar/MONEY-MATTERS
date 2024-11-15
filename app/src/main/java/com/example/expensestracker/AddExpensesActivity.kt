package com.example.expensestracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AddExpensesActivity : ComponentActivity() {
    private lateinit var itemsDatabaseHelper: ItemsDatabaseHelper
    private lateinit var expenseDatabaseHelper: ExpenseDatabaseHelper

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsDatabaseHelper = ItemsDatabaseHelper(this)
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
                AddExpenses(this, itemsDatabaseHelper, expenseDatabaseHelper)
            }
        }
    }
}

@SuppressLint("Range")
@Composable
fun AddExpenses(context: Context, itemsDatabaseHelper: ItemsDatabaseHelper, expenseDatabaseHelper: ExpenseDatabaseHelper) {
    val mContext = LocalContext.current
    var items by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Item Name
        Text(
            text = "Item Name",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color(0xFF00838F)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = items,
            onValueChange = { items = it },
            label = { Text(text = "Enter Item Name", color = Color.Gray) },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFF0F0F0),
                focusedBorderColor = Color(0xFF00838F),
                unfocusedBorderColor = Color(0xFFB0BEC5)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Quantity
        Text(
            text = "Quantity of Item",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color(0xFF00838F)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text(text = "Enter Quantity", color = Color.Gray) },
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFF0F0F0),
                focusedBorderColor = Color(0xFF00838F),
                unfocusedBorderColor = Color(0xFFB0BEC5)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Cost
        Text(
            text = "Cost of the Item",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color(0xFF00838F)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = cost,
            onValueChange = { cost = it },
            label = { Text(text = "Enter Cost", color = Color.Gray) },
            shape = RoundedCornerShape(40.dp),
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

        // Submit Button
        Button(
            onClick = {
                if (items.isNotEmpty() && quantity.isNotEmpty() && cost.isNotEmpty()) {
                    val item = Items(null, items, quantity, cost)
                    itemsDatabaseHelper.insertItems(item)
                    Toast.makeText(mContext, "Item Added", Toast.LENGTH_SHORT).show()
                } else {
                    error = "Please fill all fields"
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00838F)),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(
                text = "Submit",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}
