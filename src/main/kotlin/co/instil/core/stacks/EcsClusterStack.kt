package co.instil.core.stacks

import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.Stack
import software.amazon.awscdk.core.StackProps
import software.amazon.awscdk.services.ec2.Vpc
import software.amazon.awscdk.services.ecs.Cluster

private const val NUMBER_OF_AVAILABILITY_ZONES = 2

/**
 * An example of the next level of constructs that represent AWS resources, but with a higher-level, intent-based API
 * They provide the same functionality, but handle much of the details, boilerplate, and glue logic required by CFN constructs
 * The stack should contain one default instance, a private subnet and a public subnet for the two Availability Zones, and a security group.
 *
 * Pros -  AWS constructs offer convenient defaults and reduce the need to know all the details about the AWS resources they represent,
 *         while providing convenience methods that make it simpler to work with the resource
 *
 * demo cdk synth LunchAndLearnEcsCluster to see how much YAML this generates!
 */
class EcsClusterStack(parent: Construct, id: String, props: StackProps? = null): Stack(parent, id, props) {
    val vpc = Vpc.Builder.create(this, "TwitterInfoVpc")
            .maxAzs(NUMBER_OF_AVAILABILITY_ZONES)
            .build()

    val cluster = Cluster.Builder.create(this, "TwitterInfoCluster")
            .vpc(vpc)
            .build()
}
