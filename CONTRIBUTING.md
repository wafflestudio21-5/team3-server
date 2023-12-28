# Contributing Guide

이 문서는 본 프로젝트에 기여하는 방법에 대한 가이드라인을 제공합니다. 아래의 규칙을 따르는 것이 중요합니다.

## Git Convention

### Branches

#### 종류:

- `main`: 제품 출시를 위한 주 브랜치입니다.
- `dev/{변경사항}`: 프로젝트 전반적인 굵직한 변경을 위한 브랜치입니다.
- `feat/{기능명}`: 새로운 기능을 개발하는 브랜치입니다.
- `refactor/{기능명}`: 기존에 개발된 기능을 리팩터링하는 브랜치입니다.
- `hotfix`: 출시된 버전에서 발생한 버그를 수정하는 브랜치입니다.

#### 예시:

- `feat/login`
- `feat/register`

## Commit

본 프로젝트에서는 `<Type>`을 제외한 모든 커밋 내용에 한글 사용을 허용합니다.
main과 dev를 제외한 브랜치에서는 되도록이면 Type을 명시해주시길 바랍니다

### 커밋 형식

#### 제목
변경 사항에 대한 간결한 설명을 포함해야 합니다.

##### 허용하는 타입 <type>:
- feat: 새로운 기능 (feature).
- fix: 버그 수정.
- docs: 문서 (documentation).
- style: 코드 포매팅, 세미콜론 누락 등.
- refactor: 코드 리팩터링.
- test: 누락된 테스트 추가.
- chore: 빌드 태스크, 패키지 매니저 설정 등.

##### 내용 <subject>:
- 명령조, 현재 시제를 사용합니다.
- 끝에 마침표를 사용하지 않습니다.

## Issue Convention

- 담당자(Assignees)를 명시해야 합니다.

## PR (Pull Request) Title

- 변경 사항 (ex: 초기 프로젝트 세팅, 소셜 로그인 구현)
