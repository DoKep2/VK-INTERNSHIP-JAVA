postgres:
	@docker compose --file .docker/docker-compose.yml up -d postgres

test:
	@./mvnw clean test

pre: test

build:
	@./mvnw clean compile

run: build
	@./mvnw spring-boot:run

.PHONY: postgres test pre build run