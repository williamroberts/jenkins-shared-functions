def call(String bucketName) {
  return "\$(aws iam list-account-aliases --query 'AccountAliases[0]' --output text)";
}
