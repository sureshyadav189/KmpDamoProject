package com.example.kmpdamoproject.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import dev.suresh.details.ui.DetailsScreen
import kotlinx.serialization.Serializable

object DetailsNavGraph : BaseNavigationGraph {
    sealed interface Dest{
        @Serializable
        data object Root : Dest

        @Serializable
        data class Details(val id : String) : Dest
    }

    override fun build(
        modifier: Modifier,
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation<Dest.Root>(startDestination = Dest.Details::class){
            composable<Dest.Details> {
                val id  = it.toRoute<Dest.Details>().id
                DetailsScreen(modifier,id)
            }
        }
    }
}