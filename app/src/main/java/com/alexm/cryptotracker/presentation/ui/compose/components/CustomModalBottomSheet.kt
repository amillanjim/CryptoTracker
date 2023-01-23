package com.alexm.cryptotracker.presentation.ui.compose.components

import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomModalBottomSheet(
    parent: ViewGroup,
    composeView: ComposeView,
    title: String,
    body: String
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Expanded,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy
        ),
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    LaunchedEffect(sheetState.currentValue) {
        when (sheetState.currentValue) {
            ModalBottomSheetValue.Hidden -> parent.removeView(composeView)
            else -> {}
        }
    }

    ModalBottomSheetLayout(
        sheetBackgroundColor = MaterialTheme.colors.surface,
        scrimColor = MaterialTheme.colors.onSurface.copy(alpha = 0.55f),
        sheetShape = RoundedCornerShape(16.dp),
        sheetState = sheetState,
        sheetContent = {
            BottomSheet(
                title = title,
                body = body,
                buttonAction = { coroutineScope.launch { sheetState.hide() } }
            ) },
        modifier = Modifier.fillMaxSize()
    ) {}
}

@Composable
fun BottomSheet(
    title: String,
    body: String,
    buttonAction: () -> Unit
) {
    Column(
        modifier = Modifier.padding(32.dp)
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = body,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = buttonAction) {
            Text(
                text = "Close",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body1
            )
        }
    }
}