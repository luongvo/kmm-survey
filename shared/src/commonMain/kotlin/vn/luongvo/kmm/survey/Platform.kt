package vn.luongvo.kmm.survey

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform