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
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.ui.common.RtlModalDrawer
import vn.luongvo.kmm.survey.android.ui.navigation.AppNavigation
import vn.luongvo.kmm.survey.android.ui.screens.home.*
import vn.luongvo.kmm.survey.android.ui.theme.*
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.colors
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MainScreen(
    viewModel: HomeViewModel = getViewModel(),
    initialDrawerValue: DrawerValue = DrawerValue.Closed
) {
    val drawerState = rememberDrawerState(initialDrawerValue)
    val scope = rememberCoroutineScope()
    val user by viewModel.user.collectAsStateWithLifecycle()
    val appVersion by viewModel.appVersion.collectAsStateWithLifecycle()

    RtlModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            Drawer(
                user = user,
                appVersion = appVersion,
                onLogoutClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    viewModel.logOut()
                }
            )
        }
    ) {
        AppNavigation(
            sharedHomeViewModel = viewModel,
            onOpenDrawer = {
                scope.launch {
                    drawerState.open()
                }
            }
        )
    }
}

@Composable
private fun Drawer(
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
            text = appVersion,
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
            appVersion = "v1.0.0",
            onLogoutClick = {}
        )
    }
}
