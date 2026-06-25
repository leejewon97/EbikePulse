# EbikePulse — 기술 스택 및 빌드 결정 사항

> 프로젝트 뼈대(0단계)에서 확정한 Gradle·라이브러리·빌드 도구 결정을 기록한다.  
> 최종 갱신: 2026-06-25

관련 문서:
- 문서 지도: [`index.md`](index.md)
- 제품 스펙: [`product-spec.md`](product-spec.md)
- 실행 계획: [`project-bootstrap-plan.md`](project-bootstrap-plan.md)

---

## 1. 확정 사항 요약

| 항목 | 결정 |
|------|------|
| UI | Jetpack Compose |
| 패키지명 | `com.example.ebikepulse` (초기 유지) |
| minSdk / targetSdk / compileSdk | 30 / 37 / 37 |
| DI | Hilt **2.59.2** |
| 어노테이션 처리 | **KSP 2.3.9** (`kapt` 사용 안 함) |
| 상태 관리 패턴 | MVI (코드 적용은 2단계부터) |
| 내비게이션 | Navigation Compose **2.9.0** |
| 로컬 DB | Room **2.7.0** |
| 위치 | Play Services Location **21.3.0** |
| Health Connect | connect-client **1.1.0** (의존성만 핀, 기능은 추후) |
| 버전 관리 | Gradle Version Catalog (`gradle/libs.versions.toml`) |

---

## 2. 빌드 도구 베이스라인

현재 프로젝트 템플릿 기준(0단계 A 확인):

| 도구 | 버전 |
|------|------|
| Android Gradle Plugin (AGP) | 9.2.1 |
| Gradle Wrapper | 9.4.1 |
| Kotlin | 2.4.0 |
| Compose BOM | 2026.06.00 |
| Lifecycle | 2.11.0 |

---

## 3. 어노테이션 처리: KSP 채택 (kapt 제외)

### 결정

- Hilt compiler, Room compiler 모두 **`ksp(...)`** 로 처리한다.
- **`org.jetbrains.kotlin.kapt` 플러그인은 사용하지 않는다.**

### 이유

1. **AGP 9.x + built-in Kotlin** 환경에서 `kapt` 플러그인이 호환되지 않음  
   - 에러: `The 'org.jetbrains.kotlin.kapt' plugin is not compatible with built-in Kotlin support`
2. KSP는 Kotlin AST를 직접 분석해 **빌드가 더 빠르고**, Hilt/Room 모두 공식 지원
3. AGP 9 환경에서 권장되는 조합

### 적용 위치

- 플러그인: `app/build.gradle.kts` → `alias(libs.plugins.google.ksp)`
- 컴파일러:
  - `ksp(libs.hilt.compiler)`
  - `ksp(libs.androidx.room.compiler)`

---

## 4. Hilt 버전: 2.59.2

### 결정

- Hilt Gradle 플러그인 및 라이브러리: **2.59.2**

### 이유

- 초기 시도 버전 **2.57**은 AGP 9.2.1에서 플러그인 적용 실패  
  - 에러: `Android BaseExtension not found`
- AGP 9의 **새 DSL**(`newDsl`)과 호환되는 Hilt는 **2.59.2 이상** 필요
- `android.newDsl=false` 옵트아웃은 사용하지 않음 (임시 우회이며 2026년 중 제거 예정)

### 아직 미적용(3단계 예정)

의존성만 추가된 상태. 다음 단계에서 코드에 반영:
- `@HiltAndroidApp` Application 클래스
- `@AndroidEntryPoint` MainActivity
- `hiltViewModel()` 등 Compose 연동

---

## 5. 의존성 목록 (0단계 확정)

### MVI / Lifecycle (2단계에서 사용)

| 라이브러리 | 용도 |
|-----------|------|
| `lifecycle-viewmodel-ktx` | ViewModel (State/Event 처리) |
| `lifecycle-runtime-compose` | Compose lifecycle 연동 (`collectAsStateWithLifecycle` 등) |

### Navigation (1단계에서 사용)

| 라이브러리 | 용도 |
|-----------|------|
| `navigation-compose` | 하단 탭 IA (홈/챌린지/라이딩/기록/설정) |

### Hilt (3단계에서 코드 적용)

| 라이브러리 | 용도 |
|-----------|------|
| `hilt-android` | 런타임 DI |
| `hilt-compiler` | 코드 생성 (KSP) |
| `hilt-navigation-compose` | Compose ViewModel 주입 |

### Room (6단계에서 사용)

| 라이브러리 | 용도 |
|-----------|------|
| `room-runtime` | DB 엔진 |
| `room-ktx` | Coroutines/Flow |
| `room-compiler` | Entity/DAO 코드 생성 (KSP) |

### Location (5단계에서 사용)

| 라이브러리 | 용도 |
|-----------|------|
| `play-services-location` | Fused Location Provider (GPS 트래킹) |

### Health Connect (MVP 워치 연동, 추후)

| 라이브러리 | 용도 |
|-----------|------|
| `connect-client` | Galaxy Watch → Samsung Health → Health Connect 경로 |

0단계에서는 **버전 핀만** 추가. 기능 구현·권한 요청은 이후 단계.

---

## 6. 변경된 파일

| 파일 | 변경 내용 |
|------|----------|
| [`gradle/libs.versions.toml`](../gradle/libs.versions.toml) | 버전·라이브러리·플러그인 항목 추가 |
| [`build.gradle.kts`](../build.gradle.kts) | Hilt, KSP 플러그인 `apply false` 선언 |
| [`app/build.gradle.kts`](../app/build.gradle.kts) | 플러그인 적용 + dependencies 추가 |

---

## 7. 검증 완료 (0단계)

- [x] Gradle sync / `./gradlew :app:assembleDebug` **BUILD SUCCESSFUL**
- [x] KSP·Hilt 빌드 태스크 동작 확인 (`kspDebugKotlin`, `hiltSyncDebug`, `hiltAggregateDepsDebug`)
- [x] 앱 실행 유지 (Compose 템플릿 화면)

---

## 8. 하지 않은 것 (의도적)

- `kapt` 사용
- `android.newDsl=false` 옵트아웃
- Application / `@HiltAndroidApp` 코드 추가 (3단계)
- Room Entity/DAO 코드 추가 (6단계)
- Manifest 권한·FGS 선언 (4~5단계)
- Health Connect 런타임 연동 (MVP 워치 단계)

---

## 9. 향후 검토 가능 항목

- **패키지명 변경**: `com.example.ebikepulse` → 실제 배포용 도메인 기반 (스토어 출시 전)
- **KSP/Kotlin 버전 동기화**: Kotlin 업그레이드 시 [KSP 릴리스](https://github.com/google/ksp/releases) 호환 버전 확인
- **Health Connect 의존성**: 기능 구현 시점에 최신 안정 버전 재확인
