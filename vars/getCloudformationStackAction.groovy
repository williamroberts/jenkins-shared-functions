def call(String awsRegion, String stackName) {
  def action = 'create';

  def stackExists = sh (
    script: "aws --region ${awsRegion} cloudformation list-stacks --query 'StackSummaries[?StackName==`\"${stackName}\"`&&StackStatus!=`DELETE_COMPLETE`].StackName' --output text",
    returnStdout: true
    ).trim()

  if (stackExists) {
    def stackStatus = sh (
      script: "aws --region ${awsRegion} cloudformation list-stacks --query 'StackSummaries[?StackName==`\"${stackName}\"`].StackStatus' --output text",
      returnStdout: true
      ).trim()

    if (stackStatus.contains('IN_PROGRESS')) {
      waitForCloudformationStackToComplete(awsRegion, stackName, stackStatus)
    }

    if (stackStatus.contains('ROLLBACK') || stackStatus.contains('DELETE_FAILED')) {
      deleteCloudformationStack(awsRegion, stackName)
      action = 'create'
    } else {
      action = 'update'
    }
  }
  return action
}
