package com.example.myapp.ui.screen.item

import com.example.myapp.utils.AppButton
import com.example.myapp.utils.AppText
import com.example.myapp.utils.ErrorMessage
import com.example.myapp.utils.ProgressBar
import android.app.SearchManager
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.myapp.R
import com.example.myapp.domain.model.Item
import com.example.myapp.ui.screen.viewmodel.MainViewModel
import com.example.myapp.ui.theme.Green300
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun ItemScreen(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val state = viewModel.itemState.collectAsState()
    when (state.value) {
        is ItemState.Loading -> ProgressBar()
        is ItemState.Error -> ErrorMessage(
            exception = (state.value as ItemState.Error).error
        ) {
            navController.popBackStack()
        }
        is ItemState.Success -> {
            Content(
                navController,
                (state.value as ItemState.Success).item
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun Content(
    navController: NavHostController,
    item: Item?
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            navController
        )
        Images(item)
        TextColumn(item)
        AppButton(
            text = "Где купить?",
            modifier = Modifier
                .width(140.dp)
                .height(44.dp)
                .background(Color.White),
            textColor = Color.Black,
            image = R.drawable.location
        ) {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(
                SearchManager.QUERY,
                "${item?.name} где купить"
            )
            startActivity(context, intent, null)
        }
    }
}

@Composable
fun Images(
    item: Item?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                item?.categories?.icon?.let {
                    "http://shans.d2.i-partner.ru${it}"
                } ?: Color.White
            ),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            painter = rememberAsyncImagePainter(
                "http://shans.d2.i-partner.ru${item?.image}"
            ),
            contentDescription = null,
            modifier = Modifier
                .requiredHeight(190.dp),
            contentScale = ContentScale.Crop
        )
        Icon(
            painter = painterResource(id = R.drawable.star),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {},
            contentDescription = null,
            tint = Green300
        )
    }
}

@Composable
fun TextColumn(item: Item?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.Start
    ) {
        AppText(
            text = item?.name.toString(),
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            color = Color.Black,
            size = 22.sp,
            lineHeight = 28.sp,
            modifier = Modifier.padding(top = 16.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight(600)
        )
        AppText(
            text = item?.description.toString(),
            color = Color.LightGray,
            size = 16.sp,
            lineHeight = 22.sp,
            modifier = Modifier.padding(top = 16.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight(600)
        )
    }
}

@Composable
fun Header(
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Green300)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.padding(start = 24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}
