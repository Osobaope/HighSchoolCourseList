package com.example.highschoolcourselist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.highschoolcourselist.data.DataSource
import com.example.highschoolcourselist.model.Topic
import com.example.highschoolcourselist.ui.theme.HighSchoolCourseListTheme
import com.example.highschoolcourselist.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HighSchoolCourseListTheme {
                TopicApp()
            }
        }
    }
}

@Composable
fun TopicApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .padding(
            start = 8.dp,
            end = 8.dp,
        )
    ) {
        TopicList(
            topicList = DataSource.topics
        )

    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicList(
    modifier: Modifier = Modifier,
    topicList: List<Topic>
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),

    ) {
        items(topicList) { topic ->
            TopicCard(modifier = modifier, iconResourceId = R.drawable.ic_grain,
                topic = topic)

        }
    }

}

@Composable
fun TopicCard(modifier: Modifier,
              iconResourceId: Int,
              topic: Topic ) {
    val imageResourcePainter = painterResource(id = topic.topicImageId)
    val stringResource = stringResource(id = topic.topicNameId)
    val iconResourcePainter = painterResource(id = iconResourceId)
    val relevantTopicResource = topic.associatedTopicAmount.toString()
    Card(modifier = modifier.fillMaxWidth()) {
        Row {
            Image(
                modifier = modifier
                    .width(68.dp)
                    .height(68.dp),
                painter = imageResourcePainter,
                contentDescription = "Topic Image for $stringResource" )
            Column (modifier = modifier.padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
            )){
                Text(
                    modifier = modifier.padding(
                        bottom = 8.dp
                    ),
                    text = stringResource,
                    style = Typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
                Row (
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Image(
                        painter = iconResourcePainter, 
                        contentDescription = "Icon Image for related topics " +
                                "count")
                    Text(
                        modifier = modifier.padding(start = 8.dp),
                        text = relevantTopicResource,
                        style = Typography.labelMedium
                    )
                }
                
            }
        }

    }
}

@Preview
@Composable
fun TopicCardPreview() {
    TopicCard(modifier = Modifier,
        topic = Topic(topicImageId = R.drawable.film,
        topicNameId = R.string.film,
        associatedTopicAmount = 321),
        iconResourceId = R.drawable.ic_grain
    )
}

@Preview
@Composable
fun TopicListPreview() {
    TopicList(topicList = DataSource.topics)
}
