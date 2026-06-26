---
name: commit-message
description: Suggests EbikePulse git commit messages from status/diff/log without committing. Use when the user asks for commit message suggestions, "커밋 메시지 작성해줘", or /commit-message.
disable-model-invocation: true
---

# commit-message

커밋 메시지 **제안만**. `git commit` · `git push` 실행 금지.

## 전제

- 원칙 SSOT: `.cursor/rules/EbikePulse.mdc` §5
- 메시지 예시: [examples.md](examples.md)

## 워크플로

```
Task Progress:
- [ ] 1. git status · git diff · git log (병렬)
- [ ] 2. 분리 여부 판단 (§5 미니 체크리스트)
- [ ] 3. 커밋별 메시지 작성
- [ ] 4. 제안만 출력
```

### 분리 vs 한 번에 (EbikePulse.mdc §5)

- **분리 검토:** 타입이 다름 (`chore`+`docs`), revert/리뷰 단위를 나누고 싶을 때
- **한 번에:** 한 작업의 필수 결과물이 함께 묶일 때 (본문에 축 나열)
- **분리 순서:** 기반(Gradle·DB) → 기능·UI → 문서·규칙
- 제목에 서로 다른 타입 섞지 않음

### 메시지 형식

- **제목:** `타입: 한 줄 요약` — feat/fix/docs/chore/refactor/test/style/add/remove, 마침표 없음, **한글**
- **본문:** (선택) 무엇을·왜, 1~5문장

### 분리 시 출력

커밋이 여러 개면 **커밋별로** 제목·본문·포함 파일 목록을 나열.

### 완료 보고

- 제안 메시지
- 분리 권장 여부와 이유
- 실행하려면 Agent 모드에서 `/commit-run`

## 금지

- `git commit` · `git push`
- .env·credentials 스테이징 제안
