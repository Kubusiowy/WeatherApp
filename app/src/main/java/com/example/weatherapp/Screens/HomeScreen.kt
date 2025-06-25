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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.Model.WeatherResponse
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: WeatherViewModel)
{
    val pogoda = viewModel.weatherResponse
    val error =viewModel.errorMessage
    var city by remember { mutableStateOf<String>("") }
    val systemUiController = rememberSystemUiController()
    var refreshing by remember { mutableStateOf(false) }


    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = refreshing)


    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            refreshing = true
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.refreshWeather()
                delay(2000)
                refreshing = false

            }

        },
    ) {

    Column(modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()).padding(start = 10.dp, end = 10.dp).verticalScroll(
        rememberScrollState()
    )) {
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = true
            )
        }

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

            Card(modifier = Modifier.padding(top = 15.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE0F7FA)
                ),
                shape = RoundedCornerShape(16.dp)
                ) {


                Column(modifier = Modifier.padding(20.dp))
                {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column() {
                            Text(
                                text = "${pogoda.location.name}, ${pogoda.location.country}",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(text = pogoda.location.localtime, fontSize = 10.sp)
                            Row(verticalAlignment = Alignment.CenterVertically)
                            {
                                AsyncImage(
                                    model = "https:${pogoda.current.condition.icon}",
                                    contentDescription = "icon", modifier = Modifier.size(50.dp)
                                )
                                Text(text = "${pogoda.current.condition.text}")
                            }

                            Text(text = "stopnie celcjusza: ${pogoda.current.temp_c} °C")
                            Text(text = "stopnie farenhajta: ${pogoda.current.temp_f} °F")
                            Text(text = "prędkość wiatru: ${pogoda.current.wind_kph} km/h, ${pogoda.current.wind_mph} mph")
                            Text(text = "kierunek wiatru: ${pogoda.current.wind_dir}")
                            Text(text = "ciśnienie: ${pogoda.current.pressure_mb}")


                        }
                    }
                }

            }


            }




        }
}

}
