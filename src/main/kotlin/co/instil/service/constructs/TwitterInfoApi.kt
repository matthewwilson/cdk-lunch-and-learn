package co.instil.service.constructs

import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.Duration.seconds
import software.amazon.awscdk.services.apigateway.LambdaIntegration
import software.amazon.awscdk.services.apigateway.RestApi
import software.amazon.awscdk.services.lambda.Code.fromAsset
import software.amazon.awscdk.services.lambda.Function
import software.amazon.awscdk.services.lambda.Runtime.NODEJS_10_X

/**
 * A construct can represent a single resource, such as an S3 bucket,
 * But in this example it can represent a higher-level component consisting of multiple AWS CDK resources.
 */
class TwitterInfoApi(parent: Construct, id: String): Construct(parent, id) {
    val url: String

    init {
        val twitterInfoLambda = Function.Builder.create(this, "TwitterInfoLambda")
                .runtime(NODEJS_10_X)
                .code(fromAsset("lambda")) //from asset method is a nice way to upload the lambda code. Requires cdk bootstrap
                .handler("twitterInfoLambda.main")
                .timeout(seconds(10))
                .build()

        val api = RestApi.Builder.create(this, "Twitter-Info-API")
                .restApiName("Twitter Info API")
                .description("This service returns twitter info for a given username")
                .build()

        val getTwitterInfoIntegration = LambdaIntegration.Builder.create(twitterInfoLambda).build()

        api.root.addMethod("GET", getTwitterInfoIntegration)

        url = api.url
    }
}
