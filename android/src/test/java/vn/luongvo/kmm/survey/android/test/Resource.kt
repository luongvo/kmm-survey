package vn.luongvo.kmm.survey.android.test

import java.io.*

data class Resource(
    val path: String
) {
    fun readText(): String {
        val reader = getReader(path)
        val builder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            builder.append(line)
        }
        reader.close()
        return builder.toString()
    }

    @Throws(IOException::class)
    private fun getReader(filePath: String): BufferedReader {
        val resource = javaClass.classLoader!!.getResource(filePath)
        return BufferedReader(InputStreamReader(resource.openStream()))
    }
}
