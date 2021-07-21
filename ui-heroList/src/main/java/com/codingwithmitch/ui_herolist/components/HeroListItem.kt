package com.codingwithmitch.ui_herolist.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.codingwithmitch.dotainfo.hero_domain.Hero
import com.codingwithmitch.ui_herolist.R
import kotlin.math.round

@ExperimentalAnimationApi
@Composable
fun HeroListItem(
    hero: Hero,
    onSelectHero: (Int) -> Unit,
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(MaterialTheme.colors.surface)
            .clickable {
                onSelectHero(hero.id)
            }
        ,
        elevation = 8.dp
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ,
            verticalAlignment = Alignment.CenterVertically
        ){
            val painter = rememberImagePainter(
                hero.img,
                builder = {
                    placeholder(if(isSystemInDarkTheme()) R.drawable.black_background else R.drawable.white_background)
                    error(R.drawable.error_image)
                    fadeIn()
                }
            )
            Image(
                modifier = Modifier
                    .width(120.dp)
                    .height(70.dp)
                ,
                painter = painter,
                contentDescription = hero.localizedName,
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(.8f) // fill 80% of remaining width
                    .padding(start = 12.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                    ,
                    text = hero.localizedName,
                    style = MaterialTheme.typography.h4,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.primaryAttribute.uiValue,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Fill the rest of the width (100% - 80% = 20%)
                    .padding(end = 12.dp)
                ,
                horizontalAlignment = Alignment.End
            ) {
                val proWR: Int = remember{round(hero.proWins.toDouble() / hero.proPick.toDouble() * 100).toInt()}
                Text(
                    text = "${proWR}%",
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}
















