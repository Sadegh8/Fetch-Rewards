package com.sample.app.fetchrewardscodingexercise.presentation.list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListItemView(
    modifier: Modifier = Modifier,
    id: String,
    listId: String,
    name: String,
) {

    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name)

            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.End) {
                Text(text = listId)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = id)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ListItemPreview() {
    ListItemView(id = "684", listId = "1", name = "Item 684")

}
