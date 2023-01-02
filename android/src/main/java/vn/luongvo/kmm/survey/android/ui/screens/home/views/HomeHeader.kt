package vn.luongvo.kmm.survey.android.ui.screens.home.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import vn.luongvo.kmm.survey.android.R
import vn.luongvo.kmm.survey.android.extension.placeholder
import vn.luongvo.kmm.survey.android.ui.providers.LoadingParameterProvider
import vn.luongvo.kmm.survey.android.ui.screens.home.HomeUserAvatar
import vn.luongvo.kmm.survey.android.ui.screens.home.UserUiModel
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.dimensions
import vn.luongvo.kmm.survey.android.ui.theme.AppTheme.typography
import vn.luongvo.kmm.survey.android.ui.theme.ComposeTheme

@Composable
fun HomeHeader(
    isLoading: Boolean,
    dateTime: String,
    user: UserUiModel?,
    onUserAvatarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = dimensions.paddingMedium)
    ) {
        Text(
            text = dateTime.uppercase(),
            color = White,
            style = typography.subtitle1,
            modifier = Modifier.placeholder(isLoading = isLoading)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.home_today),
                color = White,
                style = typography.h4,
                modifier = Modifier.placeholder(isLoading = isLoading)
            )
            Button(
                onClick = onUserAvatarClick,
                modifier = Modifier.size(dimensions.avatarSize),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                elevation = null
            ) {
                AsyncImage(
                    model = user?.avatarUrl.orEmpty(),
                    contentDescription = HomeUserAvatar,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .placeholder(isLoading = isLoading)
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeHeaderPreview(
    @PreviewParameter(LoadingParameterProvider::class) isLoading: Boolean
) {
    ComposeTheme {
        HomeHeader(
            isLoading = isLoading,
            dateTime = "Monday, JUNE 15",
            user = UserUiModel(
                email = "luong@nimblehq.co",
                name = "Luong",
                avatarUrl = "https://secure.gravatar.com/avatar/8fae17b9d0c4cca18a9661bcdf650f23"
            ),
            onUserAvatarClick = {}
        )
    }
}
