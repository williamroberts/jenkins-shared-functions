def call(String awsRegion, String bucketName, String policyFilePath) {
  sh "aws --region ${awsRegion} s3api put-bucket-policy --bucket ${bucketName} --policy file://${policyFilePath}"
}
