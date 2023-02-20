package com.example.myapp.ui.screen.list

import com.example.myapp.utils.AppText
import com.example.myapp.utils.EmptyListMessage
import com.example.myapp.utils.ErrorMessage
import com.example.myapp.utils.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapp.R
import com.example.myapp.domain.model.Item
import com.example.myapp.ui.screen.viewmodel.MainViewModel
import com.example.myapp.ui.theme.Green300
import com.example.myapp.ui.navigation.Screen

@Composable
fun ListScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    val state = viewModel.listState.collectAsState()
    Scaffold(
        topBar = { Header(viewModel = viewModel) },
        content = {
            when (state.value) {
                is ListState.Loading -> ProgressBar()
                is ListState.Error -> ErrorMessage(
                    exception = (state.value as ListState.Error).error
                ) {
                    viewModel.getItems()
                }
                is ListState.Success -> {
                    val list = (state.value as ListState.Success).item
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = it
                        ) {
                            items(list?.size ?: 0) { index ->
                                ItemCard(
                                    list?.get(index),
                                ) {
                                    viewModel.getItem(it)
                                    navController.navigate(Screen.ItemScreen.route)
                                }
                            }
                        }
                    }
                }
                is ListState.EmptyList -> EmptyListMessage()
            }
        }
    )
}

@Composable
fun ItemCard(
    item: Item?,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .height(316.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable {
                onClick.invoke(item?.id ?: 0)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    item?.categories?.image?.let {
                        "http://shans.d2.i-partner.ru${it}"
                    } ?: Green300
                ),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8))
                    .fillMaxWidth()
                    .height(84.dp),
                contentScale = ContentScale.Crop
            )
            AppText(
                text = item?.name.toString().trim().uppercase(),
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                color = Color.Black,
                size = 13.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Start
            )
            AppText(
                text = item?.description?.trim().toString(),
                color = Color.LightGray,
                size = 12.sp,
                lineHeight = 17.sp,
                maxLines = 5,
                modifier = Modifier.padding(top = 12.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun Header(
    viewModel: MainViewModel
) {
    var isSearch by remember { mutableStateOf(false) }
    var request by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Green300)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier.padding(start = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    request = ""
                    viewModel.getItems()
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
            if (!isSearch) {
                AppText(
                    text = stringResource(R.string.header_main_word),
                    color = Color.White,
                    size = 16.sp,
                    modifier = Modifier
                        .width(196.dp)
                        .padding(start = 32.dp),
                    textAlign = TextAlign.Start
                )
            } else {
                TextField(
                    value = request,
                    onValueChange = {
                        request = it
                        viewModel.getItemsBySearch(it)
                    },
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .background(
                            Green300
                        ),
                    placeholder = {
                        AppText(
                            text = request,
                            color = Color.White,
                            size = 16.sp,
                            modifier = Modifier
                                .width(196.dp)
                                .padding(start = 32.dp),
                            textAlign = TextAlign.Start
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.DarkGray
                    ),

                    )
            }
        }
        Icon(
            painter = painterResource(
                id = if (!isSearch) R.drawable.search else R.drawable.cancel
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 24.dp)
                .clip(CircleShape)
                .clickable {
                    isSearch = !isSearch
                },
            tint = Color.White,
        )
    }
}
