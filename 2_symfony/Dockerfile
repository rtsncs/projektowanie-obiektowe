FROM kprzystalski/projobj-php:latest

COPY . .
RUN composer install

EXPOSE 8000
CMD ["symfony", "server:start"]
