postgres:
	@docker compose --file .docker/docker-compose.yml up -d postgres

migrate:
	@./mvnw flyway:migrate

test:
	@./mvnw clean test

pre: test

build:
	@./mvnw clean compile

run: postgres migrate build
	@./mvnw spring-boot:run

.PHONY: postgres test pre build run