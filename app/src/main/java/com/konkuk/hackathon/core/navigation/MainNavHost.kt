package com.konkuk.hackathon.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.konkuk.hackathon.feature.onboarding.OnboardingScreen

@Composable
fun MainNavHost(
    padding: PaddingValues,
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Route.Onboarding,
    ) {
        // Onboarding
        composable<Route.Onboarding> {
            OnboardingScreen(
                padding = padding,
                navigateToLogin = { navController.navigate(Route.Login) },
            )
        }
        composable<Route.Login> {

        }
        composable<Route.SignUp> {

        }
        composable<Route.VolunteerSignUp> {

        }
        composable<Route.OrganizationSignUp> {

        }

        // Home
        composable<MainTabRoute.Home> {

        }

        // Record
        composable<MainTabRoute.Record> {

        }

        // Settings
        composable<MainTabRoute.Settings> {

        }
    }
}