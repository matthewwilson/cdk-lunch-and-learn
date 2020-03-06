## Useful commands

 * `./gradlew clean build`      compile and run tests
 * `cdk ls`                     list all stacks in the app
 * `cdk synth`                  emits the synthesized CloudFormation template
 * `cdk deploy`                 deploy this stack to your default AWS account/region
 * `cdk diff`                   compare deployed stack with current state
 * `cdk docs`                   open CDK documentation

## Script

`cdk init --help`

`cdk synth LunchAndLearnEcrRepositoriesStack`

`cdk deploy LunchAndLearnEcrRepositoriesStack`

`./webapp/pushToContainerRepo.sh 1.0.0`

`cdk synth LunchAndLearnEcsCluster`

`cdk deploy LunchAndLearnEcsCluster`

`cdk deploy LunchAndLearnTwitterInfoStack`

`cdk synth LunchAndLearnTwitterProfileViewerStack`

`cdk deploy LunchAndLearnTwitterProfileViewerStack`

`cdk destroy LunchAndLearnEcrRepositoriesStack LunchAndLearnEcsCluster LunchAndLearnTwitterInfoStack LunchAndLearnTwitterProfileViewerStack`
