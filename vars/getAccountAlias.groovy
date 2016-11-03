def call(String bucketName) {
  return sh "aws iam list-account-aliases --query 'AccountAliases[0]' --output text";
}
