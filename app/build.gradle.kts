plugins {
    id("note.application")
}

dependencies {
    implementation(project(":presenter:home"))
    implementation(project(":presenter:detail"))
    implementation(project(":presenter:navigator"))
}
