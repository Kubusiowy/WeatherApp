package com.example.weatherapp.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.ViewModel.WeatherViewModel

@Composable
fun HomeScreen(viewModel: WeatherViewModel)
{
    val pogoda = viewModel.weatherResponse
    val error =viewModel.errorMessage
    var city by remember { mutableStateOf<String>("") }


    Column(modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()).padding(start = 10.dp, end = 10.dp)) {

        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)) {
            OutlinedTextField(
                value = city,
                onValueChange = {city = it},
                label = {Text(text = "Podaj Miasto")},
                singleLine = true,
                trailingIcon =  {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier.clickable{
                        viewModel.getWeather(city)
                    })
                },
                modifier = Modifier.weight(1f)
            )

        }

        if(pogoda == null){
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
            {
                Text(text = "Brak Miasta", fontSize = 26.sp, )
            }
        }else{

                Column(modifier = Modifier.fillMaxSize())
                {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                        ){
                       Column(){
                           Text(text = pogoda.location.name)
                           Text(text = "${pogoda.current.temp_c} °C")
                       }
                    }
                }




            }




        }


}
