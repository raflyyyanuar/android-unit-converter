package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.pow
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                    ) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter() {
    val unitList = listOf(
        "km",
        "hm",
        "dam",
        "m",
        "dm",
        "cm",
        "mm",
    )

    var fromValue by remember { mutableStateOf("") }
    var toValue by remember { mutableStateOf("") }

    var fromExpanded by remember { mutableStateOf(false) }
    var toExpanded by remember { mutableStateOf(false) }

    var fromUnit by remember { mutableStateOf("cm") }
    var toUnit by remember { mutableStateOf("m") }

    val precision = 10.0.pow(5)

    fun calculateResult() {
        val inputValue = fromValue.toDoubleOrNull()
        if(inputValue == null) {
            toValue = "Invalid input"
            return
        }

        val steps = unitList.indexOf(toUnit) - unitList.indexOf(fromUnit)
        val power = 10.0.pow(steps)
        val result = (power * inputValue * precision).roundToInt() / precision
        toValue = "$result $toUnit"
    }

    fun setFrom(unit : Int) {
        fromExpanded = false
        fromUnit = unitList[unit]

        calculateResult()
    }

    fun setTo(unit : Int) {
        toExpanded = false
        toUnit = unitList[unit]

        calculateResult()
    }

    fun updateInput(it : String) {
        fromValue = it
        calculateResult()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Unit Converter", modifier = Modifier.padding(top = 16.dp), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = fromValue,
            onValueChange = { updateInput(it) },
            label = { Text("Enter a value to convert from...") },
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Box {
                Button(onClick = { fromExpanded = true }) {
                    Text(fromUnit)
                    Icon(Icons.Default.ArrowDropDown, "Unit dropdown")
                }
                DropdownMenu(expanded = fromExpanded, onDismissRequest = { fromExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(unitList[0]) },
                        onClick = { setFrom(0) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[1]) },
                        onClick = { setFrom(1) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[2]) },
                        onClick = { setFrom(2) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[3]) },
                        onClick = { setFrom(3) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[4]) },
                        onClick = { setFrom(4) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[5]) },
                        onClick = { setFrom(5) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[6]) },
                        onClick = { setFrom(6) }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { toExpanded = true }) {
                    Text(toUnit)
                    Icon(Icons.Default.ArrowDropDown, "Unit dropdown")
                }
                DropdownMenu(expanded = toExpanded, onDismissRequest = { toExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(unitList[0]) },
                        onClick = { setTo(0) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[1]) },
                        onClick = { setTo(1) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[2]) },
                        onClick = { setTo(2) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[3]) },
                        onClick = { setTo(3) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[4]) },
                        onClick = { setTo(4) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[5]) },
                        onClick = { setTo(5) }
                    )
                    DropdownMenuItem(
                        text = { Text(unitList[6]) },
                        onClick = { setTo(6) }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Result: $toValue")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}
