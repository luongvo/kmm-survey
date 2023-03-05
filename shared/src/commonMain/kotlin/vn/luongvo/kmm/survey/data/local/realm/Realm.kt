package vn.luongvo.kmm.survey.data.local.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import vn.luongvo.kmm.survey.data.local.model.SurveyRealmObject

val realm: Realm by lazy {
    val configuration = RealmConfiguration.create(schema = setOf(SurveyRealmObject::class))
    Realm.open(configuration)
}
