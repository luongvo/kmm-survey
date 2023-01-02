package vn.luongvo.kmm.survey.android.ui.screens.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme
import vn.luongvo.kmm.survey.android.ui.theme.Nero90

@Composable
fun Drawer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(Nero90)
            .padding(all = dimensions.paddingMedium)
    ) {
    }
}

@Preview
@Composable
fun DrawerPreview() {
    ComposeTheme {
        Drawer()
    }
}
