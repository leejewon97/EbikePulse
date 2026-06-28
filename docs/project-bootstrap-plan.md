---
title: "EbikePulse 프로젝트 뼈대 생성 실행 계획"
last_updated: "2026-06-29"
source_of_truth:
  product_spec: "docs/product-spec.md"
  implementation_design: "docs/implementation-design.md"
  technical_decisions: "docs/technical-decisions.md"
fixed_decisions:
  ui: "Jetpack Compose 유지"
  min_sdk: 30
  target_compile_sdk: 37
  package_name: "com.example.ebikepulse (초기 유지)"
  di: "Hilt 2.59.2"
  annotation_processing: "KSP 2.3.9 (kapt 사용 안 함)"
  state_pattern: "MVI"
  map_policy: "B안(라이딩 중 지도 없음, 종료 후 지도)"
  navigation: "Navigation Compose 2.9.0"
  local_db: "Room 2.7.0"
  location: "Play Services Location 21.3.0"
---

**문서 지도**: [index.md](index.md) · 제품 스펙: [product-spec.md](product-spec.md) · 빌드 결정: [technical-decisions.md](technical-decisions.md)

## 목적(성공 기준)

- 앱이 **Compose 기반**으로 실행되고, 기본 탭 구조(홈/챌린지/라이딩/기록/설정) 골격이 있다.
- DI는 **Hilt**로 주입이 동작한다.
- 상태 관리는 **MVI(State/Event/Effect)** 규율로 “권한 요청/서비스 시작” 같은 사이드이펙트를 UI에서 분리한다.
- 초기 목표는 **MVP 1회 사용자 여정의 뼈대**를 완성하는 것:
  - 홈 → 라이딩 시작 → (기록 중) → 종료 → 종료 리포트(종료 후 지도 자리) → 기록 상세 재진입

## 진행 원칙(중요)

- “새 프로젝트” 기준이지만, 현재 저장소에는 Compose 템플릿이 있으므로 **불필요한 리팩터링 없이 필요한 것만 최소 추가**한다.
- HR/Health Connect는 **선택 기능**이므로 초기 뼈대는 **HR 없이도 완주**되게 한다.
- 지도는 **라이딩 중 의존 금지(B안)**. 종료 후 화면에서만 폴리라인/타일을 다룬다.
- PAS/스로틀은 “정답 판별”이 아니라 **추정치** 전제(단정 표현/설계 금지).

## 단계별 실행 계획(체크리스트)

아래는 “저(에이전트)에게 단계별로 실행을 시킬 수 있는 단위”로 쪼갠 체크리스트다.
각 단계는 **완료 조건(검증)** 까지 포함한다.

### 0) 템플릿 상태 확인 + 의존성 목록 확정 ✅

- [x] 현재 Compose 템플릿에서 빌드/실행이 되는지 확인
- [x] “뼈대에 필요한 의존성”만 목록으로 고정
  - Hilt 2.59.2, KSP 2.3.9, Lifecycle/ViewModel, Navigation(Compose), Room, Location
  - Health Connect (의존성 핀만, 기능은 추후)
- [x] **검증**: `./gradlew :app:assembleDebug` BUILD SUCCESSFUL

상세 결정 사항: [`technical-decisions.md`](technical-decisions.md)

### 1) 앱 엔트리/내비게이션 골격(탭 IA) ✅

