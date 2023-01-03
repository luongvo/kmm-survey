package vn.luongvo.kmm.survey.android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import timber.log.Timber
import vn.luongvo.kmm.survey.android.BuildConfig
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.common.RtlModalDrawer
import vn.luongvo.kmm.survey.android.ui.navigation.AppNavigation
import vn.luongvo.kmm.survey.android.ui.screens.home.HomeUserAvatar
import vn.luongvo.kmm.survey.android.ui.screens.home.UserUiModel
import vn.luongvo.kmm.survey.android.ui.theme.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography

@Composable
fun MainScreen(
    initialDrawerValue: DrawerValue = DrawerValue.Closed
) {
    val drawerState = rememberDrawerState(initialDrawerValue)
    val scope = rememberCoroutineScope()
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }
    var user by remember { mutableStateOf<UserUiModel?>(null) }

    RtlModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Drawer(
                user = user,
                onLogoutClick = {
                    // TODO https://github.com/luongvo/kmm-survey/issues/19
                    Timber.d("onLogoutClick")
                }
            )
        }
    ) {
        AppNavigation(
            onDrawerUiStateChange = { user = it },
            onOpenDrawer = { openDrawer() }
        )
    }
}

@Composable
private fun Drawer(
    user: UserUiModel?,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(Nero90)
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
                contentDescription = HomeUserAvatar,
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
            text = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
            color = White50,
            style = typography.subtitle1.copy(fontSize = 11.sp)
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    ComposeTheme {
        MainScreen(
            initialDrawerValue = DrawerValue.Open
        )
    }
}

@Preview
@Composable
fun DrawerPreview() {
    ComposeTheme {
        Drawer(
            user = UserUiModel(
                email = "luong@nimblehq.co",
                name = "Luong",
                avatarUrl = "https://secure.gravatar.com/avatar/8fae17b9d0c4cca18a9661bcdf650f23"
            ),
            onLogoutClick = {}
        )
    }
}
