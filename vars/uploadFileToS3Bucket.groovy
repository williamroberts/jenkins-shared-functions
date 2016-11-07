def call(String awsRegion, String filePath, String bucketName) {
  sh "aws --region ${awsRegion} s3 cp \"${filePath}\" s3://\"${bucketName}\"/"
}
