### 캡스톤 디자인 프로젝트

https://youtu.be/6kKQsWZ15y0

### 🎨 MuseMingle - 예술가와 갤러리를 위한 종합 플랫폼

## 📱 프로젝트 개요

**MuseMingle**은 예술가와 갤러리 간의 상호 작용을 지원하는 플랫폼으로, 예술 작품의 업로드, 전시회 관리, 그리고 다양한 사용자 인증 방법을 제공합니다. 이 프로젝트는 iOS 모바일 애플리케이션과 백엔드 서버로 구성되어 있으며, 사용자는 자신의 예술 작품을 공유하고 전시회를 관리하며, 다양한 인증 방법을 통해 안전하게 로그인할 수 있습니다.

---

## 🎯 주요 기능

### iOS 프론트엔드

- **사용자 인증 및 로그인**
    - OAuth2 기반의 소셜 로그인 (Apple, Google, Facebook)
    - JWT 기반의 토큰 인증 시스템
    - 사용자의 로그인 상태를 `Combine`을 사용해 실시간으로 관리
- **예술 작품 관리**
    - 예술 작품의 등록, 수정, 삭제 기능 제공
    - 작품의 이미지 업로드 (AWS S3 연동)
    - 상세 페이지에서 작품의 세부 정보 확인 가능
- **전시회 관리**
    - 전시회 생성, 수정, 삭제 기능 제공
    - 전시회에 포스터 이미지 업로드 (AWS S3 연동)
    - 전시회 상태 (예정, 진행 중, 종료) 관리
- **프로필 및 아카이브**
    - 사용자의 프로필 업데이트, 아카이브 관리 기능
    - 작품을 북마크하여 아카이브에 추가

### 백엔드

- **사용자 관리**
    - 사용자 등록, 로그인 및 인증 처리
    - 사용자 정보 수정 및 계정 삭제 기능 제공
- **예술 작품 관리**
    - 예술 작품의 등록, 수정, 삭제 API 제공
    - 예술 작품의 이미지 업로드 및 삭제 기능 (AWS S3 연동)
- **전시회 관리**
    - 전시회의 등록, 수정, 삭제 API 제공
    - 전시회 포스터 이미지 관리 (AWS S3 연동)
- **파일 관리**
    - AWS S3를 통한 안전한 파일 업로드 및 삭제 기능
    - 사용자 프로필 이미지 및 학위 증명서 관리

---

## 🔧 기술 스택

- **프론트엔드**: SwiftUI, Combine, Swift (iOS)
- **백엔드**: Kotlin, Spring Boot, JPA, AWS S3
- **데이터베이스**: MySQL (JPA 연동)
- **보안**: JWT (JSON Web Tokens) 기반 인증, OAuth2 (Google, Facebook, Apple)
- **배포**: AWS (S3, EC2)

---

## 💼 개발 과정에서의 역할 및 기여

### iOS 프론트엔드 개발

- **주요 기능**: 로그인/회원가입, 전시회 및 작품 관리, 검색, 사용자 프로필 및 아카이브 관리
- **상태 관리**: `Combine`을 사용하여 사용자 인증 상태와 UI 간의 상태 관리를 구현
- **UI/UX 설계**: SwiftUI를 이용하여 다양한 인터페이스를 설계하고 사용자 경험을 최적화
- **Apple Sign-In 구현**: `AuthenticationServices`를 이용하여 Apple 계정으로 로그인 기능을 구현

### 백엔드 개발

- **주요 기능**: 사용자 관리, 작품 및 전시회 관리, 파일 업로드 및 삭제 (AWS S3 연동)
- **보안**: JWT를 활용하여 사용자 인증을 강화하고 OAuth2를 통한 소셜 로그인 기능 구현
- **데이터베이스 연동**: Spring Data JPA를 사용하여 MySQL과의 데이터 연동을 관리
- **AWS S3 연동**: 파일 업로드/다운로드 및 삭제 기능을 S3와 연동하여 구현

---

## 🚀 프로젝트의 성과 및 의미

