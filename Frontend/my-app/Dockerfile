# =========================
# Stage 1: Build Angular
# =========================
FROM node:20-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm ci

COPY . .

RUN npm run build


# =========================
# Stage 2: Run Angular
# =========================
FROM node:20-alpine

WORKDIR /app

COPY --from=build /app/package*.json ./

RUN npm ci --omit=dev

COPY --from=build /app/dist/my-app ./dist/my-app

EXPOSE 4200

CMD ["npx", "serve", "-s", "dist/my-app", "-l", "4200"]