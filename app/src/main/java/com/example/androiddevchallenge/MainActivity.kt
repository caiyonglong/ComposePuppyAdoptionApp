/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.data.Puppy
import com.example.androiddevchallenge.ui.data.puppies
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp() {
                    val intent = Intent(this, PuppyActivity::class.java)
                    intent.putExtra("puppyIndex", puppies.indexOf(it))
                    startActivity(intent)
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(onClick: ((Puppy) -> Unit)? = null) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "PuppyAdoption")
                }
            )
        }
    ) { innerPadding ->
        PuppyList(puppies = puppies, onClick)
    }
}

@Composable
fun PuppyList(puppies: List<Puppy>, onClick: ((Puppy) -> Unit)? = null) {
    LazyColumn() {
        items(puppies) { puppy ->
            PuppyRow(puppy, onClick)
        }
    }
}

@Composable
fun PuppyRow(it: Puppy, onClick: ((Puppy) -> Unit)? = null) {
    val image = painterResource(it.imageResId)
    val imageModifier = Modifier
        .requiredHeight(180.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(4.dp))
    Column(
        Modifier
            .clickable(
                onClick = {
                    onClick?.invoke(it)
                })
            .padding(8.dp)
    ) {
        Image(
            painter = image, contentDescription = "",
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.requiredHeight(4.dp))
        Text(text = it.name, style = typography.body1)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
