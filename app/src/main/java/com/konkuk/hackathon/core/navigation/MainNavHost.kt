package com.konkuk.hackathon.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.konkuk.hackathon.feature.login.LoginScreen
import com.konkuk.hackathon.feature.onboarding.OnboardingScreen
import com.konkuk.hackathon.feature.signup.SignUpScreen
import com.konkuk.hackathon.feature.signup.organization.OrganizationSignUpScreen
import com.konkuk.hackathon.feature.signup.volunteer.VolunteerSignUpScreen

@Composable
fun MainNavHost(
    padding: PaddingValues,
    navigator: MainNavigator,
) {
    val navController = navigator.navController

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
            LoginScreen(
                padding = padding,
                navigateToHome = { navigator.navigate(MainTab.HOME) },
                navigateToSignUp = { navController.navigate(Route.SignUp) },
            )
        }
        composable<Route.SignUp> {
            SignUpScreen(
                padding = padding,
                popBackStack = { navController.popBackStack() },
                navigateToVolunteerSignUp = { navController.navigate(Route.VolunteerSignUp) },
                navigateToOrganizationSignUp = { navController.navigate(Route.OrganizationSignUp) },
            )
        }
        composable<Route.VolunteerSignUp> {
            VolunteerSignUpScreen(
                padding = padding,
                popBackStack = { navController.popBackStack() },
                navigateToHome = { navigator.navigate(MainTab.HOME) },
            )
        }
        composable<Route.OrganizationSignUp> {
            OrganizationSignUpScreen(
                padding = padding,
                popBackStack = { navController.popBackStack() },
                navigateToHome = { navigator.navigate(MainTab.HOME) },
            )
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