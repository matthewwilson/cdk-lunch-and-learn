package co.instil

import co.instil.service.stacks.TwitterInfoStack
import software.amazon.awscdk.core.App
import software.amazon.awscdk.core.Environment
import software.amazon.awscdk.core.StackProps

const val IRELAND_REGION = "eu-west-1"

fun main() {
    val app = App()

    val environment = Environment.builder()
            .region(IRELAND_REGION)
            .build()

    val stackProps = StackProps.builder()
            .env(environment)
            .build()

    TwitterInfoStack(app, "CdkLunchNLearnStack", stackProps)

    app.synth()
}
