.PHONY: help _clean-cypress clean install setup up build down reset dev e2e e2e-clean
.DEFAULT_GOAL := help

help:
	@echo "Setup & Dependencies:"
	@echo "  make install           Install npm dependencies"
	@echo "  make setup             clean + install"
	@echo ""
	@echo "Development:"
	@echo "  make dev               up + frontend dev server"
	@echo ""
	@echo "Docker:"
	@echo "  make build             Build db + backend docker images"
	@echo "  make up                Start db + backend containers"
	@echo "  make down              Stop db + backend containers"
	@echo "  make reset             Stop containers and remove volumes"
	@echo ""
	@echo "Testing:"
	@echo "  make e2e               up + run E2E tests"
	@echo "  make e2e-clean         reset + rebuild + run E2E tests"
	@echo ""
	@echo "Cleanup:"
	@echo "  make clean             _clean-cypress + remove node_modules and dist"
	@echo "  make _clean-cypress    Remove Cypress screenshots and videos"
	@echo ""

_clean-cypress:
	rm -rf cypress/screenshots cypress/videos

clean: _clean-cypress
	rm -rf node_modules dist

install:
	npm ci

setup: clean install

up:
	docker compose -f ../compose.test.yaml up db backend -d --wait

build:
	docker compose -f ../compose.test.yaml build db backend --quiet

down:
	docker compose -f ../compose.test.yaml down

reset:
	docker compose -f ../compose.test.yaml down -v

dev: up
	npm run dev

e2e: up
	npm run test:e2e

e2e-clean: reset build up install
	npm run test:e2e
