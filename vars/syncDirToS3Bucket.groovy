def call(String awsRegion, String dirPath, String bucketName) {
  sh "aws --region ${awsRegion} s3 sync \"${dirPath}\" s3://\"${bucketName}\"/"
}
