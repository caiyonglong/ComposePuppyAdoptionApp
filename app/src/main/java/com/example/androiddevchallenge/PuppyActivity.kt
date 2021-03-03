package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.data.Puppy
import com.example.androiddevchallenge.ui.data.puppies
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

class PuppyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val puppyIndex = intent.getIntExtra("puppyIndex", 0)
        setContent {
            MyTheme {
                PuppyDetailApp(puppies[puppyIndex]) {
                }
            }
        }
    }

    @Composable
    fun PuppyDetailApp(puppy: Puppy, onClick: (Puppy) -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = puppy.name)
                    },
                    navigationIcon = {
                        IconButton(onClick = { finish() }) {
                            Icon(Icons.Rounded.ArrowBack, contentDescription = "")
                        }
                    }
                )
            }
        ) { innerPadding ->
            PuppyInfo(puppy)
        }
    }

    @Composable
    fun PuppyInfo(it: Puppy) {
        val image = painterResource(it.imageResId)
        val imageModifier = Modifier
            .requiredHeight(100.dp)
            .requiredWidth(100.dp)
            .clip(shape = RoundedCornerShape(50.dp))
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image, contentDescription = "",
                modifier = imageModifier,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.requiredHeight(4.dp))
            Text(text = it.name, style = typography.body1)
            Spacer(modifier = Modifier.requiredHeight(4.dp))
            Text(text = it.des, style = typography.body2)
            Spacer(modifier = Modifier.requiredHeight(8.dp))
            Button(onClick = { toastAdoption("Adoption Successfully!") }) {
                Text(text = "Adoption")
            }
        }
    }

    fun toastAdoption(text: String) {
        com.google.android.material.snackbar.Snackbar.make(
            findViewById(android.R.id.content),
            text,
            com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
        ).show();
    }
}

