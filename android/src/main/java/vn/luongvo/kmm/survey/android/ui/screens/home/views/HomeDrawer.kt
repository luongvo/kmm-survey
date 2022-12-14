package vn.luongvo.kmm.survey.android.ui.screens.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.preview.HomeParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.home.UserUiModel
import vn.luongvo.kmm.survey.android.ui.theme.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.colors
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography

@Composable
fun HomeDrawer(
    user: UserUiModel?,
    appVersion: String,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(colors.drawerBackground)
            .padding(top = dimensions.paddingSmall)
            .padding(bottom = dimensions.paddingMedium)
            .padding(horizontal = dimensions.paddingMedium)
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = user?.name.orEmpty(),
                color = White,
                style = typography.h4
            )
            AsyncImage(
                model = user?.avatarUrl.orEmpty(),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensions.avatarSize)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = White20)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.home_logout),
            color = White50,
            style = typography.body1.copy(fontSize = 20.sp),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onLogoutClick() }
                .padding(vertical = dimensions.paddingSmall)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = appVersion,
            color = White50,
            style = typography.subtitle1.copy(fontSize = 11.sp)
        )
    }
}

@Preview
@Composable
fun HomeDrawerPreview(
    @PreviewParameter(HomeParameterProvider::class, limit = 1) params: HomeParameterProvider.Params
) {
    with(params) {
        ComposeTheme {
            HomeDrawer(
                user = user,
                appVersion = appVersion,
                onLogoutClick = {}
            )
        }
    }
}
