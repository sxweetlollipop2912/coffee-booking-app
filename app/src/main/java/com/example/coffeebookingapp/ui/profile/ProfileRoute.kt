package com.example.coffeebookingapp.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileRoute(
    profileViewModel: ProfileViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val fullName by profileViewModel.fullName.collectAsStateWithLifecycle()
    val phone by profileViewModel.phone.collectAsStateWithLifecycle()
    val email by profileViewModel.email.collectAsStateWithLifecycle()
    val address by profileViewModel.address.collectAsStateWithLifecycle()

    ProfileScreen(
        fullName = fullName,
        phone = phone,
        email = email,
        address = address,
        onFullNameSubmit = { profileViewModel.changeFullName(it) },
        onPhoneSubmit = { profileViewModel.changePhone(it) },
        onEmailSubmit = { profileViewModel.changeEmail(it) },
        onAddressSubmit = { profileViewModel.changeAddress(it) },
        onBackClick = onBack,
        modifier = modifier,
    )
}