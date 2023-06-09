package com.john_halaka.littlelemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.john_halaka.littlelemon.data.MenuItem
import com.john_halaka.littlelemon.data.MenuRepository

@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val applicationContext = context.applicationContext
    val menuRepository = MenuRepository.getInstance(applicationContext)
    // we need to access the database NOT from the main thread! not sure if this is the right place for the error or not.
    val menuViewModel: MenuViewModel = viewModel(
        factory = MenuViewModelFactory(menuRepository)
    )
    val menuItems by menuViewModel.menuItems.collectAsState()
    Log.d("HomeScreen", "menuItems: $menuItems")
    var searchPhrase by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    val filteredMenuItems =
        if (searchPhrase.isBlank() && category.isBlank()) {
            menuItems
        } else if (searchPhrase.isNotBlank()) {
            menuItems.filter {
                it.title.contains(searchPhrase, ignoreCase = true)
            }
        } else {
            menuItems.filter {
                it.category.contains(category, ignoreCase = true)
            }
        }
    Column {
        HomeHeader(navController)
        HeroSection(
            searchPhrase,
            onSearchPhraseChanged = { searchPhrase = it },
            category,
            onCategoryChanged = { category = it },
            selectedCategory,
            onSelectedCategoryChanged = { selectedCategory = it}
        )
        Categories(
            searchPhrase,
            onSearchPhraseChanged = { searchPhrase = it },
            category,
            onCategoryChanged = { category = it },
            selectedCategory,
            onSelectedCategoryChanged = { selectedCategory = it}
        )
        MenuItemsList(filteredMenuItems)
    }
}

@Composable
fun HomeHeader(navController: NavHostController) {
    TopAppBar(backgroundColor = Color.White,
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(.6f),
                alignment = Alignment.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = {

            })
            {
                Icon(Icons.Filled.Menu, contentDescription = "menu")
            }

        },
        actions = {

            IconButton(onClick = {
                 navController.navigate(Profile.route)
            }) {
                Icon(Icons.Filled.Person, contentDescription = "Profile")
            }
        }
    )
}


@Composable
fun HeroSection(
    searchPhrase: String,
    onSearchPhraseChanged: (String) -> Unit,
    category : String,
    onCategoryChanged : (String) -> Unit,
    selectedCategory : String,
    onSelectedCategoryChanged: (String) -> Unit
) {

    if (searchPhrase.isNotBlank()){
        onCategoryChanged("")
        onSelectedCategoryChanged ("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.primary1))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp),

            ) {
            Text(
                text = "Little Lemon",
                modifier = Modifier
                    .fillMaxWidth(),
                color = colorResource(id = R.color.primary2),
                style = displayTitle,
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.65f)
                    .padding(end = 8.dp)
                    .height(IntrinsicSize.Min)


            ) {
                Text(
                    text = "Chicago",
                    style = subTitle,
                    color = colorResource(id = R.color.Secondary3)
                )
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    style = leadText,
                    color = colorResource(id = R.color.Secondary3)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "food",
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(16.dp))

            )
        }

        Box(
            modifier = Modifier

                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                .background(colorResource(id = R.color.white), shape = RoundedCornerShape(16.dp))
                .border(1.dp, color = colorResource(id = R.color.white), RoundedCornerShape(16.dp)),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 16.dp)
                )

                TextField(
                    value = searchPhrase,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 4.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    placeholder = { Text("Find Your Favourite Dish") },
                    onValueChange =
                        onSearchPhraseChanged
                    ,
                    shape = RoundedCornerShape(16.dp)
                )
            }
        }


    }


}

@Composable
fun Categories(
    searchPhrase: String,
    onSearchPhraseChanged: (String) -> Unit,
    category: String,
    onCategoryChanged: (String) -> Unit ,
    selectedCategory : String,
    onSelectedCategoryChanged: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {

        Text(
            text = "order for delivery".uppercase(),
            style = (sectionTitle),
            modifier = Modifier.padding(bottom = 8.dp)

        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly


        ) {
            TextButton(
                onClick = {
                    onSearchPhraseChanged("")
                    onSelectedCategoryChanged ("Starters")
                    onCategoryChanged("Starters")
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (selectedCategory == "Starters") {
                        colorResource(id = R.color.Secondary1)
                    } else {
                        colorResource(id = R.color.Secondary3)
                    }
                ),
                modifier = Modifier.padding(end = 8.dp)


            ) {
                Text(text = "Starters", style = sectionCategories)
            }

            TextButton(
                onClick = {
                    onSearchPhraseChanged("")
                    onSelectedCategoryChanged  ("Mains")
                    onCategoryChanged("Mains")
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (selectedCategory == "Mains") {
                        colorResource(id = R.color.Secondary1)
                    } else {
                        colorResource(id = R.color.Secondary3)
                    }
                ),
                modifier = Modifier.padding(end = 8.dp)

            ) {
                Text(text = "Mains", style = sectionCategories)
            }

            TextButton(
                onClick = {
                    onSearchPhraseChanged("")
                    onSelectedCategoryChanged ("Deserts")
                    onCategoryChanged("Deserts")
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (selectedCategory == "Deserts") {
                        colorResource(id = R.color.Secondary1)
                    } else {
                        colorResource(id = R.color.Secondary3)
                    }
                ),
                modifier = Modifier.padding(end = 8.dp)

            ) {
                Text(text = "Desserts", style = sectionCategories)
            }

            TextButton(
                onClick = {
                    onSearchPhraseChanged("")
                    onSelectedCategoryChanged  ("Sides")
                    onCategoryChanged("Sides")
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (selectedCategory == "Sides") {
                        colorResource(id = R.color.Secondary1)
                    } else {
                        colorResource(id = R.color.Secondary3)
                    }
                ),

                ) {
                Text(
                    text = "Sides",
                    style = sectionCategories,

                    )
            }

        }
    }


}

@Composable
fun MenuItemsList(menuItems: List<MenuItem>) {
    LazyColumn {
        items(menuItems) { menuItem ->
            MenuItem(
                menuItem
            )
        }
    }
}




