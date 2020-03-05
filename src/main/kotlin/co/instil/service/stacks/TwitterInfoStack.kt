package co.instil.service.stacks

import co.instil.service.constructs.TwitterInfoLambdaService
import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.Stack
import software.amazon.awscdk.core.StackProps

class TwitterInfoStack(parent: Construct, id: String, props: StackProps? = null): Stack(parent, id, props) {
    init {
        TwitterInfoLambdaService(this, "TwitterInfoService")
    }
}
