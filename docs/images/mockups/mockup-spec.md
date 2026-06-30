# Ebike Pulse — README 목업 스펙

> README용 디자인 목업 단일 소스. 제품 스펙 UI는 [`implementation-design.md` §3](../../implementation-design.md#3-uiux-구체안)을 따른다.

## 상태

**README 목업 완료** (2026-07). 아래 체크리스트·`docs/images/mockups/*.png`가 canonical 출력이다. §3 UI가 바뀔 때만 Compose를 수정하고 재렌더링한다.

| 구분 | 경로 | Git |
|------|------|-----|
| README PNG (canonical) | `docs/images/mockups/` (`home.png`, `ride-recording.png`, `ride-report.png`, `overview.png`) | 포함 |
| Compose 소스 | `app/.../ui/mockup/` | 포함 |
| Studio Preview | `MockupPreviews.kt` (Paparazzi와 동일 device spec) | 포함 |
| 지도 정적 PNG | `app/.../drawable-nodpi/mockup_map_bg_*.png` | 포함 |
| Paparazzi 스냅샷 | `app/src/test/snapshots/` | **미포함** (`.gitignore`) |

## 디바이스·출력

| 항목 | 값 |
|------|-----|
| 기기 | Google Pixel 스타일, 검은 베젤, 펀치홀 |
| 화면 비율 | **9:20** |
| 출력 해상도 | **1080×2400** (프레임 포함) |
| density | **480 dpi** (`Density.XXHIGH`) |
| 소스 | `app/.../ui/mockup/` Compose (`MockupPhoneFrame` + 화면 3종) |
| Paparazzi | `MockupScreenshotTest` — `1080×2400` px, `useDeviceResolution = true` (로컬 전용, git 미포함) |
| Studio Preview | `MockupPreviews.kt` — `spec:width=1080px,height=2400px,dpi=480` (Paparazzi와 동일 레이아웃) |
| README 반영 | [`docs/images/mockups/`](.) (canonical) · [`scripts/compose_mockups.py`](../../../scripts/compose_mockups.py) (`overview.png` 합성) |

## 재생성

**워크플로:** `MockupPreviews.kt` `@Preview`로 레이아웃 확인(Paparazzi와 동일) → 아래 명령으로 canonical PNG 갱신.

**전체 (Compose → docs PNG 갱신):**

```bash
./gradlew :app:recordPaparazziDebug
python scripts/compose_mockups.py
```

**overview만 다시 만들기** (`home.png` 등이 이미 있을 때):

```bash
python scripts/compose_mockups.py
```

Android Studio Preview 패널 **줌**은 PNG 파일 크기와 다를 수 있다. 레이아웃·잘림 비교는 Preview와 `docs/images/mockups/*.png`를 1:1로 보면 된다.

## 디자인 토큰 (3장 공통)

| 토큰 | 값 |
|------|-----|
| Primary | `#00897B` (teal) |
| 배경 | `#FFFFFF` (light) |
| 패턴 | Material 3 |
| 앱 타이틀 | `Ebike Pulse` — **좌측 정렬** (리포트만 중앙 제목 `라이딩 완료`) |
| KPI | 큰 숫자 + 라벨 (**원형 게이지 없음**) |
| 하단 탭 | 홈 · 챌린지 · 라이딩 · 기록 · 설정 (5개) |
| 지도 (홈·리포트) | `drawable-nodpi` 정적 PNG — `mockup_map_bg_home` **1696×840 (2:1)** · `mockup_map_bg_report` **1888×1200 (8:5)**. 경로 색 `#00897B`. 실제 앱 지도 동작은 `implementation-design.md` §3 참고 |

## 데이터 시트 (한 라이딩 시나리오)

**공통:** 코스 `한강 방향 코스` · 날짜 `2024.05.18 (토) 08:47` · **HR 연결됨** (3장 모두)

### 홈 (완료 후 요약)

| 필드 | 값 |
|------|-----|
| 오늘 실페달 km | **12.4** |
| 오늘 주행거리 | **18.2 km** |
| 오늘 라이딩 시간 | **1:05:32** |
| HR / 품질 | **HR 연결됨** · **품질 보통** |
| 마지막 라이딩 | 실페달 12.4 / 주행 18.2 km / 1:05:32 / 평균 **16.8 km/h** |
| 이번 주 | **68 / 100 km** (68%) |

### 라이딩 중 (같은 라이딩 · 중간 시점)

| 필드 | 값 |
|------|-----|
| 상태 | **Recording** · **00:42:15** |
| 실페달 km | **5.2** |
| 주행거리 | **7.8 km** |
| 현재 속도 | **22.4 km/h** |
| 최근 HR | **128 bpm** |
| 지도 | **없음** (B안) |
| 태그 | 페달만 · 스로틀 · 혼합 |
| 버튼 | 일시정지 · 종료 |

### 종료 리포트 (완료)

| 필드 | 값 |
|------|-----|
| 실페달 km | **12.4** |
| 자력 | **68%** |
| 주행거리 | **18.2 km** |
| 시간 | **1:05:32** |
| HR | **HR 연결됨** · 평균 **132 bpm** · **신뢰도 높음** |
| CTA | **배터리 V 또는 SOC% 기록** |
| 하단 탭 | 없음 |

## 9:20 레이아웃 지침

- UI가 status bar부터 하단 nav(또는 CTA)까지 **세로로 균등 배치**
- 카드·섹션 사이 **여백을 넓혀** 9:20 높이를 채움
- **잘림 금지** — 5탭, CTA, 지도 하단까지 전부 표시
- Pixel 베젤·펀치홀은 `MockupPhoneFrame`이 Compose로 그린다 (별도 AI/합성 없음)

## §3 체크리스트

### 홈 (§3.2)

- [x] Hero: 오늘 실페달 km (큰 숫자)
- [x] 보조: 주행거리 / 라이딩 시간
- [x] HR 연동 + 데이터 품질
- [x] 마지막 라이딩 카드 (지도 썸네일)
- [x] 이번 주 진행률
- [x] 하단 5탭

### 라이딩 중 (§3.3)

- [x] Recording/Paused + 시간
- [x] 실페달 km (큰 숫자) / 주행거리 / 현재 속도
- [x] HR (최근 구간)
- [x] 일시정지 / 종료 + 수동 태그 3종
- [x] 지도 없음
- [x] 하단 5탭

### 종료 리포트 (§3.4)

- [x] 상단 지도 (코스 overview)
- [x] KPI: 실페달 km / 자력 % / 주행거리 / 시간
- [x] 배터리 기록 CTA
- [x] HR·신뢰도 안내