- **간편한 인증 시스템**: 다양한 OAuth2 인증 방식을 제공함으로써 사용자 편의성을 극대화했습니다.
- **강화된 보안**: JWT 기반의 토큰 인증으로 사용자의 데이터 보호를 강화했습니다.
- **유연한 파일 관리**: AWS S3와의 연동을 통해 파일을 안전하고 효율적으로 관리할 수 있었습니다.
- **모바일 UX 최적화**: SwiftUI를 활용하여 직관적이고 반응성이 뛰어난 UI를 구현했습니다.

---

### 🚀 링크 및 자료

- **데모 비디오:** [YouTube](https://www.youtube.com/watch?v=6kKQsWZ15y0)
- **GitHub 저장소:**
    - [프론트엔드 (Swift)](https://github.com/jamiebhpark/musemingle_front/tree/main/musemingle)
    - [백엔드 (Kotlin)](https://github.com/jamiebhpark/musemingle_back/tree/main/src/main/kotlin/mingle/musemingle)
    - [데이터베이스 및 AWS 구성](https://github.com/jamiebhpark/musemingle_db_aws)
- **문서:**
    - [프로젝트 요약 (Notion)](https://www.notion.so/Project-Summary-7bb8bb2f43c3495a833778264063f7d1?pvs=21)
    - [디자인 및 와이어프레임 (Figma)](https://www.notion.so/Wireframe-Flowchart-42248fc6f9be46b2ad3c17751eaf1a1c?pvs=21)

---

## 💻 코드 스니펫

### 1. Apple Sign-In 연동 (iOS)

```swift
import AuthenticationServices
import SwiftKeychainWrapper

class AppleSignInManager: NSObject, ASAuthorizationControllerDelegate, ObservableObject {
    var loginStatusViewModel: LoginStatusViewModel

    init(loginStatusViewModel: LoginStatusViewModel) {
        self.loginStatusViewModel = loginStatusViewModel
    }

    func signIn() {
        let request = ASAuthorizationAppleIDProvider().createRequest()
        request.requestedScopes = [.fullName, .email]

        let controller = ASAuthorizationController(authorizationRequests: [request])
        controller.delegate = self
        controller.performRequests()
    }

    func authorizationController(controller: ASAuthorizationController, didCompleteWithAuthorization authorization: ASAuthorization) {
        if let appleIDCredential = authorization.credential as? ASAuthorizationAppleIDCredential {
            if let identityToken = appleIDCredential.identityToken,
               let identityTokenString = String(data: identityToken, encoding: .utf8) {
                sendTokenToServer(token: identityTokenString)
            }
        }
    }

    private func sendTokenToServer(token: String) {
        let url = URL(string: "http://3.34.187.225/auth/apple")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")

        do {
            request.httpBody = try JSONSerialization.data(withJSONObject: ["id_token": token])
        } catch let error {
            print("Error encoding JSON: \(error)")
            return
        }

        URLSession.shared.dataTask(with: request) { (data, response, error) in
            if let error = error {
                print("Error occurred while validating the token: \(error)")
                return
            }
            // Handle response
        }.resume()
    }
}

```

**설명**:
이 코드 스니펫은 **Apple Sign-In** 기능을 구현한 부분입니다. `AppleSignInManager` 클래스는 Apple의 인증 서비스를 사용하여 사용자를 로그인시키는 역할을 합니다.

- `signIn()` 메서드는 Apple ID로 로그인을 시도하는 과정에서 사용되며, `ASAuthorizationAppleIDProvider`를 사용하여 인증 요청을 만듭니다.
- `authorizationController` 메서드는 사용자가 성공적으로 인증을 마쳤을 때 호출되며, 받은 토큰을 서버에 전달하는 역할을 합니다.
- `sendTokenToServer` 메서드는 인증 후 받은 토큰을 서버로 보내는 과정을 처리합니다. 이 토큰은 서버에서 검증되어, 사용자가 실제로 Apple에서 인증한 사용자인지를 확인하는 데 사용됩니다.

이 코드 스니펫은 MuseMingle 앱에서 사용자의 Apple 계정을 통해 안전하고 간편하게 로그인할 수 있게 하는 핵심 기능을 담당합니다.

### 2. 파일 업로드 및 삭제 (AWS S3 연동)

```kotlin
@Service
class S3Service {

    private val bucketName = "musemingle-app-images"
    private val region = Region.AP_NORTHEAST_2
    private val s3 = S3Client.builder().region(region).build()

    fun uploadFile(directory: String, file: ByteArray, originalFilename: String): String {
        val key = "$directory/$originalFilename"
        s3.putObject(
            PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build(),
            RequestBody.fromBytes(file)
        )
        return s3.utilities().getUrl { builder -> builder.bucket(bucketName).key(key) }.toExternalForm()
    }

    fun deleteFile(url: String) {
        val key = url.replace("https://${bucketName}.s3.${region.id()}.amazonaws.com/", "")
        s3.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(key).build())
    }
}

```

**설명**:
이 코드 스니펫은 **AWS S3**를 이용해 파일을 업로드하고 삭제하는 서비스입니다.

- `uploadFile` 메서드는 파일을 S3 버킷에 업로드하고, 업로드된 파일의 URL을 반환합니다. 파일은 지정된 디렉토리와 원본 파일 이름을 기반으로 S3에 저장됩니다.
- `deleteFile` 메서드는 S3에 저장된 파일을 URL을 통해 삭제하는 역할을 합니다.

이 코드 스니펫은 MuseMingle에서 사용자 프로필 이미지, 예술 작품 이미지, 전시회 포스터 이미지 등을 S3에 저장하고 관리하는 기능을 담당합니다. 이를 통해 애플리케이션에서 이미지 파일을 안전하게 저장하고, 필요시 삭제할 수 있습니다.

### 3. 전시회 관리 (백엔드)

```kotlin
@Service
class ExhibitionService(
    private val exhibitionRepository: ExhibitionRepository,
    private val s3Service: S3Service
) {
    @Transactional(readOnly = true)
    fun findById(id: Int): Exhibition? = exhibitionRepository.findById(id).orElse(null)

    @Transactional
    fun save(exhibition: Exhibition, posterImage: MultipartFile?): Exhibition {
        posterImage?.let {
            val imageUrl = s3Service.uploadFile("exhibitions", it.bytes, it.originalFilename!!)
            exhibition.posterImage = imageUrl
        }
        return exhibitionRepository.save(exhibition)
    }

    @Transactional
    fun deleteById(id: Int) {
        val exhibition = findById(id) ?: throw IllegalArgumentException("No Exhibition with given ID found")
        exhibition.posterImage?.let {
            s3Service.deleteFile(it)
        }
        exhibitionRepository.deleteById(id)
    }

    @Transactional
    fun update(id: Int, updatedExhibition: Exhibition, posterImage: MultipartFile?): Exhibition {
        val exhibition = exhibitionRepository.findById(id)
            .orElseThrow { IllegalArgumentException("No Exhibition with given ID found") }

        exhibition.posterImage?.let {
            s3Service.deleteFile(it)
        }

        posterImage?.let {
            val imageUrl = s3Service.uploadFile("exhibitions", it.bytes, it.originalFilename!!)
            exhibition.posterImage = imageUrl
        }

        return exhibitionRepository.save(exhibition)
    }
}

```

**설명**:
이 코드 스니펫은 **전시회 관리** 기능을 담당하는 서비스입니다.

- `save` 메서드는 전시회를 저장하며, 포스터 이미지가 있을 경우 AWS S3에 업로드하여 전시회의 포스터 URL을 저장합니다.
- `deleteById` 메서드는 전시회를 삭제하는데, 삭제하기 전에 S3에 저장된 포스터 이미지를 삭제합니다.
- `update` 메서드는 전시회 정보를 업데이트하며, 업데이트 시 이전 포스터 이미지를 삭제하고 새로운 이미지를 업로드하여 갱신합니다.
