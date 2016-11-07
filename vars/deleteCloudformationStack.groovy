def call(String awsRegion, String stackName) {
  sh "aws --region ${awsRegion} cloudformation delete-stack --stack-name ${stackName}"
  sh "aws --region ${awsRegion} cloudformation wait stack-delete-complete --stack-name ${stackName}"
}
