FROM ubuntu:22.04

ENV TZ=Europe/Warsaw


RUN apt-get update && apt-get install -y wget vim git curl

RUN apt-get install -y build-essential unzip

RUN apt-get install -y fp-compiler

WORKDIR /app

COPY prog.pas .
RUN fpc prog.pas
CMD ./prog
