## 🎨 MuseMingle - The Hub of Artists

https://youtu.be/6kKQsWZ15y0

---

## 📱 프로젝트 개요

**MuseMingle**은 예술가들이 자신의 작품을 공유하고 갤러리에서 전시회를 관리할 수 있는 모바일 플랫폼 앱입니다. 이 프로젝트는 제가 직접 아이디어를 기획하고, Swift와 Kotlin을 공부하여 직접 만든 졸업 작품입니다.

- Github Link : https://github.com/jamiebhpark/musemingle_front
- Github Link : https://github.com/jamiebhpark/musemingle_back
- Youtube_Demo_Link : https://youtu.be/6kKQsWZ15y0

---

## 🔍 핵심 기능 및 개발내용

### iOS 프론트엔드

- **사용자 인증 (소셜 로그인)**
    
    Apple, Google, Facebook 계정을 이용한 로그인 기능 구현
    
    JWT 토큰을 이용하여 사용자 인증 관리
    
- **작품 관리**
    
    작품 등록, 수정, 삭제 기능 구현
    
    이미지 파일 업로드 기능을 AWS S3와 연동하여 구현
    
- **전시회 관리**
    
    전시회 정보 생성, 수정, 삭제 기능 구현
    
    전시회 포스터 이미지를 AWS S3를 통해 관리
    
- **프로필 관리**
    
    사용자 프로필 편집 및 자신이 좋아하는 작품을 관리하는 아카이브 기능 제공
    

### 백엔드 (Kotlin, Spring)

- REST API 서버 구축
- 사용자 인증 처리 (JWT 토큰 사용)
- 작품 및 전시회 관리 API 제공
- AWS S3를 통한 이미지 업로드/삭제 처리

---

### 🔧 사용한 주요 기술

- **프론트엔드:** Swift, SwiftUI, Combine
- **백엔드:** Kotlin, Spring Boot, MySQL, AWS S3
- **보안:** JWT, OAuth2 (Apple, Google, Facebook)

---

### ⚙️ 기술적 도전 및 해결 과정

### 🔹 AWS S3 연동 문제 해결 (파일 업로드/삭제)

- **문제 상황**
    - 처음으로 AWS S3와의 이미지 업로드 및 삭제 기능을 연동하면서, 권한 설정 및 보안 이슈로 인해 파일 관리가 정상적으로 되지 않는 문제가 발생했습니다.
- **해결 방법**
    - AWS IAM 정책을 재정의하고, 정확한 S3 접근 권한을 설정했습니다.
    - Kotlin Spring Boot의 AWS SDK 공식 문서를 철저히 참고하여, 보안성이 강화된 안정적인 업로드 및 삭제 기능을 구현했습니다.
    - Postman과 Unit Test를 통해 반복적인 테스트를 수행하여 최종적으로 문제를 완벽히 해결했습니다.

---

### 💡 프로젝트를 통해 배운 점

- Swift와 Kotlin이라는 언어를 처음 접하고, 모바일 앱과 백엔드 개발을 함께 경험했습니다.
- 개발 중 문제 발생 시 공식 문서, Stack Overflow, YouTube 등 다양한 자료를 찾아 주도적으로 문제를 해결했습니다.
- 앱 개발의 전체 흐름을 경험하며 사용자 입장에서 UI/UX를 설계하는 방법을 배우게 되었습니다.
- 처음부터 완벽하게 구현하지 못한 기능도 있었지만, 이 과정에서 실제 개발 사이클을 간접적으로 경험했습니다.

---

### 🚀 프로젝트 결과

- 교수님과 학우들로부터 사용자 친화적인 UI 설계와 안정적인 앱 구현에 대해 좋은 평가를 받았습니다.
- 교수님에게 최우수 작품 평가를 받았습니다. 특히 기획의 탄탄함에 최고 평가하셨습니다.
- 실제 앱스토어 출시에는 이르지 못했지만, 앱 개발 전반을 경험하며 탄탄한 기본기를 쌓는 계기가 되었습니다.

