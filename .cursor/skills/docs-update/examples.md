# docs-update — 시나리오 예시

## UI 변경

**요청:** 라이딩 화면 일시정지 버튼을 하단 중앙으로

- **수정:** `implementation-design.md` §3.3만
- **미수정:** product-spec, technical-decisions, bootstrap-plan

## 기술 버전 변경

**요청:** Room 2.8.0으로 올림

- **수정:** `technical-decisions.md` (버전 표·의존성 목록)
- **미수정:** product-spec §15에 버전 숫자 추가하지 않음 (이미 Room 사용 확정이면 링크 유지)

## bootstrap 진행

**요청:** 1단계 구현 완료 반영

- **수정:** `project-bootstrap-plan.md` §1 체크박스 `[x]`
- **미수정:** implementation-design에 "1단계 완료" 문장 추가 (진행은 bootstrap SSOT)

## 로드맵 아이디어

**요청:** 나중에 커뮤니티 기능 넣고 싶음

- **수정:** `product-spec.md` §16만
- **금지:** §15 확정 사항에 기재

## MVP 범위 변경 (연쇄)

**요청:** MVP에서 챌린지 탭 제외

1. `product-spec.md` §9 MVP + §15
2. 사용자 확인 후 `implementation-design.md` §3.1 IA 조정
3. 한 번에 여러 파일 수정 시 순서·이유를 보고에 명시

## 완료 보고 템플릿

```markdown
## docs-update 완료

**수정:** `docs/implementation-design.md` §3.3

**미수정:** product-spec, technical-decisions, project-bootstrap-plan, index.md

**후속:** bootstrap 1단계 체크 반영이 필요하면 `/docs-update bootstrap 1단계 완료 체크` 요청
```

## 호출 예시

```
/docs-update 라이딩 화면 레이아웃 변경을 implementation-design에 반영해줘
/docs-update Room 버전을 technical-decisions에 반영
/docs-update bootstrap 2단계 완료로 체크리스트 갱신
/docs-update §16에 워치 대시보드 아이디어 추가
```
