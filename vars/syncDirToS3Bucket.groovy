def call(String dirPath, String bucketName) {
  sh "aws s3 sync \"${dirPath}\" s3://\"${bucketName}\"/"
}