---

## 💻 코드 스니펫 (핵심 요약)

### **1. Apple 로그인 구현 (Swift)**

**역할**

- 사용자가 앱에서 Apple 계정을 이용해 간편히 로그인할 수 있도록 구현했습니다.

```swift
import AuthenticationServices

class AppleSignInManager: NSObject, ASAuthorizationControllerDelegate {

    func signIn() {
        let request = ASAuthorizationAppleIDProvider().createRequest()
        request.requestedScopes = [.email]

        let controller = ASAuthorizationController(authorizationRequests: [request])
        controller.delegate = self
        controller.performRequests()
    }

    func authorizationController(controller: ASAuthorizationController, didCompleteWithAuthorization authorization: ASAuthorization) {
        guard let credential = authorization.credential as? ASAuthorizationAppleIDCredential,
              let tokenData = credential.identityToken,
              let tokenString = String(data: tokenData, encoding: .utf8) else { return }

        // 받은 Apple 토큰을 서버에 전송하여 로그인 인증 진행
        sendTokenToServer(token: tokenString)
    }

    private func sendTokenToServer(token: String) {
        // JWT 토큰을 서버로 보내 사용자 인증 진행 (REST API 통신)
    }
}

```

- Apple ID 로그인 과정의 흐름과 `ASAuthorizationController`의 역할 이해
- 토큰이 서버로 전송되어 사용자를 인증하는 기본 흐름 이해

---

### **2. AWS S3 파일 업로드 및 삭제 (Kotlin)**

**역할**

- 앱 내 이미지 파일을 서버에서 관리할 수 있도록 AWS S3 연동 기능을 구현했습니다.

```kotlin
@Service
class S3Service {

    private val bucketName = "musemingle-app-images"
    private val s3 = S3Client.builder().region(Region.AP_NORTHEAST_2).build()

    fun uploadFile(directory: String, file: ByteArray, originalFilename: String): String {
        val key = "$directory/$originalFilename"
        s3.putObject(
            PutObjectRequest.builder().bucket(bucketName).key(key).build(),
            RequestBody.fromBytes(file)
        )
        return s3.utilities().getUrl { builder -> builder.bucket(bucketName).key(key) }.toExternalForm()
    }

    fun deleteFile(url: String) {
        val key = url.substringAfter("amazonaws.com/")
        s3.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(key).build())
    }
}

```

- AWS S3를 사용한 이유(간편한 파일 관리, 확장성)와 업로드, 삭제 기능이 어떻게 작동하는지 개괄적인 이해
- 파일이 어떻게 S3에 저장되고 관리되는지 개념적인 이해

---

### **3. 전시회 관리 기능 구현 (Kotlin)**

**역할**

- 앱의 주요 기능인 전시회를 관리(등록, 수정, 삭제)하는 API 서버 기능을 개발했습니다.

```kotlin
@Service
class ExhibitionService(
    private val exhibitionRepository: ExhibitionRepository,
    private val s3Service: S3Service
) {
    fun save(exhibition: Exhibition, posterImage: MultipartFile?): Exhibition {
        posterImage?.let {
            val imageUrl = s3Service.uploadFile("exhibitions", it.bytes, it.originalFilename!!)
            exhibition.posterImage = imageUrl
        }
        return exhibitionRepository.save(exhibition)
    }

    fun deleteById(id: Int) {
        val exhibition = exhibitionRepository.findById(id).orElseThrow()
        exhibition.posterImage?.let { s3Service.deleteFile(it) }
        exhibitionRepository.deleteById(id)
    }
}

```

- 전시회 정보와 이미지를 관리하는 기본 로직에 대한 이해
- 사용자가 이미지를 업로드하면 AWS S3를 통해 저장, 전시회를 삭제할 때 이미지도 함께 삭제

---
