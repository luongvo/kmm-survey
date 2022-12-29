package vn.luongvo.kmm.survey.android.extension

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.*
import vn.luongvo.kmm.survey.android.ui.theme.White50
import vn.luongvo.kmm.survey.android.ui.theme.White70

fun Modifier.placeholder(
    isLoading: Boolean,
    shapeValue: Dp = 100.dp // rounded corners shimmer item effect
) = placeholder(
    visible = isLoading,
    color = White50,
    shape = RoundedCornerShape(shapeValue),
    highlight = PlaceholderHighlight.shimmer(
        highlightColor = White70
    )
)
