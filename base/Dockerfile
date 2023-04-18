FROM python:3.9

COPY ./requirements.txt app/requirements.txt

# instead of mounting volumes, these folders exist in base image
# so no need to dump everything in /tmp
# or, can create within temp without doing anything
# usingin /tmp for everything:
# can also decide tmp/data/*.csv

# RUN mkdir /data

# RUN mkdir /metrics

# RUN mkdir /models

WORKDIR /app

RUN pip install -r requirements.txt