FROM nginx

ENV LAMBDA_URL="https://kj1ox23jfl.execute-api.eu-west-1.amazonaws.com/prod/"

COPY twitter-profile-viewer/build /usr/share/nginx/html
COPY injectLambdaUrl.sh injectLambdaUrl.sh
RUN chmod 777 -R injectLambdaUrl.sh

ENTRYPOINT ["./injectLambdaUrl.sh", "/usr/share/nginx/html/index.html"]
CMD ["nginx", "-g", "daemon off;"]
