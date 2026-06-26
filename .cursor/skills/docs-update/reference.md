# docs-update — 라우팅 상세

## 문서 역할 (index.md와 동일 원칙)

```
product-spec.md        → WHAT/WHY, MVP, §15 확정, §16 로드맵
implementation-design.md → UI/UX §3, 권한/FGS §2, 개발 가이드 §1
technical-decisions.md → Gradle, 라이브러리, 버전, KSP/Hilt 핀
project-bootstrap-plan.md → 구현 단계 체크리스트, 검증 조건
index.md               → 문서 지도, SSOT 안내
```

## 확정 사항 변경 시 연쇄 검토

| 변경 | 주 수정 | 연쇄 검토 (링크·일관성만) |
|------|---------|---------------------------|
| MVP 범위 (§9·§15) | product-spec | implementation-design §3 IA |
| 탭 IA | implementation-design §3.1 | product-spec §15에 UI 상세 복사 금지 |
| Room/Hilt 버전 | technical-decisions | product-spec §15는 "Room 사용" 수준만 |
| 단계 N 완료 | bootstrap-plan §N | 코드·설계 문서는 별 요청 시만 |

## §15 vs §16 판별

| §15 확정 사항 | §16 향후 계획 |
|---------------|---------------|
| 지금 빌드·설계에 반영하는 결정 | 아이디어·미정·MVP 이후 |
| MVP 포함 여부 확정 | 커뮤니티·리워드 등 후순위 |
| 아키텍처·정책 고정 (B안 지도 등) | "검토 가능" 항목 |

## technical-decisions vs product-spec §15

- **technical-decisions:** 버전 번호, `libs.versions.toml` 대응, KSP/kapt, 변경된 파일 목록
- **product-spec §15:** 제품·아키텍처 결정 요약; 버전은 `technical-decisions.md` 링크로 위임

## bootstrap-plan 갱신 규칙

- 해당 단계 체크박스 `[ ]` → `[x]`
- 단계에 **검증** 절이 있으면 완료 보고에 검증 방법 포함
- "예상 변경 지점"은 실제 변경과 다를 때만 갱신 (과도한 동기화 금지)

## index.md 수정이 필요한 경우

- `docs/` 아래 **새 마크다운 문서** 추가
- 문서 역할·빠른 링크 테이블 구조 변경
- SSOT 라우팅 규칙 자체가 바뀔 때

일반적인 §15/§3/technical-decisions 수정만으로는 index.md를 건드리지 않는다.
