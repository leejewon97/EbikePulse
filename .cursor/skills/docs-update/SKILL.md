---
name: docs-update
description: Updates EbikePulse docs/ per SSOT routing (product-spec, implementation-design, technical-decisions, project-bootstrap-plan). Use when the user asks to update, edit, or sync documentation, spec, design, tech decisions, bootstrap checklist, or mentions /docs-update.
disable-model-invocation: true
---

# docs-update

EbikePulse `docs/` 수정 전용 워크플로. 코드 변경은 이 스킬 범위 밖.

## 전제

- 문서 입구: `docs/index.md`
- 프로젝트 규칙: `.cursor/rules/EbikePulse.mdc` §2 (SSOT·중복 금지)
- **같은 내용을 여러 문서에 쓰지 않는다.** 한 변경 = 주 SSOT 1곳 + 필요 시 다른 문서에는 링크만.

## 워크플로

```
Task Progress:
- [ ] 1. 변경 유형 분류 (라우팅)
- [ ] 2. 수정 대상 1곳 선언 (사용자에게 먼저 알림)
- [ ] 3. 해당 섹션만 읽기
- [ ] 4. 최소 diff로 수정
- [ ] 5. 교차 검증 (index, §15/§16, 중복)
- [ ] 6. 완료 보고
```

### 1. 변경 유형 분류

| 유형 | 주 SSOT | 섹션 |
|------|---------|------|
| 제품 방향·MVP·확정 기능 | `docs/product-spec.md` | §15 |
| 아이디어·미래 기능·로드맵 | `docs/product-spec.md` | §16 |
| 화면 IA·레이아웃·UX | `docs/implementation-design.md` | §3 |
| 권한·FGS·MVI+권한 분리 | `docs/implementation-design.md` | §2, §2.4 |
| 개발 원칙 (추정치·오프라인·HR) | `docs/implementation-design.md` | §1 |
| 라이브러리·버전·Gradle·KSP | `docs/technical-decisions.md` | 해당 절 |
| bootstrap 단계 완료·검증 | `docs/project-bootstrap-plan.md` | 해당 단계 |
| 문서 지도·역할 설명 | `docs/index.md` | — |
| 제품 용어 정의 | `docs/product-spec.md` | §13 |

**애매할 때:** WHAT(뭘 만들지) → product-spec · HOW(Android에서 어떻게) → implementation-design · VERSION(무슨 버전) → technical-decisions · PROGRESS(몇 단계) → bootstrap-plan.

### 2. 수정 대상 선언

복수 문서가 필요해 보이면 **주 SSOT 1곳**만 먼저 수정. 연쇄 변경(예: §15 MVP 변경 → §3.1 IA)은 계획을 제시하고 사용자 확인 후 순서대로 (제품 → UI).

### 3–4. 읽기·쓰기

- 요청 범위 밖 섹션 리라이트 금지
- §15(확정) vs §16(향후) 엄격 분리 — 미확정 아이디어를 §15에 넣지 않음
- 버전 숫자·의존성 상세는 `technical-decisions.md`만; product-spec §15에는 요약+링크
- bootstrap 진행은 `project-bootstrap-plan.md` 체크박스·검증만; UI 상세는 implementation-design 링크
- YAML `last_updated`가 있으면 갱신 (`project-bootstrap-plan.md` 등)

### 5. 교차 검증

- [ ] `docs/index.md` 링크·앵커 깨짐 없음
- [ ] product-spec §3은 §15 링크 요약 패턴 유지
- [ ] 다른 문서에 동일 내용 풀텍스트 복사 없음

### 6. 완료 보고

1. 수정한 파일·섹션 (1곳 강조)
2. 의도적으로 건드리지 않은 문서
3. 후속 제안 (있을 때만)

## 금지

- 여러 문서에 동일 내용 복사
- 요청 없는 문서 전면 리라이트
- bootstrap 외 문서에 진행 체크리스트 난립
- 이 스킬로 앱 코드 수정

## 참조

- 시나리오·예시: [examples.md](examples.md)
- 라우팅 상세: [reference.md](reference.md)
