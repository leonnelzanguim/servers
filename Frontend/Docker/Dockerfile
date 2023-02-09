FROM node:18-alpine as build

RUN mkdir -p /app

WORKDIR /app

COPY package.json /app

RUN npm install

COPY . /app

RUN npm run build

EXPOSE 80

FROM nginx:latest

COPY --from=build /app/dist/probe-aufgabe-front/ /usr/share/nginx/html


