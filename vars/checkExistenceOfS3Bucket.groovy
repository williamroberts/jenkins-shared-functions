def call(String bucketName) {
  def bucketExists = sh (
    script: "aws s3api list-buckets --query 'Buckets[?Name==`\"${bucketName}\"`].Name' --output text",
    returnStdout: true
    ).trim()
  return bucketExists == bucketName
}
