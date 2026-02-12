package com.example.kmpdamoproject

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.kmpdamoproject.navigation.BaseNavigationGraph
import com.example.kmpdamoproject.navigation.DetailsNavGraph
import com.example.kmpdamoproject.navigation.SearchNavGraph
import org.jetbrains.compose.resources.painterResource

import kmpdamoproject.composeapp.generated.resources.Res
import kmpdamoproject.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxSize()
        ) {innerPadding ->

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = SearchNavGraph.Dest.Root,
            ){
                listOf<BaseNavigationGraph>(
                    SearchNavGraph,
                    DetailsNavGraph
                ).forEach { it.build(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    navController = navController,
                    navGraphBuilder = this
                ) }
            }

        }
    }
}