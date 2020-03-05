package co.instil.service.constructs

import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.Duration.seconds
import software.amazon.awscdk.services.apigateway.LambdaIntegration
import software.amazon.awscdk.services.apigateway.RestApi
import software.amazon.awscdk.services.lambda.Code.fromAsset
import software.amazon.awscdk.services.lambda.Function
import software.amazon.awscdk.services.lambda.Runtime.NODEJS_10_X

class TwitterInfoLambdaService(parent: Construct, id: String): Construct(parent, id) {
    init {
        val twitterInfoLambda = Function.Builder.create(this, "TwitterInfoLambda")
                .runtime(NODEJS_10_X)
                .code(fromAsset("lambda"))
                .handler("twitterInfoLambda.main")
                .timeout(seconds(10))
                .build()

        val api = RestApi.Builder.create(this, "Twitter-Info-API")
                .restApiName("Twitter Handle Service")
                .description("This service returns twitter info for a given username")
                .build()

        val getTwitterInfoIntegration = LambdaIntegration.Builder.create(twitterInfoLambda).build()

        api.root.addMethod("GET", getTwitterInfoIntegration)
    }
}
