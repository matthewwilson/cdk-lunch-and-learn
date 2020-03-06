package co.instil.service.stacks

import co.instil.core.stacks.TWITTER_PROFILE_VIEWER_REPOSITORY_NAME
import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.Stack
import software.amazon.awscdk.core.StackProps
import software.amazon.awscdk.services.ecr.Repository.fromRepositoryName
import software.amazon.awscdk.services.ecs.Cluster
import software.amazon.awscdk.services.ecs.ContainerImage.fromEcrRepository
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions

/**
 * An example of the highest-level construct called patterns.
 * These constructs are designed to help you complete common tasks in AWS, often involving multiple kinds of resources.
 * In this example we create an AWS Fargate container cluster employing an Application Load Balancer (ALB).
 */
class TwitterProfileViewerStack(
    parent: Construct,
    id: String,
    props: StackProps,
    cluster: Cluster,
    apiUrl: String,
    imageTag: String
): Stack(parent, id, props) {

    private val twitterProfileViewerService = ApplicationLoadBalancedFargateService.Builder
            .create(this, "TwitterProfileViewerService")
            .cluster(cluster)
            .cpu(512)
            .desiredCount(2)
            .taskImageOptions(buildTaskOptions(apiUrl, imageTag))
            .memoryLimitMiB(2048)
            .publicLoadBalancer(true)
            .build();

    /**
     * A nice way to pass contextual information to the docker image is to use environment variables
     */
    private fun buildTaskOptions(apiUrl: String, imageTag: String) = ApplicationLoadBalancedTaskImageOptions.builder()
                .image(getImage(imageTag))
                .environment(mapOf(
                    "LAMBDA_URL" to apiUrl
                ))
                .containerPort(80)
                .build()
    /**
     * Because we created our ECR repository using the low level CFN constructs,
     * we have to do this nastiness to get a reference to it. :sad-kelvin:
     */
    private fun getImage(imageTag: String) = fromEcrRepository(
        fromRepositoryName(this, "TwitterProfileViewerRepository", TWITTER_PROFILE_VIEWER_REPOSITORY_NAME),
        imageTag
    )
}
