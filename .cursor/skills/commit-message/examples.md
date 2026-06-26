# commit-message — 예시

## 단일 커밋

```
docs: docs-update 스킬 추가

EbikePulse docs/ SSOT 라우팅 워크플로를 스킬로 분리해 문서 수정 시 중복 기재를 줄인다.
```

```
feat: 하단 탭 5개 내비게이션 골격 추가

홈/챌린지/라이딩/기록/설정 placeholder와 NavHost를 연결한다.
```

## 분리 제안 예시

변경: `app/build.gradle.kts` + `docs/technical-decisions.md` + `.cursor/skills/`

**커밋 1 (chore)**
```
chore: Room 의존성 버전 2.7.0으로 핀

bootstrap 6단계 Room 골격에 맞춘다.
```
포함: `gradle/libs.versions.toml`, `app/build.gradle.kts`

**커밋 2 (docs)**
```
docs: technical-decisions에 Room 버전 반영
```
포함: `docs/technical-decisions.md`
