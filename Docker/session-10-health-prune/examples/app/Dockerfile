FROM nginx:alpine
RUN apk add --no-cache curl
COPY index.html /usr/share/nginx/html/index.html
HEALTHCHECK --interval=5s --timeout=3s --retries=3 \
  CMD curl -fsS http://127.0.0.1/ || exit 1
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
