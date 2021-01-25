### Misolab Web Multi module Template


### `.gitignore`와 `.mvnw` 사용
- maven archetype 오류로 인해서 `.gitignore` 생성불가
  - `mv __gitignore__ .gitignore && chmod +x mvnw`

### admin/front
- `front` 모듈을 제거하고 다른 admin template로 교체한다.
  - `rm -rf admin/front && git clone https://github.com/misolab/vue-admin-template.git admin/front`
- 빌드에 번들링 파일을 static 파일을 옮기려면 `-Prelease`
  - `mvn clean install -Prelease -DskipTests`
  - `mvn spring-boot:run -pl admin`