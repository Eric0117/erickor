# EricKor ElasticSearch korean token filter plugin
엘라스틱서치 한글 관련 토큰 필터 플러그인입니다.

해당 필터를 사용하여 `자동완성`, `초성검색`, `오타교정` 등을 구현할 수 있습니다.

본 프로젝트는 Kotlin, JDK17, Gradle 7.6버전을 사용하여 제작되었습니다.

본 플러그인이 유용하다면, Github Star 부탁드립니다 >.<

## 프로젝트 구조
```bash
├── kotlin
│   ├── constants : 한글,영어의 초,중,종성별 유니코드 변환 맵
│   ├── filters : 플러그인 구현체
│   ├── plugin : 구현한 필터들을 플러그인의 내부 필터로 등록하기 위한 클래스 
│   └── util : 플러그인 내부 로직
└── yamlRestTest
    ├── kotlin
    │     └── FilterTest : Rest test를 위한 테스트 클래스
    └── resources
          └── rest-api-spec
                └── plugin
                      └── filter.yml : Rest test 시나리오 파일 
```

---

## 필터 목록
### erickor_jamo Filter (자모 분리 필터)
한글 문자열의 자음과 모음을 분리하는 필터입니다.

해당 필터와 `Edge-Ngram` or `Ngram` 토큰필터를 함께 사용해 자동완성 기능을 구현할 수 있습니다.

```
안녕하세요 반갑습니다 -> [ㅇㅏㄴㄴㅕㅇㅎㅏㅅㅔㅇㅛ], [ㅂㅏㄴㄱㅏㅂㅅㅡㅂㄴㅣㄷㅏ]
```


### erickor_chosung Filter (초성 분리 필터)
한글 문자열의 초성을 추출하는 필터입니다.

해당 필터를 사용하여 초성검색을 구현할 수 있습니다. 
```
여기어때 -> [ㅇㄱㅇㄸ]
엘라스틱서치 -> [ㅇㄹㅅㅌㅅㅊ]
```


### erickor_hantoeng Filter (한글->영어 변환 필터)
한글 문자열을 자모를 분해한 후 키보드에서 해당 글자와 일치하는 영어 문자열로 변환합니다.

해당 필터를 사용하여 사용자의 영어 오입력에 대해 올바른 결과를 찾을 수 있습니다.
```
안녕하세요 반갑습니다 -> [dkssudgktpdy], [qksrkqtmqslek]
```


### erickor_engtohan Filter (영어->한글 변환 필터)
영어 문자열을 키보드에서 해당 글자와 일치하는 한글 문자열로 변환합니다.

해당 필터를 사용하여 사용자의 한글 오입력에 대해 올바른 결과를 찾을 수 있습니다.
```
dkssudgktpdy qksrkqtmqslek -> [안녕하세요], [반갑습니다]
```

## 필터 등록
작성한 필터를 플러그인에 등록하기 위하여, `/src/main/kotlin/plugin/EricKorPlugin`에서 `Map<String, AnalysisProvider<TokenFilterFactory>>` 구조로 필터를 등록합니다.


## 프로젝트 빌드 및 설치
```bash
gradle clean
gradle assemble
```
assemble 후 `/build/distributions/erickor-{version}.zip` 파일이 생성됩니다.

해당 zip 파일을 엘라스틱서치에서 등록합니다.
```
sudo bin/elasticsearch-plugin install file:///path/directory/filename
```

플러그인을 도커에서 설치하기 위해서는, Dockerfile에서 이미지 빌드시 플러그인을 함께 설치하도록 할 수 있습니다.
```
COPY plugins/erickor-1.0.zip /plugins/erickor-1.0.zip
RUN elasticsearch-plugin install file:///plugins/erickor-1.0.zip
```

설치가 완료되었다면, 엘라스틱서치를 재시작하여 플러그인을 적용할 수 있습니다.

설치된 플러그인 목록을 확인하려면, 아래 API를 호출하여 확인할 수 있습니다.
```
GET /_cat/plugins
```

## Yaml Rest Test
```bash
gradle yamlRestTest
```
작성한 필터의 테스트를 위해서는, yamlRestTest 기능을 이용하면 됩니다.
해당 기능이 실행되면 elasticsearch test cluster가 실행되고, 프로젝트의 플러그인을 불러온 뒤, Yaml 구조에 따라 테스트를 실행합니다.

`/src/main/yamlRestTest/kotlin/FilterTest`에서 testCandidate를 등록하고,

`/src/main/yamlRestTest/resources/rest-api-spec/test/plugin/filter.yml`에 작성한 내용대로 필터를 test하게 됩니다.

참고문서

[공식문서](https://www.elastic.co/guide/en/elasticsearch/plugins/current/example-text-analysis-plugin.html)

[공식문서 github](https://github.com/elastic/elasticsearch/blob/main/plugins/examples/stable-analysis/src/yamlRestTest/resources/rest-api-spec/test/analysis/20_char_filter.yml)
