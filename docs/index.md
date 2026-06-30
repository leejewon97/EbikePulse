# Ebike Pulse — 문서 지도

> 무엇을 보려면 어디를 열면 되는지 한눈에 정리한 인덱스입니다.

## 빠른 링크

| 읽고 싶은 것 | 문서 | 역할 |
|-------------|------|------|
| 앱이 뭔지, 왜 만드는지 | [product-spec.md](product-spec.md) | 제품 정의 (WHAT/WHY) |
| 지금까지 확정한 결정 | [product-spec.md §15](product-spec.md#15-확정-사항) | 제품·아키텍처 결정 |
| MVP 범위 / 이후 기능 | [product-spec.md §9·§16](product-spec.md#9-mvp-minimum-viable-product) | 로드맵 |
| UI/UX, 화면 구성 | [implementation-design.md §3](implementation-design.md#3-uiux-구체안) | 디자인 단일 소스 |
| README 화면 목업 (PNG·재생성) | [images/mockups/mockup-spec.md](images/mockups/mockup-spec.md) | README용 목업 SSOT |
| 권한·포그라운드 서비스 | [implementation-design.md §2](implementation-design.md#2-권한포그라운드-서비스-설계-minsdk-30-기준) | Android 정책·UX |
| 개발 시 유의/해야/하지 말 것 | [implementation-design.md §1](implementation-design.md#1-개발-가이드-android-중심) | 개발 가이드 |
| Gradle·라이브러리·빌드 | [technical-decisions.md](technical-decisions.md) | 기술 스택 단일 소스 |
| 지금 당장 할 구현 단계 | [project-bootstrap-plan.md](project-bootstrap-plan.md) | 실행 체크리스트 |

## 문서 역할 (중복 방지)

```
제품 스펙 (product-spec.md)
  → 무엇을 만들지, 기능·MVP·로드맵

구현 및 설계 (implementation-design.md)
  → UI/UX, 권한/FGS, Android 개발 가이드

기술 결정 (technical-decisions.md)
  → 어떤 라이브러리·버전·빌드 도구를 쓰는지

실행 계획 (project-bootstrap-plan.md)
  → 지금 몇 단계까지 했고, 다음에 뭘 구현할지
```

**같은 내용을 여러 문서에 쓰지 않습니다.** 확정 사항이 바뀌면:

- 제품·기능 결정 → `product-spec.md` §15
- UI/UX·권한·개발 원칙 → `implementation-design.md`
- 빌드·의존성 → `technical-decisions.md`
- 구현 진행 상황 → `project-bootstrap-plan.md`

## Cursor AI 규칙

- `.cursor/rules/karpathy-guidelines.mdc` — 불변 행동 가이드
- `.cursor/rules/EbikePulse.mdc` — 프로젝트 전용 규칙 (위 문서 경로 참조)

### Cursor AI 스킬 (명시 호출)

| 스킬 | 용도 |
|------|------|
| `/docs-update` | `docs/` SSOT에 맞게 문서만 수정 |
| `/commit-message` | 커밋 메시지 제안 (commit 안 함) |
| `/commit-run` | 커밋 실행 (명시 요청 시, Agent 모드) |
