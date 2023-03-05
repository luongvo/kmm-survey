package vn.luongvo.kmm.survey.data.local.datasource

import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import vn.luongvo.kmm.survey.data.local.model.SurveyRealmObject

interface SurveyLocalDataSource {

    fun saveSurveys(surveys: List<SurveyRealmObject>)

    fun getSurveys(): List<SurveyRealmObject>
}

class SurveyLocalDataSourceImpl(private val realm: Realm) : SurveyLocalDataSource {

    override fun saveSurveys(surveys: List<SurveyRealmObject>) {
        realm.writeBlocking {
            surveys.forEach {
                copyToRealm(it, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    override fun getSurveys(): List<SurveyRealmObject> {
        return realm.query<SurveyRealmObject>().find()
    }
}
