package mingle.musemingle.service

import org.springframework.stereotype.Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.ByteArrayInputStream

@Service
class S3Service {

    private val bucketName = "musemingle-app-images"
    private val region = Region.AP_NORTHEAST_2
    private val s3 = S3Client.builder().region(region).build()

    fun uploadFile(directory: String, file: ByteArray, originalFilename: String): String {  // 파일 이름 추가
        val key = "$directory/$originalFilename"

        val byteArrayInputStream = ByteArrayInputStream(file)

        s3.putObject(
            PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build(),
            RequestBody.fromInputStream(byteArrayInputStream, file.size.toLong()) // ByteArray를 ByteArrayInputStream으로 변환
        )

        return s3.utilities().getUrl { builder -> builder.bucket(bucketName).key(key) }.toExternalForm()
    }

    fun deleteFile(url: String) {
        val key = url.replace("https://${bucketName}.s3.${region.id()}.amazonaws.com/", "")
        s3.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(key).build())
    }
}

/*package mingle.musemingle.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Service
class AmazonS3Service {

    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucketName: String

    private val s3 = S3Client.builder().build()

    fun uploadFile(keyName: String, file: ByteArray): String {
        val putRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(keyName)
            .build()

        s3.putObject(putRequest, RequestBody.fromBytes(file))

        return s3.utilities().getUrl { it.bucket(bucketName).key(keyName) }.toString()
    }

    fun deleteFile(keyName: String) {
        val deleteRequest = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(keyName)
            .build()

        s3.deleteObject(deleteRequest)
    }

    // update는 사실 upload와 동일합니다. 이미 존재하는 key로 업로드하면 해당 오브젝트를 덮어씁니다.
    fun updateFile(keyName: String, file: ByteArray): String {
        return uploadFile(keyName, file)
    }
}*/