def call() {
  def accountAlias = sh (
    script: "aws iam list-account-aliases --query 'AccountAliases[0]' --output text;",
    returnStdout: true
    ).trim()
  return accountAlias;
}
