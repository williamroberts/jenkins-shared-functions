def call(String bucketName) {
  if (checkExistenceOfS3Bucket(bucketName)) {
    println bucketName + " S3 bucket already exists. Proceeding..."
  } else {
    println bucketName + " S3 bucket not found. Creating..."
    sh "aws s3 mb s3://\"${bucketName}\";"
    println bucketName + " S3 bucket created successfully. Proceeding..."
  }
}
