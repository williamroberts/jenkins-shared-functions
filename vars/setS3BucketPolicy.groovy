def call(String bucketName, String policyFilePath) {
  sh "aws s3api put-bucket-policy --bucket ${bucketName} --policy file://${policyFilePath}"
}
