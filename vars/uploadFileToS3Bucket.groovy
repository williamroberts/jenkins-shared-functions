def call(String filePath, String bucketName) {
  sh "aws s3 cp \"${filePath}\" s3://\"${bucketName}\"/"
}