- [x] 스펙 IA(홈/챌린지/라이딩/기록/설정)대로 **라우트/탭만** 먼저 만든다 — 상세: [implementation-design §3.1](implementation-design.md#31-하단-탭ia-제안)
- [x] `MainActivity`는 루트 Composable(`EbikePulseApp`)만 호스팅하도록 단순화

- **검증**: 5개 탭 전환이 되고, 각 화면에 placeholder UI가 보임

### 2) MVI 최소 규율 도입(한 화면부터) ✅

- [x] 우선 **라이딩 화면 1곳에만** MVI 틀 적용
  - State: `Idle/Recording/Paused/Stopping` 등 최소
  - Event: `OnStartClicked/OnPauseClicked/OnStopClicked` 등
  - Effect: `RequestLocationPermission/StartRideService/StopRideService` 등

- **검증**: 버튼 클릭 → ViewModel에서 State 변화 → UI 반영 (`Recording` 시 가짜 타이머 증가, Effect는 Logcat만)

### 3) Hilt 세팅(전역 1회)

- [ ] `Application` 클래스 추가 후 `@HiltAndroidApp` 적용
- [ ] `MainActivity`에 `@AndroidEntryPoint` 적용
- [ ] 최소 1개 의존성을 DI로 주입해 “주입이 동작한다”를 확정

- **검증**: 런타임 크래시 없이 DI 주입 성공

### 4) 권한 플로우 뼈대([implementation-design.md §2](implementation-design.md#2-권한포그라운드-서비스-설계-minsdk-30-기준) 준수)

- [ ] 위치 권한(정확), 알림(Android 13+), 배터리 최적화 예외 안내를 **Effect로만 트리거**하도록 구성
- [ ] “라이딩 시작” 시 권한이 없으면 안내/요청 플로우로 유도

- **검증**: 권한이 없을 때도 앱 플로우가 끊기지 않고, 안내/요청 상태로 진입

### 5) 포그라운드 서비스 골격(라이딩 엔진 껍데기)

- [ ] 라이딩 시작/종료 이벤트에 따라 FGS 시작/중지 흐름 연결
- [ ] 서비스는 “라이딩 기록” 단일 책임 유지(UI/저장/추정 엔진 분리 가능 구조)

- **검증**: 시작 → 알림 표시(서비스 Running) → 종료 → 알림 제거

### 6) Room DB 골격(로컬 DB = 단일 소스)

- [ ] 최소 엔티티/DAO/DB 구성(초안)
  - `RideEntity`(라이딩 메타)
  - `SegmentEntity`(위치/거리 샘플 또는 구간)
  - `TagEntity`(수동 태그 이벤트)
  - `BatterySettingEntity`, `BatteryRecordEntity`
- [ ] 우선 “라이딩 1건 생성/조회”만 되는 수준으로 연결

- **검증**: 앱 재실행 후에도 저장된 더미 Ride가 목록에 보임

### 7) MVP 여정 스켈레톤 연결(끝까지 한 번 완주)

- [ ] 홈: 오늘 요약/최근 라이딩 카드 자리
- [ ] 라이딩: 시작/일시정지/종료 버튼 + 실페달 km/거리/시간 표시 자리(값은 임시)
- [ ] 종료 리포트: 요약 + 종료 후 지도 자리 + 배터리 입력 CTA 자리

- **검증**: “시작→종료→리포트→기록 상세(재진입)” 네비게이션이 끝까지 이어짐

## 예상 변경 지점(파일)

- `app/build.gradle.kts`: 의존성(Hilt/Nav/Room/Location) 추가 — **0단계 완료**; `lifecycle-viewmodel-compose` — **2단계 완료**
- `gradle/libs.versions.toml`, `build.gradle.kts`: 버전 카탈로그·플러그인 — **0단계 완료**
- `app/src/main/java/com/example/ebikepulse/navigation/`, `EbikePulseApp.kt`: 탭 IA — **1단계 완료**
- `app/src/main/java/com/example/ebikepulse/ui/ride/`: `RideContract.kt`, `RideViewModel.kt`, `RideScreen.kt` — **2단계 완료**
- `app/src/main/AndroidManifest.xml`: 권한/서비스 선언(implementation-design §2.1 기반)
- `app/src/main/java/com/example/ebikepulse/MainActivity.kt`: 루트 앱/네비게이션 호스팅
- (신규) `.../EbikePulseApplication.kt`, `.../di/*`, `.../data/*`

## 다음 요청 예시(이 문서 기반으로 단계별 실행)

- “`docs/project-bootstrap-plan.md`의 **3단계만** 구현해줘”
- “3단계(Hilt 세팅) 진행해줘. 주입 확인까지.”
- “4단계(권한 플로우) 진행해줘.”

