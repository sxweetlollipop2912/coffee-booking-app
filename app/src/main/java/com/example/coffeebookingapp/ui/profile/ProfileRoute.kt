package com.example.coffeebookingapp.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileRoute(
    profileViewModel: ProfileViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val fullName: State<String> = profileViewModel.fullName.collectAsStateWithLifecycle()
    val phone: State<String> = profileViewModel.phone.collectAsStateWithLifecycle()
    val email: State<String> = profileViewModel.email.collectAsStateWithLifecycle()
    val address: State<String> = profileViewModel.address.collectAsStateWithLifecycle()

    ProfileScreen(
        fullName = fullName.value,
        phone = phone.value,
        email = email.value,
        address = address.value,
        onFullNameSubmit = { profileViewModel.changeFullName(it) },
        onPhoneSubmit = { profileViewModel.changePhone(it) },
        onEmailSubmit = { profileViewModel.changeEmail(it) },
        onAddressSubmit = { profileViewModel.changeAddress(it) },
        onBackClick = onBack,
        modifier = modifier
    )
}