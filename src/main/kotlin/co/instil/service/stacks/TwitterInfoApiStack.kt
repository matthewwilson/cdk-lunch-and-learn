package co.instil.service.stacks

import co.instil.service.constructs.TwitterInfoApi
import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.Stack
import software.amazon.awscdk.core.StackProps

class TwitterInfoApiStack(parent: Construct, id: String, props: StackProps? = null): Stack(parent, id, props) {
    val api = TwitterInfoApi(this, "TwitterInfoService")
}
