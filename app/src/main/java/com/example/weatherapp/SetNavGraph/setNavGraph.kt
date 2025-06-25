package com.example.weatherapp.SetNavGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.Screens.HomeScreen
import com.example.weatherapp.Screens.Screens
import com.example.weatherapp.ViewModel.WeatherViewModel

@Composable
fun setNavGraph(navHostController:NavHostController,viewModel: WeatherViewModel)
{

    NavHost(navController = navHostController, startDestination = Screens.HomeScreen.route){

        composable(route = Screens.HomeScreen.route) {
            HomeScreen(viewModel)
        }

    }

}
