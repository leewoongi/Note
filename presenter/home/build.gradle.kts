plugins {
    id("note.feature")
}

android {
    namespace = "com.woongi.home"
}

dependencies {
    implementation(project(":presenter:detail"))
}
