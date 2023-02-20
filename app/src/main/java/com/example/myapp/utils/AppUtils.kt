package com.example.myapp.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.R
import com.example.myapp.ui.theme.Green300
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    size: TextUnit? = null,
    color: Color? = null,
    maxLines: Int = 30,
    fontFamily: FontFamily = FontFamily(Font(R.font.roboto_medium)),
    textAlign: TextAlign = TextAlign.Center,
    lineHeight: TextUnit? = null,
    fontWeight: FontWeight = FontWeight(100)
) {
    Text(
        modifier = modifier,
        color = color ?: Color.Black,
        text = text,
        fontFamily = fontFamily,
        fontSize = size ?: 16.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines,
        textAlign = textAlign,
        lineHeight = lineHeight ?: size ?: 16.sp,
        fontWeight = fontWeight
    )
}

@Composable
fun ProgressBar() {
    val infiniteTransition = rememberInfiniteTransition()
    val color = if (isSystemInDarkTheme()) Color.White else Color.Black
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(1500, easing = FastOutSlowInEasing)
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.rotate(angle),
            progress = 0.5f,
            color = color,
            strokeWidth = 2.dp,
        )
        AppText(text = stringResource(id = R.string.loading))
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun ErrorMessage(
    exception: String,
    onClick: () -> Unit
) {
    var stage by remember {
        mutableStateOf(false)
    }
    AnimatedVisibility(
        visible = stage,
        enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
        exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.error),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = Color.Black
            )
            AppText(
                modifier = Modifier.padding(
                    vertical = 8.dp,
                    horizontal = 56.dp
                ),
                text = exception,
                color = Color.Black,
                size = 22.sp
            )
            AppButton(
                modifier = Modifier
                    .width(140.dp)
                    .height(40.dp)
                    .background(Green300),
                text = stringResource(id = R.string.try_again),
                onClick = {
                    onClick.invoke()
                }
            )
        }
    }
    LaunchedEffect(key1 = true) {
        stage = true
    }
}

@Composable
fun EmptyListMessage() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.error),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = Color.Black
        )
        AppText(
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 56.dp
            ),
            text = stringResource(R.string.empty_list),
            color = Color.Black,
            size = 22.sp
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color? = null,
    image: Int? = null,
    onClick: () -> Unit,
) {
    Button(
        contentPadding = PaddingValues(),
        onClick = { onClick() })
    {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (image != null) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(17.dp)
                )
            }
            AppText(text = text, color = textColor)
        }
    }
}
