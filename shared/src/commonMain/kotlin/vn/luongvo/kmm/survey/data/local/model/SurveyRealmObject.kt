package vn.luongvo.kmm.survey.data.local.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import vn.luongvo.kmm.survey.domain.model.Survey

class SurveyRealmObject : RealmObject {

    @PrimaryKey
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var coverImageUrl: String = ""

    constructor(
        id: String,
        title: String,
        description: String,
        coverImageUrl: String
    ) : this() {
        this.id = id
        this.title = title
        this.description = description
        this.coverImageUrl = coverImageUrl
    }

    constructor()
}

fun SurveyRealmObject.toSurvey() = Survey(
    id = id,
    title = title,
    description = description,
    coverImageUrl = coverImageUrl,
    questions = null
)
