package vn.luongvo.kmm.survey.android.ui.screens.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.screens.home.HomeUserAvatar
import vn.luongvo.kmm.survey.android.ui.theme.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography

@Composable
fun Drawer(
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(Nero90)
            .padding(top = dimensions.paddingSmall)
            .padding(horizontal = dimensions.paddingMedium)
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Mai",
                color = Color.White,
                style = typography.h4
            )
            AsyncImage(
                model = "https://secure.gravatar.com/avatar/8fae17b9d0c4cca18a9661bcdf650f23",
                contentDescription = HomeUserAvatar,
                modifier = Modifier
                    .size(dimensions.avatarSize)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(White20)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onLogoutClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Transparent
            ),
            contentPadding = PaddingValues(vertical = dimensions.paddingSmall, horizontal = 0.dp),
            modifier = modifier.fillMaxWidth(),
            elevation = null
        ) {
            Text(
                text = stringResource(id = R.string.home_logout),
                color = White50,
                style = typography.body1.copy(fontSize = 20.sp),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    ComposeTheme {
        Drawer(
            onLogoutClick = {}
        )
    }
}
