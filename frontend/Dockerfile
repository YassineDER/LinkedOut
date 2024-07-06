FROM node:current-alpine3.20
LABEL authors="Yassine DERGAOUI"
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install -g @angular/cli
RUN npm install
COPY . .
RUN npm run build
EXPOSE 4200
CMD ["ng", "serve", "--host", "0.0.0.0", "--configuration", "production"]
