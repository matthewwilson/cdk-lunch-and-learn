package co.instil.core.stacks

import software.amazon.awscdk.core.Construct
import software.amazon.awscdk.core.Stack
import software.amazon.awscdk.core.StackProps
import software.amazon.awscdk.services.ecr.CfnRepository

const val TWITTER_PROFILE_VIEWER_REPOSITORY_NAME = "twitter-profile-viewer"

/**
 * An example of the lowest level construct available in CDK - "CFN Resources"
 * These constructs represent all of the AWS resources that are available in AWS CloudFormation.
 * CFN Resources are generated from the AWS CloudFormation Resource Specification on a regular basis.
 * They are named CfnXyz, where Xyz represents the name of the resource. For example, s3.CfnBucket represents the AWS::S3::Bucket CFN Resource.
 * When you use CFN resources, you must explicitly configure all resource properties,
 * which requires a complete understanding of the details of the underlying resource model.
 *
 * Pros - Full control
 *
 * Cons - "requires a complete understanding of the details of the underlying resource model"
 *
 */
class EcrRepositoriesStack(parent: Construct, id: String, props: StackProps? = null): Stack(parent, id, props) {

    private val repositoryNames = listOf(TWITTER_PROFILE_VIEWER_REPOSITORY_NAME)

    /**
     * One advantage of CDK over Cloudformation YAML is the ability to
     * use logic (if statements, for-loops, etc) when defining your infrastructure
     */
    private val repositories = repositoryNames.map(::buildRepository)

    private fun buildRepository(repositoryName: String) = CfnRepository.Builder
            .create(this, "$repositoryName-repository")
            .repositoryName(repositoryName)
            .build()
}
