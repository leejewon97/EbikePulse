---
name: commit-run
description: Creates git commits for EbikePulse per project rules. Stages relevant files, commits with HEREDOC messages, never pushes unless asked. Use when the user says "커밋하자", "커밋해줘", or /commit-run.
disable-model-invocation: true
---

# commit-run

**명시적 커밋 요청 시만** 실행. 메시지만 원하면 `/commit-message`로 안내.

## 전제

- 원칙 SSOT: `.cursor/rules/EbikePulse.mdc` §5
- 메시지 형식: [commit-message/SKILL.md](../commit-message/SKILL.md) · [examples.md](../commit-message/examples.md)

## 안전 규칙

- git config 변경 금지
- `push --force`, `reset --hard` 등 파괴 명령은 사용자 명시 요청 없이 금지
- `--no-verify` 등 hook 스킵 금지 (사용자 명시 시만)
- amend: 사용자 요청 + HEAD가 이번 대화 커밋 + 미 push일 때만
- hook 실패 시 amend 금지 → 수정 후 **새 커밋**
- **push는 사용자 요청 시만**
- .env·credentials 스테이징·커밋 금지

## 워크플로

```
Task Progress:
- [ ] 1. git status · git diff · git log (병렬)
- [ ] 2. 커밋 메시지 확정 (§5 · 직전 commit-message 재사용 규칙)
- [ ] 3. 분리 여부·스테이징 순서 확정 (§5 미니 체크리스트, 사용자 “~먼저”“분리해서” 우선)
- [ ] 4. 관련 파일만 스테이징 (분리 시 한 축씩)
- [ ] 5. HEREDOC으로 commit
- [ ] 6. git status로 성공 확인
```

### 직전 commit-message 제안 재사용

- 같은 대화에서 `/commit-message` 제안이 있고, 사용자가 **그대로·제안대로·N번 커밋** 등으로 승인했으면 **제목·본문을 verbatim으로 사용** (paraphrase 금지)
- 커밋 전에 커밋별 **사용할 메시지를 한 번 나열**해 확인
- 제안 이후 working tree가 바뀌었으면 `status`/`diff` 재확인 후, 필요 시 `/commit-message` 재요청 여부를 사용자에게 확인
- 승인된 제안이 없으면 [commit-message/SKILL.md](../commit-message/SKILL.md) 형식으로 작성

### 분리 vs 한 번에 (EbikePulse.mdc §5)

- **분리 검토:** 타입이 다름 (`chore`+`docs`), revert/리뷰 단위를 나누고 싶을 때
- **한 번에:** 한 작업의 필수 결과물이 함께 묶일 때 (본문에 축 나열)
- **분리 순서:** 기반(Gradle·DB) → 기능·UI → 문서·규칙
- 제목에 서로 다른 타입 섞지 않음
- **분리 시:** 한 축씩 스테이징 → commit → status 확인 후 다음 축

### 커밋 명령

```bash
git commit -m "$(cat <<'EOF'
제목

본문(선택)

EOF
)"
```

### 완료 보고

- 생성한 커밋 해시·제목
- 남은 unstaged/uncommitted
- push는 하지 않았음 (요청 시만)

## 금지

- 메시지 제안만 하고 commit 안 하는 경우 → `/commit-message`
- 관련 없는 파일 스테이징
