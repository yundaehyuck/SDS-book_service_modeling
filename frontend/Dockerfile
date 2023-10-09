FROM node:14.21.3 as builder

WORKDIR /app
COPY package.json .

RUN npm i
COPY . .
RUN npm run build
# =================== frontend nginx 적용 X ============================
EXPOSE 3000
CMD ["npm", "run", "start"]


# =================== frontend nginx 적용 시 ============================
# FROM nginx:alpine
# COPY --from=builder /app/dist /usr/share/nginx/html
# RUN rm /etc/nginx/conf.d/default.conf
# COPY nginx/nginx.conf /etc/nginx/conf.d

# EXPOSE 3000

# CMD ["nginx", "-g", "daemon off;"]