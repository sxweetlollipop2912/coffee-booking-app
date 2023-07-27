package com.example.coffeebookingapp.ui.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.coffeebookingapp.R
import com.example.coffeebookingapp.ui.UIConfig
import com.example.coffeebookingapp.ui.theme.light_onBackground2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    fullName: String,
    phone: String,
    email: String,
    address: String,
    onFullNameSubmit: (String) -> Unit,
    onPhoneSubmit: (String) -> Unit,
    onEmailSubmit: (String) -> Unit,
    onAddressSubmit: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_arrow),
                            contentDescription = "back",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                modifier = Modifier.padding(horizontal = UIConfig.TOP_BAR_SIDE_PADDING),
            )
        }
    ) { innerPadding ->
        val screenModifier = Modifier.padding(innerPadding)
        ProfileScreenContent(
            fullName,
            phone,
            email,
            address,
            onFullNameSubmit,
            onPhoneSubmit,
            onEmailSubmit,
            onAddressSubmit,
            screenModifier.padding(horizontal = UIConfig.SCREEN_SIDE_PADDING)
        )
    }
}

@Composable
fun ProfileScreenContent(
    fullName: String,
    phone: String,
    email: String,
    address: String,
    onFullNameChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onAddressChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        ProfileField(
            iconDrawable = R.drawable.person,
            label = "Full name",
            content = fullName,
            onSubmit = onFullNameChanged,
        )
        ProfileField(
            iconDrawable = R.drawable.phone,
            label = "Phone number",
            content = phone,
            onSubmit = onPhoneChanged,
        )
        ProfileField(
            iconDrawable = R.drawable.mail,
            label = "Email",
            content = email,
            onSubmit = onEmailChanged,
        )
        ProfileField(
            iconDrawable = R.drawable.location_profile,
            label = "Address",
            content = address,
            onSubmit = onAddressChanged,
        )
    }
}

@Composable
private fun ProfileField(
    @DrawableRes iconDrawable: Int,
    label: String,
    content: String,
    onSubmit: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isEditing by rememberSaveable { mutableStateOf(false) }
    if (isEditing) {
        var current by rememberSaveable { mutableStateOf(content) }
        EditField(
            iconDrawable = iconDrawable,
            label = label,
            content = current,
            onChanged = { current = it },
            onSubmit = { onSubmit(current); isEditing = false },
            modifier = modifier
        )
    } else {
        DisplayField(
            iconDrawable = iconDrawable,
            label = label,
            content = content,
            onEditClick = { isEditing = true },
            modifier = modifier
        )
    }
}

@Composable
private fun DisplayField(
    @DrawableRes iconDrawable: Int,
    label: String,
    content: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseField(
        leftIconDrawable = iconDrawable,
        rightIconDrawable = R.drawable.edit,
        label = label,
        onRightIconClick = onEditClick,
        modifier = modifier
    ) {
        Text(
            text = content,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
        )
    }
}

@Composable
private fun EditField(
    @DrawableRes iconDrawable: Int,
    label: String,
    content: String,
    onChanged: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseField(
        leftIconDrawable = iconDrawable,
        rightIconDrawable = R.drawable.right_arrow,
        label = label,
        onRightIconClick = onSubmit,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(10.dp, 5.dp)
        ) {
            BasicTextField(
                value = TextFieldValue(
                    text = content,
                    selection = TextRange(content.length)
                ),
                onValueChange = { onChanged(it.text) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground
                ),
            )
        }
    }
}

@Composable
private fun BaseField(
    @DrawableRes leftIconDrawable: Int,
    @DrawableRes rightIconDrawable: Int,
    label: String,
    onRightIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentUnit: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .padding(15.dp, 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.Top,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .size(42.dp),
            ) {
                Icon(
                    painter = painterResource(id = leftIconDrawable),
                    contentDescription = null,
                )
            }
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = light_onBackground2
                    )
                )
                contentUnit()
            }
        }
        IconButton(
            onClick = onRightIconClick,
        ) {
            Icon(
                painter = painterResource(id = rightIconDrawable),
                contentDescription = null,
            )
        }
    }
}