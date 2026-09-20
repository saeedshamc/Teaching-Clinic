FROM alpine:3.20
ARG APP_TITLE=Clinic
ENV GREETING=hello
RUN echo "$APP_TITLE" > /title.txt
CMD ["sh", "-c", "echo title=$(cat /title.txt); echo greeting=$GREETING"]
