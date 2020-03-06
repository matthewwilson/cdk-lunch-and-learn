package co.instil

import co.instil.core.stacks.EcrRepositoriesStack
import co.instil.core.stacks.EcsClusterStack
import co.instil.service.stacks.TwitterInfoApiStack
import co.instil.service.stacks.TwitterProfileViewerStack
import software.amazon.awscdk.core.App
import software.amazon.awscdk.core.Environment
import software.amazon.awscdk.core.StackProps

const val IRELAND_REGION = "eu-west-1"

/**
 * Main entry point
 * We create the App construct:
 *  - The App construct doesn't require any initialization arguments, because it's the only construct that can be used as a root for the construct tree.
 *
 *  An AWS CDK app goes through the following phases in its lifecycle.
 *      1. Construction (or Initialization)
 *      Your code instantiates all of the defined constructs and then links them together.
 *      Most of your app code is executed in this stage.
 *
 *      2. Preparation
 *      All constructs that have implemented the prepare method participate in a final round of modifications, to set up their final state.
 *      The preparation phase happens automatically. As a user, you don't see any feedback from this phase.
 *      It's rare to need to use the "prepare" hook, and generally not recommended.
 *
 *      3. Validation
 *      All constructs that have implemented the validate method can validate themselves to ensure that they're in a state that will correctly deploy.
 *      You will get notified of any validation failures that happen during this phase.
 *      Nice when you have a required field that would normally not be known until you deploy your cloudformation and wait for it to feedback.
 *
 *      4. Synthesis
 *      This is the final stage of the execution of your AWS CDK app.
 *      It's triggered by a call to app.synth(), and it traverses the construct tree and invokes the synthesize method on all constructs.
 *      Constructs that implement synthesize can participate in synthesis and emit deployment artifacts to the resulting cloud assembly.
 *      These constructs include AWS CloudFormation templates, AWS Lambda application bundles, file and Docker image assets, and other deployment artifacts.
 *      Cloud Assemblies describes the output of this phase. In most cases, you won't need to implement the synthesize method
 *
 *      5. Deployment
 *      In this phase, the AWS CDK CLI takes the deployment artifacts cloud assembly produced by the synthesis phase and deploys it to an AWS environment.
 *      It uploads assets to Amazon S3 and Amazon ECR, or wherever they need to go, and then starts an AWS CloudFormation deployment to deploy the application
 *      and create the resources.
 */
fun main() {
    val app = App()

    val environment = Environment.builder()
            .region(IRELAND_REGION)
            .build()

    val stackProps = StackProps.builder()
            .env(environment)
            .build()

    // Although we define our stacks here, each one can be deployed individually.

    EcrRepositoriesStack(app, "LunchAndLearnEcrRepositoriesStack", stackProps)

    val ecsClusterStack = EcsClusterStack(app, "LunchAndLearnEcsCluster", stackProps)

    val twitterInfoStack = TwitterInfoApiStack(app, "LunchAndLearnTwitterInfoStack", stackProps)

    TwitterProfileViewerStack(
        app,
        "LunchAndLearnTwitterProfileViewerStack",
        stackProps,
        ecsClusterStack.cluster,
        twitterInfoStack.api.url,
        getTwitterProfileViewerVersion(app)
    )

    app.synth()
}

/**
 * Sometimes we need information from the outside world.
 */
private fun getTwitterProfileViewerVersion(app: App) =
        app.node.tryGetContext("VIEWER_VERSION")?.toString() ?: "1.0.0"
