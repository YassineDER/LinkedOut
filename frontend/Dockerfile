# Stage 1: Build the Angular app
FROM node:alpine AS node
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Serve the Angular app
FROM nginx:alpine
COPY --from=node /app/dist/browser /var/www/yassine.onthewifi.com
COPY ./nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
