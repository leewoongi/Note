plugins {
    id("note.feature")
}

android {
    namespace = "com.woongi.detail"
}

dependencies {
    implementation(project(":presenter:navigator"))
}
