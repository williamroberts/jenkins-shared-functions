def call(String stackName) {
  if (stackBeingProcessedStatus == 'CREATE_IN_PROGRESS') {
    sh "aws cloudformation wait stack-create-complete --stack-name ${stackName}"
  } else (stackBeingProcessedStatus == 'UPDATE_IN_PROGRESS') {
    sh "aws cloudformation wait stack-update-complete --stack-name ${stackName}"
  } else (stackBeingProcessedStatus == 'ROLLBACK_IN_PROGRESS') {
    pollRollbackStack(stackName)
  } else {
    error('Unrecognised status of Cloudformation stack ' + stackBeingProcessedStatus ' for stack ' + stackName)
  }
}

def pollRollbackStack(String stackName) {
  sh "while true; do $(aws cloudformation list-stacks --stack-status-filter ROLLBACK_COMPLETE UPDATE_ROLLBACK_COMPLETE --query 'StackSummaries[?StackName==`\"${stackName}\"`].StackStatus' --output text); if [[ $__STACK_STATUS == *'COMPLETE' ]]; then break; fi; sleep 2; done;"
}
