def call(String awsRegion, String stackName, String stackStatus) {
  if (stackStatus == 'CREATE_IN_PROGRESS') {
    sh "aws --region ${awsRegion} cloudformation wait stack-create-complete --stack-name ${stackName}"
  } else if (stackStatus == 'UPDATE_IN_PROGRESS') {
    sh "aws --region ${awsRegion} cloudformation wait stack-update-complete --stack-name ${stackName}"
  } else if (stackStatus == 'DELETE_IN_PROGRESS') {
    sh "aws --region ${awsRegion} cloudformation wait stack-delete-complete --stack-name ${stackName}"
  } else if (stackStatus == 'ROLLBACK_IN_PROGRESS' || stackStatus == 'UPDATE_ROLLBACK_IN_PROGRESS' || stackStatus == 'UPDATE_ROLLBACK_COMPLETE_CLEANUP_IN_PROGRESS') {
    pollRollbackStack(awsRegion, stackName)
  } else {
    error('Unrecognised status of Cloudformation stack ' + stackStatus + ' for stack ' + stackName)
  }
}

def pollRollbackStack(String awsRegion, String stackName) {
  def stackStatus = sh (
    script: "aws --region ${awsRegion} cloudformation list-stacks --stack-status-filter ROLLBACK_COMPLETE UPDATE_ROLLBACK_COMPLETE --query 'StackSummaries[?StackName==`\"${stackName}\"`].StackStatus' --output text",
    returnStdout: true
    ).trim()

  if (stackStatus.endsWith('COMPLETE')) {
    return;
  } else {
    sleep(2000)
    pollRollbackStack(awsRegion, stackName)
  }
}
